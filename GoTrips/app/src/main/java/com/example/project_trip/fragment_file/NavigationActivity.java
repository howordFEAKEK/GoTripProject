package com.example.project_trip.fragment_file;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.project_trip.R;

public class NavigationActivity extends AppCompatActivity {

    // 지도 버튼안의 네비게이션 버튼을 누르면 나오는 엑티비티
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation);
    }
}