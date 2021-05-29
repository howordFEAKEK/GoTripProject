package com.example.project_trip;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.annotation.UiThread;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.project_trip.R;
import com.example.project_trip.fragment_file.Local_SelectedActivity;
import com.example.project_trip.fragment_file.Main_item_from_show_local;
import com.example.project_trip.fragment_file.RecyclerViewAdapter_from_local_guide;
import com.example.project_trip.fragment_file.RecyclerViewAdapter_from_show_local;
import com.squareup.otto.Subscribe;

import java.io.IOException;
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
        marylee = (RecyclerView) vv.findViewById(R.id.Local_recyclerView);

        // 가로 모드
        // layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        
        
        //최상단 위치 텍스트 클릭 이벤트

        tv_local_selected = vv.findViewById(R.id.title_tv_mainfragment);
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

        tv_local_selected.setText(name11);


        if (tv_local_selected.getText().toString().equals(name + " " + name2)) {

            try {
                test1 = getter.apiGetter(name, name2);
                test2 = cutter.apiCutter(test1, "BResNm");

                String str = test2;
                String[] target = str.split("\n");
                getMyList2 = new ArrayList<>();
                for (int i = 0; i < target.length; i++) {
                    Main_item_from_show_local item = new Main_item_from_show_local();
                    item.sido_name = name; // 시, 도
                    item.gungu_name = name2; // 군, 구
                    item.tour_location = loca;
                    item.tour_title = target[i];
                    Local_Data_List.local_tour_data_list.add(item);
                    //getMyList2.add(new Main_item_from_show_local(target[i]));
                }

            } catch (IOException e) {
                e.printStackTrace();
            }

            rcvAd2 = new RecyclerViewAdapter_from_show_local(getContext(), Local_Data_List.local_tour_data_list);
            marylee.setLayoutManager(new LinearLayoutManager(getActivity()));
            marylee.setAdapter(rcvAd2);

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

}