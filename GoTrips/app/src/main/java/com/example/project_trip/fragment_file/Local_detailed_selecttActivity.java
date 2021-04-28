package com.example.project_trip.fragment_file;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.project_trip.R;

import java.util.ArrayList;

public class Local_detailed_selecttActivity extends AppCompatActivity {
    
    // 현재 지역 설정을 클릭하면 나오는 세부 지역 설정 엑티비티
    
    
        RecyclerView rcyvdls;
        Local_detailed_selectAdepter adepter;
        ArrayList<Detailed_Local_Name_item> name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_local_detailed_selectt);

//        rcyvdls = (RecyclerView)findViewById(R.id.local_select_rcview);
//
//        GridLayoutManager gridLayoutManager = new GridLayoutManager(this , 3);
//        rcyvdls.setLayoutManager(gridLayoutManager);
//
//        name = new ArrayList<>();
//        name.add(new Detailed_Local_Name_item("서울"));
//        name.add(new Detailed_Local_Name_item("경기"));
//
//        adepter = new Local_detailed_selectAdepter(name,getApplicationContext());
//        rcyvdls.setAdapter(adepter);



    }
}