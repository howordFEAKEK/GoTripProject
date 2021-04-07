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


public class CusmaidFragment extends Fragment {

    View v;
    RecyclerView marylee , marylee2;
    RecyclerViewAdapter3 rcvAd;

    public CusmaidFragment() {
        // Required empty public constructor
    }



//    @Override
//    public onCreate(Bundle savedInstanceState) {
//
//
//
//
//    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        ViewGroup vv = (ViewGroup) inflater.inflate(R.layout.fragment_cusmaid, container, false);
        marylee = vv.findViewById(R.id.Cusmaid_recyclerView);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());

        // 가로 모드
        // layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        marylee.setLayoutManager(layoutManager);

        rcvAd = new RecyclerViewAdapter3(this , getMyList());
        marylee.setAdapter(rcvAd);

        return vv;


    }

    ArrayList<Main_item3> getMyList() {
        ArrayList<Main_item3> main_items = new ArrayList<>();

        Main_item3 mi = new Main_item3();

        mi.setList("박물관주소내용");
        main_items.add(mi);
        mi.setList("박물관2");
        main_items.add(mi);
        mi.setList("박물관3");
        main_items.add(mi);
        mi.setList("박물관4");
        main_items.add(mi);
        mi.setList("박물관5");
        main_items.add(mi);
        mi.setList("박물관6");
        main_items.add(mi);
        mi.setList("박물관7");
        main_items.add(mi);
        mi.setList("박물관8");
        main_items.add(mi);

        for(int i = 0 ; i < main_items.size() ; i++){

           Main_item3 sum = main_items.get(i);
           System.out.println(sum);

        }
        return main_items;

    }
}