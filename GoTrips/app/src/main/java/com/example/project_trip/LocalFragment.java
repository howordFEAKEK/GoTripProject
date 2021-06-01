package com.example.project_trip;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.annotation.UiThread;
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
import android.widget.TextView;
import android.widget.Toast;

import com.example.project_trip.R;
import com.example.project_trip.fragment_file.Local_SelectedActivity;
import com.example.project_trip.fragment_file.Main_item_from_show_local;
import com.example.project_trip.fragment_file.RecyclerViewAdapter_from_cusmaid_list;
import com.example.project_trip.fragment_file.RecyclerViewAdapter_from_local_guide;
import com.example.project_trip.fragment_file.RecyclerViewAdapter_from_show_local;
import com.squareup.otto.Subscribe;

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
import java.util.List;


public class LocalFragment extends Fragment {

    // 위치 탭에있는 프레그 먼트 입니다.

    private RecyclerView marylee;
    RecyclerViewAdapter_from_show_local rcvAd2;
    private List<Main_item_from_show_local> getMyList2;
    String test1 , test2;
    TextView tv_local_selected;
    Getter getter = new Getter();
    Cutter cutter = new Cutter();

    public LocalFragment(){

    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d("checkMainFragment" , "LocalFragment onResume");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {



        Log.d("checkMainFragment" , "LocalFragment onCreateView");

        View vv = inflater.inflate(R.layout.fragment_local, container, false);

        tv_local_selected = vv.findViewById(R.id.title_tv_mainfragment);
        marylee = (RecyclerView) vv.findViewById(R.id.Local_recyclerView);
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
                Log.d("주소22", addresscut[1]);
                Log.d("주소33", addresscut[2]);
               String gpsgetLat = addresscut[1];
               String gpsgetLon = addresscut[2];

                Local_Data_List.sett_sido = gpsgetLat;
                Local_Data_List.sett_gungo = gpsgetLon;
               String gpspluslocal = gpsgetLat + " " + gpsgetLon;
               tv_local_selected.setText(gpsgetLat + gpsgetLon);

                // 테스트 시작
                try {
                    String val = null;
                    String fileName = Environment.getExternalStorageDirectory().getAbsolutePath() + "/" + gpsgetLat + " " + gpsgetLon + ".txt";
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

                            Local_Data_List.local_tour_data_list = new ArrayList<>();
                            for (int i = 0; i < target.length; i++) {
                                Main_item_from_show_local item = new Main_item_from_show_local();
                                item.sido_name = gpsgetLat; // 시, 도
                                item.gungu_name = gpsgetLon; // 군, 구
                                item.tour_location = gpspluslocal;
                                item.tour_title = target[i];
                                Local_Data_List.local_tour_data_list.add(item);
                                //getMyList2.add(new Main_item_from_show_local(target[i]));


                            }
                            rcvAd2 = new RecyclerViewAdapter_from_show_local(getContext(), Local_Data_List.local_tour_data_list);
                            marylee.setLayoutManager(new LinearLayoutManager(getActivity()));
                            marylee.setAdapter(rcvAd2);
                    }catch(IOException e){
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

                        Local_Data_List.local_tour_data_list = new ArrayList<>();
                        for (int i = 0; i < target.length; i++) {
                            Main_item_from_show_local item = new Main_item_from_show_local();
                            item.sido_name = gpsgetLat; // 시, 도
                            item.gungu_name = gpsgetLon; // 군, 구
                            item.tour_location = gpspluslocal;
                            item.tour_title = target[i];
                            Local_Data_List.local_tour_data_list.add(item);
                            //getMyList2.add(new Main_item_from_show_local(target[i]));

                            rcvAd2 = new RecyclerViewAdapter_from_show_local(getContext(), Local_Data_List.local_tour_data_list);
                            marylee.setLayoutManager(new LinearLayoutManager(getActivity()));
                            marylee.setAdapter(rcvAd2);
                        }

//                    }
//                }).start();
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                }


                // 테스트 끝

            }
            //GPS 끝





        // 가로 모드
        // layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        
        
        //최상단 위치 텍스트 클릭 이벤트


        tv_local_selected.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity() , Local_SelectedActivity.class);
                startActivityForResult(intent , 0);
            }
        });


        BusProvider.getInstance().register(this);   // 이벤트 버스
        return vv;
    }

    // 이벤트 버스 데이터 가져오기
    @Subscribe
    public void FinishLoad(PushEvent mPushEvent) {

        String name11 = mPushEvent.getName3();

        String name = mPushEvent.getName(); // 시, 도
        String name2 = mPushEvent.getName2(); // 군, 구

        String loca = name + " " + name2; // 시도 + 군구

        Local_Data_List.sett_sido = name; // 시, 도
        Local_Data_List.sett_gungo = name2; // 군, 구

        tv_local_selected.setText(name11);


        if (tv_local_selected.getText().toString().equals(name + " " + name2)) {

//            try {
//                test1 = getter.apiGetter(name, name2);
//                test2 = cutter.apiCutter(test1, "BResNm");
//
//                String str = test2;
//                String[] target = str.split("\n");
//                getMyList2 = new ArrayList<>();
//                Local_Data_List.local_tour_data_list = new ArrayList<>();
//                for (int i = 0; i < target.length; i++) {
//                    Main_item_from_show_local item = new Main_item_from_show_local();
//                    item.sido_name = name; // 시, 도
//                    item.gungu_name = name2; // 군, 구
//                    item.tour_location = loca;
//                    item.tour_title = target[i];
//                    Local_Data_List.local_tour_data_list.add(item);
//                    //getMyList2.add(new Main_item_from_show_local(target[i]));
//                }
//
//            } catch (IOException e) {
//                e.printStackTrace();
//            }

            // 테스트 시작
            try {
            String val = null;
            String fileName = Environment.getExternalStorageDirectory().getAbsolutePath()+"/"+name + " " + name2+".txt";
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
                    getMyList2 = new ArrayList<>();
                    Local_Data_List.local_tour_data_list = new ArrayList<>();
                    for (int i = 0; i < target.length; i++) {
                        Main_item_from_show_local item = new Main_item_from_show_local();
                        item.sido_name = name; // 시, 도
                        item.gungu_name = name2; // 군, 구
                        item.tour_location = loca;
                        item.tour_title = target[i];
                        Local_Data_List.local_tour_data_list.add(item);
                        //getMyList2.add(new Main_item_from_show_local(target[i]));

                        rcvAd2 = new RecyclerViewAdapter_from_show_local(getContext(), Local_Data_List.local_tour_data_list);
                        marylee.setLayoutManager(new LinearLayoutManager(getActivity()));
                        marylee.setAdapter(rcvAd2);
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
                        .append("&" + URLEncoder.encode("SIDO", "UTF-8") + "=" + URLEncoder.encode(name, "UTF-8")); /* 시도 */
                urlBuilder.append(
                        "&" + URLEncoder.encode("GUNGU", "UTF-8") + "=" + URLEncoder.encode(name2, "UTF-8")); /* 시군구 */
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
                         getMyList2 = new ArrayList<>();
                         Local_Data_List.local_tour_data_list = new ArrayList<>();
                for (int i = 0; i < target.length; i++) {
                    Main_item_from_show_local item = new Main_item_from_show_local();
                    item.sido_name = name; // 시, 도
                    item.gungu_name = name2; // 군, 구
                    item.tour_location = loca;
                    item.tour_title = target[i];
                    Local_Data_List.local_tour_data_list.add(item);
                    //getMyList2.add(new Main_item_from_show_local(target[i]));

                    rcvAd2 = new RecyclerViewAdapter_from_show_local(getContext(), Local_Data_List.local_tour_data_list);
                    marylee.setLayoutManager(new LinearLayoutManager(getActivity()));
                    marylee.setAdapter(rcvAd2);
                }

//                    }
//                }).start();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }


            // 테스트 끝







        }


    }//...이벤트 버스끝

    @Override
    public void onDestroy() {
        super.onDestroy();
        BusProvider.getInstance().unregister(this);
        Log.d("checkMainFragment" , "onDestroy");
    }

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