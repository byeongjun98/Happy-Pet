package com.example.happypet;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import java.util.ArrayList;

public class DoctorDetailActivity extends AppCompatActivity {
    SQLiteDatabase database;
    ImageButton back_doctor_detail;
    TextView doctor_detail_hospital, doctor_detail_hospital_name, doctor_detail_name, doctor_detail_subject,
            doctor_detail_rating_text, doctor_detail_add_doctor_text, doctor_detail_reg_doctor, doctor_detail_review_cnt;
    LinearLayout doctor_detail_add_doctor;
    RatingBar doctor_detail_rating;
    ViewPager pager;

    TextView[] doctor_detail_text = new TextView[3];

    int dno, customer_num;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_detail);

        database = DatabaseHelper.getDatabaseHelper(this).getWritableDatabase();

        back_doctor_detail            = findViewById(R.id.back_doctor_detail);
        doctor_detail_hospital        = findViewById(R.id.doctor_detail_hospital);
        doctor_detail_hospital_name   = findViewById(R.id.doctor_detail_hospital_name);
        doctor_detail_subject         = findViewById(R.id.doctor_detail_subject);
        doctor_detail_rating          = findViewById(R.id.doctor_detail_rating);
        doctor_detail_name            = findViewById(R.id.doctor_detail_name);
        doctor_detail_rating_text     = findViewById(R.id.doctor_detail_rating_text);
        doctor_detail_add_doctor_text = findViewById(R.id.doctor_detail_add_doctor_text);
        doctor_detail_add_doctor      = findViewById(R.id.doctor_detail_add_doctor);
        doctor_detail_reg_doctor      = findViewById(R.id.doctor_detail_reg_doctor);
        doctor_detail_review_cnt      = findViewById(R.id.doctor_detail_review_cnt);
        doctor_detail_text[0]         = findViewById(R.id.doctor_detail_text1);
        doctor_detail_text[1]         = findViewById(R.id.doctor_detail_text2);
        doctor_detail_text[2]         = findViewById(R.id.doctor_detail_text3);


        Intent intent = getIntent();
        dno = intent.getIntExtra("dno", 0);
        String doctor_name = intent.getStringExtra("name");
        String hospital    = intent.getStringExtra("hospital");
        String subject     = intent.getStringExtra("subject");
        String rating      = intent.getStringExtra("rating");
        customer_num       = intent.getIntExtra("customer_num", 0);

        doctor_detail_hospital.setText(hospital);
        doctor_detail_hospital_name.setText(hospital);
        doctor_detail_subject.setText(subject);
        doctor_detail_name.setText(doctor_name);
        doctor_detail_rating_text.setText(rating);
        doctor_detail_rating.setRating(Float.parseFloat(rating));
        doctor_detail_add_doctor_text.setText(customer_num + "명 등록중");
        doctor_detail_review_cnt.setText("후기 " + select_customer_num_cnt(dno) + "개");

        pager = findViewById(R.id.pager);
        pager.setOffscreenPageLimit(3);

        MyPagerAdapter adapter = new MyPagerAdapter(getSupportFragmentManager());

        DoctorDetailFragment doctorDetailFragment = new DoctorDetailFragment(hospital);
        adapter.addItem(doctorDetailFragment);

        ReviewFragment reviewFragment = new ReviewFragment(dno);
        adapter.addItem(reviewFragment);

        FreeConsultingFragment freeConsultingFragment = new FreeConsultingFragment(doctor_name);
        adapter.addItem(freeConsultingFragment);

        pager.setAdapter(adapter);

        setListener();
    }

    void setListener() {
        back_doctor_detail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                overridePendingTransition(R.anim.fadein, R.anim.fadeout);
            }
        });

        doctor_detail_add_doctor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String str = doctor_detail_reg_doctor.getText().toString();

                if(str.equals("+ 전담 의사 등록")) {
                    String update_customer_num = "update doctor set "
                            + "customer_num=" + (customer_num+1)
                            + " where dno=" + dno;
                    database.execSQL(update_customer_num);
                    doctor_detail_reg_doctor.setText("등록중!");
                    doctor_detail_add_doctor_text.setText((customer_num + 1) + "명 등록중");
                    customer_num++;
                } else if(str.equals("등록중!")) {
                    String update_customer_num = "update doctor set "
                            + "customer_num=" + (customer_num-1)
                            + " where dno=" + dno;
                    database.execSQL(update_customer_num);
                    doctor_detail_reg_doctor.setText("+ 전담 의사 등록");
                    doctor_detail_add_doctor_text.setText((customer_num - 1) + "명 등록중");
                    customer_num--;
                }
                String update_customer_num = "update doctor set "
                                            + "customer_num=" + (customer_num+1)
                                            + " where dno=" + dno;
                database.execSQL(update_customer_num);
            }
        });

        for(int i=0; i<doctor_detail_text.length; i++) {
            final int idx = i;
            doctor_detail_text[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    for(int j=0; j<doctor_detail_text.length; j++) {
                        doctor_detail_text[j].setTextColor(Color.parseColor("#9F9F9F"));
                        doctor_detail_text[j].setBackground(null);
                        doctor_detail_text[j].setTypeface(null, Typeface.NORMAL);
                    }
                    doctor_detail_text[idx].setTextColor(Color.parseColor("#000000"));
                    doctor_detail_text[idx].setBackgroundResource(R.drawable.border_bottom_line_black);
                    doctor_detail_text[idx].setTypeface(null, Typeface.BOLD);

                    pager.setCurrentItem(idx);
                }
            });
        }

        pager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                doctor_detail_text[position].performClick();
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    int select_customer_num_cnt(int dno) {
        String customer_num_cnt = "select * from doctor_review where dno = " + dno;
        Cursor cursor = database.rawQuery(customer_num_cnt, null);

        return cursor.getCount();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
        overridePendingTransition(R.anim.fadein, R.anim.fadeout);
    }

    class MyPagerAdapter extends FragmentStatePagerAdapter {
        ArrayList<Fragment> items = new ArrayList<>();

        public MyPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        public void addItem(Fragment item) {
            items.add(item);
        }

        @NonNull
        @Override
        public Fragment getItem(int position) {
            return items.get(position);
        }

        @Override
        public int getCount() {
            return items.size();
        }


    }
}
