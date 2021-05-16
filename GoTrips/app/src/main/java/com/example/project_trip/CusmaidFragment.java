package com.example.project_trip;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.project_trip.R;
import com.example.project_trip.fragment_file.Main_item_from_show_local;
import com.example.project_trip.fragment_file.RecyclerViewAdapter_from_local_guide;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;


public class CusmaidFragment extends Fragment {


    // 개인맞춤 탭에 있는 프레그 먼트 입니다.

    TextView tv1;
    RecyclerView marylee , marylee2;
    RecyclerViewAdapter_from_local_guide rcvAd;
    List<Main_item_from_show_local> getMyList;
    public CusmaidFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        ViewGroup vv = (ViewGroup) inflater.inflate(R.layout.fragment_cusmaid, container, false);
        marylee = (RecyclerView) vv.findViewById(R.id.Cusmaid_recyclerView);
        rcvAd = new RecyclerViewAdapter_from_local_guide(getContext(), getMyList);
        marylee.setLayoutManager(new LinearLayoutManager(getActivity()));
        marylee.setAdapter(rcvAd);

        return vv;

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getMyList = new ArrayList<>();
        getMyList.add(new Main_item_from_show_local("경기도 화성 박물관"));
        getMyList.add(new Main_item_from_show_local("제주도 돌하르방 박물관"));
        getMyList.add(new Main_item_from_show_local("제주도 한라산 박물관"));
        getMyList.add(new Main_item_from_show_local("제주도 올레길"));
        getMyList.add(new Main_item_from_show_local("경주 박물관"));

    }
}