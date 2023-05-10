package com.example.happypet;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.opengl.Visibility;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class InmyInfoActivity extends Activity {
    DatabaseHelper dbHelper;

    SQLiteDatabase database;

    TextView user_phone_number_on, user_birth_day_on, user_name_on;
    EditText user_name;
    EditText user_tel;
    EditText user_birth;

    LinearLayout my_info_top, my_info_bottom;



    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.inmyinfo);

        dbHelper = DatabaseHelper.getDatabaseHelper(this);

        database = dbHelper.getWritableDatabase();

        user_name = findViewById(R.id.user_name);
        user_tel = findViewById(R.id.user_phone_number);
        user_birth = findViewById(R.id.user_birth_day);
        user_phone_number_on = findViewById(R.id.user_phone_number_on);
        user_birth_day_on = findViewById(R.id.user_birth_day_on);
        user_name_on = findViewById(R.id.user_name_on);

        my_info_top = findViewById(R.id.my_info_top);
        my_info_bottom = findViewById(R.id.my_info_bottom);

        Button button = findViewById(R.id.my_info_insert_button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

//                if(user_name == null){
//                    Toast.makeText(getApplicationContext(),"이름을 입력해주세요", Toast.LENGTH_SHORT).show();
//                } else if (user_tel == null) {
//                    Toast.makeText(getApplicationContext(),"전화번호를 입력해주세요", Toast.LENGTH_SHORT).show();
//                } else if (user_birth == null) {
//                    Toast.makeText(getApplicationContext(),"생년월일을 입력해주세요", Toast.LENGTH_SHORT).show();
//                }

                String username = user_name.getText().toString();
                String usertel = user_tel.getText().toString();
                String userbirth = user_birth.getText().toString();

                String my_info_sql = " insert into " + "my_info" + "(user_name, user_tel, user_birth) " +
                        "values" +
                        " ('" + username + "', '" + usertel + "','" + userbirth + "') ";
                database.execSQL(my_info_sql);
                select_my_info();
            }

        });

        select_my_info();

    }
    void select_my_info(){
        String select_my_info = " select * from my_info";
        Cursor cursor = database.rawQuery(select_my_info, null);
        if(cursor.getCount() != 0) {
            my_info_bottom.setVisibility(View.VISIBLE);
            my_info_top.setVisibility(View.GONE);
            cursor.moveToNext();

            String user_name = cursor.getString(0);
            String user_tel = cursor.getString(1);
            String user_bir = cursor.getString(2);

            user_name_on.setText(user_name);
            user_phone_number_on.setText(user_tel);
            user_birth_day_on.setText(user_bir);

        }
        else{
            return;
        }

    }
}