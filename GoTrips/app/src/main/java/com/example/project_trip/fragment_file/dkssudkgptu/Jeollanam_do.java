package com.example.project_trip.fragment_file.dkssudkgptu;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.project_trip.R;
import com.example.project_trip.fragment_file.Detail_Region_Item;

import java.util.ArrayList;

public class Jeollanam_do extends AppCompatActivity {

    
    RecyclerView recyclerView;
    KyeongGi_doAdepter kyeongGi_doAdepter;
    ArrayList<Detail_Region_Item> data;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kyeong_gi_do);
    
    data = new ArrayList<>();
        data.add(new Detail_Region_Item("광양시"));
        data.add(new Detail_Region_Item("나주시"));
        data.add(new Detail_Region_Item("목포시"));
        data.add(new Detail_Region_Item("순천시"));
        data.add(new Detail_Region_Item("여수시"));
        data.add(new Detail_Region_Item("강진군"));
        data.add(new Detail_Region_Item("고흥군"));
        data.add(new Detail_Region_Item("곡성군"));
        data.add(new Detail_Region_Item("구례군"));
        data.add(new Detail_Region_Item("담양군"));
        data.add(new Detail_Region_Item("무안군"));
        data.add(new Detail_Region_Item("보성군"));
        data.add(new Detail_Region_Item("신안군"));
        data.add(new Detail_Region_Item("영광군"));
        data.add(new Detail_Region_Item("영암군"));
        data.add(new Detail_Region_Item("완도군"));
        data.add(new Detail_Region_Item("장성군"));
        data.add(new Detail_Region_Item("장흥군"));
        data.add(new Detail_Region_Item("진도군"));
        data.add(new Detail_Region_Item("함평군"));
        data.add(new Detail_Region_Item("해남군"));
        data.add(new Detail_Region_Item("화순군"));






    recyclerView = findViewById(R.id.local_select_rcview2);
    kyeongGi_doAdepter = new KyeongGi_doAdepter(this , data);
    recyclerView.setLayoutManager(new GridLayoutManager(this , 3));
    recyclerView.setAdapter(kyeongGi_doAdepter);

    
    }
}