package com.example.project_trip.fragment_file.dkssudkgptu;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.project_trip.R;
import com.example.project_trip.fragment_file.Detail_Region_Item;

import java.util.ArrayList;

public class Jeollabuk_do extends AppCompatActivity {

    
    RecyclerView recyclerView;
    KyeongGi_doAdepter kyeongGi_doAdepter;
    ArrayList<Detail_Region_Item> data;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kyeong_gi_do);
    
    data = new ArrayList<>();
        data.add(new Detail_Region_Item("군산시"));
        data.add(new Detail_Region_Item("김제시"));
        data.add(new Detail_Region_Item("남원시"));
        data.add(new Detail_Region_Item("익산시"));
        data.add(new Detail_Region_Item("전주시"));
        data.add(new Detail_Region_Item("정읍시"));
        data.add(new Detail_Region_Item("고창군"));
        data.add(new Detail_Region_Item("무주군"));
        data.add(new Detail_Region_Item("부안군"));
        data.add(new Detail_Region_Item("순창군"));
        data.add(new Detail_Region_Item("완주군"));
        data.add(new Detail_Region_Item("임실군"));
        data.add(new Detail_Region_Item("장수군"));
        data.add(new Detail_Region_Item("진안군"));





    recyclerView = findViewById(R.id.local_select_rcview2);
    kyeongGi_doAdepter = new KyeongGi_doAdepter(this , data);
    recyclerView.setLayoutManager(new GridLayoutManager(this , 3));
    recyclerView.setAdapter(kyeongGi_doAdepter);

    
    }
}