package com.example.project_trip.fragment_file;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.project_trip.Local_Data_List;
import com.example.project_trip.R;
import com.example.project_trip.SocketManager2;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.List;

public class RecyclerViewAdapter_from_local_guide extends RecyclerView.Adapter<RecyclerViewAdapter_from_local_guide.MyViewHolder> {


    // 맞춤 탭 및 메인탭 상단의 박물관 정보 리사이클뷰의 어댑터
    
    Context mContext1;
    List<Main_item_from_show_local> miData1;

    public RecyclerViewAdapter_from_local_guide(Context mContext1, List<Main_item_from_show_local> miData1) {
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

        holder.tv_list.setText(miData1.get(position).tour_title);
//        holder.tv_imglist.setImageResource(miData.get(position).getImg());
        holder.tv_list.setOnClickListener(new View.OnClickListener() {

//           final String get_sido = Local_Data_List.local_data.get(position).sido_name;   //시도 값 가져오기
//           final String get_gungu = Local_Data_List.local_data.get(position).gungu_name; //군구 값 가져오기
//           final String get_sido_gungu = get_sido + " " + get_gungu;                     //시도,군구값 합치기 00시 00군
//           final String get_local_guide_name = temp.getLocal_title();                    //관광지 명
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(mContext1 , Local_GuideActivity.class);
                intent.putExtra("local_title" , temp.tour_title);
                mContext1.startActivity(intent);



            }
        });


    }

    @Override
    public int getItemCount() {

        return miData1.size();
    }

    
    // 뷰 홀더 클래스

    public class MyViewHolder extends RecyclerView.ViewHolder{
        private LinearLayout item_id;
        private TextView tv_list;

//        private ImageView tv_imglist;

        public MyViewHolder(@NonNull View itemView ) {
            super(itemView);
            tv_list = (TextView) itemView.findViewById(R.id.title_id);
//            this.tv_imglist = itemView.findViewById(R.id.icon_rv);


        }


    }



}
