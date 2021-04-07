package com.example.project_trip.fragment_file;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.project_trip.R;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> {

    Context mContext;
    List<Main_item> miData;

    public RecyclerViewAdapter(Context mContext, List<Main_item> miData) {
        this.mContext = mContext;
        this.miData = miData;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {

       View v;
        v = LayoutInflater.from(mContext).inflate(R.layout.row, parent, false);
        final MyViewHolder myViewHolder1 = new MyViewHolder(v);

        return myViewHolder1;

    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {

        holder.tv_list.setText(miData.get(position).getList());
//        holder.tv_imglist.setImageResource(miData.get(position).getImg());

    }

    @Override
    public int getItemCount() {

        return miData.size();
    }


    // 뷰 홀더 클래스

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private TextView tv_list;
//        private ImageView tv_imglist;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            tv_list = itemView.findViewById(R.id.title_id111);
//            this.tv_imglist = itemView.findViewById(R.id.icon_rv);


        }


    }
}
