package com.example.project_trip.fragment_file;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.project_trip.R;

 public class Save_ReviewActivity extends AppCompatActivity {

    // 리뷰작성 버튼을 클릭하면 나오는 엑티비티


    TextView txt_save , txt_guide , txt_sido , txt_gungu;
    EditText etxt_set_review , etxt_set_title;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_save_review);

        txt_save = findViewById(R.id.save_click_text1);          // 맨 밑의 저장하기 글
        txt_guide = findViewById(R.id.set_Review_guide);    // 관광지 이름 텍스트
        txt_sido = findViewById(R.id.set_Review_sido);      // 시,도
        txt_gungu = findViewById(R.id.set_Review_gungu);    // 군,구

        etxt_set_title = findViewById(R.id.save_review_edittitle);//리뷰 제목 에딧텍스트
        etxt_set_review = findViewById(R.id.set_review_review); // 리뷰 내용 에딧텍스트
        // Local_guide에서 값 가져오기
        Intent intent = getIntent();
        txt_guide.setText(intent.getStringExtra("관광지명"));
        txt_sido.setText(intent.getStringExtra("시도"));
        txt_gungu.setText(intent.getStringExtra("군구"));

        String save_review_getlocal = txt_sido.getText().toString() + " " + txt_gungu.getText().toString(); // 00시 00구 이런 지역
        String save_review_getguide = txt_guide.getText().toString();           // 관광지 이름 받아오기


        
        // 저장하기 버튼 클릭 이벤트
        txt_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder ad = new AlertDialog.Builder(Save_ReviewActivity.this);
                ad.setIcon(R.mipmap.ic_launcher);
                ad.setTitle("제목");
                ad.setMessage("추가 하겠습니까?");

                ad.setPositiveButton("추가", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // 서버 통신 코드 작성...
                        String save_review_gettitle = etxt_set_title.getText().toString();      // 작성된 리뷰 제목 받아오기
                        String save_review_getreview = etxt_set_review.getText().toString();    // 작성된 리뷰 내용 받아오기


                        Log.d("제목" , save_review_gettitle);
                        Log.d("관광지명" , save_review_getguide);
                        Log.d("지역" , save_review_getlocal);
                        Log.d("내용" , save_review_getreview);

                    }

                });

                ad.setNegativeButton("취소", new DialogInterface.OnClickListener() {
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