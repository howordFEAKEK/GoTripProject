import java.awt.desktop.SystemEventListener;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import oracle.net.aso.i;

public class RealTimeCheck extends Thread{
	private int MININFROW = 100;
	Tourism tour;
	ReView rev;
	Infrow infrow;
	
	SimpleDateFormat sample = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");
	
	
	// 인기 차트 알고리즘 (유입량을 받아와야 함)
	public void popChartAlgorism(long prev, long nowv, long infrow) { // 유입량이 없으면 진행 안 함
		List<String> changeTour = new ArrayList<>();
		long prevtime = 0; // 이전 시간
		long nowtime = 0; // 최근 시간
		double infrw = 0; // 전체 유입량
		infrw = infrow; // 전체 유입량
		prevtime = prev;
		nowtime = nowv;
		
		//tour = new Tourism(); // 알고리즘 테스트 시 임시 정의
		
		tour.resetPop(); // 인기 점수 초기화 // 여기서 널 포인트 문제
		
		changeTour = tour.selectChangeTour (prevtime, nowtime); // 변동 있는 관광지 확인
		
		for (String s : changeTour) { // 개선된 for문 사용해서 구성
			String tourName = null;
			double popScore = 0;
			tourName = s; // 관광지 이름 가져오기
			
			long looknum = 0; // 관광지 조회수
			double attnum = 0; // 관심 점수
			
			double persent = 0; //백분율 계산용
			
			// 관광지 조회수 확인 (하나씩)
			looknum = tour.lookTour (tourName,  prevtime, nowtime);
			System.out.println(looknum);
			
			// 관심점수 평균 계산 (하나씩)
			attnum = tour.attAvgTour (tourName,  prevtime, nowtime);
			System.out.println(attnum);
			
			// 조회수/유입량 = 백분율..(소수점 구현을 위해서)
			persent = Math.round(looknum/infrw * 100) / 100.00;
			System.out.println(looknum/infrw * 100);
			
			// 조회수/유입량의 백분율  * 관심평균
			popScore = persent * 100 * attnum; // 소수점 제어
			popScore = Math.round(popScore * 100) / 100.00;
			
			// 인기 점수 갱신 (하나씩)
			tour.popScoreSetting (popScore, tourName);
		}
		
		tour.updatePopChart();
		
		/*
		for(int i = 0; i < changeTour.size(); i ++) { //일반적인 for문 사용해서 구성
			String tourName = null;
			long popScore = 0;
			tourName = changeTour.get(i); //관광지 이름 가져오기
			
			long looknum = 0;
			long attnum = 0;
			
			looknum = tour.lookTour (tourName,  prevtime, nowtime); // 관광지 조회수 확인 (하나씩)
			
			attnum = tour.attAvgTour (tourName,  prevtime, nowtime); // 관심점수 평균 계산 (하나씩)
			
			popScore = looknum/infrw * 100 * attnum;
		 	
			tour.popScoreSetting (popScore, tourName); // 인기 점수 갱신 (하나씩)
		}
		*/
	}
	
	
	// 리뷰 차트 알고리즘
	public void revChartAlgorism(long prvTi, long nowTi) { //
		SimpleDateFormat caltype = new SimpleDateFormat("yyyy.MM.dd"); // 날짜 형태 지정
		Calendar cal = Calendar.getInstance(Locale.KOREA); // 캘린더 선언
		
		// 받아와야 하는 값 
		long prevtime = 0; // 이전 시간
		long nowtime = 0; // 최근 시간
		prevtime = prvTi;
		nowtime = nowTi;
		
		// 주간, 월간 계산할 때, 필요한 변수들
		String rePretime = null;
		String reNowtime = null;
		long prev = 0; // 이전 시간 계산용
		long nowv = 0; // 최근 시간 계산용
		long lwkdate = 0; // 한 주의 시작일(이전)
		long lmtdate = 0; // 한 달의 시작일(이전)
		long wkdate = 0; // 한 주의 시작일(최근)
		long mtdate = 0; // 한 달의 시작일(최근)
		Date d1 = new Date(); // 이전 시간 (날짜 형식)
		Date d2 = new Date(); // 최근 시간 (날짜 형식)
		
		//rev = new ReView(); // 알고리즘 테스트 시 임시 정의
		//--------------------------- 날짜 처리 -----------------------------------//
		
		// 밀리 세컨드 단위로 맞춰주기
		prev = prevtime * 1000; // 이전 시간
		nowv = nowtime * 1000; // 최근 시간
		
		//숫자를 날짜 형식으로 바꾸기
		d1.setTime(prev); // 이전
		d2.setTime(nowv); // 최근
		
		// 날짜 형식을 문자 형식으로 바꾸기
		rePretime = caltype.format(d1);
		reNowtime = caltype.format(d2);
		
		try {
			d1 = caltype.parse(rePretime); // 문자를 날짜로
			d2 = caltype.parse(reNowtime); // 문자를 날짜로
		}catch (ParseException e) {}
		
		//이전 날짜가 속한 주차 구하기
		cal.setTime(d1);
		cal.add(Calendar.DATE, 1 - cal.get(Calendar.DAY_OF_WEEK));
		lwkdate = cal.getTimeInMillis()/1000; // 이전 주 시작일
		
		//이전 날짜가 속한 달 구하기
		cal.setTime(d1);
		cal.add(Calendar.DATE, 1 - cal.get(Calendar.DAY_OF_MONTH));
		lmtdate = cal.getTimeInMillis()/1000; // 이전 달 시작일
		
		//최근 날짜가 속한 주차 구하기
		cal.setTime(d2);
		cal.add(Calendar.DATE, 1 - cal.get(Calendar.DAY_OF_WEEK));
		wkdate = cal.getTimeInMillis()/1000; // 최근 주 시작일
				
		//최근 날짜가 속한 달 구하기
		cal.setTime(d2);
		cal.add(Calendar.DATE, 1 - cal.get(Calendar.DAY_OF_MONTH));
		mtdate = cal.getTimeInMillis()/1000; // 최근 달 시작일
		
		//--------------------------- 날짜 처리 -----------------------------------//
		System.out.println(lwkdate + " " + wkdate);
		
		// 이전 주와 최근 주가 다르면 진행
		if(lwkdate != wkdate) {
			//모든 리뷰의 주간 점수 초기화
			rev.resetWeek();
		}
		
		// 이전 달과 최근 달이 다르면 진행
		if(lmtdate != mtdate) {
			//모든 리뷰의 월간 점수 초기화
			rev.resetMonth();
		}
		
		rev.changeReview(prevtime, nowtime); // 변동 리뷰 조회
		
		for(int i = 0; i < rev.chRevs.size(); i ++) {
			String name = rev.chRevs.get(i).writer; // 작성자
			long wrdate = rev.chRevs.get(i).date; // 작성일자
			
			double likePoint = 0; // 상관 점수
			
			long attLook1 = 0; // 관심 조회수 (주간)
			long attLook2 = 0; // 관심 조회수 (월간)
			
			double attNum1 = 0; // 관심 점수 (주간)
			double attNum2 = 0; // 관심 점수 (월간)
			
			
			double weeksc = 0; // 주간 점수
			double monthsc = 0; // 월간 점수
			
			// 좋아요, 싫어요  (비율에 따른 점수 좋아요/싫어요 값)
			likePoint = rev.getLikePoint(name, wrdate);
			
			//해당 리뷰의 관심 조회수 확인
			attLook1 = rev.lookAttReview(name, wrdate, wkdate, nowtime); // 주간
			attLook2 = rev.lookAttReview(name, wrdate, mtdate, nowtime); // 월간
			
			//관심 평균 계산
			attNum1 = rev.attAvgReview(name, wrdate, wkdate, nowtime); // 주간
			attNum2 = rev.attAvgReview(name, wrdate, mtdate, nowtime); // 월간
			
			//주간 점수 계산 (상관점수 * 관심평균  * 관심조회수)
			weeksc = attNum1 * attLook1 * likePoint;
			weeksc = Math.round(weeksc * 100)/100.00;
			
			//월간 점수 계산 (상관점수 * 관심평균  * 관심조회수)
			monthsc = attNum2 * attLook2 * likePoint;
			monthsc = Math.round(monthsc * 100)/100.00;
			
			//월간, 주간 점수 갱신
			rev.scoreSetting(weeksc, monthsc, name, wrdate);
			
		}
		
	}
	
	
	public void run () {
		RealTimeCheck realCheck = new RealTimeCheck(); 
		tour = new Tourism();
		rev = new ReView();
		infrow = new Infrow();
		
		try {
			Thread.sleep(600000); // 10분 대기 (실험은 10초 대기)
			System.out.println("휴식");
		}catch(InterruptedException e) {}
		long waitTime = 10; // 대기 시간 
		while(true) {
			long infwAmount = 0; // 유입량
			long prevtime = 0;
			long nowtime = 0;
			
			prevtime = infrow.lastLogNum(); // 이전 유입 날짜 가져오기
			//System.out.println(prevtime); // 이전 유입 날짜 성공 - 단, 데이터베이스 정리 필요
			
			// 현재 시간 가져오기 성공
			Date now = new Date(); // 현재 날짜, 시간 가져오기
			nowtime = now.getTime()/1000; // 현재 시간 - 최신 로그 번호
			
			//여기서 NullPointerException이 나타남 -> 유입량이 없을 경우.
			infwAmount = tour.logInflowNum(prevtime, nowtime); // 유입량 가져오기 (현재 파트까지 유입량 계산하기)
			System.out.println(infwAmount); // 유입량 확인
			
			if (waitTime < 30) { // 30분이 안 지나면
				if(infwAmount < MININFROW) {
					try {
						System.out.println("휴식");
						Thread.sleep(60000); // 1분 대기
						
						waitTime = waitTime + 1; // 대기시간에 1 추가
						
					}catch(InterruptedException e) {}
					continue; //다시 처음으로
					
				}else { // 30분이 안 지났지만 유입량이 최소 100보다 크면
					
					long inf = infrow.lookInfrowGet(); // 이전 유입량 3개 평균치
					
					if(infwAmount < inf) { // 유입량이 이전 유입량 평균보다 작은 경우
						try {
							System.out.println("휴식");
							Thread.sleep(60000); // 1분 대기
							
							waitTime = waitTime + 1; // 대기시간에 1 추가
							
						}catch(InterruptedException e) {}
						continue; //다시 처음으로
					}
					
					// 유입량이 이전 유입량 평균보다 큰 경우 시작..
					
					//현재 유입량 기록 및 저장하기
					infrow.infrowSave (nowtime, waitTime, infwAmount); // (현재시간, 대기시간, 유입량) 필요
					
					//인기 차트 알고리즘 동작하기
					realCheck.popChartAlgorism(prevtime, nowtime, infwAmount); // (유입량) 필요
					
					//리뷰 차트 알고리즘 동작하기
					realCheck.revChartAlgorism(prevtime, nowtime);
					
				}
			}else { // 30분 지나면 진행
				if(infwAmount > 0){ // 유입량이 0만 아니면 진행
					
					//현재 유입량 기록 및 저장하기
					infrow.infrowSave (nowtime, waitTime, infwAmount); // (현재시간, 대기시간, 유입량) 필요
					
					//인기 차트 알고리즘 동작하기 // 여기서 널 포인트 문제, RealTimeCheck 34번과 이어짐
					realCheck.popChartAlgorism(prevtime, nowtime, infwAmount); // (유입량) 필요
					
					//리뷰 차트 알고리즘 동작하기
					realCheck.revChartAlgorism(prevtime, nowtime);
					
				}else { // 유입량이 0이면 진행
					//현재 유입량 기록 및 저장하기
					infrow.infrowSave (nowtime, waitTime, infwAmount); // (현재시간, 대기시간, 유입량) 필요
					// ---> 유입량 0 기록 후 차트 갱신하지 않음
				}
			}
			
			
			try { // 처리 작업 후 10분 딜레이
				Thread.sleep(600000); // 10분 대기 (실험은 10초 대기)
				System.out.println("휴식");
				 waitTime = 10; // 인기 및 리뷰 차트 마치고 10분 대기했으니, 대기시간 10으로 지정
			}catch(InterruptedException e) {}
		}
	}
	
}