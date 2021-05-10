package com.example.project_trip.fragment_file.dkssudkgptu;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.project_trip.R;
import com.example.project_trip.fragment_file.Detail_Region_Item;

import java.util.ArrayList;

public class Chung_cheong_namdo extends AppCompatActivity {

    
    RecyclerView recyclerView;
    KyeongGi_doAdepter kyeongGi_doAdepter;
    ArrayList<Detail_Region_Item> data;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kyeong_gi_do);
    
    data = new ArrayList<>();
        data.add(new Detail_Region_Item("계룡시"));
        data.add(new Detail_Region_Item("공주시"));
        data.add(new Detail_Region_Item("논산시"));
        data.add(new Detail_Region_Item("보령시"));
        data.add(new Detail_Region_Item("서산시"));
        data.add(new Detail_Region_Item("아산시"));
        data.add(new Detail_Region_Item("천안시"));
        data.add(new Detail_Region_Item("금산군"));
        data.add(new Detail_Region_Item("당진군"));
        data.add(new Detail_Region_Item("부여군"));
        data.add(new Detail_Region_Item("서천군"));
        data.add(new Detail_Region_Item("연기군"));
        data.add(new Detail_Region_Item("예산군"));
        data.add(new Detail_Region_Item("청양군"));
        data.add(new Detail_Region_Item("태안군"));
        data.add(new Detail_Region_Item("홍성군"));




    recyclerView = findViewById(R.id.local_select_rcview2);
    kyeongGi_doAdepter = new KyeongGi_doAdepter(this , data);
    recyclerView.setLayoutManager(new GridLayoutManager(this , 3));
    recyclerView.setAdapter(kyeongGi_doAdepter);

    
    }
}