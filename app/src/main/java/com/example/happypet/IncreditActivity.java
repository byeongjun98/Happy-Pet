package com.example.happypet;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class IncreditActivity extends Activity {
    TextView textView;

    DatabaseHelper dbHelper;

    SQLiteDatabase database;

    LinearLayout addCreditButton;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.incredit);

        dbHelper = DatabaseHelper.getDatabaseHelper(this);
        database = dbHelper.getWritableDatabase();

        textView = findViewById(R.id.card_info);
        addCreditButton =findViewById(R.id.addCreditButton);

        addCreditButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), addCreditCard.class);
                startActivity(intent);
            }
        });

        String credit_insert = " select * from credit";
        Cursor cursor = database.rawQuery(credit_insert, null);

        if(cursor.getCount() != 0) {
            cursor.moveToNext();

            String bir = cursor.getString(2);
            String cardpass = cursor.getString(1);
            String cardnum = cursor.getString(0);
            String expe = cursor.getString(3);
            String type = cursor.getString(4);

            textView.setText(type+ "    " +cardnum+"                 "+expe);
        }
        }


    }



