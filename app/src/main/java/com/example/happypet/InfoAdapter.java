package com.example.happypet;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

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
        TextView commentCnt = view.findViewById(R.id.info_detail_comment_cnt);
        ImageView info_item_img = view.findViewById(R.id.info_item_img);

        InfoData infoData = items.get(i);

        String img = infoData.getImg();

        title.setText(infoData.getTitle());
        idDate.setText(infoData.getP_id() + "   " + infoData.getMk_date());

        if(img.length() != 0 || img != null) {
            info_item_img.setVisibility(View.VISIBLE);
            switch (img) {
                case "image1":
                    info_item_img.setImageResource(R.drawable.image1);
                    break;
                case "image2":
                    info_item_img.setImageResource(R.drawable.image2);
                    break;
                case "image3":
                    info_item_img.setImageResource(R.drawable.image3);
                    break;
                case "image4":
                    info_item_img.setImageResource(R.drawable.image4);
                    break;
            }
        }
        String selectAllComment = "select * from comment where ino=" + infoData.getIno();
        Cursor cursor = sqLiteDatabase.rawQuery(selectAllComment, null);

        commentCnt.setText("댓글 " + cursor.getCount());

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
