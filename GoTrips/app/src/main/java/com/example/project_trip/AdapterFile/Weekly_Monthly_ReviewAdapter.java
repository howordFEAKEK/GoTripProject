package com.example.project_trip.AdapterFile;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.project_trip.R;
import com.example.project_trip.ItemFile.Main_item;
import com.example.project_trip.SubActivityFile.Show_ReviewActivity;

import java.util.List;

public class Weekly_Monthly_ReviewAdapter extends RecyclerView.Adapter<Weekly_Monthly_ReviewAdapter.MyViewHolder> {

    // 하단의 주간리뷰와 월간리뷰의 리사이클 어댑터
    
    Context mContext;
    List<Main_item> miData;

    public Weekly_Monthly_ReviewAdapter(Context mContext, List<Main_item> miData) {
        this.mContext = mContext;
        this.miData = miData;
    }

    @NonNull
    @Override
    // 뷰홀더 생성
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {

        // 연결할 레이아웃 설정
        // inflate는 레이아웃을 연결 시켜 주는 것
       View v;
        v = LayoutInflater.from(mContext).inflate(R.layout.row, parent, false);
        final MyViewHolder myViewHolder1 = new MyViewHolder(v);

        return myViewHolder1;

    }

    @Override
    // 뷰홀더가 묶여있을 때 ( 데이터와 요소들을 연결 )
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {

        Main_item temp = miData.get(position);

        holder.tv_list.setText(miData.get(position).getList());
//        holder.tv_imglist.setImageResource(miData.get(position).getImg());
        holder.tv_list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext , Show_ReviewActivity.class);
                intent.putExtra("List" , temp.getList());
                mContext.startActivity(intent);
            }
        });


    }

    @Override
    // 목록 아이템 수
    public int getItemCount() {

        return miData.size();
    }


    // 뷰 홀더 클래스

    public class MyViewHolder extends RecyclerView.ViewHolder{

        private TextView tv_list;
//        private ImageView tv_imglist;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            tv_list = itemView.findViewById(R.id.title_id111);
//            this.tv_imglist = itemView.findViewById(R.id.icon_rv);



        }



    }
}
