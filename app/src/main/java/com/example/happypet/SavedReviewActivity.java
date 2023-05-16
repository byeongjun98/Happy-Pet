package com.example.happypet;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

public class SavedReviewActivity extends AppCompatActivity {

    TextView riview_detail_doctor_name;
    TextView riview_detail_hospital_name;
    TextView riview_after_care_title;
    TextView riview_after_care_main;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activtity_saved_review);

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        Intent intent = getIntent();
        String title = intent.getStringExtra("title");
        String main = intent.getStringExtra("main");
        String doctor_name = intent.getStringExtra("doctor_name");
        String hospital_name = intent.getStringExtra("hospital_name");

        riview_detail_doctor_name = findViewById(R.id.riview_detail_doctor_name);
        riview_detail_hospital_name = findViewById(R.id.riview_detail_hospital_name);
        riview_after_care_title = findViewById(R.id.riview_after_care_title);
        riview_after_care_main = findViewById(R.id.riview_after_care_main);

        riview_detail_doctor_name.setText(doctor_name);
        riview_detail_hospital_name.setText(hospital_name);
        riview_after_care_title.setText(title);
        riview_after_care_main.setText(main);

        Button button = findViewById(R.id.close);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });


    }
}
