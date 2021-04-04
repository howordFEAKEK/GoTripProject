package com.example.project_trip;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;


public class MainFragment extends Fragment {

    View v;
    private RecyclerView myrecyclerview;
    private List<Main_item> lstContext;

    public MainFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       v = inflater.inflate(R.layout.fragment_main, container, false);
       myrecyclerview = (RecyclerView) v.findViewById(R.id.recycler_view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        myrecyclerview.setLayoutManager(layoutManager);
       RecyclerViewAdapter recyclerViewAdapter = new RecyclerViewAdapter(getContext(),lstContext);

       myrecyclerview.setAdapter(recyclerViewAdapter);
        return v;

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        lstContext = new ArrayList<>();
        lstContext.add(new Main_item(" 일번마 등장!"));
        lstContext.add(new Main_item(" 이번마 등장!"));
        lstContext.add(new Main_item(" 삼번마 등장!"));
        lstContext.add(new Main_item(" 사번마 등장!"));
        lstContext.add(new Main_item(" 오번마 등장!"));
        lstContext.add(new Main_item(" 육번마 등장!"));
    }
}