package com.example.happypet;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class DoctorDetailFragment extends Fragment {
    TextView frag_doctor_detail_hospital;

    String hospital;

    public DoctorDetailFragment(String hospital) {
        this.hospital = hospital;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup)inflater.inflate(R.layout.fragment_doctor_detail, container, false);

        frag_doctor_detail_hospital = rootView.findViewById(R.id.frag_doctor_detail_hospital);

        frag_doctor_detail_hospital.setText(hospital);

        return rootView;
    }
}
