package com.example.project_trip.fragment_file;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.project_trip.R;

import java.util.ArrayList;

public class Local_GuideActivity extends AppCompatActivity {
    Button btn;
    TextView txt;
    RecyclerView rcyv;
    Local_GuideRecyclerAdepter adepter;
    ArrayList<Main_item4> getMyList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_local__guide);

        rcyv = (RecyclerView)findViewById(R.id.local_guide_recyclerview);
        rcyv.setLayoutManager(new LinearLayoutManager(this));


        getMyList = new ArrayList<>();
        getMyList.add(new Main_item4("리뷰입니다1"));
        getMyList.add(new Main_item4("리뷰입니다2"));
        getMyList.add(new Main_item4("리뷰입니다3"));
        getMyList.add(new Main_item4("리뷰입니다4"));
        getMyList.add(new Main_item4("리뷰입니다5"));
        getMyList.add(new Main_item4("리뷰입니다6"));
        getMyList.add(new Main_item4("리뷰입니다7"));
        getMyList.add(new Main_item4("리뷰입니다8"));
        getMyList.add(new Main_item4("리뷰입니다9"));


        adepter = new Local_GuideRecyclerAdepter(getMyList , this);
        rcyv.setAdapter(adepter);



        txt = findViewById(R.id.set_review_text);
        txt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Local_GuideActivity.this ,ReViewActivity.class);
                startActivity(intent);
            }
        });

        btn = findViewById(R.id.button2);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Local_GuideActivity.this , MapsActivity.class);
                startActivity(intent);
            }
        });

    }
}