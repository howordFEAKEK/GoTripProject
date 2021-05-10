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

public class Jeju_IslandAdepter extends RecyclerView.Adapter<Jeju_IslandAdepter.ViewHolder> {

    Context context;
    ArrayList<Detail_Region_Item> mdata;

    public Jeju_IslandAdepter(Context context, ArrayList<Detail_Region_Item> mdata) {
        this.context = context;
        this.mdata = mdata;
    }

    @NonNull
    @Override
    public Jeju_IslandAdepter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row3 , parent , false);
        Jeju_IslandAdepter.ViewHolder holder= new Jeju_IslandAdepter.ViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull Jeju_IslandAdepter.ViewHolder holder, int position) {

        holder.t1.setText(mdata.get(position).getName());

    }

    @Override
    public int getItemCount() {
        return mdata.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView t1;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
       this.t1 = itemView.findViewById(R.id.title_id11);

        }
    }
}
