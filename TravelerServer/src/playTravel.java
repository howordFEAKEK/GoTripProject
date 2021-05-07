
import java.io.*;
import java.net.*;
import java.util.StringTokenizer;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.sql.PooledConnection;

public class playTravel extends Thread{
	private DataInputStream in;
	private DataOutputStream out;
	Connection con;
	Statement stmt;
	ResultSet rs;
	StringTokenizer st;
	Socket sock;
	
	SimpleDateFormat format = new SimpleDateFormat("YYYY.MM.DD HH24:MI:SS");
	
	public playTravel(Socket socket) {
		sock = socket;
	}
	
	public void run() {
		String allmsg;
		String sign;
		String nextMsg;
		try {
			out = new DataOutputStream(sock.getOutputStream());
			while(true) {
				System.out.println("접속 확인");
				in = new DataInputStream(sock.getInputStream()); // 해당 클라이언트에서 받기
				allmsg = in.readUTF(); // 신호 및 매세지 받기
				System.out.println(allmsg);
				st = new StringTokenizer(allmsg, "/"); // 신호 자르기
				sign =  st.nextToken();
				nextMsg =  st.nextToken();
				
				switch (sign) {
				case "STARTCHART" : // 시작 메시지 - 인기 및 리뷰 차트 전달
					
					break;
					
				case "TOURLOG" : // 관광지 조회 로그 저장
					String ph = null;
					String logpre = null;
					String lognow = null;
					int att = 0;
					String tourName = null;
					long logtime = 0;
					
					Tourism tour = new Tourism();
					st = new StringTokenizer(nextMsg, "$"); // 신호 자르기
					ph = st.nextToken();
					logpre = st.nextToken();
					lognow = st.nextToken();
					att = Integer.parseInt(st.nextToken());
					tourName = st.nextToken();
					
					try {
						Date date1 = (Date) format.parse(logpre);
						Date date2 = (Date) format.parse(lognow);
						
						logtime = date2.getTime() - date1.getTime();
						logtime = logtime/1000;
					}catch (ParseException e) { }
					
					tour.saveTourLog(ph, logtime, att, tourName);
					break;
					
				case "REVLOG" : // 리뷰 조회 로그 저장
					
					break;
					
				case "POPCHART" : // 인기 차트 갱신 - 요청시
					
					break;
					
				case "REVIEWCHART" : // 리뷰 차트 갱신 - 지역 변경 시, 요청시
					
					break;
					
				case "REVIEWSAVE" : // 리뷰 작성 - 리뷰 정보 저장
					
					break;
					
				case "REVIEWINDEX" : // 리뷰 목록 - 관광지 관련 리뷰 목록
					
					break;
					
				case "REVIEWCALL" : // 리뷰 조회
					
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
