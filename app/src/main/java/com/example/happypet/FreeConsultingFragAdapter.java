package com.example.happypet;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class FreeConsultingFragAdapter extends BaseAdapter {
    ArrayList<FreeConsultingCommentData> items = new ArrayList<>();
    SQLiteDatabase sqLiteDatabase;

    public FreeConsultingFragAdapter(SQLiteDatabase sqLiteDatabase) {
        this.sqLiteDatabase = sqLiteDatabase;
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Object getItem(int i) {
        return items.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    public void addItem(FreeConsultingCommentData freeConsultingCommentData) {
        items.add(freeConsultingCommentData);
    }

    public void clear() {
        items.clear();
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.frag_fc_item, viewGroup, false);

        TextView fc_item_subject = view.findViewById(R.id.fc_item_subject);
        TextView fc_item_title = view.findViewById(R.id.fc_item_title);
        TextView fc_item_question = view.findViewById(R.id.fc_item_question);
        TextView fc_item_reg_date = view.findViewById(R.id.fc_item_reg_date);
        TextView fc_item_doctor_name = view.findViewById(R.id.fc_item_doctor_name);
        TextView fc_item_comment_reg_date = view.findViewById(R.id.fc_item_comment_reg_date);
        TextView fc_item_answer = view.findViewById(R.id.fc_item_answer);

        FreeConsultingCommentData freeConsultingCommentData = items.get(i);

        fc_item_doctor_name.setText(freeConsultingCommentData.getCommenter() + " 의사");
        fc_item_comment_reg_date.setText(freeConsultingCommentData.getReg_date());
        String tmp = "A. " + freeConsultingCommentData.getComment();
        SpannableString spannableString = new SpannableString(tmp);
        spannableString.setSpan(new ForegroundColorSpan(Color.parseColor("#00AAFF")), 0, 2, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        fc_item_answer.setText(spannableString);

        String select_free_consulting = "select * from free_consulting where fno=" + freeConsultingCommentData.getFno();

        Cursor cursor = sqLiteDatabase.rawQuery(select_free_consulting, null);
        Log.d("asdfasfas", cursor.getCount() + "");
        while(cursor.moveToNext()) {
            String title = cursor.getString(2);
            String question = cursor.getString(3);
            String subject = cursor.getString(4);
            String reg_date = cursor.getString(6);
            int idx = reg_date.indexOf(' ');
            reg_date = reg_date.substring(0, idx);

            switch(subject) {
                case "medicine":
                    subject = "내과";
                    break;
                case "dentist":
                    subject = "치과";
                    break;
                case "surgery":
                    subject = "외과";
                    break;
                case "ophthalmology":
                    subject = "안과";
                    break;
                default:
                    subject = "기타";
            }

            fc_item_subject.setText(subject);
            fc_item_title.setText(title);
            String tmp2 = "Q. " + question;
            SpannableString spannableString2 = new SpannableString(tmp2);
            spannableString.setSpan(new ForegroundColorSpan(Color.parseColor("#ff9500")), 0, 2, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

            fc_item_question.setText(spannableString2);
            fc_item_reg_date.setText(reg_date);
        }

        return view;
    }
}
