package com.example.project_trip.fragment_file;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.project_trip.R;

import java.util.List;

public class RecyclerViewAdapter_from_show_local extends RecyclerView.Adapter<RecyclerViewAdapter_from_show_local.MyViewHolder> {


    // 위치탭의 리사이클뷰 어댑터
    
    
    Context mContext1;
    List<Main_item_from_show_local> miData1;

    public RecyclerViewAdapter_from_show_local(Context mContext1, List<Main_item_from_show_local> miData1) {
        this.mContext1 = mContext1;
        this.miData1 = miData1;

    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v;
        v = LayoutInflater.from(mContext1).inflate(R.layout.row2, parent, false);
        MyViewHolder myViewHolder = new MyViewHolder(v );

        return myViewHolder;

    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {

        Main_item_from_show_local temp = miData1.get(position);

        holder.tv_list.setText(miData1.get(position).tour_title);

        holder.tv_list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(mContext1 , Local_GuideActivity.class);
                intent.putExtra("local_title" , temp.tour_title);
                intent.putExtra("sido", temp.sido_name);
                intent.putExtra("gungu", temp.gungu_name);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                mContext1.startActivity(intent);
            }
        });


    }

    @Override
    public int getItemCount() {

        return miData1.size();
    }

    
    // 뷰 홀더 클래스

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private LinearLayout item_id;
        private TextView tv_list;

//        private ImageView tv_imglist;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_list = (TextView) itemView.findViewById(R.id.title_id);
//            this.tv_imglist = itemView.findViewById(R.id.icon_rv);
        }


    }
}
