package com.example.project_trip;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import android.os.Bundle;


import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.project_trip.R;
import com.example.project_trip.fragment_file.Main_item_from_show_local;
import com.example.project_trip.fragment_file.RecyclerViewAdapter_from_local_guide;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;


public class CusmaidFragment extends Fragment {


    // 개인맞춤 탭에 있는 프레그 먼트 입니다.
    myDBHelper myHelper;
    SQLiteDatabase sqlDB;
    TextView tv1 , txttest1 , txttest2;
    EditText edit1 , edit2;
    RecyclerView marylee , marylee2;
    RecyclerViewAdapter_from_local_guide rcvAd;
    List<Main_item_from_show_local> getMyList;
    Button btnSelect , btnInit;
    public CusmaidFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        ViewGroup vv = (ViewGroup) inflater.inflate(R.layout.fragment_cusmaid, container, false);
        marylee = (RecyclerView) vv.findViewById(R.id.Cusmaid_recyclerView);
        edit1 = vv.findViewById(R.id.edit1);
        edit2 = vv.findViewById(R.id.edit2);

        txttest1 = vv.findViewById(R.id.textView3);
        txttest2 = vv.findViewById(R.id.textView4);

        btnSelect = vv.findViewById(R.id.btnSelect);
        btnInit = vv.findViewById(R.id.button4);

        rcvAd = new RecyclerViewAdapter_from_local_guide(getContext(), getMyList);
        marylee.setLayoutManager(new LinearLayoutManager(getActivity()));
        marylee.setAdapter(rcvAd);

        myHelper = new myDBHelper(getContext());
        //조회 누를시
        btnSelect.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                sqlDB = myHelper.getReadableDatabase();
                Cursor cursor;
                cursor = sqlDB.rawQuery("SELECT * FROM groupTBL;", null);

                // 내부 DB에 입력한 데이터 추가 코드
                String a = edit1.getText().toString();
                String b = edit2.getText().toString();
                sqlDB = myHelper.getWritableDatabase();
                sqlDB.execSQL("INSERT INTO groupTBL VALUES ( '" + a + "' , '"+ b + "');");
//                sqlDB.close();
                Toast.makeText(getContext(), "입력됨",
                        Toast.LENGTH_SHORT).show();
                // 추가 코드 끝

                String dosi = "도시" + "\r\n" + "--------" + "\r\n";
                String gungu = "군구" + "\r\n" + "--------" + "\r\n";

                while (cursor.moveToNext()) {
                    dosi += cursor.getString(0) + "\r\n";
                    gungu += cursor.getString(1) + "\r\n";
                }

                txttest1.setText(dosi);
                txttest2.setText(gungu);

                cursor.close();
                sqlDB.close();
            }
        });

        //기록 초기화(따로 건드릴 부분 없습니다.)
        btnInit.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                sqlDB = myHelper.getWritableDatabase();
                myHelper.onUpgrade(sqlDB, 1, 2); // 인수는 아무거나 입력하면 됨.
                Toast.makeText(getContext(), "초기화됨",
                        Toast.LENGTH_SHORT).show();
                sqlDB.close();
            }
        });
        return vv;

    }
    //Sqlite 관련 부가코드
    public class myDBHelper extends SQLiteOpenHelper {
        public myDBHelper(Context context) {
            super(context, "groupDB", null, 1);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL("CREATE TABLE  groupTBL ( dosi CHAR(20) , gungu CHAR(20));");
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS groupTBL");
            onCreate(db);
        }
    }

    //sqlite 관련 부가코드 끝


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getMyList = new ArrayList<>();
        getMyList.add(new Main_item_from_show_local("경기도 화성 박물관"));
        getMyList.add(new Main_item_from_show_local("제주도 돌하르방 박물관"));
        getMyList.add(new Main_item_from_show_local("제주도 한라산 박물관"));
        getMyList.add(new Main_item_from_show_local("제주도 올레길"));
        getMyList.add(new Main_item_from_show_local("경주 박물관"));

    }
}