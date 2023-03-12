package com.example.happypet;

import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.StringTokenizer;

public class FragmentList extends Fragment {

    RecyclerView recyclerView;

    String s1[], s2[], s3[], s4[], s5[], s6[];
    int images[] = {R.drawable.ic_baseline_person_24, R.drawable.ic_baseline_person_24, R.drawable.ic_baseline_person_24};
    int symptomimages[] = {R.drawable.firstimage, R.drawable.secondimage, R.drawable.thirdimage};
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_list, container, false);

        recyclerView = (RecyclerView) rootView.findViewById(R.id.RecyclerView);

        s1 = getResources().getStringArray(R.array.animal_name);
        s2 = getResources().getStringArray(R.array.doctor_name);
        s3 = getResources().getStringArray(R.array.hospital_name);
        s4 = getResources().getStringArray(R.array.date);
        s5 = getResources().getStringArray(R.array.symptom);
        s6 = getResources().getStringArray(R.array.price);


        ListAdapter listAdapter =new ListAdapter(getActivity(), s1, s2, s3, s4, s5, s6, images, symptomimages);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(listAdapter);

        return rootView;
    }
}
