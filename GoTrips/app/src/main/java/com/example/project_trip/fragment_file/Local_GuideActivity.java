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

import com.example.project_trip.R;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Local_GuideActivity extends AppCompatActivity {

    // 메인 탭에서 최싱단 박물관 이름을 클릭하면 나오는 엑티비티

    Button btn , btn2;
    TextView txt1 , txt2 , txttest1;
    RecyclerView rcyv;
    Local_GuideRecyclerAdepter adepter;
    ArrayList<Main_item4> getMyList;
    List<Address> list = null;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_local__guide);

        txt2 = (TextView) findViewById(R.id.local_guide_title_text);
        txt2.setText(getIntent().getStringExtra("local_title"));

        txttest1 = (TextView) findViewById(R.id.textView5);
        final Geocoder geocoder = new Geocoder(this);
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
                Intent intent = new Intent(Local_GuideActivity.this , Save_ReViewActivity.class);
                startActivity(intent);
            }
        });

        // 지도 탭 접속 버튼입니다.

        btn = findViewById(R.id.button2);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Map으로 넘어가는 인텐트
               // Intent intent = new Intent(Local_GuideActivity.this , MapsActivity.class);
               // startActivity(intent);



                String str = txt2.getText().toString();
                try {
                    list = geocoder.getFromLocationName(
                            str, // 지역 이름
                            10); // 읽을 개수
                } catch (IOException e) {
                    e.printStackTrace();
                    Log.e("test","입출력 오류 - 서버에서 주소변환시 에러발생");
                }

                if (list != null) {
                    if (list.size() == 0) {
                        txttest1.setText("해당되는 주소 정보는 없습니다");
                    } else {
                        txttest1.setText("위도 : "+list.get(0).getLatitude()+"\n경도 : "+list.get(0).getLongitude());
                        //          list.get(0).getCountryName();  // 국가명
                        //          list.get(0).getLatitude();        // 위도
                        //          list.get(0).getLongitude();    // 경도
                    }
                }
                
                
            }
        });

        btn2 = findViewById(R.id.button3);
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Map으로 넘어가는 인텐트
                String str = txt2.getText().toString();
                try {
                    list = geocoder.getFromLocationName(
                            str, // 지역 이름
                            10); // 읽을 개수
                } catch (IOException e) {
                    e.printStackTrace();
                    Log.e("test","입출력 오류 - 서버에서 주소변환시 에러발생");
                }

                if (list != null) {
                    if (list.size() == 0) {
                        txttest1.setText("해당되는 주소 정보는 없습니다");
                    } else {
                        txttest1.setText("위도 : "+list.get(0).getLatitude()+"\n경도 : "+list.get(0).getLongitude());
                        //          list.get(0).getCountryName();  // 국가명
                        //          list.get(0).getLatitude();        // 위도
                        //          list.get(0).getLongitude();    // 경도
                    }
                }

                 Intent intent = new Intent(Local_GuideActivity.this , MapsActivity.class);
                 intent.putExtra("test1" , list.get(0).getLatitude());
                 intent.putExtra("test2" , list.get(0).getLongitude());
                 intent.putExtra("test3" , txt2.getText());
                 startActivity(intent);
            }
        });

    }
}