package com.example.project_trip.fragment_file;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.project_trip.AdapterFile.RecyclerViewAdapter_from_show_local;
import com.example.project_trip.ItemFile.Main_item_from_show_local;
import com.example.project_trip.R;

import java.util.ArrayList;
import java.util.List;


public class LocalFragment extends Fragment {

    // 위치 탭에있는 프레그 먼트 입니다.

    private RecyclerView marylee;
    RecyclerViewAdapter_from_show_local rcvAd2;
    private List<Main_item_from_show_local> getMyList2;

    public LocalFragment(){

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View vv = inflater.inflate(R.layout.fragment_local, container, false);
        marylee = (RecyclerView) vv.findViewById(R.id.Local_recyclerView);
        rcvAd2 = new RecyclerViewAdapter_from_show_local(getContext(), getMyList2);
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
        getMyList2.add(new Main_item_from_show_local("불당동 박물관"));
        getMyList2.add(new Main_item_from_show_local("아산시 과학 박물관"));
        getMyList2.add(new Main_item_from_show_local("독립박물관"));
        getMyList2.add(new Main_item_from_show_local("문화유산지"));
        getMyList2.add(new Main_item_from_show_local("미술 박물관"));
        getMyList2.add(new Main_item_from_show_local("공룡 박물관"));
        getMyList2.add(new Main_item_from_show_local("산꼭대기 절"));
        getMyList2.add(new Main_item_from_show_local("석굴암"));
        getMyList2.add(new Main_item_from_show_local("해인사"));
        getMyList2.add(new Main_item_from_show_local("장안문"));
        getMyList2.add(new Main_item_from_show_local("화성"));
        getMyList2.add(new Main_item_from_show_local("국보1호"));


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