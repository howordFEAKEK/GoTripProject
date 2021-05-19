/*
4-1학기 종합 프로젝트
프로젝트 명 : 실시간 차트 및 위치 기반 관광지 안내 어플 제작
프로젝트 구분 : Back End (서버 및 데이터베이스 구현 파트)
담당 계발자 : 이인호.
학과 : 컴퓨터 공학과.
개발 시작: 2021.04.13
개발 완료: 2021.05.00   버전 1.
*/ //프로젝트 소개
import java.awt.*;
import java.awt.event.*;
import java.awt.image.SampleModel;

import javax.swing.*;
import javax.swing.event.*;
import javax.swing.tree.*;

import javax.swing.table.*;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.StringTokenizer;
// 아래 구문은 'https://blog.naver.com/kkj6369/220599492477'에서 참고한 부분입니다.
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;
// 여기 파트까지 참고한 부분입니다.
public class travelServer extends Thread{
	private ServerSocket setServer ;
	private Socket socket;
	
	public void setSocket() {
		try {
			setServer = new ServerSocket(12021);
			
			try {
				while(true) {
					System.out.println("대기중......");
					socket = setServer.accept(); // 클라이언트가 접촉할 때까지 대기
					System.out.println(socket.getInetAddress() + "에서 접속했습니다.");
					
					// 새로운 사용자 스래드 클래스를 생성해서 소켓 정보를 전달
					playTravel acess = new playTravel(socket);
					acess.start(); // 접속 및 작업 시작
				}
			}catch(IOException r) {
				System.out.println("서버 생성에 실패했습니다.");
			}
			
		}catch(IOException e) {
			System.out.println("포트가 잘못 지정되었습니다.");
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub=
		travelServer server = new travelServer();
		travelDB DB = new travelDB();
		RealTimeCheck real = new RealTimeCheck();
		
		DB.connectDatabase(); // 연습
		
		//real.start(); // 알고리즘 스레드 시작
		
		//server.setSocket(); // 서버 시작
		
		// 각 알고리즘 테스트
		ReView rev = new ReView();
		Tourism tor = new Tourism();
		//System.out.println(tor.logInflowNum(1609029233, 1609029353));
		/*
		tor.selectChangeTour(1609029233, 1609029353);
		for(int i = 0 ; i < tor.changeTour.size(); i ++) {
			System.out.println(tor.changeTour.get(i));
		}
		*/
		//System.out.println(tor.lookTour("이천박물관", 1609029233, 1609029353));
		
		//System.out.println(tor.attAvgTour("이천박물관", 1609029233, 1609029353));
		
		//tor.popScoreSetting(3.75, "이천박물관");
		
		//tor.updatePopChart();
		
		/*
		rev.changeReview(1609038350, 1609038354);
		for(int i = 0 ; i < rev.chRevs.size(); i ++) {
			System.out.println(rev.chRevs.get(i).writer + " " + rev.chRevs.get(i).date);
		}
		String name = "CB76PI";
		System.out.println(rev.getLikePoint(name, 1609030813));
		
		System.out.println(rev.lookAttReview(name, 1609030813, 1609038350, 1609038354));
		
		System.out.println("-----------주간 리뷰 차트 부분-----------------");
		rev.lookWeekRevChart();
		for(int i = 0 ; i < rev.revChart.size(); i ++) {
			System.out.println(rev.revChart.get(i).writer + " " + rev.revChart.get(i).date + " " + rev.revChart.get(i).title);
		}
		System.out.println("-----------월간 리뷰 차트 부분-----------------");
		rev.lookMonthRevChart();
		for(int i = 0 ; i < rev.revChart.size(); i ++) {
			System.out.println(rev.revChart.get(i).writer + " " + rev.revChart.get(i).date + " " + rev.revChart.get(i).title);
		}
		
		//rev.resetWeek();
		//rev.resetMonth();
		//tor.resetPop();
		*/
	}

}
