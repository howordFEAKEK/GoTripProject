package com.example.project_trip.fragment_file.dkssudkgptu;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.project_trip.R;
import com.example.project_trip.fragment_file.Detail_Region_Item;

import java.util.ArrayList;

public class Gyeongsangbuk_do extends AppCompatActivity {

    
    RecyclerView recyclerView;
    KyeongGi_doAdepter kyeongGi_doAdepter;
    ArrayList<Detail_Region_Item> data;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kyeong_gi_do);
    
    data = new ArrayList<>();
        data.add(new Detail_Region_Item("경산시"));
        data.add(new Detail_Region_Item("경주시"));
        data.add(new Detail_Region_Item("구미시"));
        data.add(new Detail_Region_Item("김천시"));
        data.add(new Detail_Region_Item("문경시"));
        data.add(new Detail_Region_Item("상주시"));
        data.add(new Detail_Region_Item("안동시"));
        data.add(new Detail_Region_Item("영주시"));
        data.add(new Detail_Region_Item("영천시"));
        data.add(new Detail_Region_Item("포항시"));
        data.add(new Detail_Region_Item("고령군"));
        data.add(new Detail_Region_Item("군위군"));
        data.add(new Detail_Region_Item("봉화군"));
        data.add(new Detail_Region_Item("성주군"));
        data.add(new Detail_Region_Item("영덕군"));
        data.add(new Detail_Region_Item("영양군"));
        data.add(new Detail_Region_Item("예천군"));
        data.add(new Detail_Region_Item("울릉군"));
        data.add(new Detail_Region_Item("울진군"));
        data.add(new Detail_Region_Item("의성군"));
        data.add(new Detail_Region_Item("청도군"));
        data.add(new Detail_Region_Item("청송군"));
        data.add(new Detail_Region_Item("칠곡군"));






    recyclerView = findViewById(R.id.local_select_rcview2);
    kyeongGi_doAdepter = new KyeongGi_doAdepter(this , data);
    recyclerView.setLayoutManager(new GridLayoutManager(this , 3));
    recyclerView.setAdapter(kyeongGi_doAdepter);

    
    }
}