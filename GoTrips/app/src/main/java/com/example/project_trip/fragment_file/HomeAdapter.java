package com.example.project_trip.fragment_file;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.project_trip.R;

import java.util.ArrayList;

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.milder> {
    
    // 현재 위치 리사이클 어댑터
  
    ArrayList<Main_item4> data;
    Context context;

    public HomeAdapter(ArrayList<Main_item4> data , Context context){

        this.data = data;
        this.context = context;

    }

    @NonNull
    @Override
    public milder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(context).inflate(R.layout.row3 , parent , false);
        milder holder = new milder(v);

        return holder;

    }

    @Override
    public void onBindViewHolder(@NonNull milder holder, int position) {

        holder.t1.setText(data.get(position).getList());

    }

    @Override
    public int getItemCount() {

        return data.size();
    }


    public class milder extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView t1;

        public milder(@NonNull View itemView) {
            super(itemView);

            this.t1 = itemView.findViewById(R.id.title_id11);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();
            Toast.makeText(context, "pstionr"+ position +1, Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(context , Local_detailed_selecttActivity.class);
//            intent.putExtra("Title",data.get(position).getList());
            context.startActivity(intent);
        }
    }
}


