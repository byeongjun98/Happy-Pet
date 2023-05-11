package com.example.happypet;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

public class BottomSheetDialog extends BottomSheetDialogFragment {
    private View view;

    private BottomSheetListener mListener;

    String sort;

    LinearLayout base_sort, distance_sort, review_sort, star_sort;
    ImageView[] check = new ImageView[4];

    int[] check_id = { R.id.base_check, R.id.distance_check, R.id.review_check, R.id.star_check };

    public BottomSheetDialog(String sort) {
        this.sort = sort;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.sort_bottomsheet, container, false);

        mListener = (BottomSheetListener) getContext();

        base_sort = view.findViewById(R.id.base_sort);
        distance_sort = view.findViewById(R.id.distance_sort);
        review_sort = view.findViewById(R.id.review_sort);
        star_sort = view.findViewById(R.id.star_sort);

        for(int i=0; i<check.length; i++) {
            check[i] = view.findViewById(check_id[i]);
        }

        switch (sort) {
            case "기본순":
                check[0].setVisibility(View.VISIBLE);
                break;
            case "거리순":
                check[1].setVisibility(View.VISIBLE);
                break;
            case "후기많은순":
                check[2].setVisibility(View.VISIBLE);
                break;
            case "별점많은순":
                check[3].setVisibility(View.VISIBLE);
                break;
        }

        setListener();

        return view;
    }

    void setListener() {
        base_sort.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mListener.onLinearLayoutClicked("기본순");
                dismiss();
            }
        });

        distance_sort.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mListener.onLinearLayoutClicked("거리순");
                dismiss();
            }
        });

        review_sort.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mListener.onLinearLayoutClicked("후기많은순");
                dismiss();
            }
        });

        star_sort.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mListener.onLinearLayoutClicked("별점많은순");
                dismiss();
            }
        });
    }

    public interface BottomSheetListener {
        void onLinearLayoutClicked(String text);
    }
}
