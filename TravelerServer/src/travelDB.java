import java.sql.*;

public class travelDB {
	Connection con; //con = DriverManager.getConnection(url, user, pw);
	Statement stmt;
	ResultSet rs;
	String url; //URL -> 오라클 데이터베이스에서 활용하는 부분 가르킴
	String user; // 오라클 데이터베이스의 사용자 아이디
	String pw; // 오라클 데이터베이스의 사용자 비밀번호
	
	public travelDB() {
		url = "jdbc:oracle:thin:@localhost:1521";
		user = "root2";
		pw = "pass";
	}
	
	public void connectDatabase() {
		try {
			System.out.println("데이터베이스 연결 준비..");
			con = DriverManager.getConnection(url, user, pw);
			System.out.println("데이터베이스 연결 성공.");
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
}
