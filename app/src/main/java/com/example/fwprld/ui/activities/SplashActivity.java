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
    private FirebaseAuth mAuth;
    String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        @SuppressLint("ResourceType") final Animation animBounce = AnimationUtils.loadAnimation(getApplicationContext(), R.animator.boun);
        @SuppressLint("ResourceType") final Animation animBounce1 = AnimationUtils.loadAnimation(getApplicationContext(), R.animator.blink);
        bounceImage = (ImageView)findViewById(R.id.imagebounce);
        connect= (ImageView)findViewById(R.id.connect);
        connect.startAnimation(animBounce1);
        bounceImage.startAnimation(animBounce);
        mAuth = FirebaseAuth.getInstance();
        mAuth.signInAnonymously()
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            FirebaseUser user = mAuth.getCurrentUser();
                            assert user != null;
                            String authid = user.getUid();
                            DatabaseReference langRef = FirebaseDatabase.getInstance().getReference();
                            langRef.child("USER_DATA").child(authid).child("selected_language").addValueEventListener(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                    if (dataSnapshot.exists()){
                                        Intent mainIntent = new Intent(SplashActivity.this, MainActivity.class);
                                        startActivity(mainIntent);
                                        finish();
                                    }else {
                                        Intent mainIntent = new Intent(SplashActivity.this, ChooseLanguageActivity.class);
                                        startActivity(mainIntent);
                                        finish();
                                    }
                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError databaseError) {

                                }
                            });

                        } else {

                        }


                    }
                });

    }
}
