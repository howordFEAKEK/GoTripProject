package com.example.project_trip.fragment_file.dkssudkgptu;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.project_trip.R;
import com.example.project_trip.fragment_file.Detail_Region_Item;

import java.util.ArrayList;

public class Seoul extends AppCompatActivity {

    RecyclerView recyclerView;
    SeoulAdapter seoulAdapter;
    ArrayList<Detail_Region_Item> mdata1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seoul);

        mdata1 = new ArrayList<>();
        mdata1.add(new Detail_Region_Item("강남구"));
        mdata1.add(new Detail_Region_Item("강동구"));
        mdata1.add(new Detail_Region_Item("강북구"));
        mdata1.add(new Detail_Region_Item("강서구"));
        mdata1.add(new Detail_Region_Item("관악구"));
        mdata1.add(new Detail_Region_Item("광진구"));
        mdata1.add(new Detail_Region_Item("구로구"));
        mdata1.add(new Detail_Region_Item("금천구"));
        mdata1.add(new Detail_Region_Item("노원구"));
        mdata1.add(new Detail_Region_Item("도봉구"));
        mdata1.add(new Detail_Region_Item("동대문구"));
        mdata1.add(new Detail_Region_Item("동작구"));
        mdata1.add(new Detail_Region_Item("마포구"));
        mdata1.add(new Detail_Region_Item("서대문구"));
        mdata1.add(new Detail_Region_Item("서초구"));
        mdata1.add(new Detail_Region_Item("성동구"));
        mdata1.add(new Detail_Region_Item("성북구"));
        mdata1.add(new Detail_Region_Item("송파구"));
        mdata1.add(new Detail_Region_Item("양천구"));
        mdata1.add(new Detail_Region_Item("영등포구"));
        mdata1.add(new Detail_Region_Item("용산구"));
        mdata1.add(new Detail_Region_Item("은평구"));
        mdata1.add(new Detail_Region_Item("종로구"));
        mdata1.add(new Detail_Region_Item("중구"));
        mdata1.add(new Detail_Region_Item("중랑구"));


        recyclerView = findViewById(R.id.local_select_rcview1);
        seoulAdapter = new SeoulAdapter(this , mdata1);
        recyclerView.setLayoutManager(new GridLayoutManager(this,3));
        recyclerView.setAdapter(seoulAdapter);
    }
}