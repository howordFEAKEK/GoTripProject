
import java.io.*;
import java.net.*;
import java.util.StringTokenizer;

public class playTravel extends Thread{
	private DataInputStream in;
	private DataOutputStream out;
	public String nickName;
	StringTokenizer st;
	Socket sock;
	public playTravel(Socket socket) {
		sock = socket;
	}
	
	public void run() {
		//참고로 반복문 안 걸어둬서 이것만 출력하고 끝 : 말 그대로 서버 접속 되는가? 정도만 확인
		System.out.println("사용자가 서버에 접속했습니다.");
	}
}
