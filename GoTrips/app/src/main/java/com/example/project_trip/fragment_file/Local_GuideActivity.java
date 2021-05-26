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

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Local_GuideActivity extends AppCompatActivity{

    // 메인 탭에서 최싱단 박물관 이름을 클릭하면 나오는 엑티비티

    Button btn , btn2;
    TextView txt1 , txt2 , txttest1 ,txt_dosi , txt_gungu , txt_more;
    RecyclerView rcyv;
    Local_GuideRecyclerAdepter adepter;
    ArrayList<Main_item4> getMyList;
    List<Address> list = null;
    Getter getter = new Getter();
    Cutter cutter = new Cutter();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_local__guide);
        final Geocoder geocoder = new Geocoder(this);            // 구글지도

        txt_dosi = findViewById(R.id.local_guide_sido_title);           // 00시,도
        txt_gungu = findViewById(R.id.local_guide_gungu_title);         // 00군,구
        txt2 = (TextView) findViewById(R.id.local_guide_title_text);    // 제목
        txt2.setText(getIntent().getStringExtra("local_title"));

        txt_more = findViewById(R.id.local_guide_more);                 // 상세 정보
       
       
        int listNum = (getIntent().getIntExtra("number" , 0));  // 리스트 인텐트 값의 인덱스 번호
        
        
        String local_g_sido_title = Local_Data_List.local_data.get(listNum).sido_name;  // 메인에서 가져온 시,도값
        String local_g_gungu_title = Local_Data_List.local_data.get(listNum).gungu_name; // 메인에서 가져온 군,구값
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


        getMyList = new ArrayList<>();
        getMyList.add(new Main_item4("리뷰입니다1"));
        getMyList.add(new Main_item4("리뷰입니다2"));
        getMyList.add(new Main_item4("리뷰입니다3"));
        getMyList.add(new Main_item4("리뷰입니다4"));
        getMyList.add(new Main_item4("리뷰입니다5"));
        getMyList.add(new Main_item4("리뷰입니다6"));
        getMyList.add(new Main_item4("리뷰입니다7"));
        getMyList.add(new Main_item4("리뷰입니다8"));
        getMyList.add(new Main_item4("리뷰입니다9"));


        adepter = new Local_GuideRecyclerAdepter(getMyList , this);
        rcyv.setAdapter(adepter);

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

}