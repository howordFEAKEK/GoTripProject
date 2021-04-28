package com.example.project_trip.fragment_file;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.project_trip.R;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.zip.Inflater;

public class Local_detailed_selectAdepter extends RecyclerView.Adapter<Local_detailed_selectAdepter.MyViewHodler> {

    // 현재 위치에서 지역을 클릭하면 나오면 세부 지역 설정의 어댑터


    ArrayList<Detailed_Local_Name_item> data;
    Context context;

    public Local_detailed_selectAdepter(ArrayList<Detailed_Local_Name_item> data , Context context){
        this.data = data;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHodler onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.row3 , parent , false);

        MyViewHodler hodler = new MyViewHodler(v);

        return hodler;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHodler holder, int position) {

        holder.t1.setText(data.get(position).getList());

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class MyViewHodler extends RecyclerView.ViewHolder {

        TextView t1;

        public MyViewHodler(@NonNull View itemView) {
            super(itemView);
            this.t1 = itemView.findViewById(R.id.title_id11);

        }

    }
}
