
import java.io.*;
import java.net.*;
import java.util.StringTokenizer;

public class playTravel extends Thread{
	private DataInputStream in;
	private DataOutputStream out;
	StringTokenizer st;
	Socket sock;
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
				in = new DataInputStream(sock.getInputStream()); // 해당 클라이언트에서 받기
				allmsg = in.readUTF(); // 신호 및 매세지 받기
				System.out.println(allmsg);
				st = new StringTokenizer(allmsg, "/"); // 신호 자르기
				sign =  st.nextToken();
				nextMsg =  st.nextToken();
				
				switch (sign) {
				case "STARTCHART" : // 시작 메시지 - 인기 및 리뷰 차트 전달
					
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
			
		}
		
	}
}
