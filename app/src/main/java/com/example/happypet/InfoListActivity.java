package com.example.happypet;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;

import androidx.annotation.Nullable;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class InfoListActivity extends Activity {
    SQLiteDatabase database;
    ImageButton backInfo;
    ListView listInfo;
    FloatingActionButton infoWrite;
    InfoAdapter infoAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_infolist);

        backInfo = findViewById(R.id.backInfo);
        listInfo = findViewById(R.id.listInfo);
        infoWrite = findViewById(R.id.info_write_btn);

        database = DatabaseHelper.getDatabaseHelper(this).getWritableDatabase();
        infoAdapter = new InfoAdapter(database);
        listInfo.setAdapter(infoAdapter);

        String selectAllInfo = "select * from information";
        Cursor cursor = database.rawQuery(selectAllInfo, null);

        while(cursor.moveToNext()) {
            int ino = cursor.getInt(0);
            String p_id = cursor.getString(1);
            String title = cursor.getString(2);
            String content = cursor.getString(3);
            String mk_date = cursor.getString(4);
            int idx = mk_date.indexOf(' ');
            mk_date = mk_date.substring(0, idx);

            InfoData infoData = new InfoData(ino, p_id, title, content, mk_date);
            infoAdapter.addItem(infoData);
        }

        backInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                overridePendingTransition(R.anim.fadein, R.anim.fadeout);
            }
        });

        infoWrite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplication(), InfoWriteActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.activity_slide_anim, R.anim.activity_none);
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
