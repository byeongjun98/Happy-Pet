package com.example.happypet;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

public class FreeConsultingListActivity extends AppCompatActivity {
    DatabaseHelper dbHelper;
    SQLiteDatabase database;

    Button fc_list_question;
    ListView fc_list_list;
    ImageButton back_fc_list;

    FreeConsultingListAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_free_consulting_list);

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        dbHelper = DatabaseHelper.getDatabaseHelper(this);
        database = dbHelper.getWritableDatabase();

        back_fc_list = findViewById(R.id.back_fc_list);
        fc_list_question = findViewById(R.id.fc_list_question);
        fc_list_list = findViewById(R.id.fc_list_list);

        adapter = new FreeConsultingListAdapter(database);
        fc_list_list.setAdapter(adapter);

        back_fc_list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                overridePendingTransition(R.anim.fadein, R.anim.fadeout);
            }
        });

        fc_list_question.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(SaveSharedPreference.getUserName(FreeConsultingListActivity.this).length() == 0) {
                    Intent intent = new Intent(FreeConsultingListActivity.this, LoginFormActivity.class);
                    startActivity(intent);
                    return;
                }

                Intent intent = new Intent(FreeConsultingListActivity.this, RealtimeConsultingActivity.class);
                startActivity(intent);
            }
        });

        select_free_consulting();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
        overridePendingTransition(R.anim.fadein, R.anim.fadeout);
    }

    void select_free_consulting() {
        adapter.clear();

        String selectFreeConsulting = "select * from free_consulting order by reg_date desc";
        Cursor cursor = database.rawQuery(selectFreeConsulting, null);

        while(cursor.moveToNext()) {
            int fno = cursor.getInt(0);
            String p_id = cursor.getString(1);
            String title = cursor.getString(2);
            String question = cursor.getString(3);
            String category = cursor.getString(4);
            String image = cursor.getString(5);
            String reg_date = cursor.getString(6);
            int idx = reg_date.indexOf(' ');
            reg_date = reg_date.substring(0, idx);

            FreeConsultingData freeConsultingData = new FreeConsultingData(fno, p_id, title, question, category, image, reg_date);
            adapter.addItem(freeConsultingData);

            adapter.notifyDataSetChanged();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

        select_free_consulting();
    }
}
