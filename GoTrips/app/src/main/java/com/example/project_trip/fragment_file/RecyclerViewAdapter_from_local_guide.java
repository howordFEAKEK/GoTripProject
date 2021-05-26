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

        holder.tv_list.setText(miData1.get(position).getLocal_title());
//        holder.tv_imglist.setImageResource(miData.get(position).getImg());
        holder.tv_list.setOnClickListener(new View.OnClickListener() {

//           final String get_sido = Local_Data_List.local_data.get(position).sido_name;   //시도 값 가져오기
//           final String get_gungu = Local_Data_List.local_data.get(position).gungu_name; //군구 값 가져오기
//           final String get_sido_gungu = get_sido + " " + get_gungu;                     //시도,군구값 합치기 00시 00군
//           final String get_local_guide_name = temp.getLocal_title();                    //관광지 명
            @Override
            public void onClick(View v) {

//                new Thread() {
//                    @Override
//                    public void run() {
//                        DataOutputStream out;
//                        DataInputStream in;
//
//                        //신호
//                        String msg = "REVIEWINDEX/";
//
//                        // 이 부분은 가져와서 저장(관광지 클릭 시 넘겨받아서)
//                        // 보낼 내용 ( 관광지명, 지역명)
//                        String text = get_local_guide_name + " " + get_sido_gungu;
//
//                        String readMsg = null; // 받은 메시지
//                        String sign = null; // 신호
//                        String context = null; // 내용
//                        try {
//                            msg = msg + text +"";
//                            out = new DataOutputStream(SocketManager2.socket.getOutputStream());
//                            in = new DataInputStream(SocketManager2.socket.getInputStream());
//                            out.writeUTF(msg);
//
//                            readMsg = in.readUTF(); // 받을 때까지 대기
//
//                            final String finalReadMsg = readMsg; // 파이널 선언해줘서 받은 데이터 전달
////                            runOnUiThread(new Runnable() { // 이게 메인 스레드 외에서도 수정 가능하게 함
////                                @Override
////                                public void run() {
////                                    //UI 바꾸려면 이 방법을 사용하던가?
////                                    //아니면 받은 내용 따로 보관하는 전역 변수 만들어서 보관하고, 스레드 나간 다음에 처리하기
////                                    Toast.makeText(MainActivity.this, finalReadMsg, Toast.LENGTH_SHORT).show();
////                                }
////                            });
//
//                            st = new StringTokenizer(readMsg, "/");
//                            sign = st.nextToken(); // 신호
//                            context = st.nextToken(); // 내용
//
//                            if (sign.equals("NOLIST")){
//                                // 목록이 없는 경우
//                                System.out.println("목록이 없습니다.");
//                            }else{
//                                st = new StringTokenizer(context, "$");
//
//                                //목록 하나에 토큰 3개(작성자, 작성일, 제목)
//                                System.out.println(st.countTokens()/3);
//                                int num = st.countTokens()/3;
//                                for (int i = 0 ; i < num ; i ++){
//                                    System.out.println(st.nextToken() +" "+ st.nextToken() +" "+ st.nextToken());
//                                } // 이 부분에서 데이터 어떻게 처리할지는 알아서
//                            }
//
//                        }catch (IOException e){}
//                    }
//                }.start();

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
