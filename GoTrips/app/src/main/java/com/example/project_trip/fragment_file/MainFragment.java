package com.example.project_trip.fragment_file;

import android.content.Intent;
import android.nfc.Tag;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.project_trip.R;

import java.util.ArrayList;
import java.util.List;

import static android.content.ContentValues.TAG;


public class MainFragment extends Fragment {

    View v;
    RecyclerView marylee , marylee2 , marylee3;
    RecyclerViewAdapter rcvAd;
    RecyclerViewAdapter3 rcvAd3;
    List<Main_item3> getMyList;
    List<Main_item> getMyList1;
    public MainFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        ViewGroup vv = (ViewGroup) inflater.inflate(R.layout.fragment_main, container, false);


        marylee = (RecyclerView) vv.findViewById(R.id.recycler_view);
        rcvAd3 = new RecyclerViewAdapter3(getContext(), getMyList);
        marylee.setLayoutManager(new LinearLayoutManager(getActivity()));
        marylee.setAdapter(rcvAd3);


        marylee2 = vv.findViewById(R.id.recycler_view2);
        LinearLayoutManager layoutManager2 = new LinearLayoutManager(getContext());
        // 가로 모드
        layoutManager2.setOrientation(LinearLayoutManager.HORIZONTAL);
        rcvAd = new RecyclerViewAdapter(getContext(), getMyList1);
        marylee2.setLayoutManager(layoutManager2);

        marylee2.setAdapter(rcvAd);


        marylee3 = vv.findViewById(R.id.recycler_view3);
        LinearLayoutManager layoutManager3 = new LinearLayoutManager(getContext());
        // 가로 모드
        layoutManager3.setOrientation(LinearLayoutManager.HORIZONTAL);
        rcvAd = new RecyclerViewAdapter(getContext(), getMyList1);
        marylee3.setLayoutManager(layoutManager3);
        marylee3.setAdapter(rcvAd);


        return vv;

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getMyList1 = new ArrayList<>();
        getMyList1.add(new Main_item("주간 리뷰"));
        getMyList1.add(new Main_item(""));
        getMyList1.add(new Main_item(""));
        getMyList1.add(new Main_item(""));
        getMyList1.add(new Main_item(""));
        getMyList1.add(new Main_item(""));
        getMyList1.add(new Main_item(""));
        getMyList1.add(new Main_item(""));


        getMyList = new ArrayList<>();
        getMyList.add(new Main_item3("박물관입니다."));
        getMyList.add(new Main_item3("박물관입니다2."));
        getMyList.add(new Main_item3("박물관입니다3."));
        getMyList.add(new Main_item3("박물관입니다4."));
        getMyList.add(new Main_item3("박물관입니다5."));
        getMyList.add(new Main_item3("박물관입니다6."));
        getMyList.add(new Main_item3("박물관입니다7."));
        getMyList.add(new Main_item3("박물관입니다8."));
        getMyList.add(new Main_item3("박물관입니다9."));
        getMyList.add(new Main_item3("박물관입니다10."));
        getMyList.add(new Main_item3("박물관입니다11."));
        getMyList.add(new Main_item3("박물관입니다12."));


    }


}