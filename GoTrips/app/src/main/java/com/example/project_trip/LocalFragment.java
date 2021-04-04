package com.example.project_trip;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.EventListener;


public class LocalFragment extends Fragment {

    ListView lv;

    SearchView searchVIew;
    ArrayAdapter<String> adapter;
    String[] data = {"1" , "2" , "3" , "4" };
    public LocalFragment(){


    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_local, container, false);
        lv = (ListView) view.findViewById(R.id.Local_List);
        adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1,data);
        lv.setAdapter(adapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getContext(),"테스트"+position, Toast.LENGTH_SHORT).show();

            }
        });
        return view;

    }
}