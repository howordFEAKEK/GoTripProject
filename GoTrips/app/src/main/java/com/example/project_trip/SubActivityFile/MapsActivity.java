package com.example.project_trip.SubActivityFile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.project_trip.R;

public class MapsActivity extends AppCompatActivity {

    // 지도 버튼을 클릭하면 나오는 지도 엑티비티
    
    TextView txt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        txt = findViewById(R.id.navigation_click_text);
        txt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MapsActivity.this , NavigationActivity.class);
                startActivity(intent);
            }
        });
    }
}