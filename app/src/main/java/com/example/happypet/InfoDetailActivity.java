package com.example.happypet;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.Nullable;

public class InfoDetailActivity extends Activity {
    ImageButton backInfoDetail;
    TextView infoDetailTitle, infoDetailIdDate, infoDetailContent;
    SQLiteDatabase database;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_detail);

        database = DatabaseHelper.getDatabaseHelper(this).getWritableDatabase();

        backInfoDetail = findViewById(R.id.backInfoDetail);
        infoDetailTitle = findViewById(R.id.info_detail_title);
        infoDetailIdDate = findViewById(R.id.info_detail_id_date);
        infoDetailContent = findViewById(R.id.info_detail_content);

        Intent intent = getIntent();
        int ino = Integer.parseInt(intent.getStringExtra("ino"));

        String selectAllInfo = "select * from information"
                                + " where ino=" + ino;
        Cursor cursor = database.rawQuery(selectAllInfo, null);
        cursor.moveToNext();

        String date = cursor.getString(6);
        date = date.substring(0, date.indexOf(' '));
        infoDetailTitle.setText(cursor.getString(2));
        infoDetailContent.setText(cursor.getString(3));
        infoDetailIdDate.setText(cursor.getString(1) + "   " + date);


        backInfoDetail.setOnClickListener(new View.OnClickListener() {
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
