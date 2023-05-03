package com.example.happypet;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.Nullable;

public class SubjectActivity extends Activity {
    LinearLayout subject_category, sort;
    TextView subject_text, doctor_cnt;
    ImageButton subject_back;
    ListView doctor_list;
    DoctorAdapter doctorAdapter;
    SQLiteDatabase database;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subject);

        database = DatabaseHelper.getDatabaseHelper(this).getWritableDatabase();
        subject_back = findViewById(R.id.subject_back);
        doctor_list = findViewById(R.id.doctor_list);
        doctorAdapter = new DoctorAdapter(database);
        doctor_list.setAdapter(doctorAdapter);
        subject_text = findViewById(R.id.subject_text);
        doctor_cnt = findViewById(R.id.doctor_cnt);

        Intent intent = getIntent();
        String subject1 = intent.getStringExtra("subject1");
        String subject2 = intent.getStringExtra("subject2");
        subject_text.setText(subject1);

        select_subject_doctor(subject2);

        setListener();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
        overridePendingTransition(R.anim.fadein, R.anim.fadeout);
    }

    void setListener() {
        subject_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                overridePendingTransition(R.anim.fadein, R.anim.fadeout);
            }
        });
    }

    public void select_subject_doctor(String subject2) {
        doctorAdapter.clear();

        String selectAllInfo = "select * from doctor where subject='" + subject2 + "'";
        Cursor cursor = database.rawQuery(selectAllInfo, null);

        doctor_cnt.setText("총 " + cursor.getCount() + "명의 의사 검색");

        while(cursor.moveToNext()) {
            int dno = cursor.getInt(0);
            String name = cursor.getString(1);
            String star_rating = cursor.getString(2);
            String subject = cursor.getString(3);
            String hospital = cursor.getString(4);

            DoctorData doctorData = new DoctorData(dno, name, star_rating, subject, hospital);
            doctorAdapter.addItem(doctorData);

            doctorAdapter.notifyDataSetChanged();
        }
    }
}
