package com.example.fwprld.ui.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.fwprld.R;

public class ChildAboutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_child_about);
    }
    @Override
    public void onBackPressed() {
        startActivity(new Intent(getApplicationContext(),AboutActivity.class));
        finish();
        super.onBackPressed();
    }
}
