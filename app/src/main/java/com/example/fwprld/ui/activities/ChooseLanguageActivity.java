package com.example.fwprld.ui.activities;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.fwprld.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ChooseLanguageActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    DatabaseReference langRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
         setContentView(R.layout.activity_choose_language);
         ImageView imgEnglish = findViewById(R.id.imgEnglish);
          mAuth = FirebaseAuth.getInstance();
         imgEnglish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseUser user = mAuth.getCurrentUser();

                String authid = user.getUid();
                langRef = FirebaseDatabase.getInstance().getReference();
                langRef.child("USER_DATA").child(authid).child("selected_language").setValue("English");
                Intent mainIntent = new Intent(ChooseLanguageActivity.this, MainActivity.class);
                startActivity(mainIntent);
                finish();
            }
        });

    }

}
