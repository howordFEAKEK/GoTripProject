package com.example.project_trip.AdapterFile;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.project_trip.R;
import com.example.project_trip.SubActivityFile.Local_GuideActivity;
import com.example.project_trip.ItemFile.Main_item_from_show_local;

import java.util.List;

public class Local_GuideAdapter extends RecyclerView.Adapter<Local_GuideAdapter.MyViewHolder> {


    // 맞춤 탭 및 메인탭 상단의 박물관 정보 리사이클뷰의 어댑터
    
    Context mContext1;
    List<Main_item_from_show_local> miData1;

    public Local_GuideAdapter(Context mContext1, List<Main_item_from_show_local> miData1) {
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

        holder.tv_list.setText(miData1.get(position).getLocal_title());

        holder.tv_list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext1 , Local_GuideActivity.class);
                intent.putExtra("local_title" , temp.getLocal_title());
                mContext1.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {

        return miData1.size();
    }

    
    // 뷰 홀더 클래스

    public static class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private LinearLayout item_id;
        private TextView tv_list;

//        private ImageView tv_imglist;
        ItemClickListener itemClickListener;
        public MyViewHolder(@NonNull View itemView ) {
            super(itemView);
            tv_list = (TextView) itemView.findViewById(R.id.title_id);
//            this.tv_imglist = itemView.findViewById(R.id.icon_rv);
            itemView.setOnClickListener(this);

        }


        @Override
        public void onClick(View v) {
            this.itemClickListener.onItemClick(v , getLayoutPosition());
        }

        public void setItemClickListener(ItemClickListener ic){
            this.itemClickListener = ic;
        }


        public interface ItemClickListener{
            void onItemClick(View v , int pos);
        }
    }



}
