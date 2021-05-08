
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
	
	SimpleDateFormat sample = new SimpleDateFormat("YYYY.MM.DD HH:mm:ss");
	
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
						
						att = nowDate.getTime() - preDate.getTime();
						att = logtime/10000;
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
						att = logtime/10000;
						if(att > 30) {
							att = 30;
						}
					}catch (ParseException e) { }
					
					System.out.println(ph + " " + logtime + " " + att + " " + tourName);
					
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
					
					tourNN = tour.selectTour(tourName);
					if (tourNN == null) {
						tour.saveTour(tourName, tourLoc); // 관광지 목록에 등록
						// 목록 없음 표시
					}else {
						reView.selectReviewIndex(tourName);
						if(reView.reviewLists == null) { //리스트가 null인 경우, 관광지에 리뷰 목록이 없는 경우 처리
							//목록 없음 표시
						}else { // 리뷰 목록이 있으면 여기서 처리
							
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
					
					break;
					
				case "REVIEWGOOD" : // 리뷰 좋아요
					
					break;
					
				case "REVIEWBAD" : // 리뷰 싫어요
					
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
