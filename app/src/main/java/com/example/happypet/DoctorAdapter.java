package com.example.happypet;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class DoctorAdapter extends BaseAdapter {
    ArrayList<DoctorData> items = new ArrayList<>();
    SQLiteDatabase sqLiteDatabase;

    public DoctorAdapter(SQLiteDatabase sqLiteDatabase) {
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

    public void addItem(DoctorData doctorData) {
        items.add(doctorData);
    }

    public void clear() {
        items.clear();
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.doctor_item, viewGroup, false);

        TextView doctor_name = view.findViewById(R.id.doctor_name);
        TextView star_rating = view.findViewById(R.id.star_rating);
        TextView hospital_name = view.findViewById(R.id.hospital_name);
        TextView subject_name = view.findViewById(R.id.subject_name);

        DoctorData doctorData = items.get(i);
        doctor_name.setText(doctorData.getName() + " 의사");
        star_rating.setText(doctorData.getStar_rating());
        hospital_name.setText(doctorData.getHospital());

        String subject = doctorData.getSubject();
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
        subject_name.setText(subject);

        return view;
    }
}
