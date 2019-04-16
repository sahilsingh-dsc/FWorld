package com.example.fwprld;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        int SPLASH_TIME_OUT = 3000;
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent chooseLangIntent = new Intent(SplashActivity.this, ChooseLanguageActivity.class);
                startActivity(chooseLangIntent);
                finish();

            }
        }, SPLASH_TIME_OUT);
    }
}
