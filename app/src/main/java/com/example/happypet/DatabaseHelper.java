package com.example.happypet;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static DatabaseHelper databaseHelper;
    private SQLiteDatabase database;
    private Context context;
    public static String NAME = "HappyPet.db";
    public static int VERSION = 1;

    private DatabaseHelper(Context context) {
        super(context, NAME, null, VERSION);
        this.context = context;
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

//        database = getDatabaseHelper(context).getWritableDatabase();

        String user_create_sql = "create table if not exists user(" +
                " id text PRIMARY KEY, " +
                " pwd text, " +
                " nickname text, " +
                " email text, " +
                " reg_date timestamp default current_timestamp)";

        String information_create_sql = "create table if not exists information(" +
                " ino integer PRIMARY KEY autoincrement, " +
                " p_id text, " +
                " title text, " +
                " content text, " +
                " mk_date timestamp default current_timestamp)";

//        String insert_sql1 = "insert into " + "information"
//                + "(p_id, title, content) "
//                + " values "
//                + " (aaaa, 캣타워가생겼어요, ㅇㅁㄴㅇㅁㄴㅇㅁㄴㅇ)";
//
//        String insert_sql2 = "insert into " + "information"
//                + "(p_id, title, content) "
//                + " values "
//                + " (aaaa, 우리거북이가아파요, ㅇㅁㄴㅇㅁㄴㅇㅁㄴㅇ)";
//
//        String insert_sql3 = "insert into " + "information"
//                + "(p_id, title, content) "
//                + " values "
//                + " (aaaa, ㅎㅎ, ㅇㅁㄴㅇㅁㄴㅇㅁㄴㅇ)";
//
//        String insert_sql4 = "insert into " + "information"
//                + "(p_id, title, content) "
//                + " values "
//                + " (aaaa, 강아지+고양이, ㅇㅁㄴㅇㅁㄴㅇㅁㄴㅇ)";

        sqLiteDatabase.execSQL(user_create_sql);
        sqLiteDatabase.execSQL(information_create_sql);

//        database.execSQL(insert_sql1);
//        database.execSQL(insert_sql2);
//        database.execSQL(insert_sql3);
//        database.execSQL(insert_sql4);

        Log.d("database exec", "데이터베이스 실행");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        Log.d("onUpgrade", i + " -> " + i1);
    }
}
