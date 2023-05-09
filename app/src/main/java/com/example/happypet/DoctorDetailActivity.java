package com.example.happypet;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.Nullable;

public class DoctorDetailActivity extends Activity {
    ImageButton back_doctor_detail;
    TextView doctor_detail_hospital;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_detail);

        back_doctor_detail = findViewById(R.id.back_doctor_detail);
        doctor_detail_hospital = findViewById(R.id.doctor_detail_hospital);

        Intent intent = getIntent();
        int dno = intent.getIntExtra("dno", 0);
        String hospital = intent.getStringExtra("hospital");

        doctor_detail_hospital.setText(hospital);

        setListener();
    }

    void setListener() {
        back_doctor_detail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                overridePendingTransition(R.anim.fadein, R.anim.fadeout);
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
        overridePendingTransition(R.anim.fadein, R.anim.fadeout);
    }
}
