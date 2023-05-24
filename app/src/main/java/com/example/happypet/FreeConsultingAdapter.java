package com.example.happypet;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class FreeConsultingAdapter extends BaseAdapter {
    ArrayList<FreeConsultingData> items = new ArrayList<>();
    SQLiteDatabase sqLiteDatabase;

    public FreeConsultingAdapter(SQLiteDatabase sqLiteDatabase) {
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
        view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.free_consulting_item, viewGroup, false);

        LinearLayout free_consulting_layout = view.findViewById(R.id.free_consulting_layout);
        TextView free_consulting_title = view.findViewById(R.id.free_consulting_title);
        TextView free_consulting_question = view.findViewById(R.id.free_consulting_question);
        TextView free_consulting_answer = view.findViewById(R.id.free_consulting_answer);
        ImageView free_consulting_img = view.findViewById(R.id.free_consulting_img);



        FreeConsultingData freeConsultingData = items.get(i);
        String category = freeConsultingData.getCategory();

        String select_free_consulting_comment = "select * from free_consulting_comment where fno = " + freeConsultingData.getFno();
        Cursor cursor = sqLiteDatabase.rawQuery(select_free_consulting_comment, null);
        if(cursor.getCount() > 0) {
            free_consulting_answer.setTextColor(Color.parseColor("#E3942F"));
            free_consulting_answer.setBackgroundColor(Color.parseColor("#FFF7EA"));
            free_consulting_answer.setText("답변 완료");
        }

        switch(category) {
            case "medicine":
                free_consulting_img.setImageResource(R.drawable.kidneys);
                break;
            case "dentist":
                free_consulting_img.setImageResource(R.drawable.dentist);
                break;
            case "surgery":
                free_consulting_img.setImageResource(R.drawable.scissors);
                break;
            case "ophthalmology":
                free_consulting_img.setImageResource(R.drawable.vision);
                break;
        }

        free_consulting_title.setText(freeConsultingData.getTitle());
        free_consulting_question.setText(freeConsultingData.getQuestion());

        free_consulting_layout.setOnClickListener(new View.OnClickListener() {
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
}
