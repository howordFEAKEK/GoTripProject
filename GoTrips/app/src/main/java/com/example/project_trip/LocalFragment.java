package com.example.project_trip;

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

import com.example.project_trip.R;
import com.example.project_trip.fragment_file.Local_SelectedActivity;
import com.example.project_trip.fragment_file.Main_item_from_show_local;
import com.example.project_trip.fragment_file.RecyclerViewAdapter_from_local_guide;
import com.example.project_trip.fragment_file.RecyclerViewAdapter_from_show_local;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class LocalFragment extends Fragment {

    // 위치 탭에있는 프레그 먼트 입니다.

    private RecyclerView marylee;
    RecyclerViewAdapter_from_show_local rcvAd2;
    private List<Main_item_from_show_local> getMyList2;
    String test1 , test2;
    TextView tv_local_selected;
    Getter getter = new Getter();
    Cutter cutter = new Cutter();

    public LocalFragment(){

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View vv = inflater.inflate(R.layout.fragment_local, container, false);
        marylee = (RecyclerView) vv.findViewById(R.id.Local_recyclerView);

        // 가로 모드
        // layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        
        
        //최상단 위치 텍스트 클릭 이벤트

        tv_local_selected = vv.findViewById(R.id.title_tv_mainfragment);
        tv_local_selected.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity() , Local_SelectedActivity.class);
                startActivityForResult(intent , 0);
            }
        });



        return vv;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


        String name = data.getStringExtra("name");
        String name2 = data.getStringExtra("data_name2");
        String name3 = name.concat(name2);

        tv_local_selected.setText(name +" " + name2);


        if(tv_local_selected.getText().toString().equals(name +" " + name2))  {

            try {
                test1 = getter.apiGetter(name, name2);
                test2 = cutter.apiCutter(test1, "BResNm");

                String str = test2;
                String[] target = str.split("\n");
                getMyList2 = new ArrayList<>();
                for(int i = 0; i < target.length; i++){
                    getMyList2.add(new Main_item_from_show_local(target[i]));
                }
                
            } catch (IOException e) {
                e.printStackTrace();
            }

            rcvAd2 = new RecyclerViewAdapter_from_show_local(getContext(), getMyList2);
            marylee.setLayoutManager(new LinearLayoutManager(getActivity()));
            marylee.setAdapter(rcvAd2);

        }

    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


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