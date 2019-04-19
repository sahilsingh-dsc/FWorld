package com.example.fwprld;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.CountDownTimer;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class SplashActivity extends AppCompatActivity {
    ImageView bounceImage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        @SuppressLint("ResourceType") final Animation animBounce = AnimationUtils.loadAnimation(getApplicationContext(), R.animator.boun);
        bounceImage = (ImageView)findViewById(R.id.imagebounce);
        bounceImage.startAnimation(animBounce);
        new CountDownTimer(4000,1000) {

            @Override
            public void onFinish() {
                Intent chooseLangIntent = new Intent(SplashActivity.this, ChooseLanguageActivity.class);
                startActivity(chooseLangIntent);
                finish();
            }
            @Override
            public void onTick(long millisUntilFinished) {

            }
        }.start();
    }
}
