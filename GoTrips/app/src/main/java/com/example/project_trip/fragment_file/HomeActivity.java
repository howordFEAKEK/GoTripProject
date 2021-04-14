package com.example.project_trip.fragment_file;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.LinearLayout;

import com.example.project_trip.R;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity {

    RecyclerView rcy;
    HomeAdapter adapter;
    ArrayList<Main_item4> data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

//        rcy = (RecyclerView) findViewById(R.id.local_select_rcview);
////        rcy.setLayoutManager(new LinearLayoutManager(this));
//
//        adapter = new HomeAdapter(getMyList , getApplicationContext());
//        rcy.setAdapter(adapter);
//
//        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
//        rcy.setLayoutManager(linearLayoutManager);
//
//
//        getMyList = new ArrayList<>();
//        getMyList.add(new Main_item4("안녕하세요"));

        rcy = (RecyclerView)findViewById(R.id.local_select_rcview);
//        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
//        rcy.setLayoutManager(linearLayoutManager);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(this , 3);
        rcy.setLayoutManager(gridLayoutManager);


        data = new ArrayList<>();
        data.add(new Main_item4("서울"));
        data.add(new Main_item4("경기"));
        data.add(new Main_item4("전라북도"));
        data.add(new Main_item4("전라남도"));
        data.add(new Main_item4("경상북도"));
        data.add(new Main_item4("경상남도"));
        data.add(new Main_item4("충청북도"));
        data.add(new Main_item4("충청남도"));
        data.add(new Main_item4("인천"));
        data.add(new Main_item4("부산"));
        data.add(new Main_item4("대전"));
        data.add(new Main_item4("강원도"));
        data.add(new Main_item4("대구"));
        data.add(new Main_item4("광주"));
        data.add(new Main_item4("세종"));

        adapter = new HomeAdapter(data,getApplicationContext());
        rcy.setAdapter(adapter);


    }
    
}