package com.example.project_trip.fragment_file;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.project_trip.R;

import java.util.ArrayList;

public class CusmaidAdapter_from_local_guide extends RecyclerView.Adapter<CusmaidAdapter_from_local_guide.MyViewHolder> {


    // 맞춤 탭 및 메인탭 상단의 박물관 정보 리사이클뷰의 어댑터


    ArrayList<Main_item_from_show_local> miData1;
    UpdateRecyclerVIew updateRecyclerVIew;
    Activity activity;
    boolean check = true;
    boolean select = true;

    public CusmaidAdapter_from_local_guide(Context mContext1, ArrayList<Main_item_from_show_local> miData1 , Activity activity , UpdateRecyclerVIew updateRecyclerVIew) {

        this.miData1 = miData1;
        this.activity = activity;
        this.updateRecyclerVIew = updateRecyclerVIew;

    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v;
        v = LayoutInflater.from(parent.getContext()).inflate(R.layout.row2, parent, false);
        MyViewHolder myViewHolder = new MyViewHolder(v );

        return myViewHolder;

    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {

        Main_item_from_show_local temp = miData1.get(position);

        holder.tv_list.setText(temp.getLocal_title());

        if(check){
            ArrayList<Detail_Region_Item> items = new ArrayList<Detail_Region_Item>();
            items.add(new Detail_Region_Item("화성1"));
            items.add(new Detail_Region_Item("화성2"));
            items.add(new Detail_Region_Item("화성3"));
            items.add(new Detail_Region_Item("화성4"));
            items.add(new Detail_Region_Item("화성5"));

            updateRecyclerVIew.callback(position, items);

            check = false;

        }


//        holder.tv_imglist.setImageResource(miData.get(position).getImg());
        holder.tv_list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });


    }

    @Override
    public int getItemCount() {

        return miData1.size();
    }

    
    // 뷰 홀더 클래스

    public class MyViewHolder extends RecyclerView.ViewHolder{

        private TextView tv_list;

//        private ImageView tv_imglist;

        public MyViewHolder(@NonNull View itemView ) {
            super(itemView);
            tv_list = (TextView) itemView.findViewById(R.id.title_id);
//            this.tv_imglist = itemView.findViewById(R.id.icon_rv);


        }


    }



}
