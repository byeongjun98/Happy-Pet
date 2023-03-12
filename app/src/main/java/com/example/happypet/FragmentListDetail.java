package com.example.happypet;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

public class FragmentListDetail extends AppCompatActivity {
    TextView doctor_name, hospital_name, symptom, price;

    String data1, data2, data3, data4;

    ImageView symptomimage;

    int symimage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_list_detail);
        ActionBar bar = getSupportActionBar();
        bar.hide();

        symptomimage = findViewById(R.id.list_detail_image);

        doctor_name = findViewById(R.id.list_detail_doctorname);
        hospital_name = findViewById(R.id.list_detail_hospitalname);
        symptom = findViewById(R.id.list_detail_symptom2);
        price = findViewById(R.id.list_detail_price2);

        getData();
        setData();

        Button button = findViewById(R.id.list_detail_button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void getData() {
        if (getIntent().hasExtra("진료사진") && getIntent().hasExtra("의사이름") && getIntent().hasExtra("병원이름") && getIntent().hasExtra("내 증상") && getIntent().hasExtra("진료비")) {
            data1 = getIntent().getStringExtra("의사이름");
            data2 = getIntent().getStringExtra("병원이름");
            data3 = getIntent().getStringExtra("내 증상");
            data4 = getIntent().getStringExtra("진료비");
            symimage = getIntent().getIntExtra("진료사진", 1);

        } else {
            Toast.makeText(this, "No data", Toast.LENGTH_SHORT).show();
        }


    }

    private void setData() {
        doctor_name.setText(data1);
        hospital_name.setText(data2);
        symptom.setText(data3);
        price.setText(data4);
        symptomimage.setImageResource(symimage);
    }
}