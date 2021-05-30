package com.example.project_trip;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.StrictMode;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.project_trip.fragment_file.Local_SelectedActivity;
import com.example.project_trip.fragment_file.Main_item;
import com.example.project_trip.fragment_file.Main_item3;
import com.example.project_trip.fragment_file.Main_item_from_show_local;
import com.example.project_trip.fragment_file.RecyclerViewAdapter;
import com.example.project_trip.fragment_file.RecyclerViewAdapter_from_local_guide;
import com.example.project_trip.fragment_file.RecyclerViewAdapter_from_show_local;
//import com.squareup.otto.Subscribe;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;


public class MainFragment extends Fragment {

    // 메인 탭에 있는 프레그먼트입니다.

    private Activity activity;
    View v;
    RecyclerView marylee , marylee2 , marylee3;
    RecyclerViewAdapter rcvAd, rcvBd;
    RecyclerViewAdapter_from_local_guide rcvAd3;
    ArrayList<Main_item_from_show_local> getMyList = new ArrayList<>();
    List<Main_item> getMyList1 , getMyList2;

    TextView txt , tv_local_selected;
    String test1 , test2 , test3 , test4 , test5;
    Getter getter =new Getter();
    Cutter cutter = new Cutter();
    MainFragment mainFragment;


    public MainFragment() {
        // Required empty public constructor
    }




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        Log.d("checkMainFragment" , "MainFragment onCreateView");

        ViewGroup vv = (ViewGroup) inflater.inflate(R.layout.fragment_main, container, false);
       
        //API사용할때 팅기지 않게 하는거
        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }//...끝


        // 위치 선택 버튼
        tv_local_selected = vv.findViewById(R.id.title_tv_mainfragment);
        tv_local_selected.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity() , Local_SelectedActivity.class);
                startActivityForResult(intent , 0);
            }
        });// ... 위치선택 버튼 끝



        // 관광지 리스트
        marylee = (RecyclerView) vv.findViewById(R.id.recycler_view);
        // 최하단의 FinishLoad 메소드에서 이어짐


        // 월간 리뷰
        marylee2 = vv.findViewById(R.id.recycler_view2);
        LinearLayoutManager layoutManager2 = new LinearLayoutManager(getContext());
        layoutManager2.setOrientation(LinearLayoutManager.HORIZONTAL);//가로모드
        rcvAd = new RecyclerViewAdapter(getContext(), getMyList2);
        marylee2.setLayoutManager(layoutManager2);
        marylee2.setAdapter(rcvAd);//...월간 리뷰 끝

        // 주간 리뷰
        marylee3 = vv.findViewById(R.id.recycler_view3);
        LinearLayoutManager layoutManager3 = new LinearLayoutManager(getContext());
        // 가로 모드
        layoutManager3.setOrientation(LinearLayoutManager.HORIZONTAL);
        rcvBd = new RecyclerViewAdapter(getContext(), getMyList1);
        marylee3.setLayoutManager(layoutManager3);
        marylee3.setAdapter(rcvBd);//...주간 리뷰 끝

        //BusProvider.getInstance().register(this);


        return vv;

    }
/*
    @Subscribe
    public void FinishLoad(PushEvent mPushEvent) {
        Log.d("Event" , "EventBus");
        String name11 = mPushEvent.getName3();

        String name = mPushEvent.getName();
        String name2 = mPushEvent.getName2();

        tv_local_selected.setText(name11);


        if (tv_local_selected.getText().toString().equals(name + " " + name2)) {

            try {
                test1 = getter.apiGetter(name, name2);

                test2 = cutter.apiCutter(test1, "BResNm");
                test3 = cutter.apiCutter(test1, "CSido");
                test4 = cutter.apiCutter(test1, "DGungu");
                test5 = cutter.apiCutter(test1, "EPreSimpleDesc");

                String str = test2;
                String str2 = test3;
                String str3 = test4;
                String str4 = test5;

                String[] target = str.split("\n"); // 관광지명
                String[] target2 = str2.split("\n"); // 시도
                String[] target3 = str3.split("\n"); // 군구
                String[] target4 = str4.split("\n");

                Local_Data_List.local_data.clear();

                getMyList = new ArrayList<>();
                for (int i = 0; i < target.length; i++) {
                    getMyList.add(new Main_item_from_show_local(target[i]));
                    // 주소 넣기
                    String local_setItem_sido = target2[i];
                    String local_setItem_gungu = target3[i];
                    Local_Data_List.local_data.add(new Local_title_item(local_setItem_sido , local_setItem_gungu));


                }

            } catch (IOException e) {
                e.printStackTrace();
            }

            rcvAd3 = new RecyclerViewAdapter_from_local_guide(getContext(), getMyList);
            marylee.setLayoutManager(new LinearLayoutManager(getActivity()));
            marylee.setAdapter(rcvAd3);


        }
    }
*/


    @Override
    public void onDestroy() {
        super.onDestroy();
        //BusProvider.getInstance().unregister(this);
        Log.d("checkMainFragment" , "onDestroy");

    }
    
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getMyList1 = new ArrayList<>();
        getMyList1.add(new Main_item("주간 리뷰"));
        getMyList1.add(new Main_item("석굴암 가세요!"));
        getMyList1.add(new Main_item("해인사 가세요!"));
        getMyList1.add(new Main_item("화성도 가보세요!"));
        getMyList1.add(new Main_item("석굴암 안좋아요..."));
        getMyList1.add(new Main_item("화성 추천드려요!"));
        getMyList1.add(new Main_item("화성 정말 좋을까?"));
        getMyList1.add(new Main_item("최고의 박물관 아산 박물관"));

        getMyList2 = new ArrayList<>();
        getMyList2.add(new Main_item("월간 리뷰"));
        getMyList2.add(new Main_item("석굴암 가세요!"));
        getMyList2.add(new Main_item("화성 가지 마세요!"));
        getMyList2.add(new Main_item("화성을 왜 안가요!"));
        getMyList2.add(new Main_item("석굴암도 가자!"));

    }

    @Override
    public void onResume() {
        super.onResume();
//        Log.d("checkMainFragment" , "MainFragment onResume");
//        String name1 = tv_local_selected.getText().toString();
//        String name_target[] = name1.split(" ");
//        String sido1 = name_target[0];
//        String gungu1 = name_target[1];
//        Log.d("sido" , sido1 );
//        Log.d("gungu" , gungu1 );
//
//        if(onTimePickerSetListener != null){
//            onTimePickerSetListener.onTimePickerSet(sido1 , gungu1);
//        }

        // 인기 차트 요청하기
        new Thread() {
            @Override
            public void run() {
                DataOutputStream out;
                DataInputStream in;
                String msg = "POPCHART/list";
                String popchart = null;

                StringTokenizer st;
                String sign = null;
                String text = null;

                if (SocketManager2.socket == null){
                    //소켓이 없으면 진행 안 함
                    return;
                }

                try {
                    out = new DataOutputStream(SocketManager2.socket.getOutputStream());
                    in = new DataInputStream(SocketManager2.socket.getInputStream());
                    out.writeUTF(msg); // 인기차트 요청

                    popchart = in.readUTF();
                    st = new StringTokenizer(popchart, "/");
                    sign = st.nextToken();
                    text = st.nextToken();

                    Main_item_from_show_local popitem = null;

                    if (sign.equals("POPCHART")){
                        st = new StringTokenizer(text, "$");
                        int count = 0;
                        //getMyList.clear();
                        Local_Data_List.main_tour_data_list.clear();

                        // 가져온 것이 있으면
                        if (st.countTokens() > 1){
                            count = st.countTokens()/2;
                        }else {System.out.println("인기차트가 없습니다.");}

                        for (int i = 0; i < count; i ++){
                            popitem = new Main_item_from_show_local();
                            StringTokenizer stnext;
                            popitem.tour_title = st.nextToken(); // 관광지명
                            popitem.tour_location = st.nextToken(); // 지역

                            stnext = new StringTokenizer(popitem.tour_location, " ");
                            popitem.sido_name = stnext.nextToken(); // 시, 도
                            popitem.gungu_name = stnext.nextToken(); // 군, 구

                            Local_Data_List.main_tour_data_list.add(popitem);
                            //getMyList.add(popitem);
                            System.out.println("인기차트 동작" + i);
                        }

                    }else {
                        System.out.println("인기차트 실패");
                    }

                }catch (IOException e){

                }catch (Exception e){
                    // 마구잡이로 탭을 바꿨을 때, 인덱스바운드 뜨는 것 방지
                    System.out.println(e);
                }
                System.out.println(msg);

                String msg2 = "WEEKCHART/";
                String weekchart = null;

                StringTokenizer st2;
                String wsign = null;
                String wtext = null;

                msg2 = msg2 + Local_Data_List.sett_sido + " " + Local_Data_List.sett_gungo;

                if (SocketManager2.socket == null){
                    //소켓이 없으면 진행 안 함
                    return;
                }

                try {
                    out = new DataOutputStream(SocketManager2.socket.getOutputStream());
                    in = new DataInputStream(SocketManager2.socket.getInputStream());
                    out.writeUTF(msg2); // 인기차트 요청

                    weekchart = in.readUTF();
                    st2 = new StringTokenizer(weekchart, "/");
                    wsign = st2.nextToken();
                    wtext = st2.nextToken();

                    Main_item weekitem = null;

                    if (wsign.equals("WEEKCHART")){
                        st2 = new StringTokenizer(wtext, "$");
                        int count = 0;
                        //getMyList.clear();
                        Local_Data_List.main_review_week_list.clear();

                        // 가져온 것이 있으면
                        if (st2.countTokens() > 1){
                            count = st2.countTokens()/3;
                        }else {System.out.println("주간차트가 없습니다.");}

                        for (int i = 0; i < count; i ++){
                            weekitem = new Main_item();
                            StringTokenizer stnext;
                            weekitem.review_index_writername = st2.nextToken(); // 관광지명
                            weekitem.review_index_date = st2.nextToken(); // 지역
                            weekitem.review_index_title = st2.nextToken();

                            Local_Data_List.main_review_week_list.add(weekitem);
                            //getMyList.add(popitem);
                            System.out.println("주간차트 동작" + i);
                        }

                    }else {
                        System.out.println("주간차트 실패");
                    }

                }catch (IOException e){

                }catch (Exception e){
                    // 마구잡이로 탭을 바꿨을 때, 인덱스바운드 뜨는 것 방지
                    System.out.println(e);
                }
                System.out.println(msg2);

                String msg3 = "MONTHCHART/";
                String monthchart = null;

                StringTokenizer st3;
                String msign = null;
                String mtext = null;

                msg3 = msg3 + Local_Data_List.sett_sido + " " + Local_Data_List.sett_gungo;

                if (SocketManager2.socket == null){
                    //소켓이 없으면 진행 안 함
                    return;
                }

                try {
                    out = new DataOutputStream(SocketManager2.socket.getOutputStream());
                    in = new DataInputStream(SocketManager2.socket.getInputStream());
                    out.writeUTF(msg3); // 인기차트 요청

                    monthchart = in.readUTF();
                    st3 = new StringTokenizer(monthchart, "/");
                    msign = st3.nextToken();
                    mtext = st3.nextToken();

                    Main_item monthitem = null;

                    if (msign.equals("MONTHCHART")){
                        st3 = new StringTokenizer(mtext, "$");
                        int count = 0;
                        //getMyList.clear();
                        Local_Data_List.main_review_month_list.clear();

                        // 가져온 것이 있으면
                        if (st3.countTokens() > 1){
                            count = st3.countTokens()/3;
                        }else {System.out.println("월간차트가 없습니다.");}

                        for (int i = 0; i < count; i ++){
                            monthitem = new Main_item();
                            StringTokenizer stnext;
                            monthitem.review_index_writername = st3.nextToken(); // 관광지명
                            monthitem.review_index_date = st3.nextToken(); // 지역
                            monthitem.review_index_title = st3.nextToken();

                            Local_Data_List.main_review_month_list.add(monthitem);
                            //getMyList.add(popitem);
                            System.out.println("월간차트 동작" + monthitem.review_index_title);
                        }

                    }else {
                        System.out.println("월간차트 실패");
                    }

                }catch (IOException e){

                }catch (Exception e){
                    // 마구잡이로 탭을 바꿨을 때, 인덱스바운드 뜨는 것 방지
                    System.out.println(e);
                }
                System.out.println(msg3);


            }
        }.start();

        rcvAd3 = new RecyclerViewAdapter_from_local_guide(getContext(), Local_Data_List.main_tour_data_list);
        marylee.setLayoutManager(new LinearLayoutManager(getActivity()));
        marylee.setAdapter(rcvAd3);

        LinearLayoutManager layoutManager2 = new LinearLayoutManager(getContext());
        layoutManager2.setOrientation(LinearLayoutManager.HORIZONTAL);//가로모드
        rcvAd = new RecyclerViewAdapter(getContext(), Local_Data_List.main_review_month_list);
        marylee2.setLayoutManager(layoutManager2);
        marylee2.setAdapter(rcvAd);//...월간 리뷰 끝

        LinearLayoutManager layoutManager3 = new LinearLayoutManager(getContext());
        layoutManager3.setOrientation(LinearLayoutManager.HORIZONTAL);//가로모드
        rcvBd = new RecyclerViewAdapter(getContext(), Local_Data_List.main_review_week_list);
        marylee3.setLayoutManager(layoutManager3);
        marylee3.setAdapter(rcvBd);//...주간 리뷰 끝

    }

}