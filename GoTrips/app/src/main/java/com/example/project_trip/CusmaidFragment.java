package com.example.project_trip;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;

import android.app.AlertDialog;
import android.content.DialogInterface;


import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Environment;
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

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
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

    String vall1 ,vall2 ,vall3 , test1 , test2 , gpsgetLon , gpsgetLat , gpsgetterapi , gpscutterapi;

    Getter getter = new Getter();
    Cutter cutter = new Cutter();
    String gpspluslocal;
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
                String strCnt = "";

                while (cursor.moveToNext()) {
                    strNames += cursor.getString(0) + "\r\n";
                    strNumbers += cursor.getString(1) + "\r\n";
                    strCnt += cursor.getString(2) + "\r\n";
                }
                String[] buf = strNames.split("\r\n");
                String[] buf2 = strNumbers.split("\r\n");
                if (buf[0].isEmpty()) {
                    Toast.makeText(getContext(), "조회하신 관광지가 없습니다. 위치 탭으로 가셔서 위치 설정을 하신다음에 눌러주세요!", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(getContext(), "자주 조회하신 도시는 " + buf[0] + "입니다.\n자주 조회하신 군구는 " + buf2[0] + "입니다.", Toast.LENGTH_LONG).show();

                    vall1 = buf[0];
                    vall2 = buf2[0];
                    vall3 = vall1 + " " + vall2;
                    List<Cusmaid_title_item> sqltestdata = new ArrayList<>();
                    sqltestdata.add(new Cusmaid_title_item(vall1, vall2));
                    cusmaid_local_title.setText(vall3);

                    cursor.close();
                    sqlDB.close();
                    Log.d("시도", vall1);
                    Log.d("군구", vall2);
                    Log.d("합", vall3);
                    if (cusmaid_local_title.getText().toString().equals(vall1 + " " + vall2)) {


//                    try {
//                        String name = val;
//                        String name2 = val2;
//                        test1 = getter.apiGetter(name, name2);
//                        test2 = cutter.apiCutter(test1, "BResNm");
//                        String str = test2;
//                        String[] target = str.split("\n");
//                        Local_Data_List.cusmaid_tour_data_list = new ArrayList<>();
//                        for (int i = 0; i < target.length; i++) {
//
//                            Main_item_from_show_local item = new Main_item_from_show_local();
//                            item.sido_name = name; // 시, 도
//                            item.gungu_name = name2; // 군, 구
//                            item.tour_location = val3;
//                            item.tour_title = target[i];
//                            Local_Data_List.cusmaid_tour_data_list.add(item);
//                        }
//
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }
//                    rcvAd = new RecyclerViewAdapter_from_cusmaid_list(getContext(), Local_Data_List.cusmaid_tour_data_list);
//                    marylee.setLayoutManager(new LinearLayoutManager(getActivity()));
//                    marylee.setAdapter(rcvAd);

                        // 테스트 시작
                        try {
                            String val = null;
                            String fileName = Environment.getExternalStorageDirectory().getAbsolutePath() + "/" + vall1 + " " + vall2 + ".txt";
                            File file = new File(fileName);
                            boolean isExists = file.exists();

                            if (isExists) {
                                //파일이 있다면
                                Log.d("", "파일읽기");
                                try {
                                    StringBuffer strBuffer = new StringBuffer();

                                    InputStream is = new FileInputStream(fileName);
                                    BufferedReader reader = new BufferedReader(new InputStreamReader(is));
                                    String line = "";
                                    while ((line = reader.readLine()) != null) {
                                        strBuffer.append(line + "\n");
                                    }

                                    reader.close();
                                    is.close();
                                    val = strBuffer.toString();
                                    Log.d("read test", val);

                                    String test1111 = cutter.apiCutter(val, "BResNm");
                                    String str = test1111;
                                    String[] target = str.split("\n");

                                    Local_Data_List.cusmaid_tour_data_list = new ArrayList<>();
                                    for (int i = 0; i < target.length; i++) {
                                        Main_item_from_show_local item = new Main_item_from_show_local();
                                        item.sido_name = vall1; // 시, 도
                                        item.gungu_name = vall2; // 군, 구
                                        item.tour_location = vall3;
                                        item.tour_title = target[i];
                                        Local_Data_List.cusmaid_tour_data_list.add(item);
                                        //getMyList2.add(new Main_item_from_show_local(target[i]));

                                        rcvAd = new RecyclerViewAdapter_from_cusmaid_list(getContext(), Local_Data_List.cusmaid_tour_data_list);
                                        marylee.setLayoutManager(new LinearLayoutManager(getActivity()));
                                        marylee.setAdapter(rcvAd);
                                    }
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }


                            } else {
                                Log.d("", "파일쓰기");
                                //파일이 없다면
                                // 도시의 이름과 군구를 입력 받고 그에 해당하는 관광지를 전송함
                                StringBuilder urlBuilder = new StringBuilder(
                                        "http://openapi.tour.go.kr/openapi/service/TourismResourceService/getTourResourceList"); /* URL */
                                urlBuilder.append("?" + URLEncoder.encode("ServiceKey", "UTF-8")
                                        + "=HljdApr9dt8GOT0E0H87Ii2Ah7buUCv6SHh5AmjZJLwWZvRO9Ww4hgPgXehtDS3Ytn3hxKWk9J6sVS4MHdURJA%3D%3D"); /*
                                 * Service
                                 * Key
                                 */
                                urlBuilder
                                        .append("&" + URLEncoder.encode("SIDO", "UTF-8") + "=" + URLEncoder.encode(vall1, "UTF-8")); /* 시도 */
                                urlBuilder.append(
                                        "&" + URLEncoder.encode("GUNGU", "UTF-8") + "=" + URLEncoder.encode(vall2, "UTF-8")); /* 시군구 */
                                urlBuilder.append(
                                        "&" + URLEncoder.encode("RES_NM", "UTF-8") + "=" + URLEncoder.encode("", "UTF-8")); /* 관광지 */
                                URL url = new URL(urlBuilder.toString());
                                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                                conn.setRequestMethod("GET");
                                conn.setRequestProperty("Content-type", "application/json");
                                System.out.println("Response code: " + conn.getResponseCode());
                                BufferedReader rd;
                                if (conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
                                    rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                                } else {
                                    rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
                                }
                                StringBuilder sb = new StringBuilder();
                                String line;
                                while ((line = rd.readLine()) != null) {
                                    sb.append(line);
                                }

                                val = sb.toString();
                                rd.close();
                                conn.disconnect();

                                //파일 output stream 생성
                                FileOutputStream fos = new FileOutputStream(fileName);
                                //파일쓰기

                                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(fos));
                                writer.write(sb.toString());
                                writer.flush();
                                Log.d("write test", sb.toString());
                                String val1 = sb.toString();

                                writer.close();
                                fos.close();
//                new Thread(new Runnable() {
//                    @Override
//                    public void run() {
                                String test1111 = cutter.apiCutter(val1, "BResNm");
                                String str = test1111;
                                String[] target = str.split("\n");

                                Local_Data_List.cusmaid_tour_data_list = new ArrayList<>();
                                for (int i = 0; i < target.length; i++) {
                                    Main_item_from_show_local item = new Main_item_from_show_local();
                                    item.sido_name = vall1; // 시, 도
                                    item.gungu_name = vall2; // 군, 구
                                    item.tour_location = vall3;
                                    item.tour_title = target[i];
                                    Local_Data_List.cusmaid_tour_data_list.add(item);
                                    //getMyList2.add(new Main_item_from_show_local(target[i]));

                                    rcvAd = new RecyclerViewAdapter_from_cusmaid_list(getContext(), Local_Data_List.cusmaid_tour_data_list);
                                    marylee.setLayoutManager(new LinearLayoutManager(getActivity()));
                                    marylee.setAdapter(rcvAd);
                                }

//                    }
//                }).start();
                            }

                        } catch (IOException e) {
                            Toast.makeText(getContext(), "현재 조회된 지역이 없습니다. 지역을 먼저 설정하신 후 눌러주세요", Toast.LENGTH_SHORT).show();
                            e.printStackTrace();
                        }


                        // 테스트 끝


                    }
                }
            }
        });



        //GPS 사용
        int hasFineLocationPermission = ContextCompat.checkSelfPermission(getContext(),
                Manifest.permission.ACCESS_FINE_LOCATION);
        int hasCoarseLocationPermission = ContextCompat.checkSelfPermission(getContext(),
                Manifest.permission.ACCESS_COARSE_LOCATION);


        if (hasFineLocationPermission == PackageManager.PERMISSION_GRANTED &&
                hasCoarseLocationPermission == PackageManager.PERMISSION_GRANTED) {

            GpsTracker gpsTracker = new GpsTracker(getContext());

            double latitude = gpsTracker.getLatitude();
            double longitude = gpsTracker.getLongitude();

            String address = getCurrentAddress(latitude, longitude);


            Log.d("주소", address);
            String[] addresscut = address.split(" ");
            Log.d("주소2", addresscut[1]);
            Log.d("주소3", addresscut[2]);
            gpsgetLat = addresscut[1];
            gpsgetLon = addresscut[2];

            Local_Data_List.sett_sido = gpsgetLat;
            Local_Data_List.sett_gungo = gpsgetLon;
            gpspluslocal = gpsgetLat + " " + gpsgetLon;
            Toast.makeText(getContext(), "현재위치  " + gpspluslocal, Toast.LENGTH_LONG).show();
        }
        //GPS 끝
        btnGpsguide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(getContext(), "현재 지역은 " + gpspluslocal +"입니다" ,Toast.LENGTH_LONG).show();

                cusmaid_local_title.setText(gpspluslocal);

                Log.d("클릭리스너 안에 gpsgetLat", gpsgetLat);
                Log.d("클릭리스너 안에 gpsgetLot", gpsgetLon);

                // 테스트 시작
                try {
                    String val = null;
                    String fileName = Environment.getExternalStorageDirectory().getAbsolutePath()+"/"+gpsgetLat + " " + gpsgetLon+".txt";
                    File file = new File(fileName);
                    boolean isExists = file.exists();

                    if(isExists) {
                        //파일이 있다면
                        Log.d("","파일읽기");
                        try{
                            StringBuffer strBuffer = new StringBuffer();

                            InputStream is = new FileInputStream(fileName);
                            BufferedReader reader = new BufferedReader(new InputStreamReader(is));
                            String line="";
                            while((line=reader.readLine())!=null){
                                strBuffer.append(line+"\n");
                            }

                            reader.close();
                            is.close();
                            val=strBuffer.toString();
                            Log.d("read test",val);

                            String test1111 = cutter.apiCutter(val , "BResNm");
                            String str = test1111;
                            String[] target = str.split("\n");

                            Local_Data_List.cusmaid_tour_data_list = new ArrayList<>();
                            for (int i = 0; i < target.length; i++) {
                                Main_item_from_show_local item = new Main_item_from_show_local();
                                item.sido_name = gpsgetLat; // 시, 도
                                item.gungu_name = gpsgetLon; // 군, 구
                                item.tour_location = gpspluslocal;
                                item.tour_title = target[i];
                                Local_Data_List.cusmaid_tour_data_list.add(item);
                                //getMyList2.add(new Main_item_from_show_local(target[i]));

                                rcvAd = new RecyclerViewAdapter_from_cusmaid_list(getContext(), Local_Data_List.cusmaid_tour_data_list);
                                marylee.setLayoutManager(new LinearLayoutManager(getActivity()));
                                marylee.setAdapter(rcvAd);
                            }
                        }catch (IOException e){
                            e.printStackTrace();
                        }


                    }else {
                        Log.d("","파일쓰기");
                        //파일이 없다면
                        // 도시의 이름과 군구를 입력 받고 그에 해당하는 관광지를 전송함
                        StringBuilder urlBuilder = new StringBuilder(
                                "http://openapi.tour.go.kr/openapi/service/TourismResourceService/getTourResourceList"); /* URL */
                        urlBuilder.append("?" + URLEncoder.encode("ServiceKey", "UTF-8")
                                + "=HljdApr9dt8GOT0E0H87Ii2Ah7buUCv6SHh5AmjZJLwWZvRO9Ww4hgPgXehtDS3Ytn3hxKWk9J6sVS4MHdURJA%3D%3D"); /*
                         * Service
                         * Key
                         */
                        urlBuilder
                                .append("&" + URLEncoder.encode("SIDO", "UTF-8") + "=" + URLEncoder.encode(gpsgetLat, "UTF-8")); /* 시도 */
                        urlBuilder.append(
                                "&" + URLEncoder.encode("GUNGU", "UTF-8") + "=" + URLEncoder.encode(gpsgetLon, "UTF-8")); /* 시군구 */
                        urlBuilder.append(
                                "&" + URLEncoder.encode("RES_NM", "UTF-8") + "=" + URLEncoder.encode("", "UTF-8")); /* 관광지 */
                        URL url = new URL(urlBuilder.toString());
                        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                        conn.setRequestMethod("GET");
                        conn.setRequestProperty("Content-type", "application/json");
                        System.out.println("Response code: " + conn.getResponseCode());
                        BufferedReader rd;
                        if (conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
                            rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                        } else {
                            rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
                        }
                        StringBuilder sb = new StringBuilder();
                        String line;
                        while ((line = rd.readLine()) != null) {
                            sb.append(line);
                        }

                        val = sb.toString();
                        rd.close();
                        conn.disconnect();

                        //파일 output stream 생성
                        FileOutputStream fos = new FileOutputStream(fileName);
                        //파일쓰기

                        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(fos));
                        writer.write(sb.toString());
                        writer.flush();
                        Log.d("write test",sb.toString());
                        String val1 = sb.toString();

                        writer.close();
                        fos.close();
//                new Thread(new Runnable() {
//                    @Override
//                    public void run() {
                        String test1111 = cutter.apiCutter(val1 , "BResNm");
                        String str = test1111;
                        String[] target = str.split("\n");

                        Local_Data_List.cusmaid_tour_data_list = new ArrayList<>();
                        for (int i = 0; i < target.length; i++) {
                            Main_item_from_show_local item = new Main_item_from_show_local();
                            item.sido_name = gpsgetLat; // 시, 도
                            item.gungu_name = gpsgetLon; // 군, 구
                            item.tour_location = gpspluslocal;
                            item.tour_title = target[i];
                            Local_Data_List.cusmaid_tour_data_list.add(item);
                            //getMyList2.add(new Main_item_from_show_local(target[i]));

                            rcvAd = new RecyclerViewAdapter_from_cusmaid_list(getContext(), Local_Data_List.cusmaid_tour_data_list);
                            marylee.setLayoutManager(new LinearLayoutManager(getActivity()));
                            marylee.setAdapter(rcvAd);
                        }

//                    }
//                }).start();
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                }


                // 테스트 끝





//                try {
//                    gpsgetterapi = getter.apiGetter(gpsgetLat, gpsgetLon);
//                    gpscutterapi = cutter.apiCutter(gpsgetterapi, "BResNm");
//                    String str = gpscutterapi;
//                    String[] target = str.split("\n");
//                    getMyList = new ArrayList<>();
//                    Local_Data_List.cusmaid_tour_data_list = new ArrayList<>();
//                    for (int i = 0; i < target.length; i++) {
//
//                        Main_item_from_show_local item = new Main_item_from_show_local();
//                        item.sido_name = gpsgetLat; // 시, 도
//                        item.gungu_name = gpsgetLon; // 군, 구
//                        item.tour_location = gpspluslocal;
//                        item.tour_title = target[i];
//                        Local_Data_List.cusmaid_tour_data_list.add(item);
////
//                    }
//
//
//
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//                rcvAd = new RecyclerViewAdapter_from_cusmaid_list(getContext(), Local_Data_List.cusmaid_tour_data_list);
//                marylee.setLayoutManager(new LinearLayoutManager(getActivity()));
//                marylee.setAdapter(rcvAd);


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