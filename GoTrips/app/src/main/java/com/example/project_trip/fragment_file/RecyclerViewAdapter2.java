package com.example.project_trip.fragment_file;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.project_trip.R;

import java.util.ArrayList;

public class RecyclerViewAdapter2 extends RecyclerView.Adapter<RecyclerViewAdapter2.MyViewHolder> {


    LocalFragment mContext;
    ArrayList<Main_item2> miData;

    public RecyclerViewAdapter2(LocalFragment mContext, ArrayList<Main_item2> miData) {
        this.mContext = mContext;
        this.miData = miData;
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.row2, null);

        return new MyViewHolder(v);

    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        holder.tv_list.setText(miData.get(position).getList());
//        holder.tv_imglist.setImageResource(miData.get(position).getImg());

    }

    @Override
    public int getItemCount() {
        return miData.size();
    }

    
    // 뷰 홀더 클래스
    
    public static class MyViewHolder extends RecyclerView.ViewHolder{

        private TextView tv_list;
//        private ImageView tv_imglist;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            this.tv_list = itemView.findViewById(R.id.title_id);
//            this.tv_imglist = itemView.findViewById(R.id.icon_rv);

        }
    }


}
