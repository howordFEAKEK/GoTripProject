
import java.io.*;
import java.net.*;
import java.util.StringTokenizer;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.sql.PooledConnection;

public class playTravel extends Thread{
	private DataInputStream in;
	private DataOutputStream out;
	Connection con;
	Statement stmt;
	ResultSet rs;
	StringTokenizer st;
	Socket sock;
	
	SimpleDateFormat sample = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");
	
	public playTravel(Socket socket) {
		sock = socket;
	}
	
	public void run() {
		String allmsg;
		String sign;
		String nextMsg;
		try {
			Tourism tour = new Tourism();
			ReView reView = new ReView();
			out = new DataOutputStream(sock.getOutputStream());
			while(true) {
				System.out.println("접속 확인");
				in = new DataInputStream(sock.getInputStream()); // 해당 클라이언트에서 받기
				allmsg = in.readUTF(); // 신호 및 매세지 받기
				System.out.println(allmsg);
				st = new StringTokenizer(allmsg, "/"); // 신호 자르기
				sign =  st.nextToken(); // 신호
				nextMsg =  st.nextToken(); // 나머지 메시지
				
				String ph = null; // 제품번호 or 전화번호
				String logpre = null; // 조회시간
				String lognow = null; // 마감시간
				String tourName = null; //관광지명
				String tourLoc = null; // 관광지 위치 정보 (00도 00시 ...)
				long att = 0; // 관심점수
				long logtime = 0; //로그번호 (마감시간을 숫자 시간으로)
				
				String writer = null; // 작성자
				String writeDate = null; //작성일자
				long writeday = 0; // 작성일자 숫자 버전
				String title = null; // 리뷰 제목
				String context = null; // 리뷰 내용
				
				Date preDate = null; // 조회시간 숫자 변환용
				Date nowDate = null; // 마감시간 숫자 변환용
				Date wrDate = null; // 작성일자 숫자 변환용
				
				switch (sign) {
				case "STARTCHART" : // 시작 메시지 - 인기 및 리뷰 차트 전달
					
					break;
					
				case "POPCHART" : // 인기 차트 갱신 - 요청시
					
					break;
					
				case "REVIEWCHART" : // 리뷰 차트 갱신 - 지역 변경 시, 요청시
					
					break;
					
				case "TOURLOG" : // 관광지 조회 로그 저장
					st = new StringTokenizer(nextMsg, "$"); // 신호 자르기
					ph = st.nextToken();
					logpre = st.nextToken();
					lognow = st.nextToken();
					tourName = st.nextToken();
					
					try {
						preDate = sample.parse(logpre);
						nowDate = sample.parse(lognow);
						
						logtime = nowDate.getTime()/1000;
						
						att = nowDate.getTime() - preDate.getTime(); // 마감시간 - 조회시간
						
						att = att/10000;
						att = att + 1; // 최소치 보정
						if(att > 30) {
							att = 30;
						}
					}catch (ParseException e) { }
					
					System.out.println(ph + " " + logtime + " " + att + " " + tourName);
					
					tour.saveTourLog(ph, logtime, att, tourName);
					break;
					
				case "REVLOG" : // 리뷰 조회 로그 저장
					st = new StringTokenizer(nextMsg, "$"); // 신호 자르기
					ph = st.nextToken();
					logpre = st.nextToken();
					lognow = st.nextToken();
					writer = st.nextToken();
					writeDate = st.nextToken();
					tourName = st.nextToken();
					
					try {
						preDate = sample.parse(logpre);
						nowDate = sample.parse(lognow);
						wrDate = sample.parse(writeDate);
						
						logtime = nowDate.getTime()/1000;
						writeday = wrDate.getTime()/1000;
						
						att = nowDate.getTime() - preDate.getTime();
						att = att/10000;
						if(att > 30) {
							att = 30;
						}
					}catch (ParseException e) { }
					
					tour.saveTourLog(ph, logtime, att, tourName); // 관광지 로그 저장
					reView.saveReviewLog(ph, logtime, att, writer, writeday); // 리뷰 로그 저장
					break;
					
				case "REVIEWSAVE" : // 리뷰 작성 - 리뷰 정보 저장
					st = new StringTokenizer(nextMsg, "$"); // 신호 자르기
					ph = st.nextToken();
					writeDate = st.nextToken();
					title = st.nextToken();
					context = st.nextToken();
					tourName = st.nextToken();
					
					try {
						wrDate = sample.parse(writeDate);
						writeday = wrDate.getTime()/1000;
					}catch (ParseException e) { }
					
					reView.reviewSave(ph, writeday, title, context, tourName);
					break;
					
				case "REVIEWINDEX" : // 리뷰 목록 - 관광지 관련 리뷰 목록
					String tourNN = null; // 관광지가 있는지 확인
					st = new StringTokenizer(nextMsg, "$"); // 신호 자르기
					tourName = st.nextToken();
					tourLoc = st.nextToken();
					
					System.out.println(tourName + " " + tourLoc);
					
					tourNN = tour.selectTour(tourName); // 관광지 목록에서 관광지명 검색
					
					if (tourNN == null) { //관광지가 없는 경우
						
						tour.saveTour(tourName, tourLoc); // 관광지 목록에 등록
						tour.saveTU(tourName, tourLoc); // 조회용 목록에 등록 (뷰용 테이블)
						
						// 목록 없음 표시
						String noMsg1 = null;
						noMsg1 = "NOLIST/nolist"; // ---> 1번째 NOLIST
						
						try {
							out.writeUTF(noMsg1); // 리스트 없음 메시지 보내기
						}catch(IOException e) {}
						
						
					}else { // 관광지가 있는 경우
						
						// 관광지에 대한 목록 조회
						reView.selectReviewIndex(tourName);
						
						// 리스트가 비어있는지 확인
						if(reView.reviewLists.isEmpty()) {
							
							//목록 없음 표시
							String noMsg2 = null; // ---> 2번째 NOLIST
							noMsg2 = "NOLIST/nolist";
							
							try {
								out.writeUTF(noMsg2); // 리스트 없음 메시지 보내기
							}catch(IOException e) {}
							
							
						}else { // 리뷰 목록이 있으면 여기서 처리
							String sendMsg = "REVIEWINDEX/";
							
							System.out.println(reView.reviewLists.size());
							
							// 메시지 작성하기
							for(int i = 0 ; i < reView.reviewLists.size() ; i ++) {
								sendMsg = sendMsg + reView.reviewLists.get(i).writer + "$"
										+ reView.reviewLists.get(i).date + "$" + reView.reviewLists.get(i).title + "$";
							}
							
							// 마지막에 붙은 $ 처리용...
							sendMsg = sendMsg + "last";
							
							try {
								out.writeUTF(sendMsg);
							}catch(IOException e) {}
							
						}
					}
					
					break;
					
				case "REVIEWCALL" : // 리뷰 조회
					String sendMsg = null;
					st = new StringTokenizer(nextMsg, "$"); // 신호 자르기
					writer = st.nextToken();
					writeDate = st.nextToken();
					try {
						wrDate = sample.parse(writeDate);
						writeday = wrDate.getTime()/1000;
					}catch (ParseException e) { }
					
					sendMsg = reView.selectReview(writer, writeday); //보낼 리뷰 정보 가져오기
					
					// 메시지 보내기 부분
					try {
						out.writeUTF(sendMsg); // 리뷰에 대한 정보 보내기
					}catch(IOException e) {}
					break;
					
				case "REVIEWGOOD" : // 리뷰 좋아요
					st = new StringTokenizer(nextMsg, "$"); // 신호 자르기
					writer = st.nextToken(); // 작성자
					writeDate = st.nextToken(); //작성일자
					try {
						wrDate = sample.parse(writeDate); // 작성일자
						writeday = wrDate.getTime()/1000; // 작성일자 숫자 변환
					}catch (ParseException e) { }
					reView.revLike(writer, writeday);
					break;
					
				case "REVIEWBAD" : // 리뷰 싫어요
					st = new StringTokenizer(nextMsg, "$"); // 신호 자르기
					writer = st.nextToken(); // 작성자
					writeDate = st.nextToken(); //작성일자
					try {
						wrDate = sample.parse(writeDate); // 작성일자
						writeday = wrDate.getTime()/1000; // 작성일자 숫자 변환
					}catch (ParseException e) { }
					reView.rewBad(writer, writeday);
					break;

				default:
					break;
				}
			}
		}catch(IOException e) {
			e.printStackTrace();
		}
		
	}
}
