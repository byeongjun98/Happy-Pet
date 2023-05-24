package com.example.happypet;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;

import androidx.annotation.Nullable;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class InfoListActivity extends Activity {
    SQLiteDatabase database;

    ImageButton backInfo;
    ListView listInfo;
    FloatingActionButton infoWrite;
    LinearLayout info_category_entire, info_category_dog, info_category_cat, info_category_etc;
    ImageView info_entire, info_dog, info_cat, info_etc;

    InfoAdapter infoAdapter;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_infolist);

        backInfo             = findViewById(R.id.backInfo);
        listInfo             = findViewById(R.id.listInfo);
        infoWrite            = findViewById(R.id.info_write_btn);
        info_category_entire = findViewById(R.id.info_category_entire);
        info_category_dog    = findViewById(R.id.info_category_dog);
        info_category_cat    = findViewById(R.id.info_category_cat);
        info_category_etc    = findViewById(R.id.info_category_etc);
        info_entire          = findViewById(R.id.info_entire);
        info_dog             = findViewById(R.id.info_dog);
        info_cat             = findViewById(R.id.info_cat);
        info_etc             = findViewById(R.id.info_etc);

        database = DatabaseHelper.getDatabaseHelper(this).getWritableDatabase();
        infoAdapter = new InfoAdapter(database);
        listInfo.setAdapter(infoAdapter);

        selectAll_information();

        setListener();
    }

    void setListener() {
        backInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                overridePendingTransition(R.anim.fadein, R.anim.fadeout);
            }
        });

        info_category_entire.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                info_entire.setBackgroundResource(R.drawable.border_round_imageview2);
                info_dog.setBackgroundResource(R.drawable.border_round_imageview);
                info_cat.setBackgroundResource(R.drawable.border_round_imageview);
                info_etc.setBackgroundResource(R.drawable.border_round_imageview);

                selectAll_information();
            }
        });

        info_category_dog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                info_entire.setBackgroundResource(R.drawable.border_round_imageview);
                info_dog.setBackgroundResource(R.drawable.border_round_imageview2);
                info_cat.setBackgroundResource(R.drawable.border_round_imageview);
                info_etc.setBackgroundResource(R.drawable.border_round_imageview);

                select_information("dog");
            }
        });

        info_category_cat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                info_entire.setBackgroundResource(R.drawable.border_round_imageview);
                info_dog.setBackgroundResource(R.drawable.border_round_imageview);
                info_cat.setBackgroundResource(R.drawable.border_round_imageview2);
                info_etc.setBackgroundResource(R.drawable.border_round_imageview);

                select_information("cat");
            }
        });

        info_category_etc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                info_entire.setBackgroundResource(R.drawable.border_round_imageview);
                info_dog.setBackgroundResource(R.drawable.border_round_imageview);
                info_cat.setBackgroundResource(R.drawable.border_round_imageview);
                info_etc.setBackgroundResource(R.drawable.border_round_imageview2);

                select_information("etc");
            }
        });

        infoWrite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(SaveSharedPreference.getUserName(InfoListActivity.this).length() == 0) {
                    Intent intent = new Intent(InfoListActivity.this, LoginFormActivity.class);
                    startActivity(intent);
                    return;
                }

                Intent intent = new Intent(getApplication(), InfoWriteActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.activity_slide_anim, R.anim.activity_none);
            }
        });
    }

    void selectAll_information() {
        infoAdapter.clear();

        String selectAllInfo = "select * from information order by mk_date desc";
        Cursor cursor = database.rawQuery(selectAllInfo, null);

        while(cursor.moveToNext()) {
            int ino = cursor.getInt(0);
            String p_id = cursor.getString(1);
            String title = cursor.getString(2);
            String content = cursor.getString(3);
            String img = cursor.getString(5);
            String mk_date = cursor.getString(6);
            int idx = mk_date.indexOf(' ');
            mk_date = mk_date.substring(0, idx);

            InfoData infoData = new InfoData(ino, p_id, title, content, img, mk_date);
            infoAdapter.addItem(infoData);

            infoAdapter.notifyDataSetChanged();
        }
    }

    void select_information(String category) {
        infoAdapter.clear();

        String selectAllInfo = "select * from information where category = '" + category + "'";
        Cursor cursor = database.rawQuery(selectAllInfo, null);

        while(cursor.moveToNext()) {
            int ino = cursor.getInt(0);
            String p_id = cursor.getString(1);
            String title = cursor.getString(2);
            String content = cursor.getString(3);
            String img = cursor.getString(5);
            String mk_date = cursor.getString(6);
            int idx = mk_date.indexOf(' ');
            mk_date = mk_date.substring(0, idx);

            InfoData infoData = new InfoData(ino, p_id, title, content, img, mk_date);
            infoAdapter.addItem(infoData);

            infoAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
        overridePendingTransition(R.anim.fadein, R.anim.fadeout);
    }

    @Override
    protected void onResume() {
        super.onResume();

        selectAll_information();
    }
}
