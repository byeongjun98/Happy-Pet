package com.example.happypet;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class FreeConsultingListAdapter extends BaseAdapter {
    ArrayList<FreeConsultingData> items = new ArrayList<>();
    SQLiteDatabase sqLiteDatabase;

    public FreeConsultingListAdapter(SQLiteDatabase sqLiteDatabase) {
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

    public void addItem(FreeConsultingData freeConsultingData) {
        items.add(freeConsultingData);
    }

    public void clear() {
        items.clear();
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.free_consulting_item2, viewGroup, false);

        LinearLayout free_consulting_layout2 = view.findViewById(R.id.free_consulting_layout2);
        TextView free_consulting_title2 = view.findViewById(R.id.free_consulting_title2);
        TextView free_consulting_question2 = view.findViewById(R.id.free_consulting_question2);
        TextView free_consulting_category2 = view.findViewById(R.id.free_consulting_category2);
        TextView free_consulting_cnt2 = view.findViewById(R.id.free_consulting_cnt2);

        FreeConsultingData freeConsultingData = items.get(i);

        free_consulting_title2.setText(freeConsultingData.getTitle());
        free_consulting_question2.setText(freeConsultingData.getQuestion());

        String category = freeConsultingData.getCategory();
        switch(category) {
            case "medicine":
                category = "내과";
                break;
            case "dentist":
                category = "치과";
                break;
            case "surgery":
                category = "외과";
                break;
            case "ophthalmology":
                category = "안과";
                break;
            default:
                category = "기타";
        }
        free_consulting_category2.setText(category);
        free_consulting_cnt2.setText(select_fc_detail_answer_cnt(freeConsultingData.getFno()) + "개의 답변");

        free_consulting_layout2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), FreeConsultingDetailActivity.class);
                intent.putExtra("fno", freeConsultingData.getFno());
                intent.putExtra("p_id", freeConsultingData.getP_id());
                intent.putExtra("title", freeConsultingData.getTitle());
                intent.putExtra("question", freeConsultingData.getQuestion());
                intent.putExtra("category", freeConsultingData.getCategory());
                intent.putExtra("reg_date", freeConsultingData.getReg_date());
                view.getContext().startActivity(intent);
            }
        });

        return view;
    }

    int select_fc_detail_answer_cnt(int fno) {
        String selectAnswerCnt = "select * from free_consulting_comment where fno=" + fno;
        Cursor cursor = sqLiteDatabase.rawQuery(selectAnswerCnt, null);

        return cursor.getCount();
    }
}
