package com.example.project_trip.fragment_file;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.LinearLayout;

import com.example.project_trip.R;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity {

    // 메인탭에서 현재위치를 클릭하면 나오는 화면의 엑티비티입니다.

    RecyclerView rcy , rcy2;
    HomeAdapter adapter;
    ArrayList<Main_item4> data;
    ArrayList<Detail_Region_Item> data2;
    Detail_Ragion_Adapter detail_ragion_adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        data = new ArrayList<>();
        data.add(new Main_item4("시/도 선택"));
        data.add(new Main_item4("서울특별시"));
        data.add(new Main_item4("인천광역시"));
        data.add(new Main_item4("대전광역시"));
        data.add(new Main_item4("광주광역시"));
        data.add(new Main_item4("대구광역시"));
        data.add(new Main_item4("울산광역시"));
        data.add(new Main_item4("부산광역시"));
        data.add(new Main_item4("경기도"));
        data.add(new Main_item4("강원도"));
        data.add(new Main_item4("충청북도"));
        data.add(new Main_item4("충청남도"));
        data.add(new Main_item4("전라북도"));
        data.add(new Main_item4("전라남도"));
        data.add(new Main_item4("경상북도"));
        data.add(new Main_item4("경상남도"));
        data.add(new Main_item4("제주도"));

        rcy = (RecyclerView)findViewById(R.id.local_select_rcview);
        adapter = new HomeAdapter(data , this);
        rcy.setLayoutManager(new GridLayoutManager(this , 3));
        rcy.setAdapter(adapter);





    }

}