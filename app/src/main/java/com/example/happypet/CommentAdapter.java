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

public class CommentAdapter extends BaseAdapter {
    ArrayList<CommentData> items = new ArrayList<>();
    SQLiteDatabase sqLiteDatabase;

    public CommentAdapter(SQLiteDatabase sqLiteDatabase) {
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

    public void addItem(CommentData commentData) {
        items.add(commentData);
    }

    public void clear() {
        items.clear();
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.comment_item, viewGroup, false);

        TextView comment_date, comment_id, comment;

        comment_id   = view.findViewById(R.id.comment_id);
        comment_date = view.findViewById(R.id.comment_date);
        comment      = view.findViewById(R.id.comment);

        CommentData commentData = items.get(i);

        comment_id.setText(commentData.getCommenter());
        comment_date.setText(commentData.getDate());
        comment.setText(commentData.getComment());

        return view;
    }
}
