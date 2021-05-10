package com.example.project_trip.SubActivityFile;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.project_trip.AdapterFile.Detail_Ragion_Adapter;
import com.example.project_trip.AdapterFile.SubLocal_SelectAdapter;
import com.example.project_trip.R;
import com.example.project_trip.ItemFile.Detail_Region_Item;
import com.example.project_trip.ItemFile.Main_item4;

import java.util.ArrayList;

public class Local_SelectActivity extends AppCompatActivity {

    // 메인탭에서 현재위치를 클릭하면 나오는 화면의 엑티비티입니다.

    RecyclerView rcy , rcy2;
    SubLocal_SelectAdapter adapter;
    ArrayList<Main_item4> data;
    ArrayList<Detail_Region_Item> data2;
    Detail_Ragion_Adapter detail_ragion_adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        data = new ArrayList<>();
        data.add(new Main_item4("서울"));
        data.add(new Main_item4("경기"));
        data.add(new Main_item4("전라북도"));
        data.add(new Main_item4("전라남도"));
        data.add(new Main_item4("경상북도"));
        data.add(new Main_item4("경상남도"));
        data.add(new Main_item4("충청북도"));
        data.add(new Main_item4("충청남도"));
        data.add(new Main_item4("인천"));
        data.add(new Main_item4("부산"));
        data.add(new Main_item4("대전"));
        data.add(new Main_item4("강원도"));
        data.add(new Main_item4("대구"));
        data.add(new Main_item4("광주"));
        data.add(new Main_item4("세종"));

        rcy = (RecyclerView)findViewById(R.id.local_select_rcview);
        adapter = new SubLocal_SelectAdapter(data , this);
        rcy.setLayoutManager(new GridLayoutManager(this , 3));
        rcy.setAdapter(adapter);





    }

}