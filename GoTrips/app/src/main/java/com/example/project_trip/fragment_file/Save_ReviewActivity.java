package com.example.project_trip.fragment_file;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.project_trip.R;

public class Save_ReViewActivity extends AppCompatActivity {

    // 리뷰작성 버튼을 클릭하면 나오는 엑티비티


    TextView txt , txt_guide , txt_sido , txt_gungu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_save_review);

        txt = findViewById(R.id.save_click_text1);
        txt_guide = findViewById(R.id.set_Review_guide);
        txt_sido = findViewById(R.id.set_Review_sido);
        txt_gungu = findViewById(R.id.set_Review_gungu);
        
        // Local_guide에서 값 가져오기
        Intent intent = getIntent();
        txt_guide.setText(intent.getStringExtra("관광지명"));
        txt_sido.setText(intent.getStringExtra("시도"));
        txt_gungu.setText(intent.getStringExtra("군구"));
        
        
        // 저장하기 버튼 클릭 이벤트
        txt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder ad = new AlertDialog.Builder(Save_ReViewActivity.this);
                ad.setIcon(R.mipmap.ic_launcher);
                ad.setTitle("제목");
                ad.setMessage("추가 하겠습니까?");

                ad.setPositiveButton("취소", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

                ad.setNegativeButton("추가", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                ad.show();



            }
        });



    }
}