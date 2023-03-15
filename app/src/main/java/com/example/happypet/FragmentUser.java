package com.example.happypet;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

public class FragmentUser extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_user, container, false);

        Button button = rootView.findViewById(R.id.onAdress);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity().getApplicationContext(),InAddressActivity.class);
                startActivity(intent);
            }
        });

        Button button1 = rootView.findViewById(R.id.onCreadit);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity().getApplicationContext(),IncreditActivity.class);
                startActivity(intent);
            }
        });
        Button button2 = rootView.findViewById(R.id.onMyInfo);
        button2.setOnClickListener(new View.OnClickListener() {
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
