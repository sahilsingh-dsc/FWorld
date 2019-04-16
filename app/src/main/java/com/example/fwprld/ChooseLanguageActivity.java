package com.example.fwprld;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class ChooseLanguageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
         setContentView(R.layout.activity_choose_language);
          ImageView imgEnglish = findViewById(R.id.imgEnglish);
         imgEnglish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mainIntent = new Intent(ChooseLanguageActivity.this, MainActivity.class);
                startActivity(mainIntent);
                finish();
            }
        });
    }
}
