package com.example.project_trip.fragment_file.dkssudkgptu;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.project_trip.R;
import com.example.project_trip.fragment_file.Detail_Region_Item;

import java.util.ArrayList;

public class Gyeongsangnam_do extends AppCompatActivity {

    
    RecyclerView recyclerView;
    KyeongGi_doAdepter kyeongGi_doAdepter;
    ArrayList<Detail_Region_Item> data;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kyeong_gi_do);
    
    data = new ArrayList<>();
        data.add(new Detail_Region_Item("거제시"));
        data.add(new Detail_Region_Item("김해시"));
        data.add(new Detail_Region_Item("마산시"));
        data.add(new Detail_Region_Item("밀양시"));
        data.add(new Detail_Region_Item("사천시"));
        data.add(new Detail_Region_Item("양산시"));
        data.add(new Detail_Region_Item("진주시"));
        data.add(new Detail_Region_Item("진해시"));
        data.add(new Detail_Region_Item("창원시"));
        data.add(new Detail_Region_Item("통영시"));
        data.add(new Detail_Region_Item("거창군"));
        data.add(new Detail_Region_Item("고성군"));
        data.add(new Detail_Region_Item("남해군"));
        data.add(new Detail_Region_Item("산청군"));
        data.add(new Detail_Region_Item("의령군"));
        data.add(new Detail_Region_Item("창녕군"));
        data.add(new Detail_Region_Item("하동군"));
        data.add(new Detail_Region_Item("함안군"));
        data.add(new Detail_Region_Item("함양군"));
        data.add(new Detail_Region_Item("합천군"));



    recyclerView = findViewById(R.id.local_select_rcview2);
    kyeongGi_doAdepter = new KyeongGi_doAdepter(this , data);
    recyclerView.setLayoutManager(new GridLayoutManager(this , 3));
    recyclerView.setAdapter(kyeongGi_doAdepter);

    
    }
}