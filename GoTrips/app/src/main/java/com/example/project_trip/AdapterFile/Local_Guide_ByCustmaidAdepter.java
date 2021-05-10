package com.example.project_trip.AdapterFile;

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
import com.example.project_trip.ItemFile.Main_item4;
import com.example.project_trip.SubActivityFile.Show_LocalActivity;
import com.example.project_trip.SubActivityFile.Show_ReviewActivity;

import java.util.ArrayList;

public class Local_Guide_ByCustmaidAdepter extends RecyclerView.Adapter<Local_Guide_ByCustmaidAdepter.MyViewHodler> {

    // 메인탭의 최상단에 있는 박물관 정보 리사이클뷰의 어댑터
    Context context;
    private ArrayList<Main_item4> data;


    public Local_Guide_ByCustmaidAdepter( Context context , ArrayList<Main_item4> data) {

        this.context = context;
        this.data = data;

    }

    @NonNull
    @Override
    public MyViewHodler onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.row2 , parent , false);
        MyViewHodler holder = new MyViewHodler(v);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHodler holder, int position) {
        holder.tv_name.setText(data.get(position).getList());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class MyViewHodler extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView tv_name;

        public MyViewHodler(@NonNull View itemView) {
            super(itemView);

            this.tv_name = (TextView) itemView.findViewById(R.id.title_id);

            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {
            int postion = getAdapterPosition();
            Toast.makeText(context, "pstionr"+ postion +1, Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(context , Show_LocalActivity.class);
            context.startActivity(intent);
        }
    }
}
