package com.example.happypet;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

public class InAddressActivity extends Activity {
    LinearLayout addAddressButton;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.inmyadress);

        addAddressButton =findViewById(R.id.addAddressButton);

        addAddressButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(getApplication().getApplicationContext(),searchAddressActivity.class);
                startActivity(intent);
            }
        });

    }
}
