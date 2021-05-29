package com.example.project_trip.fragment_file;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.project_trip.Cutter;
import com.example.project_trip.Getter;
import com.example.project_trip.Local_Data_List;
import com.example.project_trip.R;
import com.example.project_trip.SocketManager2;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.StringTokenizer;

public class Local_GuideActivity extends AppCompatActivity{

    // 메인 탭에서 최싱단 박물관 이름을 클릭하면 나오는 엑티비티

    Button btn , btn2;
    TextView txt1 , txt2 , txttest1 ,txt_dosi , txt_gungu , txt_more;
    RecyclerView rcyv;
    Local_GuideRecyclerAdepter adepter;
    ArrayList<Main_item4> getMyList = new ArrayList<>();
    List<Address> list = null;
    Getter getter = new Getter();
    Cutter cutter = new Cutter();

    // 날짜 형태 포멧
    SimpleDateFormat sample = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");

    //조회 시간
    Date startTime = new Date();
    //날짜를 문자로
    String startstr = sample.format(startTime);

    //마감 시간
    Date lastTime = null;
    String laststr = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_local__guide);
        final Geocoder geocoder = new Geocoder(this);            // 구글지도

        txt_dosi = findViewById(R.id.local_guide_sido_title);           // 00시,도
        txt_gungu = findViewById(R.id.local_guide_gungu_title);         // 00군,구
        txt2 = (TextView) findViewById(R.id.local_guide_title_text);    // 제목
        txt2.setText(getIntent().getStringExtra("local_title"));

        String tourname = getIntent().getStringExtra("local_title"); // 관광지명

        txt_more = findViewById(R.id.local_guide_more);                 // 상세 정보
       
       
        int listNum = (getIntent().getIntExtra("number" , 0));  // 리스트 인텐트 값의 인덱스 번호


        String local_g_sido_title = getIntent().getStringExtra("sido");  // 메인에서 가져온 시,도값
        String local_g_gungu_title = getIntent().getStringExtra("gungu"); // 메인에서 가져온 군,구값
        String local_g_sido_gungu_title = local_g_sido_title + " " + local_g_gungu_title;
        txt_dosi.setText(local_g_sido_title);   // 시,도값 세팅
        txt_gungu.setText(local_g_gungu_title); // 군,구값 세팅


        //관광지 상세정보
        try {
            String more_guide = getter.apiGetterName(local_g_sido_title , local_g_gungu_title , txt2.getText().toString()); // 관광지 정보 가져오기
            String more_guide_cut = cutter.apiCutter(more_guide , "FSimpleDesc");
            String more_guide2 = getter.apiGetter2(local_g_sido_title , local_g_gungu_title , txt2.getText().toString());
            String more_guide_cut2 = cutter.apiCutter(more_guide2 , "EPreSimpleDesc");
            if(more_guide_cut.isEmpty()){
                Log.d("EPreSimpleDesc" ,more_guide_cut2);
                txt_more.setText(more_guide_cut2);
            }else{
                Log.d("FSimpleDesc" ,more_guide_cut);
                txt_more.setText(more_guide_cut);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } //관광지 상세정보 끝

//        try {
//            String more_guide2 = getter.apiGetter2(local_g_sido_title , local_g_gungu_title , txt2.getText().toString());
//            String more_guide_cut2 = cutter.apiCutter(more_guide2 , "EPreSimpleDesc");
//            Log.d("EPreSimpleDesc" ,more_guide_cut2);
//
//
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        } //관광지 상세정보 끝


        // 중단의 리뷰 리스트입니다.

        rcyv = (RecyclerView)findViewById(R.id.local_guide_recyclerview);
        rcyv.setLayoutManager(new LinearLayoutManager(this));

        /*
        관광지명 -> 서버의 관광지명변수?
            if(txt2.getText.toString.equals("관광지명"){
            관광지 명에 맡는 서버에있는 리뷰 가져와서 리스트에 넣은다음(getMyList)
            출력하면 될거같음
        }


         */

        new Thread() {
            public void run() {
                DataOutputStream out;
                DataInputStream in;
                String msg = "REVIEWINDEX/";
                String title = tourname;
                String loca = local_g_sido_gungu_title;

                try{
                    out = new DataOutputStream(SocketManager2.socket.getOutputStream());
                    in = new DataInputStream(SocketManager2.socket.getInputStream());

                    msg = msg + title + "$" + loca; // 신호/관광지명$지역정보

                    out.writeUTF(msg); // 서버에 전송하기

                    String readmsg = in.readUTF();
                    final String reading = readmsg;
                    StringTokenizer st = new StringTokenizer(reading, "/");
                    String sign = st.nextToken();
                    String massage = st.nextToken();

                    if (sign.equals("NOLIST")){
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                getMyList.clear(); //일단 검증을 위해 제외
                                adepter = new Local_GuideRecyclerAdepter(getMyList , Local_GuideActivity.this);
                                rcyv.setAdapter(adepter);
                            }
                        });
                        System.out.println("목록이 없음");
                    }else {
                        getMyList.clear();

                        st = new StringTokenizer(massage, "$");
                        int indexNum = st.countTokens()/3;
                        for (int i = 0; i < indexNum ; i ++){
                            Main_item4 item4 = new Main_item4();
                            item4.review_index_writername = st.nextToken();
                            item4.review_index_date = st.nextToken();
                            item4.review_index_title = st.nextToken();

                            // 이 리스트가 static으로 되어 있어야 함
                            // 그래야 리뷰 클릭했을 때, 여기 있는 작성자, 작성일자로 가져올 수 있음
                            getMyList.add(item4);
                        }
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                // getMyList.clear(); //일단 검증을 위해 제외
                                adepter = new Local_GuideRecyclerAdepter(getMyList , Local_GuideActivity.this);
                                rcyv.setAdapter(adepter);
                            }
                        });
                        System.out.println("목록이 있음");
                    }


                }catch (IOException e) {}
            }
        }.start();


        //adepter = new Local_GuideRecyclerAdepter(getMyList , this);
        //rcyv.setAdapter(adepter);

        // 하단의 리뷰 작성 버튼입니다.

        txt1 = findViewById(R.id.set_review_text);
        txt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Local_GuideActivity.this , Save_ReviewActivity.class);
                intent.putExtra("관광지명" , txt2.getText().toString());
                intent.putExtra("시도" , txt_dosi.getText().toString());
                intent.putExtra("군구" , txt_gungu.getText().toString());
                startActivity(intent);
            }
        });

        // 지도 탭 접속 버튼입니다.

        btn = findViewById(R.id.button2);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                
                // 관광지 제목 위도,경도값으로 변경
                String str = txt2.getText().toString();
                try {
                    list = geocoder.getFromLocationName(
                            str, // 지역 이름
                            10); // 읽을 개수
                } catch (IOException e) {
                    e.printStackTrace();
                    Log.e("test","입출력 오류 - 서버에서 주소변환시 에러발생");
                }

                //위경도값 저장할때 사용하면 될듯
                if (list != null) {
                    if (list.size() == 0) {
//                        txttest1.setText("해당되는 주소 정보는 없습니다");
                    } else {
//                        txttest1.setText("위도 : "+list.get(0).getLatitude()+"\n경도 : "+list.get(0).getLongitude());
                        //          list.get(0).getCountryName();  // 국가명
                        //          list.get(0).getLatitude();        // 위도
                        //          list.get(0).getLongitude();    // 경도
                    }
                }
                
                
                // Map으로 넘어가는 인텐트
                Intent intent = new Intent(Local_GuideActivity.this , MapsActivity.class);
                intent.putExtra("test1" , list.get(0).getLatitude());
                intent.putExtra("test2" , list.get(0).getLongitude());
                intent.putExtra("test3" , txt2.getText());
                startActivity(intent);

                
            }
        });

    }

    // 뒤로가기 버튼 눌렀을 때 이벤트
    @Override
    public void onBackPressed(){
        super.onBackPressed();
        //마감 시간 갱신
        lastTime = new Date();
        //날짜를 문자로
        laststr = sample.format(lastTime);
        // 조회시간, 마감시간 성공적
        System.out.println(startstr + " " + laststr);

        new Thread() {
            @Override
            public void run(){
                DataOutputStream out;
                DataInputStream in;
                String user = "TUS77T"; // 나중에 제품 번호나 전화번호 받아서 처리
                String sign = "TOURLOG/"; // 관광 로그 신호
                String tourname = getIntent().getStringExtra("local_title"); // 관광지명
                String msg = sign + user + "$" + startstr + "$" + laststr + "$" + tourname;
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