package com.example.happypet;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class FreeConsultingFragment extends Fragment {
    DatabaseHelper dbHelper;
    SQLiteDatabase database;

    ListView frag_fc_list;
    FreeConsultingFragAdapter adapter;

    String doctor_name;

    public FreeConsultingFragment(String doctor_name) {
        this.doctor_name = doctor_name;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup)inflater.inflate(R.layout.fragment_free_consulting, container, false);

        dbHelper = DatabaseHelper.getDatabaseHelper(getContext());
        database = dbHelper.getWritableDatabase();

        frag_fc_list = rootView.findViewById(R.id.frag_fc_list);
        adapter = new FreeConsultingFragAdapter(database);
        frag_fc_list.setAdapter(adapter);

        select_fc_comment();

        return rootView;
    }

    void select_fc_comment() {
        adapter.clear();

        String selectFreeConsultingComment = "select * from free_consulting_comment where commenter='" + doctor_name + "'";
        Cursor cursor = database.rawQuery(selectFreeConsultingComment, null);

        while (cursor.moveToNext()) {
            int fcno = cursor.getInt(0);
            int fno = cursor.getInt(1);
            String comment = cursor.getString(2);
            String commenter = cursor.getString(3);
            String reg_date = cursor.getString(4);
            int idx = reg_date.indexOf(' ');
            reg_date = reg_date.substring(0, idx);

            FreeConsultingCommentData freeConsultingCommentData = new FreeConsultingCommentData(fcno, fno, comment, commenter, reg_date);
            adapter.addItem(freeConsultingCommentData);

            adapter.notifyDataSetChanged();
        }
    }
}
