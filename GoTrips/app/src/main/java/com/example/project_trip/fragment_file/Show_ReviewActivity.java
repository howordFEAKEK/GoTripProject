package com.example.project_trip.fragment_file;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.project_trip.Local_Data_List;
import com.example.project_trip.R;
import com.example.project_trip.SocketManager2;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.StringTokenizer;

public class Show_ReviewActivity extends AppCompatActivity {

    // 박물관을 클릭해서 나오는 리뷰 또는 메인탭의 주간 리뷰 , 월간 리뷰의 리뷰를 클릭하면 나오는 리뷰 엑티비티
    
    TextView txt_Review_title , txt_Review_guide , txt_Review_local , txt_Review;
    Button btn_like , btn_hate;

    SimpleDateFormat sample = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");

    //리뷰 하나에 토큰 7개 (작성자, 작성일, 제목, 내용, 좋아요, 싫어요, 관광지명)
    String writer = null; // 작성자
    String wrdate = null; // 작성일자
    String title = null; // 제목
    String contextveiw = null; // 내용
    String like = null; // 좋아요
    String dislike = null; // 싫어요
    String tourname = null; // 관광지명

    boolean rock = true;

    //조회 시간
    Date startTime2 = new Date();
    //날짜를 문자로
    String startstr2 = sample.format(startTime2);

    //마감 시간
    Date lastTime2 = null;
    String laststr2 = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_review);

        txt_Review_title = (TextView) findViewById(R.id.show_review_title); //리뷰 제목
        txt_Review_guide = (TextView) findViewById(R.id.show_review_guide); //리뷰 관광지명
        txt_Review_local = (TextView) findViewById(R.id.show_review_local); //리뷰 지역
        txt_Review = (TextView) findViewById(R.id.show_review_review);      //리뷰 내용

        btn_like = (Button) findViewById(R.id.show_review_Like);            //좋아요 버튼
        btn_hate = (Button) findViewById(R.id.show_review_Hate);            //싫어요 버튼



        txt_Review_title.setText(getIntent().getStringExtra("List"));

    }

    @Override
    public void onResume() {
        super.onResume();

        new Thread() {
            @Override
            public void run() {
                DataOutputStream out;
                DataInputStream in;
                String msg = "REVIEWCALL/"; // 리뷰 보기 신호
                String wruser = getIntent().getStringExtra("wirter"); //작성자
                String wrtime = getIntent().getStringExtra("wrdate"); //작성일자
                String readMsg = null; // 받은 메시지
                String sign = null; // 신호
                String context = null; // 내용
                StringTokenizer st = null;
                try {
                    msg = msg + wruser + "$" + wrtime;

                    System.out.println("리뷰 조회 요청 메시지: " + msg);

                    out = new DataOutputStream(SocketManager2.socket.getOutputStream());
                    in = new DataInputStream(SocketManager2.socket.getInputStream());
                    out.writeUTF(msg);

                    readMsg = in.readUTF(); // 메시지 받기 대기

                    st = new StringTokenizer(readMsg, "/");
                    sign = st.nextToken(); // 신호
                    context = st.nextToken(); // 내용

                    if (sign.equals("REVIEWCALL")) {
                        // 신호가 맞는 경우
                        st = new StringTokenizer(context, "$");
                        //리뷰 하나에 토큰 7개 (작성자, 작성일, 제목, 내용, 좋아요, 싫어요, 관광지명)
                        writer = st.nextToken(); // 작성자
                        wrdate = st.nextToken(); // 작성일자
                        title = st.nextToken(); // 제목
                        contextveiw = st.nextToken(); // 내용
                        like = st.nextToken(); // 좋아요
                        dislike = st.nextToken(); // 싫어요
                        tourname = st.nextToken(); // 관광지명
                        System.out.println("리뷰 조회 내용");
                        System.out.println(writer + " " + wrdate + " " + title
                                + " " + contextveiw + " " + like + " " + dislike + " " +
                                tourname);
                        // 이 부분에서 데이터 어떻게 처리할지는 알아서

                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                txt_Review_title.setText(title);
                                txt_Review_guide.setText(tourname);
                                //txt_Review_local.setText(); // 지역은 안 가져오는데..?
                                txt_Review.setText(contextveiw);
                            }
                        });

                    } else {
                    }// 리뷰가 없는 경우 없음


                } catch (IOException e) {
                }
            }
        }.start();

        if (rock == true) {
            btn_like.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    new Thread() {
                        @Override
                        public void run() {
                            DataOutputStream out;
                            DataInputStream in;
                            String msg = "REVIEWGOOD/"; // 리뷰 보기 신호
                            String wruser = getIntent().getStringExtra("wirter"); //작성자
                            String wrtime = getIntent().getStringExtra("wrdate"); //작성일자
                            try {
                                msg = msg + wruser + "$" + wrtime;

                                System.out.println("리뷰 좋아요: " + msg);

                                out = new DataOutputStream(SocketManager2.socket.getOutputStream());
                                in = new DataInputStream(SocketManager2.socket.getInputStream());
                                out.writeUTF(msg);

                            } catch (IOException e) {
                            }
                        }
                    }.start();
                }
            });
            rock = false;
        }


        if (rock == true) {
            btn_hate.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    new Thread() {
                        @Override
                        public void run() {
                            DataOutputStream out;
                            DataInputStream in;
                            String msg = "REVIEWBAD/"; // 리뷰 보기 신호
                            String wruser = getIntent().getStringExtra("wirter"); //작성자
                            String wrtime = getIntent().getStringExtra("wrdate"); //작성일자
                            try {
                                msg = msg + wruser + "$" + wrtime;

                                System.out.println("리뷰 싫어요: " + msg);

                                out = new DataOutputStream(SocketManager2.socket.getOutputStream());
                                in = new DataInputStream(SocketManager2.socket.getInputStream());
                                out.writeUTF(msg);

                            } catch (IOException e) {
                            }
                        }
                    }.start();
                }
            });
            rock = false;
        }
    }
    @Override
    public void onBackPressed() { // 뒤로 가기 버튼을 누르면
        super.onBackPressed();
        //마감 시간 갱신
        lastTime2 = new Date();
        //날짜를 문자로
        laststr2 = sample.format(lastTime2);
        // 조회시간, 마감시간 성공적
        System.out.println(startstr2 + " " + laststr2);

        new Thread() {
            @Override
            public void run(){
                DataOutputStream out;
                DataInputStream in;
                String user2 = Local_Data_List.username; // 나중에 제품 번호나 전화번호 받아서 처리
                String sign2 = "REVLOG/"; // 리뷰 로그 신호
                String wrname = null; //작성자
                String wrday = null; // 작성일자
                String tournam = null; // 관광지명

                wrname = writer;
                wrday = wrdate;
                tournam = tourname;

                // 내용물(본인, 조회시간, 마감시간, 작성자, 작성일자, 관광지명)
                String msg = sign2 + user2 + "$" + startstr2 + "$" + laststr2 + "$" + wrname + "$" + wrday + "$" + tournam;
                System.out.println(msg);

                try {
                    out = new DataOutputStream(SocketManager2.socket.getOutputStream());
                    out.writeUTF(msg);
                }catch (IOException e){

                }
            }
        }.start();

        finish();
    }

}