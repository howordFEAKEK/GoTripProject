package com.example.project_trip.fragment_file;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.project_trip.R;
import com.example.project_trip.fragment_file.dkssudkgptu.KyeongGi_do;
import com.example.project_trip.fragment_file.dkssudkgptu.Seoul;

import java.util.ArrayList;

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.milder> {
    
    // 현재 위치 리사이클 어댑터

    ArrayList<Main_item4> data;
    Context context;

    UpdateRecyclerVIew updateRecyclerVIew;
    Activity activity;
    boolean check = true;
    boolean select = true;

    public HomeAdapter(ArrayList<Main_item4> data  ,Context context){

        this.data = data;
        this.context = context;



    }

    @NonNull
    @Override
    public milder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.row3 , parent , false);
        milder holder = new milder(v);

        return holder;

    }

    @Override
    public void onBindViewHolder(@NonNull milder holder, int position) {

        holder.t1.setText(data.get(position).getList());

        holder.t1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (position == 0) {
                    Intent intent = new Intent( context , Seoul.class);
                    context.startActivity(intent);

                }
                else if (position == 1) {
                    Intent intent = new Intent( context , KyeongGi_do.class);
                    context.startActivity(intent);

                }
            }
        });


    }

    @Override
    public int getItemCount() {

        return data.size();
    }


    public class milder extends RecyclerView.ViewHolder{

        TextView t1;

        public milder(@NonNull View itemView) {
            super(itemView);

            this.t1 = itemView.findViewById(R.id.title_id11);


        }


    }
}


