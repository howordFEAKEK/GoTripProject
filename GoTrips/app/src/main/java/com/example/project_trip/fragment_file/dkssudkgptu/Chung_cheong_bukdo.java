package com.example.project_trip.fragment_file.dkssudkgptu;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.project_trip.R;
import com.example.project_trip.fragment_file.Detail_Region_Item;

import java.util.ArrayList;

public class Chung_cheong_bukdo extends AppCompatActivity {

    
    RecyclerView recyclerView;
    KyeongGi_doAdepter kyeongGi_doAdepter;
    ArrayList<Detail_Region_Item> data;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kyeong_gi_do);
    
    data = new ArrayList<>();
        data.add(new Detail_Region_Item("제천시"));
        data.add(new Detail_Region_Item("청주시"));
        data.add(new Detail_Region_Item("충주시"));
        data.add(new Detail_Region_Item("괴산군"));
        data.add(new Detail_Region_Item("단양군"));
        data.add(new Detail_Region_Item("보은군"));
        data.add(new Detail_Region_Item("영동군"));
        data.add(new Detail_Region_Item("옥천군"));
        data.add(new Detail_Region_Item("음성군"));
        data.add(new Detail_Region_Item("증평군"));
        data.add(new Detail_Region_Item("진천군"));
        data.add(new Detail_Region_Item("청원군"));




    recyclerView = findViewById(R.id.local_select_rcview2);
    kyeongGi_doAdepter = new KyeongGi_doAdepter(this , data);
    recyclerView.setLayoutManager(new GridLayoutManager(this , 3));
    recyclerView.setAdapter(kyeongGi_doAdepter);

    
    }
}