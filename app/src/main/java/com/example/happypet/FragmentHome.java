package com.example.happypet;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import java.util.StringTokenizer;

public class FragmentHome extends Fragment {
    DatabaseHelper dbHelper;
    SQLiteDatabase database;

    TextView[] info_ino_textView = new TextView[4];
    TextView[] info_title_textView = new TextView[4];
    TextView[] info_id_textView = new TextView[4];
    TextView moreInfo;
    LinearLayout[] infoLayout = new LinearLayout[4];
    RelativeLayout medicine, dentist, surgery, ophthalmology;
    RelativeLayout realtime_consulting, comprehensive_care;
    LinearLayout subject_entire;

    int[] info_ino    = { R.id.ino1, R.id.ino2, R.id.ino3, R.id.ino4 };
    int[] info_id     = { R.id.info_id1, R.id.info_id2, R.id.info_id3, R.id.info_id4 };
    int[] info_title  = { R.id.info_title1, R.id.info_title2, R.id.info_title3, R.id.info_title4 };
    int[] info_layout = { R.id.infoLayout1, R.id.infoLayout2, R.id.infoLayout3, R.id.infoLayout4 };

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_home, container, false);

        dbHelper = DatabaseHelper.getDatabaseHelper(getContext());
        database = dbHelper.getWritableDatabase();

//        String test = "select * from information";
//        Cursor cursor = database.rawQuery(test, null);
//        cursor.moveToNext();
//        Log.d("asdfg", cursor.getString(4));

//        String insert_sql1 = "insert into " + "information"
//                + "(p_id, title, content) "
//                + " values "
//                + " ('" + "aaaa', " + "'캣타워가생겼어요', " + "'ㅇㅁㄴㅇㅁㄴㅇㅁㄴㅇ')";
//
//        String insert_sql2 = "insert into " + "information"
//                + "(p_id, title, content) "
//                + " values "
//                + " ('" + "aaaa', " + "'우리거북이가아파요', " + "'ㅇㅁㄴㅇㅁㄴㅇㅁㄴㅇ')";
//
//        String insert_sql3 = "insert into " + "information"
//                + "(p_id, title, content) "
//                + " values "
//                + " ('" + "aaaa', " + "'ㅎㅎ', " + "'ㅇㅁㄴㅇㅁㄴㅇㅁㄴㅇ')";
//
//        String insert_sql4 = "insert into " + "information"
//                + "(p_id, title, content) "
//                + " values "
//                + " ('" + "aaaa', " + "'강아지+고양이', " + "'ㅇㅁㄴㅇㅁㄴㅇㅁㄴㅇ')";
//
//        database.execSQL(insert_sql1);
//        database.execSQL(insert_sql2);
//        database.execSQL(insert_sql3);
//        database.execSQL(insert_sql4);

        init(rootView);
        setListener();
        selectAllInfo();
        doctor_input_test();

        return rootView;
    }

    private void init(ViewGroup rootView) {
        for(int i=0; i<info_ino_textView.length; i++) {
            info_ino_textView[i] = rootView.findViewById(info_ino[i]);
        }

        for(int i=0; i<info_id_textView.length; i++) {
            info_id_textView[i] = rootView.findViewById(info_id[i]);
        }

        for(int i=0; i<info_title_textView.length; i++) {
            info_title_textView[i] = rootView.findViewById(info_title[i]);
        }

        for(int i=0; i<infoLayout.length; i++) {
            infoLayout[i] = rootView.findViewById(info_layout[i]);
        }

        moreInfo = rootView.findViewById(R.id.moreInfo);

        medicine = rootView.findViewById(R.id.medicine);
        dentist = rootView.findViewById(R.id.dentist);
        surgery = rootView.findViewById(R.id.surgery);
        ophthalmology = rootView.findViewById(R.id.ophthalmology);

        realtime_consulting = rootView.findViewById(R.id.realtime_consulting);
        comprehensive_care = rootView.findViewById(R.id.comprehensive_care);

        subject_entire = rootView.findViewById(R.id.subject_entire);
    }

    public void setListener() {
        moreInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), InfoListActivity.class);
                getContext().startActivity(intent);
            }
        });

        for(int i=0; i<infoLayout.length; i++) {
            String ino = info_ino_textView[i].getText().toString();

            infoLayout[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(getContext(), InfoDetailActivity.class);
                    intent.putExtra("ino", ino);
                    getContext().startActivity(intent);
                }
            });
        }

        medicine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), SubjectActivity.class);
                intent.putExtra("subject1", "내과");
                intent.putExtra("subject2", "medicine");
                getContext().startActivity(intent);
            }
        });

        dentist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), SubjectActivity.class);
                intent.putExtra("subject1", "치과");
                intent.putExtra("subject2", "dentist");
                getContext().startActivity(intent);
            }
        });

        surgery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), SubjectActivity.class);
                intent.putExtra("subject1", "외과");
                intent.putExtra("subject2", "surgery");
                getContext().startActivity(intent);
            }
        });

        ophthalmology.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), SubjectActivity.class);
                intent.putExtra("subject1", "안과");
                intent.putExtra("subject2", "ophthalmology");
                getContext().startActivity(intent);
            }
        });

        subject_entire.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        realtime_consulting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), RealtimeConsultingActivity.class);
                getContext().startActivity(intent);
            }
        });

        comprehensive_care.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    public void selectAllInfo() {
        String selectAllInfo = "select * from information";

        Cursor cursor = database.rawQuery(selectAllInfo, null);
        Log.d("sssss", cursor.getCount() + "");
        int i=0;

        while(cursor.moveToNext()) {
            info_ino_textView[i].setText(cursor.getInt(0) + "");
            info_id_textView[i].setText(cursor.getString(1));
            info_title_textView[i].setText(cursor.getString(2));
            Log.d("aaa", cursor.getString(1) + cursor.getString(2));
            i++;
        }
    }

    void doctor_input_test() {
        String doctor_delete = "delete from doctor";

        String doctor_insert = "insert into " + "doctor"
                + "(name, star_rating, subject, hospital) "
                + " values "
                + " ('" + "이상문', " + "'5.0', " + "'medicine', " + "'원주시내병원')";

        database.execSQL(doctor_delete);
        for(int i=0; i<5; i++) {
            database.execSQL(doctor_insert);
        }

//        String insert_sql4 = "insert into " + "information"
//                + "(p_id, title, content) "
//                + " values "
//                + " ('" + "aaaa', " + "'강아지+고양이', " + "'ㅇㅁㄴㅇㅁㄴㅇㅁㄴㅇ')";
    }

    @Override
    public void onResume() {
        super.onResume();

        selectAllInfo();
    }
}
