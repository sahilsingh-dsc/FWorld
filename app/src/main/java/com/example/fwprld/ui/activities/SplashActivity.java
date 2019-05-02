package com.example.fwprld.ui.activities;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.CountDownTimer;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.fwprld.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class SplashActivity extends AppCompatActivity {
    ImageView bounceImage,connect;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        @SuppressLint("ResourceType") final Animation animBounce = AnimationUtils.loadAnimation(getApplicationContext(), R.animator.boun);
        @SuppressLint("ResourceType") final Animation animBounce1 = AnimationUtils.loadAnimation(getApplicationContext(), R.animator.blink);
        bounceImage = (ImageView) findViewById(R.id.imagebounce);
        connect = (ImageView) findViewById(R.id.connect);
        connect.startAnimation(animBounce1);
        bounceImage.startAnimation(animBounce);

        Intent mainIntent = new Intent(SplashActivity.this, MainActivity.class);
        startActivity(mainIntent);
        finish();

    }
}
