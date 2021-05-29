package com.example.project_trip;

import java.io.*;
import java.net.*;

public class SocketManager2 {
    public final static String ip = "192.168.35.228"; //아이피 주소(로컬 접속)
    public final static int port = 12021; // 포트 번호

    public static Socket socket; //정적 형식 소켓 저장

    public static Socket getSocket() throws IOException { //소켓 가져오기
        if (socket == null){ //소켓 확인
            socket = new Socket();
        }

        if(!socket.isConnected()){ //소켓 연결
            socket.connect(new InetSocketAddress(ip, port));
        }
        return socket;
    }

    public static void closeSock() throws IOException{ //소켓 닫기
        if(socket != null){
            socket.close();
        }
    }
}
