package com.example.happypet;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

public class FragmentUser extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_user, container, false);

        LinearLayout adresslayout = rootView.findViewById(R.id.adressbutton);
        adresslayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity().getApplicationContext(),InAddressActivity.class);
                startActivity(intent);
            }
        });

        LinearLayout creaditlaydout = rootView.findViewById(R.id.creaditsetbutton);
        creaditlaydout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity().getApplicationContext(),IncreditActivity.class);
                startActivity(intent);
            }
        });
        LinearLayout myinfolayout = rootView.findViewById(R.id.myinfobutton);
        myinfolayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity().getApplicationContext(),InmyInfoActivity.class);
                startActivity(intent);
            }
        });
        Button button3 = rootView.findViewById(R.id.myReview);
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity().getApplicationContext(),ReviewActicity.class);
                startActivity(intent);
            }
        });

        Button button4 = rootView.findViewById(R.id.setting);
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity().getApplicationContext(),SettingActivity.class);
                startActivity(intent);
            }
        });
        Button button5 = rootView.findViewById(R.id.notice);
        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity().getApplicationContext(),NoticeActivity.class);
                startActivity(intent);
            }
        });

        return rootView;
    }
}
