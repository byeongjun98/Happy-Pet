package com.example.happypet;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

public class ComprehensiveActivity extends AppCompatActivity {
    ImageButton back_comprehensive;

    TextView comprehensive_vision_text, comprehensive_dentist_text;
    RelativeLayout comprehensive_medicine, comprehensive_dentist, comprehensive_surgery, comprehensive_ophthalmology;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comprehensive);

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        back_comprehensive = findViewById(R.id.back_comprehensive);
        comprehensive_vision_text = findViewById(R.id.comprehensive_vision_text);
        comprehensive_dentist_text = findViewById(R.id.comprehensive_dentist_text);
        comprehensive_medicine = findViewById(R.id.comprehensive_medicine);
        comprehensive_dentist = findViewById(R.id.comprehensive_dentist);
        comprehensive_surgery = findViewById(R.id.comprehensive_surgery);
        comprehensive_ophthalmology = findViewById(R.id.comprehensive_ophthalmology);

        setListener();
    }

    void setListener() {
        back_comprehensive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                overridePendingTransition(R.anim.fadein, R.anim.fadeout);
            }
        });

        comprehensive_vision_text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplication(), SubjectActivity.class);
                intent.putExtra("subject1", "안과");
                intent.putExtra("subject2", "ophthalmology");
                startActivity(intent);
            }
        });

        comprehensive_dentist_text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplication(), SubjectActivity.class);
                intent.putExtra("subject1", "치과");
                intent.putExtra("subject2", "dentist");
                startActivity(intent);
            }
        });

        comprehensive_ophthalmology.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplication(), SubjectActivity.class);
                intent.putExtra("subject1", "안과");
                intent.putExtra("subject2", "ophthalmology");
                startActivity(intent);
            }
        });

        comprehensive_dentist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplication(), SubjectActivity.class);
                intent.putExtra("subject1", "치과");
                intent.putExtra("subject2", "dentist");
                startActivity(intent);
            }
        });

        comprehensive_medicine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplication(), SubjectActivity.class);
                intent.putExtra("subject1", "내과");
                intent.putExtra("subject2", "medicine");
                startActivity(intent);
            }
        });

        comprehensive_surgery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplication(), SubjectActivity.class);
                intent.putExtra("subject1", "안과");
                intent.putExtra("subject2", "surgery");
                startActivity(intent);
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
        overridePendingTransition(R.anim.fadein, R.anim.fadeout);
    }
}
