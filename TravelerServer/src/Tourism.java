import java.sql.*;
import java.util.ArrayList;
import java.util.List;
public class Tourism {
	//Connection con = null; // 커넥션을 빌려오기 위해
	//PreparedStatement pstmt = null;
	//Statement stmt = null;
	//ResultSet rs = null; // 결과값을 저장하고 있음
	
	public List<String> changeTour = new ArrayList<String>(); // 변동 관광지
	public List<PopChart> poplist = new ArrayList<>();
	
	//-------------------관광지 관련 SQL-------------------------//
	// 관광지 유무 조회 (통과 O)
	public String selectTour(String tourName) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT TOUR_NAME FROM TOUR_PLACE WHERE TOUR_NAME=?";
		String result = null;
		try {
			con = travelDB.pool.getConnection(); // 연결 정보 빌려오기
			System.out.println("풀 빌려오기");
			try {
	            pstmt = con.prepareStatement(sql); // SQL 해석
	            pstmt.setString(1, tourName);
	            rs = pstmt.executeQuery();
	            
	            if(rs.next()) {
	            	result = rs.getString(1);
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
	
	// 관광지를 목록에 저장 (통과 O)
	public void saveTour(String tourName, String location) {
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = "INSERT INTO TOUR_PLACE(TOUR_NAME, LOCATION_DATA) VALUES (?, ?)";
		try {
			con = travelDB.pool.getConnection(); // 연결 정보 빌려오기
			System.out.println("풀 빌려오기");
			try {
	            pstmt = con.prepareStatement(sql); // SQL 해석
	            pstmt.setString(1, tourName);
	            pstmt.setString(2, location);
	 
	            if (pstmt.executeUpdate() == 1) {
	                System.out.println("관광지 저장 성공");
	            } else {
	                System.out.println("관광지 저장 실패");
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
	
	// 조회용 테이블에 관광지 저장 (통과 O)
	public void saveTU(String tourName, String location) {
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = "INSERT INTO TOUR_VIEW(TU_NAME, LOC_DATA) VALUES (?, ?)";
		try {
			con = travelDB.pool.getConnection(); // 연결 정보 빌려오기
			System.out.println("풀 빌려오기");
			try {
	            pstmt = con.prepareStatement(sql); // SQL 해석
	            pstmt.setString(1, tourName);
	            pstmt.setString(2, location);
	 
	            if (pstmt.executeUpdate() == 1) {
	                System.out.println("관광지 저장 성공");
	            } else {
	                System.out.println("관광지 저장 실패");
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
	
	
	// 로그 등록 (통과 O)
	public void saveTourLog (String ph, long logtime, long att, String tourName) {
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = "INSERT INTO TOUR_LOG(READER, TOUR_LOG_NO, TOUR_ATT_POINT, TOUR_NAME) VALUES (?,?,TRUNC(?, 0),?)";
		try {
			con = travelDB.pool.getConnection(); // 연결 정보 빌려오기
			System.out.println("풀 빌려오기");
			try {
				pstmt = con.prepareStatement(sql); // SQL 해석
	            pstmt.setString(1, ph);
	            pstmt.setLong(2, logtime);
	            pstmt.setLong(3, att);
	            pstmt.setString(4, tourName);
	 
	            if (pstmt.executeUpdate() == 1) {
	                System.out.println("관광 로그 저장 성공");
	            } else {
	                System.out.println("관광 로그 저장 실패");
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
	
	// 관광지 로그 유입량 조회 (통과 O)
	public int logInflowNum (long prevtime, long nowtime) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT COUNT(*) FROM TOUR_LOG WHERE TOUR_LOG_NO BETWEEN ? AND ?";
		int result = 0;
		try {
			con = travelDB.pool.getConnection(); // 연결 정보 빌려오기
			System.out.println("풀 빌려오기");
			try {
	            pstmt = con.prepareStatement(sql); // SQL 해석
	            pstmt.setLong(1, prevtime);
	            pstmt.setLong(2, nowtime);
	            rs = pstmt.executeQuery();
	            
	            while(rs.next()) {
	            	result = rs.getInt(1);
	            }
	 
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }finally {
	        	if(rs != null) {
	        		rs.close();
	        	}
				pstmt.close();
				con.close();
				if(rs == null) {
	        		System.out.println("null 값을 가졌");
	        	}
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	
	//-------------------인기 차트 관련 SQL-------------------------//
	// 인기 점수 초기화 (통과 O)
	public void resetPop () {
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = "UPDATE TOUR_PLACE SET POP_SCORE = 0";
		try {
			con = travelDB.pool.getConnection(); // 연결 정보 빌려오기
			System.out.println("풀 빌려오기");
			try {
	            pstmt = con.prepareStatement(sql); // SQL 해석
	 
	            if (pstmt.executeUpdate() == 1) {
	                System.out.println("인기 초기화 성공");
	            } else {
	                System.out.println("인기 초기화 실패");
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
	
	// 변동사항 있는 관광지 확인 (통과 O)
	public List<String> selectChangeTour (long prevtime, long nowtime) {
		changeTour.clear();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT DISTINCT TOUR_NAME FROM (SELECT TOUR_NAME FROM TOUR_LOG WHERE TOUR_LOG_NO BETWEEN ? AND ?)";
		
		try {
			con = travelDB.pool.getConnection(); // 연결 정보 빌려오기
			System.out.println("풀 빌려오기");
			try {
	            pstmt = con.prepareStatement(sql); // SQL 해석
	            pstmt.setLong(1, prevtime);
	            pstmt.setLong(2, nowtime);
	            rs = pstmt.executeQuery();
	            while(rs.next()) {
	            	changeTour.add(rs.getString(1));
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
		return changeTour;
	}
	
	// 관광지 조회수 확인 (하나씩) (통과 O)
	public long lookTour (String tourName, long prevtime, long nowtime) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT COUNT(*) FROM (SELECT TOUR_LOG_NO, TOUR_ATT_POINT FROM TOUR_LOG WHERE TOUR_NAME = ?) WHERE TOUR_LOG_NO BETWEEN ? AND ?";
		long result = 0;
		try {
			con = travelDB.pool.getConnection(); // 연결 정보 빌려오기
			System.out.println("풀 빌려오기");
			try {
	            pstmt = con.prepareStatement(sql); // SQL 해석
	            pstmt.setString(1, tourName);
	            pstmt.setLong(2, prevtime);
	            pstmt.setLong(3, nowtime);
	            rs = pstmt.executeQuery();
	            
	            if (rs.next()) {
	            	result = rs.getInt(1);
				}
	 
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }finally {
	        	if(rs != null) {
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
	
	// 관광지 관심 평균 계산 (하나씩) (통과 O)
	public double attAvgTour (String tourName, long prevtime, long nowtime) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT ROUND(AVG(TOUR_ATT_POINT), 2) " + 
				"FROM (SELECT TOUR_LOG_NO, TOUR_ATT_POINT " + 
				"FROM TOUR_LOG WHERE TOUR_NAME = ?) " + 
				"WHERE TOUR_LOG_NO BETWEEN ? AND ?";
		double result = 0; // 평균이기 때문에.. 소수점 필요 - 계산에서 소수점 2자리까지 인정
		try {
			con = travelDB.pool.getConnection(); // 연결 정보 빌려오기
			System.out.println("풀 빌려오기");
			try {
	            pstmt = con.prepareStatement(sql); // SQL 해석
	            pstmt.setString(1, tourName);
	            pstmt.setLong(2, prevtime);
	            pstmt.setLong(3, nowtime);
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
	
	// 인기점수 갱신 (하나씩) (통과 O)
	public void popScoreSetting (double popScore, String tourName) {
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = "UPDATE TOUR_PLACE SET POP_SCORE = ? WHERE TOUR_NAME = ?";
		try {
			con = travelDB.pool.getConnection(); // 연결 정보 빌려오기
			System.out.println("풀 빌려오기");
			try {
	            pstmt = con.prepareStatement(sql); // SQL 해석
	            pstmt.setDouble(1, popScore);
	            pstmt.setString(2, tourName);
	 
	            if (pstmt.executeUpdate() == 1) {
	                System.out.println("인기 초기화 성공");
	            } else {
	                System.out.println("인기 초기화 실패");
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
	
	// 인기 차트 갱신 - 인기 차트 조회용 테이블에 등록 (통과 O)
	public void updatePopChart () {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "UPDATE (SELECT A.POP_SC AS A_POP_SC, B.POP_SCORE AS B_POP_SCORE " + 
				"FROM TOUR_VIEW A, TOUR_PLACE B WHERE A.TU_NAME = B.TOUR_NAME) SET A_POP_SC = B_POP_SCORE";
		try {
			con = travelDB.pool.getConnection(); // 연결 정보 빌려오기
			System.out.println("풀 빌려오기");
			try {
	            pstmt = con.prepareStatement(sql); // SQL 해석
	            rs = pstmt.executeQuery();
	 
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
	
	// 인기 차트 조회 - 조회용 테이블에서 인기 차트 목록 가져오기 (통과 O)
	public void lookingPopChart () {
		poplist.clear();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT TU_NAME, LOC_DATA " + 
				"FROM (SELECT TU_NAME, LOC_DATA, POP_SC " + 
				"FROM TOUR_VIEW ORDER BY POP_SC DESC) " + 
				"WHERE ROWNUM <= 10";
		try {
			con = travelDB.pool.getConnection(); // 연결 정보 빌려오기
			System.out.println("풀 빌려오기");
			try {
	            pstmt = con.prepareStatement(sql); // SQL 해석
	            rs = pstmt.executeQuery();
	            
	            while(rs.next()) {
	            	PopChart pop = new PopChart();
	            	pop.TUName = rs.getString(1);
	            	pop.TUlocate = rs.getString(2);
	            	poplist.add(pop);
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
	
	
}

class PopChart {
	public String TUName = null; //관광지 이름
	public String TUlocate = null; // 관광지 지역정보
}
