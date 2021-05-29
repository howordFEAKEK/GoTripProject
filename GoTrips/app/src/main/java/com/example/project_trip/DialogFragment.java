package com.example.project_trip;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;

public class DialogFragment extends AppCompatDialogFragment {

    TextView txt1 , txt2;
    CusmaidFragment.myDBHelper myHelper;
    SQLiteDatabase sqlDB;
    @Override
    public Dialog onCreateDialog(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.fragment_dialog, null);

        builder.setView(view)
                .setTitle(Html.fromHtml("<font color='#000000'>조회정보</font>"))
                .setNegativeButton("확인", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .setPositiveButton("초기화", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        sqlDB = myHelper.getWritableDatabase();
                        myHelper.onUpgrade(sqlDB, 1, 2); // 인수는 아무거나 입력하면 됨.
                        Toast.makeText(getContext(), "초기화됨",
                                Toast.LENGTH_SHORT).show();
                        sqlDB.close();
                    }
                });

        txt1 = view.findViewById(R.id.dialog_txtsido);  //도시값 텍스트
        txt2 = view.findViewById(R.id.dialog_txtgungu); //군구값 텍스트
        
        //내부 DB 관광지 조회 시작
        myHelper = new CusmaidFragment.myDBHelper(getContext());
        sqlDB = myHelper.getReadableDatabase();
                Cursor cursor;
                cursor = sqlDB.rawQuery("SELECT * FROM groupTBL;", null);

                // 내부 DB에 입력한 데이터 추가 코드

                // 추가 코드 끝

                String dosi = "도시" + "\r\n" + "--------" + "\r\n";
                String gungu = "군구" + "\r\n" + "--------" + "\r\n";

                while (cursor.moveToNext()) {
                    dosi += cursor.getString(0) + "\r\n";
                    gungu += cursor.getString(1) + "\r\n";
                }
                
                txt1.setText(dosi);
                txt2.setText(gungu);


                cursor.close();
                sqlDB.close();
                
                // 내부 DB 관광지 조회 끝
                
        return builder.create();

    }

    public interface DialogListener{
        void applayTexts(String dosi , String gungu);
    }

}
