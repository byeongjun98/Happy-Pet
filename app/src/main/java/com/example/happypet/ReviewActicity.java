package com.example.happypet;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

public class ReviewActicity extends Activity {
    DatabaseHelper dbHelper;
    SQLiteDatabase database;

    RelativeLayout firstLin;

    RelativeLayout review_card;
    TextView review_hospital_name, review_doctor_name, list_date, review_title_card;
    RatingBar review_finalPoint;

    String main = "";

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review);

        dbHelper = DatabaseHelper.getDatabaseHelper(this);
        database = dbHelper.getWritableDatabase();

        review_card = findViewById(R.id.review_card);

        firstLin             = findViewById(R.id.firstLin);
        review_hospital_name = findViewById(R.id.review_hospital_name);
        review_doctor_name   = findViewById(R.id.review_doctor_name);
        review_title_card    = findViewById(R.id.review_title_card);
        list_date            = findViewById(R.id.list_date);
        review_finalPoint    = findViewById(R.id.review_finalPoint);

        String select_review = "select * from  review order by reg_date desc LIMIT 1";
        Cursor cursor = database.rawQuery(select_review, null);

        while(cursor.moveToNext()) {
            String title = cursor.getString(0);
            main = cursor.getString(1);
            String doctor_name = cursor.getString(2);
            String hospital_name = cursor.getString(3);
            float star_rating = Float.parseFloat(cursor.getString(4));
            String reg_date = cursor.getString(5);
            int idx = reg_date.indexOf(' ');
            reg_date = reg_date.substring(0, idx);

            review_title_card.setText(title);
            review_doctor_name.setText(doctor_name);
            review_hospital_name.setText(hospital_name);
            review_finalPoint.setRating(star_rating);
            list_date.setText(reg_date);
        }

        review_card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplication(), SavedReviewActivity.class);
                intent.putExtra("main", main);
                intent.putExtra("title", review_title_card.getText().toString());
                intent.putExtra("doctor_name", review_doctor_name.getText().toString());
                intent.putExtra("hospital_name", review_hospital_name.getText().toString());
                startActivity(intent);
            }
        });

        Button review_delete = findViewById(R.id.review_delete);

        review_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String delete_review = "delete from review";
                database.execSQL(delete_review);
            }
        }
        );
    }
}
