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
import androidx.appcompat.app.AppCompatActivity;

public class SubjectActivity extends AppCompatActivity implements BottomSheetDialog.BottomSheetListener {
    LinearLayout subject_category, sort;
    TextView subject_text, doctor_cnt, sort_type;
    ImageButton subject_back;
    ListView doctor_list;
    DoctorAdapter doctorAdapter;
    SQLiteDatabase database;

    String subject2;

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
        subject_category = findViewById(R.id.subject_category);
        sort = findViewById(R.id.sort);
        sort_type = findViewById(R.id.sort_type);

        Intent intent = getIntent();
        String subject1 = intent.getStringExtra("subject1");
        subject2 = intent.getStringExtra("subject2");
        subject_text.setText(subject1);

        select_subject_doctor();

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

        subject_category.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        sort.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(sort_type.getText().toString());
                bottomSheetDialog.show(getSupportFragmentManager(), "BottomSheet");
            }
        });
    }

    public void select_subject_doctor() {
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
            int customer_num = cursor.getInt(5);

            DoctorData doctorData = new DoctorData(dno, name, star_rating, subject, hospital, customer_num);
            doctorAdapter.addItem(doctorData);

            doctorAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onLinearLayoutClicked(String text) {
        sort_type.setText(text);

        switch (sort_type.getText().toString()) {
            case "기본순":
                select_subject_doctor();
                break;
            case "거리순":
                break;
            case "후기많은순":
                break;
            case "별점많은순":
                select_sort("star_rating");
                break;
        }
    }

    void select_sort(String sort_type) {
        doctorAdapter.clear();

        String sort_select = "select * from doctor where subject='" + subject2 + "'"
                                + " order by " + sort_type + " desc";

        Cursor cursor = database.rawQuery(sort_select, null);

        while(cursor.moveToNext()) {
            int dno = cursor.getInt(0);
            String name = cursor.getString(1);
            String star_rating = cursor.getString(2);
            String subject = cursor.getString(3);
            String hospital = cursor.getString(4);
            int customer_num = cursor.getInt(5);

            DoctorData doctorData = new DoctorData(dno, name, star_rating, subject, hospital, customer_num);
            doctorAdapter.addItem(doctorData);

            doctorAdapter.notifyDataSetChanged();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
    }
}
