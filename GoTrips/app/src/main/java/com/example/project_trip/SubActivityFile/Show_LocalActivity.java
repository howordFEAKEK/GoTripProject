package com.example.project_trip.SubActivityFile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.project_trip.R;

public class Show_LocalActivity extends AppCompatActivity {

    // 위치탭에서 박물관를 누르면 해당 박물관의 위치 정보가 나오는 화면입니다.

    TextView tv1 , tv2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_local);

        tv1 = (TextView)findViewById(R.id.show_local_title_text);
        tv1.setText(getIntent().getStringExtra("local_title"));

        tv2 = (TextView)findViewById(R.id.local_go_navi);
        tv2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Show_LocalActivity.this , NavigationActivity.class);
                startActivity(intent);
            }
        });


    }
}