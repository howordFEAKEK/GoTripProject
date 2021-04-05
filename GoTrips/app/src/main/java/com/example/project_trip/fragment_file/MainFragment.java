package com.example.project_trip.fragment_file;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.project_trip.RecyclerView_item_file.Main_item;
import com.example.project_trip.R;
import com.example.project_trip.RecyclerViewAdapter;

import java.util.ArrayList;


public class MainFragment extends Fragment {

    View v;
    RecyclerView marylee , marylee2;
    RecyclerViewAdapter rcvAd;

    public MainFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
//        // Inflate the layout for this fragment
//       v = inflater.inflate(R.layout.fragment_main, container, false);
//       myrecyclerview = (RecyclerView) v.findViewById(R.id.recycler_view);
//        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
//        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
//        myrecyclerview.setLayoutManager(layoutManager);
//       RecyclerViewAdapter recyclerViewAdapter = new RecyclerViewAdapter(getContext(),lstContext);
//
//       myrecyclerview.setAdapter(recyclerViewAdapter);
//        return v;
        ViewGroup vv = (ViewGroup) inflater.inflate(R.layout.fragment_main, container, false);
        marylee = vv.findViewById(R.id.recycler_view);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        
        // 가로 모드
        // layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL); 
        marylee.setLayoutManager(layoutManager);

        rcvAd = new RecyclerViewAdapter(this, getMyList());
        marylee.setAdapter(rcvAd);

        marylee2 = vv.findViewById(R.id.recycler_view2);

        LinearLayoutManager layoutManager2 = new LinearLayoutManager(getContext());

        // 가로 모드
         layoutManager2.setOrientation(LinearLayoutManager.HORIZONTAL);
        marylee2.setLayoutManager(layoutManager2);

        rcvAd = new RecyclerViewAdapter(this, getMyList2());
        marylee2.setAdapter(rcvAd);





        return vv;

    }

    private ArrayList<Main_item> getMyList() {

        ArrayList<Main_item> main_items = new ArrayList<>();

        Main_item mi = new Main_item();

        mi.setList("제발 되거라");
        mi.setImg(R.drawable.abc);
        main_items.add(mi);

        mi = new Main_item();
        mi.setList("제발 되거라2");
        main_items.add(mi);


        return main_items;
    }

    private ArrayList<Main_item> getMyList2() {

        ArrayList<Main_item> main_items = new ArrayList<>();

        Main_item mi = new Main_item();

        mi.setList("이거도 되냐?");
        mi.setImg(R.drawable.abc);
        main_items.add(mi);

        mi = new Main_item();
        mi.setList("진짜 되냐???/2");
        main_items.add(mi);


        return main_items;
    }



    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);




//        lstContext = new ArrayList<>();
//        lstContext.add(new Main_item(" 일번마 등장!"));
//        lstContext.add(new Main_item(" 이번마 등장!"));
//        lstContext.add(new Main_item(" 삼번마 등장!"));
//        lstContext.add(new Main_item(" 사번마 등장!"));
//        lstContext.add(new Main_item(" 오번마 등장!"));
//        lstContext.add(new Main_item(" 육번마 등장!"));
    }


}