package com.example.happypet;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

public class IncreditActivity extends Activity {
    LinearLayout addCreditButton;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.incredit);

        addCreditButton =findViewById(R.id.addCreditButton);

        addCreditButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplication().getApplicationContext(),addCreditCard.class);
                        startActivity(intent);
            }
        });


    }
}
