import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class ReView {
	public List<ReviewList> reviewLists = new ArrayList<ReviewList>();
	public List<RevChange> chRevs = new ArrayList<RevChange>();
	public List<ReviewCH> revChart = new ArrayList<>(); // 리뷰 차트 보관용	
	
	SimpleDateFormat sample = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss"); // 날짜 포멧용
	
	//리뷰 저장 (통과 O)
	public void reviewSave (String ph, long writeTime, String title, String text, String tourName, String loaction) {
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = "INSERT INTO REVIEW(WRITER, WRITE_DATE, TITLE, CONTENT, TOUR_NAME, LOCATION_DATA) " + 
				"VALUES (?, ?, ?, ?, ?, ?)";
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
	            pstmt.setString(6, loaction);
	 
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
	
	//리뷰 로그 저장 (통과 O)
	public void saveReviewLog (String ph, long logtime, long att, String writer, long writeTime) {
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = "INSERT INTO REVIEW_LOG(READER, REVIEW_LOG_NO, REVIEW_ATT_POINT, WRITER, WRITE_DATE) " + 
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
	
	//리뷰 목록 조회 (통과 O)
	public void selectReviewIndex (String tourName) {
		reviewLists.clear();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT WRITER, WRITE_DATE, TITLE FROM REVIEW " + 
				"WHERE TOUR_NAME = ? ORDER BY WEEKLY_SCORE DESC";
		ReviewList result = null;
		try {
			con = travelDB.pool.getConnection(); // 연결 정보 빌려오기
			System.out.println("풀 빌려오기");
			try {
	            pstmt = con.prepareStatement(sql); // SQL 해석
	            pstmt.setString(1, tourName);
	            rs = pstmt.executeQuery();
	            
	            while(rs.next()) {
	            	
	            	long wrdate = rs.getLong(2);
	            	String wrdatStr = null;
	            	java.util.Date wda = null;
	            	
	            	wrdate = wrdate * 1000;
	            	wda = new java.util.Date(wrdate);
	            	wrdatStr = sample.format(wda);
	            	
	            	result = new ReviewList();
	            	result.writer = rs.getString(1);
	            	result.date = wrdatStr;
	            	result.title = rs.getString(3);
	            	
	            	reviewLists.add(result);
	            }
	 
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }finally {
	        	
	        	if (rs != null) {
	        		rs.close();
				}
				pstmt.close();
				con.close();
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	//리뷰 조회 (통과 O)
	public String selectReview (String writer, long writeDate) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT WRITER, WRITE_DATE, TITLE, CONTENT, GOOD_POINT, BAD_POINT, TOUR_NAME " + 
				"FROM REVIEW WHERE WRITER = ? AND WRITE_DATE = ?";
		String result = null;
		try {
			con = travelDB.pool.getConnection(); // 연결 정보 빌려오기
			System.out.println("풀 빌려오기");
			try {
	            pstmt = con.prepareStatement(sql); // SQL 해석
	            pstmt.setString(1, writer);
	            pstmt.setLong(2, writeDate);
	            rs = pstmt.executeQuery();
	            
	            if(rs.next()) {
	            	System.out.println("리뷰 조회하기");
	            	
	            	long wrdate = rs.getLong(2);
	            	String wrdatStr = null;
	            	java.util.Date wda = null;
	            	
	            	wrdate = wrdate * 1000;
	            	wda = new java.util.Date(wrdate);
	            	wrdatStr = sample.format(wda);
	            	
	            	
	            	result = "REVIEWCALL/" + rs.getString(1) +"$"+ wrdatStr +"$"+ rs.getString(3) +"$"+
	        	            rs.getString(4) +"$"+ rs.getLong(5) +"$"+ rs.getLong(6) +"$"+ rs.getString(7);
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
	
	//리뷰 좋아요 (통과 O)
	public void revLike (String writer, long writeDate) {
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = "UPDATE REVIEW SET  GOOD_POINT = GOOD_POINT + 1 WHERE WRITER = ? AND WRITE_DATE = ?";
		try {
			con = travelDB.pool.getConnection(); // 연결 정보 빌려오기
			System.out.println("풀 빌려오기");
			try {
	            pstmt = con.prepareStatement(sql); // SQL 해석
	            pstmt.setString(1, writer);
	            pstmt.setLong(2, writeDate);
	 
	            if (pstmt.executeUpdate() == 1) {
	                System.out.println("좋아요 성공");
	            } else {
	                System.out.println("좋아요 실패");
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
	
	//리뷰 싫어요 (통과 O)
	public void rewBad (String writer, long writeDate) {
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = "UPDATE REVIEW SET  BAD_POINT = BAD_POINT + 1 WHERE WRITER = ? AND WRITE_DATE = ?";
		try {
			con = travelDB.pool.getConnection(); // 연결 정보 빌려오기
			System.out.println("풀 빌려오기");
			try {
	            pstmt = con.prepareStatement(sql); // SQL 해석
	            pstmt.setString(1, writer);
	            pstmt.setLong(2, writeDate);
	 
	            if (pstmt.executeUpdate() == 1) {
	                System.out.println("싫어요 성공");
	            } else {
	                System.out.println("싫어요 실패");
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
	
	
	//------------------------ 리뷰 차트 알고리즘 부분 ---------------------------------//
	// 변동 리뷰 확인 (통과 O)
	public void changeReview(long prevtime, long nowtime) {
		chRevs.clear();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT DISTINCT WRITER, WRITE_DATE FROM (SELECT WRITER, WRITE_DATE " + 
				"FROM REVIEW_LOG WHERE REVIEW_LOG_NO BETWEEN ? AND ?)";
		
		try {
			con = travelDB.pool.getConnection(); // 연결 정보 빌려오기
			System.out.println("풀 빌려오기");
			try {
	            pstmt = con.prepareStatement(sql); // SQL 해석
	            pstmt.setLong(1, prevtime);
	            pstmt.setLong(2, nowtime);
	            rs = pstmt.executeQuery();
	            while(rs.next()) {
	            	RevChange rev = new RevChange();
	            	rev.writer = rs.getString(1);
	            	rev.date = rs.getLong(2);
	            	chRevs.add(rev);
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
	
	// 좋아요, 싫어요 확인 (통과 O)
	public double getLikePoint(String name, long wrdate) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT GOOD_POINT, BAD_POINT FROM REVIEW WHERE WRITER = ? AND WRITE_DATE = ?";
		double result = 0;
		double like = 0;
		double dislike = 0;
		try {
			con = travelDB.pool.getConnection(); // 연결 정보 빌려오기
			System.out.println("풀 빌려오기");
			System.out.println(name);
			System.out.println(wrdate);
			try {
	            pstmt = con.prepareStatement(sql); // SQL 해석
	            pstmt.setString(1, name);
	            pstmt.setLong(2, wrdate);
	            rs = pstmt.executeQuery();
	            
	            if (rs.next()) {
					like = rs.getLong(1);
					dislike = rs.getLong(2);
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
		
		
		if (like == 0 && dislike == 0) {return 1;} // 좋아요, 싫어요... 0인 경우
		if (like == 0) {return 0.1;} // 좋아요... 0인 경우
		if (dislike == 0) {return 10;} // 싫어요... 0인 경우
		if (like/dislike < 0.1) {return 0.1;}
		if (like/dislike > 10) {return 10;}
		
		result = Math.round(like/dislike*100)/100.00; // 좋아요, 싫어요... 둘 다 값이 있는 경우
		
		return result;
	}
	
	// 관심 조회수 확인 (하나씩) (통과 O)
	public long lookAttReview(String name, long date, long prevtime, long nowtime) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT COUNT(*) FROM (SELECT REVIEW_LOG_NO FROM REVIEW_LOG WHERE WRITER = ? " + 
				"AND WRITE_DATE = ? AND REVIEW_ATT_POINT >= 2) WHERE REVIEW_LOG_NO BETWEEN ? AND ?";
		long result = 0;
		try {
			con = travelDB.pool.getConnection(); // 연결 정보 빌려오기
			System.out.println("풀 빌려오기");
			try {
	            pstmt = con.prepareStatement(sql); // SQL 해석
	            pstmt.setString(1, name);
	            pstmt.setLong(2, date);
	            pstmt.setLong(3, prevtime);
	            pstmt.setLong(4, nowtime);
	            rs = pstmt.executeQuery();
	            if (rs.next()) {
	            	System.out.println("동작 관심조회수");
	            	result = rs.getLong(1);
				}
	 
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }finally {
	        	if (rs != null) {
	        		rs.close();
				}
				pstmt.close();
				con.close();
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	// 관심 평균 계산 (하나씩) (통과 O)
	public double attAvgReview(String writer, long date, long prevtime, long nowtime ) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT ROUND(AVG(REVIEW_ATT_POINT), 2) " + 
				"FROM (SELECT REVIEW_LOG_NO, REVIEW_ATT_POINT " + 
				"FROM REVIEW_LOG WHERE WRITER = ? AND WRITE_DATE = ?) " + 
				"WHERE REVIEW_LOG_NO BETWEEN ? AND ?";
		double result = 0; // 평균이기 때문에.. 소수점 필요 - 계산에서 소수점 2자리까지 인정
		try {
			con = travelDB.pool.getConnection(); // 연결 정보 빌려오기
			System.out.println("풀 빌려오기");
			try {
	            pstmt = con.prepareStatement(sql); // SQL 해석
	            pstmt.setString(1, writer);
	            pstmt.setLong(2, date);
	            pstmt.setLong(3, prevtime);
	            pstmt.setLong(4, nowtime);
	            rs = pstmt.executeQuery();
	            
	            if (rs.next()) {
	            	result = rs.getDouble(1);
				}
	 
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }finally {
	        	if (rs != null) {
	        		rs.close();
				}
				pstmt.close();
				con.close();
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	// 월간, 주간 점수 갱신 (하나씩) (통과 O)
	public void scoreSetting(double weeksc, double monthsc, String writer, long date) {
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = "UPDATE REVIEW SET WEEKLY_SCORE = ?, MONTHLY_SCORE = ? WHERE WRITER = ? AND WRITE_DATE = ?";
		try {
			con = travelDB.pool.getConnection(); // 연결 정보 빌려오기
			System.out.println("풀 빌려오기");
			try {
	            pstmt = con.prepareStatement(sql); // SQL 해석
	            pstmt.setDouble(1, weeksc);
	            pstmt.setDouble(2, monthsc);
	            pstmt.setString(3, writer);
	            pstmt.setLong(4, date);
	 
	            if (pstmt.executeUpdate() == 1) {
	                System.out.println("리뷰 점수 갱신 성공");
	            } else {
	                System.out.println("리뷰 점수 갱신 실패");
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
	
	// 리뷰 차트 조회(주간) (통과 O)
	public void lookWeekRevChart(String loca) {
		revChart.clear();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT WRITER, WRITE_DATE, TITLE FROM (SELECT WRITER, WRITE_DATE, TITLE, WEEKLY_SCORE FROM REVIEW " + 
				"WHERE LOCATION_DATA = ? ORDER BY WEEKLY_SCORE DESC) WHERE ROWNUM <= 10";
		ReviewCH result = null;
		try {
			con = travelDB.pool.getConnection(); // 연결 정보 빌려오기
			System.out.println("풀 빌려오기");
			try {
	            pstmt = con.prepareStatement(sql); // SQL 해석
	            pstmt.setString(1, loca);
		        rs = pstmt.executeQuery();
	            
	            while(rs.next()) {
	            	result = new ReviewCH();
	            	result.writer = rs.getString(1);
	            	result.date = rs.getLong(2);
	            	result.title = rs.getString(3);
		            	
	            	revChart.add(result);
	            }
		 
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }finally {
		        	
	        	if (rs != null) {
	        		rs.close();
				}
				pstmt.close();
				con.close();
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	// 리뷰 차트 조회 (월간) (통과 O)
		// 리뷰 차트 조회(월간)
	public void lookMonthRevChart(String loca) {
		revChart.clear();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT WRITER, WRITE_DATE, TITLE FROM (SELECT WRITER, WRITE_DATE, TITLE, MONTHLY_SCORE FROM REVIEW " + 
				"WHERE LOCATION_DATA = ? ORDER BY MONTHLY_SCORE DESC) WHERE ROWNUM <= 10";
		ReviewCH result = null;
		try {
			con = travelDB.pool.getConnection(); // 연결 정보 빌려오기
			System.out.println("풀 빌려오기");
			try {
	            pstmt = con.prepareStatement(sql); // SQL 해석
	            pstmt.setString(1, loca);
	            rs = pstmt.executeQuery();
	            
	            while(rs.next()) {
	            	result = new ReviewCH();
	            	result.writer = rs.getString(1);
	            	result.date = rs.getLong(2);
	            	result.title = rs.getString(3);
	            	
	            	revChart.add(result);
	            }
		 
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }finally {
	        	
	        	if (rs != null) {
	        		rs.close();
				}
				pstmt.close();
				con.close();
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	//주간 점수 초기화 (통과 O)
	public void resetWeek() {
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = "UPDATE REVIEW SET WEEKLY_SCORE = 0";
		try {
			con = travelDB.pool.getConnection(); // 연결 정보 빌려오기
			System.out.println("풀 빌려오기");
			try {
	            pstmt = con.prepareStatement(sql); // SQL 해석
	 
	            if (pstmt.executeUpdate() == 1) {
	                System.out.println("주간 초기화 성공");
	            } else {
	                System.out.println("주간 초기화 실패");
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
	
	//월간 점수 초기화 (통과 O)
	public void resetMonth() {
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = "UPDATE REVIEW SET MONTHLY_SCORE = 0";
		try {
			con = travelDB.pool.getConnection(); // 연결 정보 빌려오기
			System.out.println("풀 빌려오기");
			try {
	            pstmt = con.prepareStatement(sql); // SQL 해석
	 
	            if (pstmt.executeUpdate() == 1) {
	                System.out.println("월간 초기화 성공");
	            } else {
	                System.out.println("월간 초기화 실패");
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
	
}

// 리뷰 목록 클래스
class ReviewList {
	public String writer = null; // 작성자
	public String date = null; //작성일자
	public String title = null; //리뷰 제목
}

class RevChange {
	public String writer = null; // 작성자
	public long date = 0; //작성일자
}

//리뷰 차트 담기용
class ReviewCH {
	public String writer = null; // 작성자
	public long date = 0; //작성일자
	public String title = null; //리뷰 제목
}
