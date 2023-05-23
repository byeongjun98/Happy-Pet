package com.example.happypet;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

public class InfoDetailActivity extends AppCompatActivity {
    ImageButton backInfoDetail, comment_btn;
    EditText comment_edt;
    TextView infoDetailTitle, infoDetailIdDate, infoDetailContent, commentCnt;
    ImageView info_detail_img;
    ListView comment_list;
    SQLiteDatabase database;

    CommentAdapter commentAdapter;

    int ino = 0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_detail);

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        database = DatabaseHelper.getDatabaseHelper(this).getWritableDatabase();

        backInfoDetail = findViewById(R.id.backInfoDetail);
        infoDetailTitle = findViewById(R.id.info_detail_title);
        infoDetailIdDate = findViewById(R.id.info_detail_id_date);
        infoDetailContent = findViewById(R.id.info_detail_content);
        comment_btn = findViewById(R.id.comment_btn);
        comment_edt = findViewById(R.id.comment_edt);
        comment_list = findViewById(R.id.comment_list);
        commentCnt = findViewById(R.id.comment_cnt);
        info_detail_img = findViewById(R.id.info_detail_img);

        commentAdapter = new CommentAdapter(database);
        comment_list.setAdapter(commentAdapter);

        Intent intent = getIntent();
        ino = Integer.parseInt(intent.getStringExtra("ino"));

        selectAllInfo();
        selectAllComment();

        setListener();

    }

    public void selectAllInfo() {
        String selectAllInfo = "select * from information"
                + " where ino=" + ino;
        Cursor cursor = database.rawQuery(selectAllInfo, null);
        cursor.moveToNext();

        String date = cursor.getString(6);
        date = date.substring(0, date.indexOf(' '));
        infoDetailTitle.setText(cursor.getString(2));
        infoDetailContent.setText(cursor.getString(3));
        infoDetailIdDate.setText(cursor.getString(1) + "   " + date);
    }

    public void selectAllComment() {
        commentAdapter.clear();

        String selectAllComment = "select * from comment where ino=" + ino;
        Cursor cursor = database.rawQuery(selectAllComment, null);

        commentCnt.setText("댓글 " + cursor.getCount());

        while(cursor.moveToNext()) {
            String comment = cursor.getString(2);
            String commenter = cursor.getString(3);
            String mk_date = cursor.getString(4);
            int idx = mk_date.indexOf(' ');
            mk_date = mk_date.substring(0, idx);

            CommentData commentData = new CommentData(comment, commenter, mk_date);
            commentAdapter.addItem(commentData);

            commentAdapter.notifyDataSetChanged();
        }
    }

    public void setListener() {
        backInfoDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                overridePendingTransition(R.anim.fadein, R.anim.fadeout);
            }
        });

        comment_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(SaveSharedPreference.getUserName(InfoDetailActivity.this).length() == 0) {
                    Intent intent = new Intent(InfoDetailActivity.this, LoginFormActivity.class);
                    startActivity(intent);
                    return;
                }

                String comment = comment_edt.getText().toString();
                if(comment.equals("") || comment == null) {
                    Toast.makeText(InfoDetailActivity.this, "댓글을 입력해주세요!", Toast.LENGTH_SHORT).show();
                    return;
                }

//                String comment_insert = "insert into " + "comment"
//                        + "(ino, comment, commenter) "
//                        + " values "
//                        + " (" + ino + ", '" + comment + "', '" + commenter + "')";

                String comment_insert = "insert into " + "comment"
                        + "(ino, comment, commenter) "
                        + " values "
                        + " (" + ino + ", '" + comment + "', '" + "aaaa" + "')";

                database.execSQL(comment_insert);

                selectAllComment();
                comment_edt.setText("");
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
