package com.example.happypet;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    FragmentHome fragmentHome;
    FragmentMedical fragmentMedical;
    FragmentUser fragmentUser;
    FragmentList fragmentList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        fragmentHome = new FragmentHome();
        fragmentMedical = new FragmentMedical();
        fragmentList = new FragmentList();
        fragmentUser = new FragmentUser();

        getSupportFragmentManager().beginTransaction().replace(R.id.container, fragmentHome).commit();

        BottomNavigationView bottomNavigation = findViewById(R.id.bottom_navigation);
        bottomNavigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.tab1:
                        getSupportFragmentManager().beginTransaction().replace(R.id.container, fragmentHome).commit();

                        return true;
                    case R.id.tab2:
                        getSupportFragmentManager().beginTransaction().replace(R.id.container, fragmentMedical).commit();

                        return true;
                    case R.id.tab3:
                        getSupportFragmentManager().beginTransaction().replace(R.id.container, fragmentList).commit();

                        return true;
                    case R.id.tab4:
                        getSupportFragmentManager().beginTransaction().replace(R.id.container, fragmentUser).commit();

                        return true;
                }

                return false;
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        SaveSharedPreference.clearUserName(MainActivity.this);
    }
}