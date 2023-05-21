package com.example.happypet;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class UserFindActivity extends AppCompatActivity {
    ImageButton back_user_find;
    TextView user_find_type, type_comment;
    EditText user_find_type_form;
    Button user_find_next;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_find);

        Intent intent = getIntent();
        String type = intent.getStringExtra("type");

        back_user_find = findViewById(R.id.back_user_find);
        user_find_type = findViewById(R.id.user_find_type);
        type_comment = findViewById(R.id.type_comment);
        user_find_type_form = findViewById(R.id.user_find_type_form);
        user_find_next = findViewById(R.id.user_find_next);

        user_find_type.setText(type + " 찾기");

        if(type.equals("아이디")) {
            type_comment.setText("이름을 입력해주세요.");
            user_find_type_form.setHint("이름");
        } else if(type.equals("비밀번호")) {
            type_comment.setText("비밀번호를 찾고자하는\n아이디를 입력해주세요.");
            user_find_type_form.setHint("아이디");
        }

        setListener();
    }

    void setListener() {
        back_user_find.setOnClickListener(new View.OnClickListener() {
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
