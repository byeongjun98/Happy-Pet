package com.example.happypet;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
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
    ImageButton back_doctor_detail;
    TextView doctor_detail_hospital, doctor_detail_hospital_name, doctor_detail_name, doctor_detail_subject;
    RatingBar doctor_detail_rating;
    ViewPager pager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_detail);

        back_doctor_detail = findViewById(R.id.back_doctor_detail);
        doctor_detail_hospital = findViewById(R.id.doctor_detail_hospital);
        doctor_detail_hospital_name = findViewById(R.id.doctor_detail_hospital_name);
        doctor_detail_subject = findViewById(R.id.doctor_detail_subject);
        doctor_detail_rating = findViewById(R.id.doctor_detail_rating);
        doctor_detail_name = findViewById(R.id.doctor_detail_name);

        Intent intent = getIntent();
        int dno = intent.getIntExtra("dno", 0);
        String doctor_name = intent.getStringExtra("name");
        String hospital = intent.getStringExtra("hospital");
        String subject = intent.getStringExtra("subject");
        String rating = intent.getStringExtra("rating");

        doctor_detail_hospital.setText(hospital);
        doctor_detail_hospital_name.setText(hospital);
        doctor_detail_subject.setText(subject);
        doctor_detail_name.setText(doctor_name);
        doctor_detail_rating.setRating(Float.parseFloat(rating));

        pager = findViewById(R.id.pager);
        pager.setOffscreenPageLimit(3);

        MyPagerAdapter adapter = new MyPagerAdapter(getSupportFragmentManager());

        DoctorDetailFragment doctorDetailFragment = new DoctorDetailFragment();
        adapter.addItem(doctorDetailFragment);

        ReviewFragment reviewFragment = new ReviewFragment();
        adapter.addItem(reviewFragment);

        FreeConsultingFragment freeConsultingFragment = new FreeConsultingFragment();
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

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            String title = "";

            switch (position) {
                case 0:
                    title = "의사상세";
                    break;
                case 1:
                    title = "후기";
                    break;
                case 2:
                    title = "무료 상담";
                    break;
            }

            return title;
        }
    }
}
