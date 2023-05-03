package com.example.happypet;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;

public class write_review_Activity extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.write_review);

        TextView textView1 = findViewById(R.id.review_doctor_name);
        TextView textView = findViewById(R.id.review_hospital_name);

        Intent intent = getIntent();
        String doctor_name = intent.getStringExtra("doctor_name");
        String hospital_name = intent.getStringExtra("hospital_name");

        textView1.setText(doctor_name);
        textView.setText(hospital_name);
    }
}
