package com.example.project_trip.SubActivityFile;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.example.project_trip.R;

public class Show_ReviewActivity extends AppCompatActivity {

    // 박물관을 클릭해서 나오는 리뷰 또는 메인탭의 주간 리뷰 , 월간 리뷰의 리뷰를 클릭하면 나오는 리뷰 엑티비티
    
    TextView tv1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_review);

        tv1 = (TextView) findViewById(R.id.show_revire_title);
        tv1.setText(getIntent().getStringExtra("List"));



    }
}