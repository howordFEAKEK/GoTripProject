package com.example.project_trip.fragment_file;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.project_trip.R;

import java.util.ArrayList;

public class Local_GuideActivity extends AppCompatActivity {

    // 메인 탭에서 최싱단 박물관 이름을 클릭하면 나오는 엑티비티

    Button btn;
    TextView txt1 , txt2 , txt3;
    RecyclerView rcyv;
    Local_GuideRecyclerAdepter adepter;
    ArrayList<Main_item_from_show_local> getMyList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_local__guide);

        txt2 = (TextView) findViewById(R.id.local_guide_title_text);
        txt2.setText(getIntent().getStringExtra("local_title"));
        txt3 = (TextView) findViewById(R.id.title_id);
        txt3.setText(getIntent().getStringExtra("local title"));
        // 중단의 리뷰 리스트입니다.

        rcyv = (RecyclerView)findViewById(R.id.local_guide_recyclerview);
        rcyv.setLayoutManager(new LinearLayoutManager(this));

//
//        getMyList = new ArrayList<>();
//        getMyList.add(new Main_item4("리뷰입니다1"));
//        getMyList.add(new Main_item4("리뷰입니다2"));
//        getMyList.add(new Main_item4("리뷰입니다3"));
//        getMyList.add(new Main_item4("리뷰입니다4"));
//        getMyList.add(new Main_item4("리뷰입니다5"));
//        getMyList.add(new Main_item4("리뷰입니다6"));
//        getMyList.add(new Main_item4("리뷰입니다7"));
//        getMyList.add(new Main_item4("리뷰입니다8"));
//        getMyList.add(new Main_item4("리뷰입니다9"));


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
                Intent intent = new Intent(Local_GuideActivity.this , MapsActivity.class);
                startActivity(intent);
            }
        });

    }
}