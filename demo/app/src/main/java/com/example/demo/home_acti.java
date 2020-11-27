package com.example.demo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.demo.fragment.Frag_Direc;
import com.example.demo.fragment.Frag_Ency;
import com.example.demo.fragment.Frag_Home;
import com.example.demo.fragment.Frag_Noti;
import com.example.demo.fragment.Frag_Shop;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class home_acti extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_acti);
        BottomNavigationView navigationView = findViewById(R.id.bottom_nav);
        navigationView.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener);
        Fragment home = new Frag_Home();
        getSupportFragmentManager().beginTransaction().replace(R.id.frag_bottom_nav,home).commit();
    }
    private BottomNavigationView.OnNavigationItemSelectedListener onNavigationItemSelectedListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    Fragment fragment = null;

                    switch (item.getItemId()){
                        case R.id.item_home:
                            fragment = new Frag_Home();
                            break;
                        case R.id.item_shop:
                            fragment = new Frag_Shop();
                            break;
                        case R.id.item_ency:
                            fragment = new Frag_Ency();
                            break;
                        case R.id.item_noti:
                            fragment = new Frag_Noti();
                            break;
                        case R.id.item_category:
                            fragment = new Frag_Direc();
                            break;
                    }
                    getSupportFragmentManager().beginTransaction().replace(R.id.frag_bottom_nav,fragment).commit();
                    return true;
                }
            };
}