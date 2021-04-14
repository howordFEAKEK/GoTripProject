package com.example.project_trip.fragment_file;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.example.project_trip.R;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewAdapter3 extends RecyclerView.Adapter<RecyclerViewAdapter3.MyViewHolder> {


   Context mContext2;
    List<Main_item3> miData2;

    public RecyclerViewAdapter3(Context mContext2, List<Main_item3> miData2) {
        this.mContext2 = mContext2;
        this.miData2 = miData2;
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.row2, parent, false);

        return new MyViewHolder(v);

    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        holder.tv_list.setText(miData2.get(position).getList());
//        holder.tv_imglist.setImageResource(miData2.get(position).getImg());
//        holder.itemView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(mContext2, "맞춤 추천" + String.valueOf(holder.getAdapterPosition()+1), Toast.LENGTH_SHORT).show();
//                Intent intent = new Intent(mContext2 , Local_NextPageFragment.class);
//                mContext2.startActivity(intent);
//
//            }
//        });
    }

    @Override
    public int getItemCount() {

        return miData2.size();
    }

    
    // 뷰 홀더 클래스
    
    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        private TextView tv_list;
//        private ImageView tv_imglist;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            this.tv_list = itemView.findViewById(R.id.title_id);
//            this.tv_imglist = itemView.findViewById(R.id.icon_rv);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int postion = getAdapterPosition();
            Toast.makeText(mContext2, "postion" + postion +1, Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(mContext2, HomeActivity.class);
            mContext2.startActivity(intent);
            
        }
    }


}
