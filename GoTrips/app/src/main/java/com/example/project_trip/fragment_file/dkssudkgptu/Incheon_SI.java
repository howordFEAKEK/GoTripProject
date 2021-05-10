package com.example.project_trip.fragment_file.dkssudkgptu;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.project_trip.R;
import com.example.project_trip.fragment_file.Detail_Region_Item;

import java.util.ArrayList;

public class Incheon_SI extends AppCompatActivity {

    
    RecyclerView recyclerView;
    Incheon_SIAdepter incheon_siAdepter;
    ArrayList<Detail_Region_Item> data;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_incheon_si);
    
    data = new ArrayList<>();
        data.add(new Detail_Region_Item("계양구"));
        data.add(new Detail_Region_Item("남구"));
        data.add(new Detail_Region_Item("남동구"));
        data.add(new Detail_Region_Item("동구"));
        data.add(new Detail_Region_Item("부평구"));
        data.add(new Detail_Region_Item("서구"));
        data.add(new Detail_Region_Item("연수구"));
        data.add(new Detail_Region_Item("중구"));
        data.add(new Detail_Region_Item("강화군"));
        data.add(new Detail_Region_Item("옹진군"));

    recyclerView = findViewById(R.id.local_select_rcview1);
    incheon_siAdepter = new Incheon_SIAdepter(this , data);
    recyclerView.setLayoutManager(new GridLayoutManager(this , 3));
    recyclerView.setAdapter(incheon_siAdepter);

    
    }
}