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
                " category text, " +
                " img text, " +
                " mk_date timestamp default current_timestamp)";

        String comment_create_sql = "create table if not exists comment(" +
                " cno integer PRIMARY KEY autoincrement, " +
                " ino integer, " +
                " comment text, " +
                " commenter text, " +
                " reg_date timestamp default current_timestamp)";

        String doctor_create_sql = "create table if not exists doctor(" +
                " dno integer PRIMARY KEY autoincrement, " +
                " name text, " +
                " star_rating text, " +
                " subject text, " +
                " hospital text, " +
                " customer_num integer)";

        String doctor_review_create_sql = "create table if not exists doctor_review(" +
                " rno integer PRIMARY KEY autoincrement, " +
                " dno integer, " +
                " star_rating text, " +
                " content text, " +
                " reg_date timestamp default current_timestamp)";

        String free_consulting_create_sql = "create table if not exists free_consulting(" +
                " fno integer PRIMARY KEY autoincrement, " +
                " p_id text, " +
                " title text, " +
                " question text, " +
                " category text, " +
                " image text, " +
                " reg_date timestamp default current_timestamp)";

        String free_consulting_comment_create_sql = "create table if not exists free_consulting_comment(" +
                " fcno integer PRIMARY KEY autoincrement, " +
                " fno integer, " +
                " comment text, " +
                " commenter text, " +
                " reg_date timestamp default current_timestamp)";

        String credit_create_sql = "create table if not exists credit(" +
                " card_number text PRIMARY KEY , " +
                " card_password text, " +
                " Birth text, " +
                " expiration_period text, " +
                " card_type text)";

        String my_info_sql = "create table if not exists my_info(" +
                " user_name text PRIMARY KEY , " +
                " user_tel text, " +
                " user_birth text)";

        String review_sql ="create table if not exists review(" +
                " review_title text PRIMARY KEY, " +
                " review_main text, " +
                " name text, " +
                " hospital text, " +
                " star_rating text, " +
                " reg_date timestamp default current_timestamp)";

        sqLiteDatabase.execSQL(user_create_sql);
        sqLiteDatabase.execSQL(information_create_sql);
        sqLiteDatabase.execSQL(comment_create_sql);
        sqLiteDatabase.execSQL(doctor_create_sql);
        sqLiteDatabase.execSQL(doctor_review_create_sql);
        sqLiteDatabase.execSQL(free_consulting_create_sql);
        sqLiteDatabase.execSQL(free_consulting_comment_create_sql);
        sqLiteDatabase.execSQL(credit_create_sql);
        sqLiteDatabase.execSQL(my_info_sql);
        sqLiteDatabase.execSQL(review_sql);

        Log.d("database exec", "데이터베이스 실행");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        Log.d("onUpgrade", i + " -> " + i1);
    }
}
