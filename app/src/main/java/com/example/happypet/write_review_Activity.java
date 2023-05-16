package com.example.happypet;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.Nullable;

public class write_review_Activity extends Activity {

    DatabaseHelper dbHelper;

    SQLiteDatabase database;

    EditText review_title;
    EditText review_main;
    RatingBar reviewRating;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.write_review);

        review_title = findViewById(R.id.review_title);
        review_main = findViewById(R.id.review_main);
        reviewRating = findViewById(R.id.reviewRating);

        TextView textView1 = findViewById(R.id.review_doctor_name);
        TextView textView = findViewById(R.id.review_hospital_name);

        Intent intent = getIntent();
        String doctor_name = intent.getStringExtra("doctor_name");
        String hospital_name = intent.getStringExtra("hospital_name");

        textView1.setText(doctor_name);
        textView.setText(hospital_name);

        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);

        Button button = findViewById(R.id.review_save);
        Button button1 = findViewById(R.id.reviewwritecancelButton);

        dbHelper = DatabaseHelper.getDatabaseHelper(this);
        database = dbHelper.getWritableDatabase();

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String re_title = review_title.getText().toString();
                String re_main = review_main.getText().toString();
                String star_rating = String.valueOf(reviewRating.getRating());

                String re_save = " insert into " + "review" + " (review_title, review_main, name, hospital, star_rating)" +
                        " values " +
                        " ('" +re_title + "', '" + re_main + "', '"  + doctor_name + "', '" + hospital_name + "', '" +  star_rating + "')"+ "";
                database.execSQL(re_save);
                finish();
            }
        });

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }
}
