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
import com.example.project_trip.fragment_file.dkssudkgptu.Busan_si;
import com.example.project_trip.fragment_file.dkssudkgptu.Chung_cheong_bukdo;
import com.example.project_trip.fragment_file.dkssudkgptu.Chung_cheong_namdo;
import com.example.project_trip.fragment_file.dkssudkgptu.Daegu_si;
import com.example.project_trip.fragment_file.dkssudkgptu.Daejeon_si;
import com.example.project_trip.fragment_file.dkssudkgptu.Gangwon_do;
import com.example.project_trip.fragment_file.dkssudkgptu.Gwangju_si;
import com.example.project_trip.fragment_file.dkssudkgptu.Gyeongsangbuk_do;
import com.example.project_trip.fragment_file.dkssudkgptu.Gyeongsangnam_do;
import com.example.project_trip.fragment_file.dkssudkgptu.Incheon_SI;
import com.example.project_trip.fragment_file.dkssudkgptu.Jeju_Island;
import com.example.project_trip.fragment_file.dkssudkgptu.Jeollabuk_do;
import com.example.project_trip.fragment_file.dkssudkgptu.Jeollanam_do;
import com.example.project_trip.fragment_file.dkssudkgptu.KyeongGi_do;
import com.example.project_trip.fragment_file.dkssudkgptu.Seoul;
import com.example.project_trip.fragment_file.dkssudkgptu.Ulsan_si;

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
                if (position == 1) {
                    Intent intent = new Intent( context , Seoul.class);
                    context.startActivity(intent);

                }
                else if (position == 2) {
                    Intent intent = new Intent( context , Incheon_SI.class);
                    context.startActivity(intent);

                }
                else if (position == 3) {
                    Intent intent = new Intent( context , Daejeon_si.class);
                    context.startActivity(intent);

                }
                else if (position == 4) {
                    Intent intent = new Intent( context , Gwangju_si.class);
                    context.startActivity(intent);

                }
                else if (position == 5) {
                    Intent intent = new Intent( context , Daegu_si.class);
                    context.startActivity(intent);

                }
                else if (position == 6) {
                    Intent intent = new Intent( context , Ulsan_si.class);
                    context.startActivity(intent);

                }
                else if (position == 7) {
                    Intent intent = new Intent( context , Busan_si.class);
                    context.startActivity(intent);

                }
                else if (position == 8) {
                    Intent intent = new Intent( context , KyeongGi_do.class);
                    context.startActivity(intent);

                }
                else if (position == 9) {
                    Intent intent = new Intent( context , Gangwon_do.class);
                    context.startActivity(intent);

                }
                else if (position == 10) {
                    Intent intent = new Intent( context , Chung_cheong_bukdo.class);
                    context.startActivity(intent);

                }
                else if (position == 11) {
                    Intent intent = new Intent( context , Chung_cheong_namdo.class);
                    context.startActivity(intent);

                }
                else if (position == 12) {
                    Intent intent = new Intent( context , Jeollabuk_do.class);
                    context.startActivity(intent);

                }
                else if (position == 13) {
                    Intent intent = new Intent( context , Jeollanam_do.class);
                    context.startActivity(intent);

                }
                else if (position == 14) {
                    Intent intent = new Intent( context , Gyeongsangbuk_do.class);
                    context.startActivity(intent);

                }
                else if (position == 15) {
                    Intent intent = new Intent( context , Gyeongsangnam_do.class);
                    context.startActivity(intent);

                }
                else if (position == 16) {
                    Intent intent = new Intent( context , Jeju_Island.class);
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


