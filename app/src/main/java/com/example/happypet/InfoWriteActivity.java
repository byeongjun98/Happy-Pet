package com.example.happypet;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class InfoWriteActivity extends Activity {
    DatabaseHelper dbHelper;
    SQLiteDatabase database;

    ImageButton backWrite;
    EditText write_title, write_content;
    LinearLayout photo_add;
    FrameLayout write_photo;
    Button write_register;
    LinearLayout write_category_dog, write_category_cat, write_category_etc;
    ImageView write_dog, write_cat, write_etc;

    Boolean write_title_check = false, write_content_check= false;

    String category = "etc";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_write);

        backWrite = findViewById(R.id.back_write);
        write_title = findViewById(R.id.write_title);
        write_content = findViewById(R.id.write_content);
        photo_add = findViewById(R.id.photo_add);
        write_photo = findViewById(R.id.write_photo);
        write_register = findViewById(R.id.write_register);
        write_category_dog = findViewById(R.id.write_category_dog);
        write_category_cat = findViewById(R.id.write_category_cat);
        write_category_etc = findViewById(R.id.write_category_etc);
        write_dog = findViewById(R.id.write_dog);
        write_cat = findViewById(R.id.write_cat);
        write_etc = findViewById(R.id.write_etc);


        dbHelper = DatabaseHelper.getDatabaseHelper(this);
        database = dbHelper.getWritableDatabase();

        setListener();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
        overridePendingTransition(R.anim.fadein, R.anim.fadeout);
    }

    void setListener() {
        backWrite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                overridePendingTransition(R.anim.fadein, R.anim.fadeout);
            }
        });

        write_dog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                write_dog.setBackgroundResource(R.drawable.border_round_imageview2);
                write_cat.setBackgroundResource(R.drawable.border_round_imageview);
                write_etc.setBackgroundResource(R.drawable.border_round_imageview);

                category = "dog";
            }
        });

        write_cat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                write_dog.setBackgroundResource(R.drawable.border_round_imageview);
                write_cat.setBackgroundResource(R.drawable.border_round_imageview2);
                write_etc.setBackgroundResource(R.drawable.border_round_imageview);

                category = "cat";
            }
        });

        write_etc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                write_dog.setBackgroundResource(R.drawable.border_round_imageview);
                write_cat.setBackgroundResource(R.drawable.border_round_imageview);
                write_etc.setBackgroundResource(R.drawable.border_round_imageview2);

                category = "etc";
            }
        });

        write_title.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(charSequence.toString().equals("")) {
                    write_title_check = false;
                } else {
                    write_title_check = true;
                }

                if(write_title_check && write_content_check) {
                    write_register.setEnabled(true);
                    write_register.setTextColor(Color.rgb(255, 255, 255));
                } else {
                    write_register.setEnabled(false);
                    write_register.setTextColor(Color.argb(80, 255, 255, 255));
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {}
        });

        write_content.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(charSequence.toString().equals("")) {
                    write_content_check = false;
                } else {
                    write_content_check = true;
                }

                if(write_title_check && write_content_check) {
                    write_register.setEnabled(true);
                    write_register.setTextColor(Color.rgb(255, 255, 255));
                } else {
                    write_register.setEnabled(false);
                    write_register.setTextColor(Color.argb(80, 255, 255, 255));
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {}
        });

        photo_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        write_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String title   = write_title.getText().toString();
                String content = write_content.getText().toString();

//                String write_insert = "insert into " + "information"
//                        + "(p_id, title, content, category, img) "
//                        + " values "
//                        + " ('" + p_id + "', '" + title + "', '" + content + "', '" + category + "', '" + img + "')"
//                        + "";

                String write_insert = "insert into " + "information"
                        + "(p_id, title, content, category) "
                        + " values "
                        + " ('" + SaveSharedPreference.getUserName(InfoWriteActivity.this) + "', '" + title + "', '" + content + "', '" + category + "')"
                        + "";

                database.execSQL(write_insert);

                Toast.makeText(InfoWriteActivity.this, "등록 성공", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }
}
