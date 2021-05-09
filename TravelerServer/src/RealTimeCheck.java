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
	
	public RealTimeCheck () {
		tour = new Tourism();
		rev = new ReView();
		infrow = new Infrow();
	}
	
	public void popChartAlgorism(long infrow) { // 인기 차트 알고리즘
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
		int waitTime = 0; // 대기 시간
		while(true) {
			long infwAmount = 0; // 유입량
			long prevtime = 0;
			long nowtime = 0;
			
			prevtime = infrow.lastLogNum(); // 이전 유입 날짜 가져오기
			Date now = new Date(); // 현재 날짜, 시간 가져오기
			nowtime = now.getTime()/1000; // 현재 시간 - 최신 로그 번호
			
			infwAmount = tour.logInflowNum(prevtime, nowtime); // 유입량 가져오기
			
			if (waitTime < 30) { // 30분이 안 지나면
				if(infwAmount < MININFROW) {
					try {
						Thread.sleep(60000); // 1분 대기
						System.out.println("휴식");
					}catch(InterruptedException e) {}
					continue; //다시 처음으로
				}else {
					
				}
			}else { } // 30분 지나면 진행
			
			
			try {
				Thread.sleep(10000);
				System.out.println("휴식");
			}catch(InterruptedException e) {}
		}
	}
	
}