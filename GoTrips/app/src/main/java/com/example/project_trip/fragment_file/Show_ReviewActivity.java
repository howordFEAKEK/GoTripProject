package com.example.project_trip.fragment_file;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.example.project_trip.R;

public class Show_ReviewActivity extends AppCompatActivity {

    // 박물관을 클릭해서 나오는 리뷰 또는 메인탭의 주간 리뷰 , 월간 리뷰의 리뷰를 클릭하면 나오는 리뷰 엑티비티
    
    TextView txt_Review_title , txt_Review_guide , txt_Review_local , txt_Review;
    Button btn_like , btn_hate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_review);

        txt_Review_title = (TextView) findViewById(R.id.show_review_title); //리뷰 제목
        txt_Review_guide = (TextView) findViewById(R.id.show_review_guide); //리뷰 관광지명
        txt_Review_local = (TextView) findViewById(R.id.show_review_local); //리뷰 지역
        txt_Review = (TextView) findViewById(R.id.show_review_review);      //리뷰 내용

        btn_like = (Button) findViewById(R.id.show_review_Like);            //좋아요 버튼
        btn_hate = (Button) findViewById(R.id.show_review_Hate);            //싫어요 버튼



        txt_Review_title.setText(getIntent().getStringExtra("List"));



    }
}