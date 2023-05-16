package com.example.happypet;

// NoticeDetailActivity.java

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

public class NoticeDetailActivity extends AppCompatActivity {

    private TextView textTitle, textContent, textDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notice_detail);

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();


        textTitle = findViewById(R.id.textTitle);
        textContent = findViewById(R.id.textContent);
        textDate = findViewById(R.id.textDate);

        // Intent에서 공지사항의 제목, 내용, 날짜 정보를 가져와서 표시
        Intent intent = getIntent();
        if (intent != null) {
            String title = intent.getStringExtra("title");
            String content = intent.getStringExtra("content");
            String date = intent.getStringExtra("date");

            textTitle.setText(title);
            textContent.setText(content);
            textDate.setText(date);
        }
    }
}

