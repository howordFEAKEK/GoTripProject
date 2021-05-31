package com.example.project_trip;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;

import android.app.AlertDialog;
import android.content.DialogInterface;


import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.StrictMode;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.project_trip.R;
import com.example.project_trip.fragment_file.Main_item_from_show_local;
import com.example.project_trip.fragment_file.RecyclerViewAdapter_from_cusmaid_list;
import com.example.project_trip.fragment_file.RecyclerViewAdapter_from_local_guide;
import com.example.project_trip.fragment_file.RecyclerViewAdapter_from_show_local;

import org.w3c.dom.Text;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class CusmaidFragment extends Fragment {


    // 개인맞춤 탭에 있는 프레그 먼트 입니다.
    myDBHelper myHelper;
    SQLiteDatabase sqlDB;
    TextView tv1 , txttest1 , txttest2, cusmaid_local_title;
    EditText edit1 , edit2;
    RecyclerView marylee , marylee2;
    RecyclerViewAdapter_from_cusmaid_list rcvAd;
    private List<Main_item_from_show_local> getMyList = new ArrayList<>();
    private List<Cusmaid_title_item> getItemLocal;
    Button btnSelect , btnInit , btnGpsguide;

    String val ,val2 ,val3 , test1 , test2 , gpsgetLon , gpsgetLat , gpsgetterapi , gpscutterapi;

    Getter getter = new Getter();
    Cutter cutter = new Cutter();

    public CusmaidFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //API사용할때 팅기지 않게 하는거
        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }//...끝
        ViewGroup vv = (ViewGroup) inflater.inflate(R.layout.fragment_cusmaid, container, false);
        marylee = (RecyclerView) vv.findViewById(R.id.Cusmaid_recyclerView);

        cusmaid_local_title = vv.findViewById(R.id.cusmaid_local_title);
        btnSelect = vv.findViewById(R.id.btnSelect);
        btnInit = vv.findViewById(R.id.button4);
        btnGpsguide = vv.findViewById(R.id.button5);
//        rcvAd = new RecyclerViewAdapter_from_local_guide(getContext(), getMyList);
//        marylee.setLayoutManager(new LinearLayoutManager(getActivity()));
//        marylee.setAdapter(rcvAd);

        myHelper = new myDBHelper(getContext());
        //조회 누를시
        btnSelect.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                openDialog();

            }
//                sqlDB = myHelper.getReadableDatabase();
//                Cursor cursor;
//                cursor = sqlDB.rawQuery("SELECT * FROM groupTBL;", null);
//
//                // 내부 DB에 입력한 데이터 추가 코드
//
//                // 추가 코드 끝
//
//                String dosi = "도시" + "\r\n" + "--------" + "\r\n";
//                String gungu = "군구" + "\r\n" + "--------" + "\r\n";
//
//                while (cursor.moveToNext()) {
//                    dosi += cursor.getString(0) + "\r\n";
//                    gungu += cursor.getString(1) + "\r\n";
//                }
//
//                txttest1.setText(dosi);
//                txttest2.setText(gungu);
//
//
//                cursor.close();
//                sqlDB.close();
//            }
        });
        myHelper = new myDBHelper(getContext());
        // 2021-04-10 추가, 내부 DB이용 자주 검색한 지역 찾기
        btnInit.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                sqlDB = myHelper.getReadableDatabase();
                Cursor cursor;

                cursor = sqlDB.rawQuery("SELECT sido, gungu, count(gungu) cnt from groupTBL GROUP by gungu order by cnt DESC;", null);
                String strNames = "";
                String strNumbers = "";
                String strCnt="";

                while (cursor.moveToNext()) {
                    strNames += cursor.getString(0) + "\r\n";
                    strNumbers += cursor.getString(1) + "\r\n";
                    strCnt += cursor.getString(2) + "\r\n";
                }
                String[] buf = strNames.split("\r\n");
                String[] buf2 = strNumbers.split("\r\n");
                Toast.makeText(getContext(),"자주 조회하신 도시는 "+buf[0]+"입니다.\n자주 조회하신 군구는 "+buf2[0]+"입니다.", Toast.LENGTH_LONG).show();
                val = buf[0];
                val2 = buf2[0];
                val3 = val + " " +val2;
                List<Cusmaid_title_item> sqltestdata = new ArrayList<>();
                sqltestdata.add(new Cusmaid_title_item(val , val2));
                cusmaid_local_title.setText(val3);

                cursor.close();
                sqlDB.close();
                Log.d("시도" , val);
                Log.d("군구" , val2);
                Log.d("합" , val3);
                if(cusmaid_local_title.getText().toString().equals(val + " " + val2)) {


                    try {
                        String name = val;
                        String name2 = val2;
                        test1 = getter.apiGetter(name, name2);
                        test2 = cutter.apiCutter(test1, "BResNm");
                        String str = test2;
                        String[] target = str.split("\n");
                        Local_Data_List.cusmaid_tour_data_list = new ArrayList<>();
                        for (int i = 0; i < target.length; i++) {

                            Main_item_from_show_local item = new Main_item_from_show_local();
                            item.sido_name = name; // 시, 도
                            item.gungu_name = name2; // 군, 구
                            item.tour_location = val3;
                            item.tour_title = target[i];
                            Local_Data_List.cusmaid_tour_data_list.add(item);
                        }

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    rcvAd = new RecyclerViewAdapter_from_cusmaid_list(getContext(), Local_Data_List.cusmaid_tour_data_list);
                    marylee.setLayoutManager(new LinearLayoutManager(getActivity()));
                    marylee.setAdapter(rcvAd);
                }
            }

        });

        //GPS 사용
        GpsTracker gpsTracker = new GpsTracker(getContext());

        double latitude = gpsTracker.getLatitude();
        double longitude = gpsTracker.getLongitude();

        String address = getCurrentAddress(latitude, longitude);

        Toast.makeText(getContext(), "현재위치 \n위도 " + latitude + "\n경도 " + longitude, Toast.LENGTH_LONG).show();
        Log.d("주소",address);
        String[] addresscut = address.split(" ");
        Log.d("주소2" , addresscut[1]);
        Log.d("주소3" , addresscut[2]);
        gpsgetLat = addresscut[1];
        gpsgetLon = addresscut[2];
        Local_Data_List.sett_sido = gpsgetLat;
        Local_Data_List.sett_gungo = gpsgetLon;
        String gpspluslocal = gpsgetLat + " " + gpsgetLon;
        //GPS 끝
        btnGpsguide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(getContext(), "조사중 " + longitude, Toast.LENGTH_LONG).show();

                cusmaid_local_title.setText(gpspluslocal);

                Log.d("클릭리스너 안에 gpsgetLat", gpsgetLat);
                Log.d("클릭리스너 안에 gpsgetLot", gpsgetLon);
                try {
                    gpsgetterapi = getter.apiGetter(gpsgetLat, gpsgetLon);
                    gpscutterapi = cutter.apiCutter(gpsgetterapi, "BResNm");
                    String str = gpscutterapi;
                    String[] target = str.split("\n");
                    getMyList = new ArrayList<>();
                    Local_Data_List.cusmaid_tour_data_list = new ArrayList<>();
                    for (int i = 0; i < target.length; i++) {

                        Main_item_from_show_local item = new Main_item_from_show_local();
                        item.sido_name = gpsgetLat; // 시, 도
                        item.gungu_name = gpsgetLon; // 군, 구
                        item.tour_location = gpspluslocal;
                        item.tour_title = target[i];
                        Local_Data_List.cusmaid_tour_data_list.add(item);
//
                    }



                } catch (IOException e) {
                    e.printStackTrace();
                }
                rcvAd = new RecyclerViewAdapter_from_cusmaid_list(getContext(), Local_Data_List.cusmaid_tour_data_list);
                marylee.setLayoutManager(new LinearLayoutManager(getActivity()));
                marylee.setAdapter(rcvAd);


            }
            });


        return vv;

    }

    private void openDialog() {
        DialogFragment dialogFragment = new DialogFragment();

        dialogFragment.show(getFragmentManager(), "dialog");
    }

    //Sqlite 관련 부가코드
    public static class myDBHelper extends SQLiteOpenHelper {
        public myDBHelper(Context context) {
            super(context, "groupDB", null, 1);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL("CREATE TABLE  groupTBL ( sido CHAR(20) , gungu CHAR(20));");
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


    }
    //GPS
    public String getCurrentAddress(double latitude, double longitude) {

        //GPS를 주소로 변환
        Geocoder geocoder = new Geocoder(getContext());

        List<Address> addresses;

        try {

            addresses = geocoder.getFromLocation(
                    latitude,
                    longitude,
                    7);

            //address가 현재 주소


        } catch (IOException ioException) {
            //네트워크 문제
            Toast.makeText(getContext(), "지오코더 서비스 사용불가", Toast.LENGTH_LONG).show();
            return "지오코더 서비스 사용불가";
        } catch (IllegalArgumentException illegalArgumentException) {
            Toast.makeText(getContext(), "잘못된 GPS 좌표", Toast.LENGTH_LONG).show();
            return "잘못된 GPS 좌표";

        }


        if (addresses == null || addresses.size() == 0) {
            Toast.makeText(getContext(), "주소 미발견", Toast.LENGTH_LONG).show();
            return "주소 미발견";

        }

        Address address = addresses.get(0);
        return address.getAddressLine(0).toString() + "\n";

    }

}