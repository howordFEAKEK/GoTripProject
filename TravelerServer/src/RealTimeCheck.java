import java.awt.desktop.SystemEventListener;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class RealTimeCheck extends Thread{
	private int MININFROW = 100;
	Tourism tour;
	ReView rev;
	Infrow infrow;
	
	SimpleDateFormat sample = new SimpleDateFormat("YYYY.MM.DD HH:mm:ss");
	
	public void popChartAlgorism(long infrow) { // 인기 차트 알고리즘 (유입량을 받아와야 함)
		List<String> changeTour = new ArrayList<>();
		long prevtime = 0; // 이전 시간
		long nowtime = 0; // 최근 시간
		long infrw = 0; // 유입량
		infrw = infrow; // 유입량
		
		tour.resetPop(); // 인기 점수 초기화
		
		changeTour = tour.selectChangeTour (prevtime, nowtime); // 변동 있는 관광지 확인
		
		for(int i = 0; i < changeTour.size(); i ++) {
			String tourName = null;
			long popScore = 0;
			tourName = changeTour.get(i);
			
			long looknum = 0;
			long attnum = 0;
			
			looknum = tour.lookTour (tourName,  prevtime, nowtime); // 관광지 조회수 확인 (하나씩)
			
			attnum = tour.attAvgTour (tourName,  prevtime, nowtime); // 관심점수 평균 계산 (하나씩)
			
			popScore = looknum/infrw * 100 * attnum;
		 	
			tour.popScoreSetting (popScore, tourName); // 인기 점수 갱신 (하나씩)
		}
	}
	
	public void revChartAlgorism() { // 리뷰 차트 알고리즘
		
	}
	
	public void run () {
		RealTimeCheck realCheck = new RealTimeCheck(); 
		tour = new Tourism();
		rev = new ReView();
		infrow = new Infrow();
		
		try {
			Thread.sleep(10000); // 10분 대기 (실험은 10초 대기)
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
			System.out.println(infwAmount);
			
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
					//infrow.infrowSave (nowtime, waitTime, infwAmount); // (현재시간, 대기시간, 유입량) 필요
					
					//인기 차트 알고리즘 동작하기
					//realCheck.popChartAlgorism(infwAmount); // (유입량) 필요
					
				}
			}else { } // 30분 지나면 진행
			
			
			
			
			
			try {
				Thread.sleep(10000); // 10분 대기 (실험은 10초 대기)
				System.out.println("휴식");
				 waitTime = 10; // 인기 및 리뷰 차트 마치고 10분 대기했으니, 대기시간 10으로 지정
			}catch(InterruptedException e) {}
		}
	}
	
}