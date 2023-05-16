// MainActivity.java
package com.example.happypet;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.happypet.R;

import java.util.ArrayList;
import java.util.List;

public class NoticeActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private NoticeAdapter noticeAdapter;
    private List<Notice> noticeList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notice);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        noticeList = new ArrayList<>();
        // 공지사항 데이터를 가져와서 noticeList에 추가하는 로직이 들어갈 수 있습니다.
        // 예시로 더미 데이터를 추가합니다.
        noticeList.add(new Notice("제목 1", "내용 1", "2023-05-17"));
        noticeList.add(new Notice("제목 2", "내용 2", "2023-05-16"));

        noticeAdapter = new NoticeAdapter(noticeList,this);
        recyclerView.setAdapter(noticeAdapter);
    }
}
