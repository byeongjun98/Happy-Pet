package com.example.happypet;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class InfoAdapter extends BaseAdapter {
    ArrayList<InfoData> items = new ArrayList<>();
    SQLiteDatabase sqLiteDatabase;

    public InfoAdapter(SQLiteDatabase sqLiteDatabase) {
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

    public void addItem(InfoData infoData) {
        items.add(infoData);
    }

    public void clear() {
        items.clear();
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.info_item, viewGroup, false);

        RelativeLayout infoLayout = view.findViewById(R.id.infoLayout);
        TextView title = view.findViewById(R.id.info_item_title);
        TextView idDate = view.findViewById(R.id.info_item_id_date);

        InfoData infoData = items.get(i);

        title.setText(infoData.getTitle());
        idDate.setText(infoData.getP_id() + "   " + infoData.getMk_date());

        infoLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), InfoDetailActivity.class);
                intent.putExtra("ino", infoData.getIno()+ "");
                view.getContext().startActivity(intent);
            }
        });

        return view;
    }
}
