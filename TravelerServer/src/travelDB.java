import java.sql.*;

import javax.sql.PooledConnection;

import oracle.jdbc.pool.OracleConnectionPoolDataSource;

public class travelDB {
	public static PooledConnection pool;
	OracleConnectionPoolDataSource dbpool;
	Connection con; //con = DriverManager.getConnection(url, user, pw);
	Statement stmt;
	ResultSet rs;
	
	public travelDB() {
		try {
			dbpool = new OracleConnectionPoolDataSource();
			dbpool.setURL("jdbc:oracle:thin:@localhost:1521"); //URL -> 오라클 데이터베이스에서 활용하는 부분 가르킴
			dbpool.setUser("root2"); // 오라클 데이터베이스의 사용자 아이디
			dbpool.setPassword("pass"); // 오라클 데이터베이스의 사용자 비밀번호
			pool = dbpool.getPooledConnection();
		}catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void connectDatabase() {
		try {
			System.out.println("데이터베이스 연결 준비..");
			con = pool.getConnection();
			System.out.println("데이터베이스 연결 성공.");
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
}
