package com.example.project_trip.fragment_file.dkssudkgptu;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.project_trip.R;
import com.example.project_trip.fragment_file.Detail_Region_Item;

import java.util.ArrayList;

public class Gangwon_do extends AppCompatActivity {

    
    RecyclerView recyclerView;
    KyeongGi_doAdepter kyeongGi_doAdepter;
    ArrayList<Detail_Region_Item> data;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kyeong_gi_do);
    
    data = new ArrayList<>();
        data.add(new Detail_Region_Item("강릉시"));
        data.add(new Detail_Region_Item("동해시"));
        data.add(new Detail_Region_Item("삼척시"));
        data.add(new Detail_Region_Item("속초시"));
        data.add(new Detail_Region_Item("원주시"));
        data.add(new Detail_Region_Item("춘천시"));
        data.add(new Detail_Region_Item("태백시"));
        data.add(new Detail_Region_Item("고성군"));
        data.add(new Detail_Region_Item("양구군"));
        data.add(new Detail_Region_Item("양양군"));
        data.add(new Detail_Region_Item("영월군"));
        data.add(new Detail_Region_Item("인제군"));
        data.add(new Detail_Region_Item("정선군"));
        data.add(new Detail_Region_Item("철원군"));
        data.add(new Detail_Region_Item("평창군"));
        data.add(new Detail_Region_Item("홍천군"));
        data.add(new Detail_Region_Item("화천군"));
        data.add(new Detail_Region_Item("횡성군"));





    recyclerView = findViewById(R.id.local_select_rcview2);
    kyeongGi_doAdepter = new KyeongGi_doAdepter(this , data);
    recyclerView.setLayoutManager(new GridLayoutManager(this , 3));
    recyclerView.setAdapter(kyeongGi_doAdepter);

    
    }
}