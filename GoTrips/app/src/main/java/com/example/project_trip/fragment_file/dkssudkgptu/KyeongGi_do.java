package com.example.project_trip.fragment_file.dkssudkgptu;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.GridView;

import com.example.project_trip.R;
import com.example.project_trip.fragment_file.Detail_Region_Item;

import java.util.ArrayList;

public class KyeongGi_do extends AppCompatActivity {

    
    RecyclerView recyclerView;
    KyeongGi_doAdepter kyeongGi_doAdepter;
    ArrayList<Detail_Region_Item> data;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kyeong_gi_do);
    
    data = new ArrayList<>();
        data.add(new Detail_Region_Item("고양시"));
        data.add(new Detail_Region_Item("과천시"));
        data.add(new Detail_Region_Item("광명시"));
        data.add(new Detail_Region_Item("광주시"));
        data.add(new Detail_Region_Item("구리시"));
        data.add(new Detail_Region_Item("군포시"));
        data.add(new Detail_Region_Item("김포시"));
        data.add(new Detail_Region_Item("남양주시"));
        data.add(new Detail_Region_Item("동두천시"));
        data.add(new Detail_Region_Item("부천시"));
        data.add(new Detail_Region_Item("성남시"));
        data.add(new Detail_Region_Item("수원시"));
        data.add(new Detail_Region_Item("시흥시"));
        data.add(new Detail_Region_Item("안산시"));
        data.add(new Detail_Region_Item("안성시"));
        data.add(new Detail_Region_Item("안양시"));
        data.add(new Detail_Region_Item("양주시"));
        data.add(new Detail_Region_Item("오산시"));
        data.add(new Detail_Region_Item("용인시"));
        data.add(new Detail_Region_Item("의왕시"));
        data.add(new Detail_Region_Item("의정부시"));
        data.add(new Detail_Region_Item("이천시"));
        data.add(new Detail_Region_Item("파주시"));
        data.add(new Detail_Region_Item("평택시"));
        data.add(new Detail_Region_Item("포천시"));
        data.add(new Detail_Region_Item("하남시"));
        data.add(new Detail_Region_Item("화성시"));
        data.add(new Detail_Region_Item("가평군"));
        data.add(new Detail_Region_Item("양평군"));
        data.add(new Detail_Region_Item("여주군"));
        data.add(new Detail_Region_Item("연천군"));




    recyclerView = findViewById(R.id.local_select_rcview2);
    kyeongGi_doAdepter = new KyeongGi_doAdepter(this , data);
    recyclerView.setLayoutManager(new GridLayoutManager(this , 3));
    recyclerView.setAdapter(kyeongGi_doAdepter);

    
    }
}