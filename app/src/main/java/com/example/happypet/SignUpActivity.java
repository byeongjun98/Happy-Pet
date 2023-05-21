package com.example.happypet;

import android.app.AlertDialog;
import android.content.DialogInterface;
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
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

public class SignUpActivity extends AppCompatActivity {
    SQLiteDatabase database;

    EditText sign_up_id, sign_up_pwd, sign_up_pwd_dupl, sign_up_nickname;
    TextView sign_up_id_check, sign_up_pwd_check, sign_up_pwd_dupl_check, sign_up_nickname_check;
    Button sign_up;

    boolean bId = false, bPwd = false, bPwdDupl = false, bNickname = false;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        database = DatabaseHelper.getDatabaseHelper(this).getWritableDatabase();

        sign_up_id = findViewById(R.id.sign_up_id);
        sign_up_pwd = findViewById(R.id.sign_up_pwd);
        sign_up_pwd_dupl = findViewById(R.id.sign_up_pwd_dupl);
        sign_up_nickname = findViewById(R.id.sign_up_nickname);
        sign_up_pwd_dupl_check = findViewById(R.id.sign_up_pwd_dupl_check);
        sign_up_id_check = findViewById(R.id.sign_up_id_check);
        sign_up_pwd_check = findViewById(R.id.sign_up_pwd_check);
        sign_up_nickname_check = findViewById(R.id.sign_up_nickname_check);
        sign_up = findViewById(R.id.sign_up);

        setListener();
    }

    void setListener() {
        sign_up_id.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if(!b) {
                    String id = sign_up_id.getText().toString();

                    if(id.length() == 0) {
                        sign_up_id_check.setText("필수 정보입니다.");
                        sign_up_id_check.setTextColor(Color.parseColor("#ff0000"));
                        sign_up_id_check.setVisibility(View.VISIBLE);
                        bId = false;
                        return;
                    }

                    if((5 > id.length()) || (id.length() > 20 )) {
                        sign_up_id_check.setText("5~20자의 영문,특수기호(_)만 사용 가능합니다.");
                        sign_up_id_check.setTextColor(Color.parseColor("#ff0000"));
                        sign_up_id_check.setVisibility(View.VISIBLE);
                        bId = false;
                        return;
                    }

                    String select_user = "select * from user where id='" + id + "'";

                    Cursor cursor = database.rawQuery(select_user, null);

                    if(cursor.getCount() == 1) {
                        sign_up_id_check.setText("중복된 아이디입니다.");
                        sign_up_id_check.setTextColor(Color.parseColor("#ff0000"));
                        sign_up_id_check.setVisibility(View.VISIBLE);
                        bId = false;
                        return;
                    }

                    sign_up_id_check.setText("멋진 아이디입니다!!");
                    sign_up_id_check.setTextColor(Color.parseColor("#0c70f2"));
                    sign_up_id_check.setVisibility(View.VISIBLE);
                    bId = true;
                }
            }
        });

        sign_up_pwd.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if(!b) {
                    String pwd = sign_up_pwd.getText().toString();

                    if(pwd.length() == 0) {
                        sign_up_pwd_check.setText("필수 정보입니다.");
                        sign_up_pwd_check.setVisibility(View.VISIBLE);
                        bPwd = false;
                        return;
                    }

                    if(8 > pwd.length() || pwd.length() > 16) {
                        sign_up_pwd_check.setText("8~16자 영문 대 소문자, 숫자, 특수문자를 사용하세요.");
                        sign_up_pwd_check.setVisibility(View.VISIBLE);
                        bPwd = false;
                        return;
                    }

                    sign_up_pwd_check.setVisibility(View.GONE);
                    bPwd = true;
                }
            }
        });

        sign_up_pwd_dupl.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if(!b) {
                    String pwd = sign_up_pwd.getText().toString();
                    String str = sign_up_pwd_dupl.getText().toString();

                    if(!pwd.equals(str)) {
                        sign_up_pwd_dupl_check.setText("비밀번호가 일치하지 않습니다.");
                        sign_up_pwd_dupl_check.setVisibility(View.VISIBLE);
                        bPwdDupl = false;
                    } else {
                        sign_up_pwd_dupl_check.setVisibility(View.GONE);
                        bPwdDupl = true;
                    }
                }
            }
        });

        sign_up_nickname.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if(!b) {
                    String nickname = sign_up_nickname.getText().toString();

                    if(nickname.length() == 0) {
                        sign_up_nickname_check.setText("필수 정보입니다.");
                        sign_up_nickname_check.setVisibility(View.VISIBLE);
                        sign_up_id_check.setTextColor(Color.parseColor("#ff0000"));
                        bNickname = false;
                        return;
                    }

                    if(4 > nickname.length() || nickname.length() > 20) {
                        sign_up_nickname_check.setText("8~20 한글, 영문자로 입력해 주세요.");
                        sign_up_id_check.setTextColor(Color.parseColor("#ff0000"));
                        sign_up_nickname_check.setVisibility(View.VISIBLE);
                        bNickname = false;
                        return;
                    }

                    sign_up_id_check.setTextColor(Color.parseColor("#0c70f2"));
                    sign_up_nickname_check.setText("멋진 닉네임이네요!!");
                    sign_up_nickname_check.setVisibility(View.VISIBLE);
                    bNickname = true;
                }
            }
        });

        sign_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String id = sign_up_id.getText().toString();
                String pwd = sign_up_pwd.getText().toString();
                String nickname = sign_up_nickname.getText().toString();

                if(bId && bPwd && bPwdDupl && bNickname) {
                    String insert_user = "insert into " + "user"
                            + "(id, pwd, nickname) "
                            + " values "
                            + " ('" + id + "', '" + pwd + "', '" + nickname + "')";

                    database.execSQL(insert_user);

                    AlertDialog.Builder builder = new AlertDialog.Builder(SignUpActivity.this);
                    builder.setTitle("회원가입완료!!");
                    builder.setPositiveButton("확인", new DialogInterface.OnClickListener(){
                        @Override
                        public void onClick(DialogInterface dialog, int id) {
                            finish();
                        }
                    });
                    builder.show();
                } else {
                    Toast.makeText(SignUpActivity.this, "입력 양식을 확인해 주세요.", Toast.LENGTH_SHORT).show();
                }
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
