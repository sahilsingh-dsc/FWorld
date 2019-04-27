package com.example.fwprld.ui.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.fwprld.R;

public class GeneralActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_general);
    }
    @Override
    public void onBackPressed() {
        startActivity(new Intent(getApplicationContext(),SettingActivity.class));
        finish();
        super.onBackPressed();
    }
}
