package com.example.happypet;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.media.Rating;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class ReviewFragment extends Fragment {
    SQLiteDatabase database;

    LinearLayout[] doctor_review_layout = new LinearLayout[5];
    TextView[] doctor_review_date = new TextView[5];
    TextView[] doctor_review_content = new TextView[5];
    RatingBar[] doctor_review_rating = new RatingBar[5];

    int[] doctor_review_layout_id = { R.id.doctor_review_layout1, R.id.doctor_review_layout2, R.id.doctor_review_layout3,
                                        R.id.doctor_review_layout4, R.id.doctor_review_layout5 };
    int[] doctor_review_date_id = { R.id.doctor_review_date1, R.id.doctor_review_date2, R.id.doctor_review_date3,
                                        R.id.doctor_review_date4, R.id.doctor_review_date5 };
    int[] doctor_review_content_id = { R.id.doctor_review_content1, R.id.doctor_review_content2, R.id.doctor_review_content3,
                                        R.id.doctor_review_content4, R.id.doctor_review_content5 };
    int[] doctor_review_rating_id = { R.id.doctor_review_rating1, R.id.doctor_review_rating2, R.id.doctor_review_rating3,
                                        R.id.doctor_review_rating4, R.id.doctor_review_rating5 };

    int dno;

    public ReviewFragment(int dno) {
        this.dno = dno;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup)inflater.inflate(R.layout.fragment_review, container, false);

        database = DatabaseHelper.getDatabaseHelper(rootView.getContext()).getWritableDatabase();

        for(int i=0; i<doctor_review_layout.length; i++) {
            doctor_review_layout[i] = rootView.findViewById(doctor_review_layout_id[i]);
            doctor_review_date[i] = rootView.findViewById(doctor_review_date_id[i]);
            doctor_review_content[i] = rootView.findViewById(doctor_review_content_id[i]);
            doctor_review_rating[i] = rootView.findViewById(doctor_review_rating_id[i]);
        }

        select_customer_num_cnt(dno);

        return rootView;
    }

    void select_customer_num_cnt(int dno) {
        String customer_num_cnt = "select * from doctor_review where dno = " + dno + " LIMIT 5";
        Cursor cursor = database.rawQuery(customer_num_cnt, null);

        for(int i=0; i<cursor.getCount(); i++) {
            cursor.moveToNext();

            doctor_review_layout[i].setVisibility(View.VISIBLE);
            String mk_date = cursor.getString(4);
            int idx = mk_date.indexOf(' ');
            mk_date = mk_date.substring(0, idx);
            doctor_review_date[i].setText(mk_date);
            doctor_review_rating[i].setRating(Float.parseFloat(cursor.getString(2)));
            doctor_review_content[i].setText(cursor.getString(3));
        }
    }
}
