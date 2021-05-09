import java.sql.*;
import java.util.ArrayList;
import java.util.List;
public class Infrow {
	
	
	// 유입량 저장
	public void infrowSave (long intime, long waitTime, long amount) {
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = "INSERT INTO INFROW(INFROW_DATE, WAITING_TIME, INFROW_AMOUNT) VALUES (?, ?, ?)";
		try {
			con = travelDB.pool.getConnection(); // 연결 정보 빌려오기
			System.out.println("풀 빌려오기");
			try {
	            pstmt = con.prepareStatement(sql); // SQL 해석
	            pstmt.setLong(1, intime); // 유입 날짜
	            pstmt.setLong(2, waitTime); //대기시간 (최소 10분)
	            pstmt.setLong(3, amount); // 유입량
	 
	            if (pstmt.executeUpdate() == 1) {
	                System.out.println("유입량 저장 성공");
	            } else {
	                System.out.println("유입량 저장 실패");
	            }
	 
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }finally {
				pstmt.close();
				con.close();
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	// 최근 유입량 조회
	public long lookInfrowGet () {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT WAITING_TIME, INFROW_AMOUNT " + 
				"FROM (SELECT WAITING_TIME, INFROW_AMOUNT " + 
				"FROM INFROW ORDER BY INFROW_DATE) WHERE ROWNUM <= 3";
		long result = 0;
		int set = 0;
		try {
			con = travelDB.pool.getConnection(); // 연결 정보 빌려오기
			System.out.println("풀 빌려오기");
			try {
	            pstmt = con.prepareStatement(sql); // SQL 해석
	            rs = pstmt.executeQuery();
	            
	            while(rs.next()) {
	            	result = result + rs.getInt(2)/rs.getInt(1);
	            	set = set+1;
	            }
	            try {
	            	result = result/set;
	            }catch(ArithmeticException e) {
	            	result = 0;
	            }
	 
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }finally {
	        	rs.close();
				pstmt.close();
				con.close();
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	// 최근 로그번호 조회 (이전 로그번호가 됨)
	public long lastLogNum () {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT INFROW_DATE " + 
				"FROM (SELECT INFROW_DATE FROM INFROW " + 
				"ORDER BY INFROW_DATE DESC) WHERE ROWNUM = 1";
		long result = 0;
		try {
			con = travelDB.pool.getConnection(); // 연결 정보 빌려오기
			System.out.println("풀 빌려오기");
			try {
	            pstmt = con.prepareStatement(sql); // SQL 해석
	            rs = pstmt.executeQuery();
	            
	            while(rs.next()) {
	            	result = rs.getLong(1);
	            }
	            
	 
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }finally {
	        	rs.close();
				pstmt.close();
				con.close();
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	
}
