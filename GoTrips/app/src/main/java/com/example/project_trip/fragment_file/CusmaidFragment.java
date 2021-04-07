package com.example.project_trip.fragment_file;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.project_trip.R;

import java.util.ArrayList;
import java.util.List;


public class CusmaidFragment extends Fragment {


    RecyclerView marylee , marylee2;
    RecyclerViewAdapter3 rcvAd;
    List<Main_item3> getMyList;
    public CusmaidFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        ViewGroup vv = (ViewGroup) inflater.inflate(R.layout.fragment_cusmaid, container, false);
        marylee = (RecyclerView) vv.findViewById(R.id.Cusmaid_recyclerView);
        rcvAd = new RecyclerViewAdapter3(getContext(), getMyList);
        marylee.setLayoutManager(new LinearLayoutManager(getActivity()));
        marylee.setAdapter(rcvAd);

        return vv;


    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

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
        getMyList.add(new Main_item3("박물관입니다."));



    }
}