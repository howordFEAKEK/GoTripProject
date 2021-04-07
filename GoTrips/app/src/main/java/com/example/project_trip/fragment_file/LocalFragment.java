package com.example.project_trip.fragment_file;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.project_trip.R;

import java.util.ArrayList;


public class LocalFragment extends Fragment {


    RecyclerView marylee , marylee2;
    RecyclerViewAdapter2 rcvAd;
    public LocalFragment(){


    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup vv = (ViewGroup) inflater.inflate(R.layout.fragment_local, container, false);
        marylee = vv.findViewById(R.id.Local_recyclerView);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());

        // 가로 모드
        // layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        marylee.setLayoutManager(layoutManager);

        rcvAd = new RecyclerViewAdapter2(this , getMyList());
        marylee.setAdapter(rcvAd);

    return vv;
    }

    ArrayList<Main_item2> getMyList() {
        ArrayList<Main_item2> main_items = new ArrayList<>();

        Main_item2 mi = new Main_item2();

        mi.setList("박물관주소내용");
        main_items.add(mi);

        mi = new Main_item2();
        mi.setList("박물관2");
        main_items.add(mi);


        return main_items;

    }
}