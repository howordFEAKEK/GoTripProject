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
import com.squareup.otto.Subscribe;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class MainFragment extends Fragment {

    // 메인 탭에 있는 프레그먼트입니다.

    private Activity activity;
    View v;
    RecyclerView marylee , marylee2 , marylee3;
    RecyclerViewAdapter rcvAd;
    RecyclerViewAdapter_from_local_guide rcvAd3;
    ArrayList<Main_item_from_show_local> getMyList;
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
        rcvAd = new RecyclerViewAdapter(getContext(), getMyList1);
        marylee3.setLayoutManager(layoutManager3);
        marylee3.setAdapter(rcvAd);//...주간 리뷰 끝

        BusProvider.getInstance().register(this);


        return vv;

    }

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



    @Override
    public void onDestroy() {
        super.onDestroy();
        BusProvider.getInstance().unregister(this);

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


    }




}