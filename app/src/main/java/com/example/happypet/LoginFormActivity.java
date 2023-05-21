package com.example.happypet;

import android.content.Intent;
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
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

public class LoginFormActivity extends AppCompatActivity {
    SQLiteDatabase database;

    ImageButton back_login_form;
    EditText login_form_id, login_form_pwd;
    TextView id_find, pwd_find, sign_up_btn, id_pwd_check;
    ImageView login_form_id_cancel, login_form_pwd_cancel;
    Button sign_in_btn;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_form);

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        database = DatabaseHelper.getDatabaseHelper(this).getWritableDatabase();

        back_login_form = findViewById(R.id.back_login_form);
        login_form_id = findViewById(R.id.login_form_id);
        login_form_pwd = findViewById(R.id.login_form_pwd);
        id_find = findViewById(R.id.id_find);
        pwd_find = findViewById(R.id.pwd_find);
        sign_up_btn = findViewById(R.id.sign_up_btn);
        id_pwd_check = findViewById(R.id.id_pwd_check);
        login_form_id_cancel = findViewById(R.id.login_form_id_cancel);
        login_form_pwd_cancel = findViewById(R.id.login_form_pwd_cancel);
        sign_in_btn = findViewById(R.id.sign_in_btn);

        setListener();
    }

    void setListener() {
        back_login_form.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                overridePendingTransition(R.anim.fadein, R.anim.fadeout);
            }
        });

        login_form_id.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                String str = login_form_id.getText().toString();
                if(str.length() == 0) {
                    login_form_id_cancel.setVisibility(View.GONE);
                } else {
                    login_form_id_cancel.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {}
        });

        login_form_pwd.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                String str = login_form_pwd.getText().toString();
                if(str.length() == 0) {
                    login_form_pwd_cancel.setVisibility(View.GONE);
                } else {
                    login_form_pwd_cancel.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {}
        });

        login_form_id_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                login_form_id.setText("");
            }
        });

        login_form_pwd_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                login_form_pwd.setText("");
            }
        });

        id_find.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginFormActivity.this, UserFindActivity.class);
                intent.putExtra("type", "아이디");
                startActivity(intent);
            }
        });

        pwd_find.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginFormActivity.this, UserFindActivity.class);
                intent.putExtra("type", "비밀번호");
                startActivity(intent);
            }
        });

        sign_up_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginFormActivity.this, SignUpActivity.class);
                startActivity(intent);
            }
        });

        sign_in_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String id = login_form_id.getText().toString();
                String pwd = login_form_pwd.getText().toString();

                if(id.length() == 0) {
                    id_pwd_check.setText("아이디를 입력해주세요.");
                    id_pwd_check.setVisibility(View.VISIBLE);
                    login_form_id.requestFocus();
                    return;
                }
                if(pwd.length() == 0) {
                    id_pwd_check.setText("비밀번호를 입력해주세요.");
                    id_pwd_check.setVisibility(View.VISIBLE);
                    login_form_pwd.requestFocus();
                    return;
                }

                String select_user = "select * from user where id='" + id + "'";

                Cursor cursor = database.rawQuery(select_user, null);
                if(cursor.getCount() != 1) {
                    id_pwd_check.setText("아이디 또는 비밀번호를 잘못 입력하셨습니다.\n입력하신 내용을 다시 확인해주세요.");
                    id_pwd_check.setVisibility(View.VISIBLE);
                    return;
                }

                cursor.moveToNext();
                String password = cursor.getString(1);
                if(!password.equals(pwd)) {
                    id_pwd_check.setText("아이디 또는 비밀번호를 잘못 입력하셨습니다.\n입력하신 내용을 다시 확인해주세요.");
                    id_pwd_check.setVisibility(View.VISIBLE);
                    return;
                }

                SaveSharedPreference.setUserName(LoginFormActivity.this, id);

                finish();
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
