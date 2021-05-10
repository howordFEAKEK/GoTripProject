package com.example.project_trip.fragment_file;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.project_trip.R;

import java.util.ArrayList;
import java.util.List;


public class MainFragment extends Fragment {

    // 메인 탭에 있는 프레그먼트입니다.

    View v;
    RecyclerView marylee , marylee2 , marylee3;
    RecyclerViewAdapter rcvAd;
    RecyclerViewAdapter_from_local_guide rcvAd3;
    List<Main_item_from_show_local> getMyList;
    List<Main_item> getMyList1 , getMyList2;

    TextView txt;
    public MainFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        ViewGroup vv = (ViewGroup) inflater.inflate(R.layout.fragment_main, container, false);

        // 관광지 리스트
        
        marylee = (RecyclerView) vv.findViewById(R.id.recycler_view);
        rcvAd3 = new RecyclerViewAdapter_from_local_guide(getContext(), getMyList);
        marylee.setLayoutManager(new LinearLayoutManager(getActivity()));
        marylee.setAdapter(rcvAd3);

        
        // 위치 선택 버튼

        txt = vv.findViewById(R.id.local_setting);

        txt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getActivity() , HomeActivity.class);
                getActivity().startActivity(intent);
            }
        });


        // 월간 리뷰
        
        marylee2 = vv.findViewById(R.id.recycler_view2);
        LinearLayoutManager layoutManager2 = new LinearLayoutManager(getContext());
        // 가로 모드
        layoutManager2.setOrientation(LinearLayoutManager.HORIZONTAL);
        rcvAd = new RecyclerViewAdapter(getContext(), getMyList2);
        marylee2.setLayoutManager(layoutManager2);

        marylee2.setAdapter(rcvAd);

        // 주간 리뷰
        
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
        getMyList1.add(new Main_item("석굴암 가세요!"));
        getMyList1.add(new Main_item("해인사 가세요!"));
        getMyList1.add(new Main_item("화성도 가보세요!"));
        getMyList1.add(new Main_item("석굴암 안좋아요..."));
        getMyList1.add(new Main_item("화성 추천드려요!"));
        getMyList1.add(new Main_item("화성 정말 좋을까?"));
        getMyList1.add(new Main_item("최고의 박물관 아산 박물관"));

        getMyList2 = new ArrayList<>();
        getMyList2.add(new Main_item("월간 리뷰"));
        getMyList2.add(new Main_item("석굴암 가세요!"));
        getMyList2.add(new Main_item("화성 가지 마세요!"));
        getMyList2.add(new Main_item("화성을 왜 안가요!"));
        getMyList2.add(new Main_item("석굴암도 가자!"));



        getMyList = new ArrayList<>();
        getMyList.add(new Main_item_from_show_local("수원 화성"));
        getMyList.add(new Main_item_from_show_local("독립 박물관"));
        getMyList.add(new Main_item_from_show_local("해인사"));
        getMyList.add(new Main_item_from_show_local("아산 박물관"));
        getMyList.add(new Main_item_from_show_local("천안 장군 박물관"));
        getMyList.add(new Main_item_from_show_local("천안 미술관"));
        getMyList.add(new Main_item_from_show_local("충북 역사 박물관"));
        getMyList.add(new Main_item_from_show_local("산꼭대기 절"));
        getMyList.add(new Main_item_from_show_local("근현대 박물관"));
        getMyList.add(new Main_item_from_show_local("유적지"));
        getMyList.add(new Main_item_from_show_local("이순신 장군 동상"));
        getMyList.add(new Main_item_from_show_local("조선 박물관"));


    }


}