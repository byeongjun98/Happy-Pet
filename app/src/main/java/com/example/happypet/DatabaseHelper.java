package com.example.happypet;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static DatabaseHelper databaseHelper;
    public static String NAME = "HappyPet.db";
    public static int VERSION = 1;

    private DatabaseHelper(Context context) {
        super(context, NAME, null, VERSION);
    }

    public static DatabaseHelper getDatabaseHelper(Context context) {
        if(databaseHelper != null) {
            return databaseHelper;
        }

        return new DatabaseHelper(context);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        Log.d("database", "데이터베이스 생성");

//        String todoList_create_sql = "create table if not exists todolist(" +
//                " cnt integer PRIMARY KEY autoincrement, " +
//                " title text, " +
//                " mk_date timestamp default current_timestamp)";
//
//        String todo_create_sql = "create table if not exists todo(" +
//                " n_cnt integer PRIMARY KEY autoincrement, " +
//                " p_cnt integer, " +
//                " content text, " +
//                " complete_yn text default  'n', " +
//                " mk_date timestamp default current_timestamp)";
//
//        sqLiteDatabase.execSQL(todoList_create_sql);
//        sqLiteDatabase.execSQL(todo_create_sql);
        Log.d("database exec", "데이터베이스 실행");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        Log.d("onUpgrade", i + " -> " + i1);
    }
}
