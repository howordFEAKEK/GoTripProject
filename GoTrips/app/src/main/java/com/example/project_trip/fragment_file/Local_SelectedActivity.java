package com.example.project_trip.fragment_file;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.Context;

import com.example.project_trip.BusProvider;
import com.example.project_trip.CusmaidFragment;
import com.example.project_trip.PushEvent;
import com.example.project_trip.R;

public class Local_SelectedActivity extends AppCompatActivity {

    ArrayAdapter<CharSequence> adspin1, adspin2;
    String choice_do = "";
    String choice_se = "";
    myDBHelper myHelper;
    SQLiteDatabase sqlDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_local__selected);

        Intent rintent = getIntent();
        myHelper = new myDBHelper(this);
        final Spinner spin1 = (Spinner) findViewById(R.id.spinner);
        final Spinner spin2 = (Spinner) findViewById(R.id.spinner2);
        Button btn_refresh = (Button) findViewById(R.id.btn_refresh);
        TextView textView = findViewById(R.id.text1);
        adspin1 = ArrayAdapter.createFromResource(this, R.array.spinner_region, android.R.layout.simple_spinner_dropdown_item);
        adspin1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin1.setAdapter(adspin1);


        spin1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (adspin1.getItem(i).equals("서울특별시")) {
                    choice_do = "서울특별시";
                    adspin2 = ArrayAdapter.createFromResource(Local_SelectedActivity.this, R.array.spinner_region_seoul, android.R.layout.simple_spinner_dropdown_item);

                    adspin2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spin2.setAdapter(adspin2);
                    spin2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                            choice_se = adspin2.getItem(i).toString();
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> adapterView) {
                        }
                    });
                } else if (adspin1.getItem(i).equals("인천광역시")) {
                    choice_do = "인천광역시";
                    adspin2 = ArrayAdapter.createFromResource(Local_SelectedActivity.this, R.array.spinner_region_incheon, android.R.layout.simple_spinner_dropdown_item);
                    adspin2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spin2.setAdapter(adspin2);
                    spin2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                            choice_se = adspin2.getItem(i).toString();
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> adapterView) {
                        }
                    });
                } else if (adspin1.getItem(i).equals("부산광역시")) {
                    choice_do = "부산광역시";
                    adspin2 = ArrayAdapter.createFromResource(Local_SelectedActivity.this, R.array.spinner_region_busan, android.R.layout.simple_spinner_dropdown_item);
                    adspin2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spin2.setAdapter(adspin2);
                    spin2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                            choice_se = adspin2.getItem(i).toString();
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> adapterView) {
                        }
                    });
                } else if (adspin1.getItem(i).equals("대구광역시")) {
                    choice_do = "대구광역시";
                    adspin2 = ArrayAdapter.createFromResource(Local_SelectedActivity.this, R.array.spinner_region_daegu, android.R.layout.simple_spinner_dropdown_item);
                    adspin2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spin2.setAdapter(adspin2);
                    spin2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                            choice_se = adspin2.getItem(i).toString();
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> adapterView) {
                        }
                    });
                } else if (adspin1.getItem(i).equals("광주광역시")) {
                    choice_do = "광주광역시";
                    adspin2 = ArrayAdapter.createFromResource(Local_SelectedActivity.this, R.array.spinner_region_gwangju, android.R.layout.simple_spinner_dropdown_item);
                    adspin2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spin2.setAdapter(adspin2);
                    spin2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                            choice_se = adspin2.getItem(i).toString();
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> adapterView) {
                        }
                    });
                } else if (adspin1.getItem(i).equals("대전광역시")) {
                    choice_do = "대전광역시";
                    adspin2 = ArrayAdapter.createFromResource(Local_SelectedActivity.this, R.array.spinner_region_daejeon, android.R.layout.simple_spinner_dropdown_item);
                    adspin2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spin2.setAdapter(adspin2);
                    spin2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                            choice_se = adspin2.getItem(i).toString();
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> adapterView) {
                        }
                    });
                } else if (adspin1.getItem(i).equals("울산광역시")) {
                    choice_do = "울산광역시";
                    adspin2 = ArrayAdapter.createFromResource(Local_SelectedActivity.this, R.array.spinner_region_ulsan, android.R.layout.simple_spinner_dropdown_item);
                    adspin2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spin2.setAdapter(adspin2);
                    spin2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                            choice_se = adspin2.getItem(i).toString();
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> adapterView) {
                        }
                    });
                } else if (adspin1.getItem(i).equals("세종특별자치시")) {
                    choice_do = "세종특별자치시";
                    adspin2 = ArrayAdapter.createFromResource(Local_SelectedActivity.this, R.array.spinner_region_sejong, android.R.layout.simple_spinner_dropdown_item);
                    adspin2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spin2.setAdapter(adspin2);
                    spin2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                            choice_se = adspin2.getItem(i).toString();
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> adapterView) {
                        }
                    });
                } else if (adspin1.getItem(i).equals("경기도")) {
                    choice_do = "경기도";
                    adspin2 = ArrayAdapter.createFromResource(Local_SelectedActivity.this, R.array.spinner_region_gyeonggi, android.R.layout.simple_spinner_dropdown_item);
                    adspin2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spin2.setAdapter(adspin2);
                    spin2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                            choice_se = adspin2.getItem(i).toString();
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> adapterView) {
                        }
                    });
                } else if (adspin1.getItem(i).equals("강원도")) {
                    choice_do = "강원도";
                    adspin2 = ArrayAdapter.createFromResource(Local_SelectedActivity.this, R.array.spinner_region_gangwon, android.R.layout.simple_spinner_dropdown_item);
                    adspin2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spin2.setAdapter(adspin2);
                    spin2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                            choice_se = adspin2.getItem(i).toString();
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> adapterView) {
                        }
                    });
                } else if (adspin1.getItem(i).equals("충청북도")) {
                    choice_do = "충청북도";
                    adspin2 = ArrayAdapter.createFromResource(Local_SelectedActivity.this, R.array.spinner_region_chung_buk, android.R.layout.simple_spinner_dropdown_item);
                    adspin2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spin2.setAdapter(adspin2);
                    spin2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                            choice_se = adspin2.getItem(i).toString();
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> adapterView) {
                        }
                    });
                } else if (adspin1.getItem(i).equals("충청남도")) {
                    choice_do = "충청남도";
                    adspin2 = ArrayAdapter.createFromResource(Local_SelectedActivity.this, R.array.spinner_region_chung_nam, android.R.layout.simple_spinner_dropdown_item);
                    adspin2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spin2.setAdapter(adspin2);
                    spin2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                            choice_se = adspin2.getItem(i).toString();
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> adapterView) {
                        }
                    });
                } else if (adspin1.getItem(i).equals("전라북도")) {
                    choice_do = "전라북도";
                    adspin2 = ArrayAdapter.createFromResource(Local_SelectedActivity.this, R.array.spinner_region_jeon_buk, android.R.layout.simple_spinner_dropdown_item);
                    adspin2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spin2.setAdapter(adspin2);
                    spin2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                            choice_se = adspin2.getItem(i).toString();
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> adapterView) {
                        }
                    });
                } else if (adspin1.getItem(i).equals("전라남도")) {
                    choice_do = "전라남도";
                    adspin2 = ArrayAdapter.createFromResource(Local_SelectedActivity.this, R.array.spinner_region_jeon_nam, android.R.layout.simple_spinner_dropdown_item);
                    adspin2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spin2.setAdapter(adspin2);
                    spin2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                            choice_se = adspin2.getItem(i).toString();
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> adapterView) {
                        }
                    });
                } else if (adspin1.getItem(i).equals("경상북도")) {
                    choice_do = "경상북도";
                    adspin2 = ArrayAdapter.createFromResource(Local_SelectedActivity.this, R.array.spinner_region_gyeong_buk, android.R.layout.simple_spinner_dropdown_item);
                    adspin2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spin2.setAdapter(adspin2);
                    spin2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                            choice_se = adspin2.getItem(i).toString();
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> adapterView) {
                        }
                    });
                } else if (adspin1.getItem(i).equals("경상남도")) {
                    choice_do = "경상남도";
                    adspin2 = ArrayAdapter.createFromResource(Local_SelectedActivity.this, R.array.spinner_region_gyeong_nam, android.R.layout.simple_spinner_dropdown_item);
                    adspin2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spin2.setAdapter(adspin2);
                    spin2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                            choice_se = adspin2.getItem(i).toString();
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> adapterView) {
                        }
                    });
                } else if (adspin1.getItem(i).equals("제주특별자치도")) {
                    choice_do = "제주특별자치도";
                    adspin2 = ArrayAdapter.createFromResource(Local_SelectedActivity.this, R.array.spinner_region_jeju, android.R.layout.simple_spinner_dropdown_item);
                    adspin2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spin2.setAdapter(adspin2);
                    spin2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                            choice_se = adspin2.getItem(i).toString();
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> adapterView) {
                        }
                    });
                }


            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        // eventBus
        // result

        btn_refresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                rintent.putExtra("name" , spin1.getSelectedItem().toString());
//                rintent.putExtra("data_name2", spin2.getSelectedItem().toString());
                String name1 = spin1.getSelectedItem().toString();  //스핀1값 시도 값 저장
                String name2 = spin2.getSelectedItem().toString();  //스핀2값 군구 값 저장
                String name3 = name1+" "+name2;                     // 합치기
                textView.setText(spin1.getSelectedItem().toString());
//                setResult(0,rintent);
                BusProvider.getInstance().post(new PushEvent(name1 , name2, name3));    //이벤트 버스에 태우기
                //Toast.makeText(this, choice_do + "=" + choice_se, Toast.LENGTH_SHORT).show();


                sqlDB = myHelper.getWritableDatabase();
                sqlDB.execSQL("INSERT INTO groupTBL VALUES ( '" + name1 + "' , '"+ name2 + "');");
                sqlDB.close();
                Log.d("","입력됨");


                finish();
            }
        });


    }
    public class myDBHelper extends SQLiteOpenHelper {
        public myDBHelper(Context context) {
            super(context, "groupDB", null, 1);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL("CREATE TABLE  groupTBL ( /*gName*/sido CHAR(20) PRIMARY KEY, /*gNumber*/gungu CHAR(20));");
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS groupTBL");
            onCreate(db);
        }
    }

}