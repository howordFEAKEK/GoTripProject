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
		
		// 현재 일이 속한 주차 구하기 성공
		SimpleDateFormat sample = new SimpleDateFormat("yyyy.MM.dd");
		
		// 현재 일이 속한 주차 구하기 성공
		Calendar c = Calendar.getInstance();
		c.set(Calendar.DAY_OF_WEEK,Calendar.SUNDAY);
		System.out.println(sample.format(c.getTime()));
		
		// 특정 일이 속한 주차 구하기
		String date = "2021.06.12";
		Date d1 = new Date();
		try {
			d1 = sample.parse(date); // 문자를 날짜로
		}catch (ParseException e) {}
		Calendar cal = Calendar.getInstance(Locale.KOREA);
		cal.setTime(d1);
		cal.add(Calendar.DATE, 1 - cal.get(Calendar.DAY_OF_WEEK));
		System.out.println(sample.format(cal.getTime()));
		
		// 특정 일이 속한 달 구하기
		cal.setTime(d1);
		cal.add(Calendar.DATE, 1 - cal.get(Calendar.DAY_OF_MONTH));
		System.out.println(sample.format(cal.getTime()));
		
		// 문자, 숫자, 날짜 형태 변경하기
		Date d2 = null;
		try {
			d2 = sample.parse(date); // 문자를 날짜로
		}catch (ParseException e) {}
		
		long ldate = d2.getTime(); // 날짜를 숫자로
		
		Date dd = new Date(ldate); // 숫자를 날짜로
		
		String log = sample.format(ldate); // 숫자를 문자로
		
		System.out.println(log);
		
		
		server.setSocket(); // 서버 시작
	}

}
