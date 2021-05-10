package com.example.project_trip.AdapterFile;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.project_trip.R;
import com.example.project_trip.ItemFile.Detail_Region_Item;

import java.util.ArrayList;

public class Detail_Ragion_Adapter extends RecyclerView.Adapter<Detail_Ragion_Adapter.DetailHolder> {

   ArrayList<Detail_Region_Item> dri1;

   public Detail_Ragion_Adapter(ArrayList<Detail_Region_Item> dri1){
       this.dri1 = dri1;
   }

    @NonNull
    @Override
    public Detail_Ragion_Adapter.DetailHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row3,parent,false);

        DetailHolder detailHolder = new DetailHolder(view);
        return detailHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull Detail_Ragion_Adapter.DetailHolder holder, int position) {
        Detail_Region_Item dItem = dri1.get(position);
        holder.textView.setText(dItem.getName());
    }

    @Override
    public int getItemCount() {
        return dri1.size();
    }

    public class DetailHolder extends RecyclerView.ViewHolder {

       public TextView textView;

        public DetailHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.title_id11);
        }
    }
}
