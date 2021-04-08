package com.example.project_trip.fragment_file;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.project_trip.R;

import java.util.ArrayList;
import java.util.List;

import static android.content.ContentValues.TAG;


public class LocalFragment extends Fragment {


    private RecyclerView marylee;
    RecyclerViewAdapter2 rcvAd2;
    private List<Main_item> getMyList2;

    public LocalFragment(){

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View vv = inflater.inflate(R.layout.fragment_local, container, false);
        marylee = (RecyclerView) vv.findViewById(R.id.Local_recyclerView);
        rcvAd2 = new RecyclerViewAdapter2(getContext(), getMyList2);
        marylee.setLayoutManager(new LinearLayoutManager(getActivity()));
        marylee.setAdapter(rcvAd2);
        // 가로 모드
        // layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);

    return vv;
    }
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getMyList2 = new ArrayList<>();
        getMyList2.add(new Main_item("박물관입니다."));
        getMyList2.add(new Main_item("박물관입니다2."));
        getMyList2.add(new Main_item("박물관입니다3."));
        getMyList2.add(new Main_item("박물관입니다4."));
        getMyList2.add(new Main_item("박물관입니다5."));
        getMyList2.add(new Main_item("박물관입니다6."));
        getMyList2.add(new Main_item("박물관입니다7."));
        getMyList2.add(new Main_item("박물관입니다8."));
        getMyList2.add(new Main_item("박물관입니다9."));
        getMyList2.add(new Main_item("박물관입니다10."));
        getMyList2.add(new Main_item("박물관입니다11."));
        getMyList2.add(new Main_item("박물관입니다12."));



    }


//    ArrayList<Main_item2> getMyList() {
//        ArrayList<Main_item2> main_items = new ArrayList<>();
//
//        Main_item2 mi = new Main_item2();
//
//        mi.setList("박물관주소내용");
//        main_items.add(mi);
//
//        mi = new Main_item2();
//        mi.setList("박물관2");
//        main_items.add(mi);
//
//
//        return main_items;
//
//    }

}