package com.example.project_trip.fragment_file.dkssudkgptu;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.project_trip.R;
import com.example.project_trip.fragment_file.Detail_Region_Item;

import java.util.ArrayList;

public class Busan_si extends AppCompatActivity {


    RecyclerView recyclerView;
    KyeongGi_doAdepter kyeongGi_doAdepter;
    ArrayList<Detail_Region_Item> data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kyeong_gi_do);

    data = new ArrayList<>();
        data.add(new Detail_Region_Item("강서구"));
        data.add(new Detail_Region_Item("금정구"));
        data.add(new Detail_Region_Item("남구"));
        data.add(new Detail_Region_Item("동구"));
        data.add(new Detail_Region_Item("동래구"));
        data.add(new Detail_Region_Item("부산진구"));
        data.add(new Detail_Region_Item("사상구"));
        data.add(new Detail_Region_Item("사하구"));
        data.add(new Detail_Region_Item("서구"));
        data.add(new Detail_Region_Item("수영구"));
        data.add(new Detail_Region_Item("연제구"));
        data.add(new Detail_Region_Item("영도구"));
        data.add(new Detail_Region_Item("중구"));
        data.add(new Detail_Region_Item("해운대구"));
        data.add(new Detail_Region_Item("기장군"));
        

    recyclerView = findViewById(R.id.local_select_rcview2);
    kyeongGi_doAdepter = new KyeongGi_doAdepter(this , data);
    recyclerView.setLayoutManager(new GridLayoutManager(this , 3));
    recyclerView.setAdapter(kyeongGi_doAdepter);

    
    }
}