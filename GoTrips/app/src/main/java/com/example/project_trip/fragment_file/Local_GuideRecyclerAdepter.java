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

public class Local_GuideRecyclerAdepter extends RecyclerView.Adapter<Local_GuideRecyclerAdepter.MyViewHodler> {

    // 박물관 클릭시 나오는 리뷰 리스트 어댑터
    
    private ArrayList<Main_item4> data;
    Context context;

    public Local_GuideRecyclerAdepter(ArrayList<Main_item4> data, Context context) {
        this.data = data;
        this.context = context;

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
        holder.tv_name.setText(data.get(position).review_index_title);
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
            String wirter = data.get(postion).review_index_writername;
            String wrDate = data.get(postion).review_index_date;
            Toast.makeText(context, "pstionr"+ postion +1, Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(context , Show_ReviewActivity.class);
            intent.putExtra("wirter", wirter);
            intent.putExtra("wrdate", wrDate);
            context.startActivity(intent);
        }
    }
}
