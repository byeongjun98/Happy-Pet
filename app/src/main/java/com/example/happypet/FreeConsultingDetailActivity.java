package com.example.happypet;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

public class FreeConsultingDetailActivity extends AppCompatActivity {
    DatabaseHelper dbHelper;
    SQLiteDatabase database;

    ImageButton back_fc_detail;
    TextView fc_detail_title, fc_detail_p_id, fc_detail_question, fc_detail_keep, fc_detail_answer_cnt;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_free_consulting_detail);

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        dbHelper = DatabaseHelper.getDatabaseHelper(this);
        database = dbHelper.getWritableDatabase();

        Intent intent = getIntent();

        int fno = intent.getIntExtra("fno", 1);
        String title = intent.getStringExtra("title");
        String question = intent.getStringExtra("question");
        String category = intent.getStringExtra("category");
        String reg_date = intent.getStringExtra("reg_date");

        back_fc_detail = findViewById(R.id.back_fc_detail);

        fc_detail_title = findViewById(R.id.fc_detail_title);
        fc_detail_question = findViewById(R.id.fc_detail_question);
        fc_detail_keep = findViewById(R.id.fc_detail_keep);
        fc_detail_answer_cnt = findViewById(R.id.fc_detail_answer_cnt);

        fc_detail_title.setText(title);
        fc_detail_question.setText(question);


        fc_detail_keep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int a = fc_detail_keep.getCurrentTextColor();

                if(a == -27392) {
                    fc_detail_keep.setTextColor(Color.parseColor("#000000"));
                    fc_detail_keep.setText("☆  보관");
                } else {
                    fc_detail_keep.setTextColor(Color.parseColor("#ff9500"));
                    fc_detail_keep.setText("★  보관");
                }

            }
        });

        back_fc_detail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                overridePendingTransition(R.anim.fadein, R.anim.fadeout);
            }
        });

        int cnt = select_fc_detail_answer_cnt(fno);
        fc_detail_answer_cnt.setText(cnt + "개의 답변이 있어요");
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
        overridePendingTransition(R.anim.fadein, R.anim.fadeout);
    }

    int select_fc_detail_answer_cnt(int fno) {
        String selectAnswerCnt = "select * from free_consulting_comment where fno=" + fno;
        Cursor cursor = database.rawQuery(selectAnswerCnt, null);

        return cursor.getCount();
    }
}
