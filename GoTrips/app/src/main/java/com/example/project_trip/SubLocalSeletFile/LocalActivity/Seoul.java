package com.example.project_trip.SubLocalSeletFile.LocalActivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.project_trip.R;
import com.example.project_trip.SubLocalSeletFile.LocalAdapter.SeoulAdapter;
import com.example.project_trip.ItemFile.Detail_Region_Item;

import java.util.ArrayList;

public class Seoul extends AppCompatActivity {

    RecyclerView recyclerView;
    SeoulAdapter seoulAdapter;
    ArrayList<Detail_Region_Item> mdata1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seoul);

        mdata1 = new ArrayList<>();
        mdata1.add(new Detail_Region_Item("강남구"));
        mdata1.add(new Detail_Region_Item("은평구"));
        mdata1.add(new Detail_Region_Item("영등포구"));

        recyclerView = findViewById(R.id.local_select_rcview1);
        seoulAdapter = new SeoulAdapter(this , mdata1);
        recyclerView.setLayoutManager(new GridLayoutManager(this,3));
        recyclerView.setAdapter(seoulAdapter);
    }
}