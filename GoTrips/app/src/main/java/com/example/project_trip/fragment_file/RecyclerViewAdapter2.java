package com.example.project_trip.fragment_file;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.project_trip.R;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewAdapter2 extends RecyclerView.Adapter<RecyclerViewAdapter2.MyViewHolder> {


    Context mContext1;
    List<Main_item2> miData1;
    Dialog myDialog;


    public RecyclerViewAdapter2(Context mContext1, List<Main_item2> miData1) {
        this.mContext1 = mContext1;
        this.miData1 = miData1;

    }



    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v;
        v = LayoutInflater.from(mContext1).inflate(R.layout.row2, parent, false);
        MyViewHolder myViewHolder = new MyViewHolder(v);

        return myViewHolder;

    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {

        holder.tv_list.setText(miData1.get(position).getName());
//        holder.tv_imglist.setImageResource(miData.get(position).getImg());

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
