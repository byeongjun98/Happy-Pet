package com.example.happypet;

import android.app.Activity;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class RealtimeConsultingActivity extends AppCompatActivity {
    DatabaseHelper dbHelper;
    SQLiteDatabase database;

    ImageButton realtime_back;
    EditText realtime_title, realtime_content;
    Button realtime_register;
    Spinner realtime_spinner;

    String[] items = { "내과", "치과", "외과", "안과" };

    boolean realtime_title_check = false, realtime_content_check = false;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_realtime_consulting);

        realtime_back = findViewById(R.id.back_realtime);
        realtime_title = findViewById(R.id.realtime_title);
        realtime_content = findViewById(R.id.realtime_content);
        realtime_register = findViewById(R.id.realtime_register);
        realtime_spinner = findViewById(R.id.realtime_spinner);

        dbHelper = DatabaseHelper.getDatabaseHelper(this);
        database = dbHelper.getWritableDatabase();

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, items);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        realtime_spinner.setAdapter(adapter);

        setListener();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
        overridePendingTransition(R.anim.fadein, R.anim.fadeout);
    }

    void setListener() {
        realtime_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                overridePendingTransition(R.anim.fadein, R.anim.fadeout);
            }
        });

        realtime_title.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(charSequence.toString().equals("")) {
                    realtime_title_check = false;
                } else {
                    realtime_title_check = true;
                }

                if(realtime_title_check && realtime_content_check) {
                    realtime_register.setEnabled(true);
                    realtime_register.setTextColor(Color.rgb(255, 255, 255));
                } else {
                    realtime_register.setEnabled(false);
                    realtime_register.setTextColor(Color.argb(80, 255, 255, 255));
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {}
        });

        realtime_content.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(charSequence.toString().equals("")) {
                    realtime_content_check = false;
                } else {
                    realtime_content_check = true;
                }

                if(realtime_title_check && realtime_content_check) {
                    realtime_register.setEnabled(true);
                    realtime_register.setTextColor(Color.rgb(255, 255, 255));
                } else {
                    realtime_register.setEnabled(false);
                    realtime_register.setTextColor(Color.argb(80, 255, 255, 255));
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {}
        });

        realtime_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        realtime_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String title   = realtime_title.getText().toString();
                String content = realtime_content.getText().toString();
                String category = realtime_spinner.getSelectedItem().toString();

//                String realtime_insert = "insert into " + "free_consulting"
//                        + "(p_id, title, question, category, img) "
//                        + " values "
//                        + " ('" + p_id + "', '" + title + "', '" + content + "', '" + category + "', '" + img + "')"
//                        + "";

                String realtime_insert = "insert into " + "free_consulting"
                        + "(p_id, title, question, category, img) "
                        + " values "
                        + " ('" + "aaa" + "', '" + title + "', '" + content + "', '" + category + "', '" + "asd" + "')"
                        + "";

                database.execSQL(realtime_insert);

                Toast.makeText(RealtimeConsultingActivity.this, "등록 성공", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }
}
