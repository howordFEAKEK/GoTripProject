package com.example.project_trip.fragment_file;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.project_trip.AdapterFile.Local_GuideAdapter;
import com.example.project_trip.AdapterFile.Local_Guide_ByCustmaidAdepter;
import com.example.project_trip.ItemFile.Main_item4;
import com.example.project_trip.ItemFile.Main_item_from_show_local;
import com.example.project_trip.R;

import java.util.ArrayList;
import java.util.List;


public class CusmaidFragment extends Fragment {


    // 개인맞춤 탭에 있는 프레그 먼트 입니다.

    TextView tv1;
    RecyclerView marylee , marylee2;
    Local_Guide_ByCustmaidAdepter rcvAd;
    ArrayList<Main_item4> getMyList;
    public CusmaidFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        ViewGroup vv = (ViewGroup) inflater.inflate(R.layout.fragment_cusmaid, container, false);
        marylee = (RecyclerView) vv.findViewById(R.id.Cusmaid_recyclerView);
        rcvAd = new Local_Guide_ByCustmaidAdepter(getContext(), getMyList);
        marylee.setLayoutManager(new LinearLayoutManager(getActivity()));
        marylee.setAdapter(rcvAd);

        return vv;

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getMyList = new ArrayList<>();
        getMyList.add(new Main_item4("경기도 화성 박물관"));
        getMyList.add(new Main_item4("제주도 돌하르방 박물관"));
        getMyList.add(new Main_item4("제주도 한라산 박물관"));
        getMyList.add(new Main_item4("제주도 올레길"));
        getMyList.add(new Main_item4("경주 박물관"));

    }
}