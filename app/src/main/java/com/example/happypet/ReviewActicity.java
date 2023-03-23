package com.example.happypet;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class ReviewActicity extends Activity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review);

        Button revieweditbutton = findViewById(R.id.reviewEdit);
        revieweditbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(getApplication().getApplicationContext(),write_review_Activity.class);
                startActivity(intent);

            }
        });
    }
}
