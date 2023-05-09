package com.example.happypet;

import android.app.Activity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;

public class addCreditCard extends Activity {
    EditText birth_day;
    EditText card_password;
    EditText card_number;
    EditText expiration_period;

    EditText card_type;

    DatabaseHelper dbHelper;
    SQLiteDatabase database;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addcreditcard);

        dbHelper = DatabaseHelper.getDatabaseHelper(this);
        database = dbHelper.getWritableDatabase();

        birth_day= findViewById(R.id.birth);
        card_password = findViewById(R.id.card_password);
        card_number = findViewById(R.id.cardnumber);
        expiration_period =findViewById(R.id.expiration_period);
        card_type = findViewById(R.id.card_type);

        Button button = findViewById(R.id.creditcardinsertbutton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String bir = birth_day.getText().toString();
                String cardpass = card_password.getText().toString();
                String cardnum = card_number.getText().toString();
                String expe = expiration_period.getText().toString();
                String type = card_type.getText().toString();

                String credit_insert = " insert into " + "credit" + " (card_number, card_password, Birth, expiration_period,card_type ) " +
                                        " values " +
                                        " ('" +cardnum + "', '" + cardpass + "','" + bir + "', '" + expe + "','"+ type +"')"
                                        + "";
                database.execSQL(credit_insert);





            }
        });

    }
}
