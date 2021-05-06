package com.example.project_trip.fragment_file.dkssudkgptu;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.project_trip.R;
import com.example.project_trip.fragment_file.Detail_Region_Item;

import java.util.ArrayList;

public class SeoulAdapter extends RecyclerView.Adapter<SeoulAdapter.ViewHolder> {

    Context context;
    ArrayList<Detail_Region_Item> data;

    public SeoulAdapter(Context context , ArrayList<Detail_Region_Item> data){
        this.context = context;
        this.data = data;
    }

    @NonNull
    @Override
    public SeoulAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row3 , parent , false);
        SeoulAdapter.ViewHolder holder = new SeoulAdapter.ViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull SeoulAdapter.ViewHolder holder, int position) {

        holder.t1.setText(data.get(position).getName());

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView t1;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            this.t1 = itemView.findViewById(R.id.title_id11);
        }
    }
}
