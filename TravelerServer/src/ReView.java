import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ReView {
	public List<ReviewList> reviewLists;
	
	
	//리뷰 저장
	public void reviewSave (String ph, long writeTime, String title, String text, String tourName) {
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = "INSERT INTO REVIEW(WRITER, WRITE_DATE, TITLE, CONTENT, TOUR_NAME) " + 
				"VALUES (?, ?, ?, ?, ?)";
		try {
			con = travelDB.pool.getConnection(); // 연결 정보 빌려오기
			System.out.println("풀 빌려오기");
			try {
	            pstmt = con.prepareStatement(sql); // SQL 해석
	            pstmt.setString(1, ph);
	            pstmt.setLong(2, writeTime);
	            pstmt.setString(3, title);
	            pstmt.setString(4, text);
	            pstmt.setString(5, tourName);
	 
	            if (pstmt.executeUpdate() == 1) {
	                System.out.println("리뷰 저장 성공");
	            } else {
	                System.out.println("리뷰 저장 실패");
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
	
	//리뷰 로그 저장
	public void saveReviewLog (String ph, long logtime, long att, String writer, long writeTime) {
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = "INSERT INTO REVIEW_LOG(READER, REVIEW_LOG_NO, REVIEW_ATT_POINT, WRITER, WRITE_DATE)\r\n" + 
				"VALUES (?, ?, TRUNC(?, 0), ?, ?)";
		try {
			con = travelDB.pool.getConnection(); // 연결 정보 빌려오기
			System.out.println("풀 빌려오기");
			try {
	            pstmt = con.prepareStatement(sql); // SQL 해석
	            pstmt.setString(1, ph);
	            pstmt.setLong(2, logtime);
	            pstmt.setLong(3, att);
	            pstmt.setString(4, writer);
	            pstmt.setLong(5, writeTime);
	 
	            if (pstmt.executeUpdate() == 1) {
	                System.out.println("리뷰 저장 성공");
	            } else {
	                System.out.println("리뷰 저장 실패");
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
	
	//리뷰 목록 조회
	public void selectReviewIndex (String tourName) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT WRITER, WRITE_DATE, TITLE FROM REVIEW " + 
				"WHERE TOUR_NAME = '?' ORDER BY WEEKLY_SCORE DESC";
		ReviewList result = null;
		try {
			con = travelDB.pool.getConnection(); // 연결 정보 빌려오기
			System.out.println("풀 빌려오기");
			try {
	            pstmt = con.prepareStatement(sql); // SQL 해석
	            pstmt.setString(1, tourName);
	            rs = pstmt.executeQuery();
	            
	            while(rs.next()) {
	            	result = new ReviewList();
	            	result.writer = rs.getString(1);
	            	result.date = rs.getLong(2);
	            	result.title = rs.getString(3);
	            	
	            	reviewLists.add(result);
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
	}
	
	//리뷰 조회
	public String selectReview (String writer, long wirteDate) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT WRITER, WRITE_DATE, TITLE, CONTENT, GOOD_POINT, BAD_POINT, TOUR_NAME " + 
				"FROM REVIEW WHERE WRITER = '?' AND WRITE_DATE = ?;";
		String result = null;
		try {
			con = travelDB.pool.getConnection(); // 연결 정보 빌려오기
			System.out.println("풀 빌려오기");
			try {
	            pstmt = con.prepareStatement(sql); // SQL 해석
	            pstmt.setString(1, writer);
	            pstmt.setLong(2, wirteDate);
	            rs = pstmt.executeQuery();
	            
	            result = "REVIEWCALL/" + rs.getString(1) +"$"+ rs.getLong(2) +"$"+ rs.getString(3) +"$"+
	            rs.getString(4) +"$"+ rs.getLong(5) +"$"+ rs.getLong(6) +"$"+ rs.getString(7);
	 
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

// 리뷰 목록 클래스
class ReviewList {
	public String writer = null; // 작성자
	public long date = 0; //작성일자
	public String title = null; //리뷰 제목
}

