package com.example.project_trip;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> {


    Context mContext;
    List<Main_item> miData;

    public RecyclerViewAdapter(Context mContext, List<Main_item> miData){
        this.mContext = mContext;
        this.miData = miData;
    }


    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v ;
        v = LayoutInflater.from(mContext).inflate(R.layout.recycler_view_item_main,parent,false);
        MyViewHolder vHolder = new MyViewHolder(v);

        return vHolder;

    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        holder.tv_list.setText(miData.get(position).getList());
    }

    @Override
    public int getItemCount() {
        return miData.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        private TextView tv_list;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            tv_list = (TextView) itemView.findViewById(R.id.main_list_id);


        }
    }


}
