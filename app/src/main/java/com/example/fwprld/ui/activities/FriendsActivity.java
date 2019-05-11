package com.example.fwprld.ui.activities;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;

import com.example.fwprld.R;

public class FriendsActivity extends AppCompatActivity {

    TabLayout tabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friends);


        tabLayout = (TabLayout) findViewById(R.id.friendstabs_id);

        tabLayout.addTab(tabLayout.newTab().setText("FOLLOWING"));
        tabLayout.addTab(tabLayout.newTab().setText("FANS"));
        tabLayout.addTab(tabLayout.newTab().setText("LIKE"));


        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);


        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {


            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {


            }
        });
    }
}


