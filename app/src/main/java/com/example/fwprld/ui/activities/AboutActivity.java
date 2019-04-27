package com.example.fwprld.ui.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.fwprld.R;

public class AboutActivity extends AppCompatActivity {
TextView about,contact,refund,terms,privacy;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        about =(TextView)findViewById(R.id.aboutus);
        about.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),ChildAboutActivity.class));
                finish();
            }
        });
        contact =(TextView)findViewById(R.id.contactus);
        contact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),ContactUsActivity.class));
                finish();
            }
        });
        refund =(TextView)findViewById(R.id.refund);
        refund.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),RefundCancellationActivity.class));
                finish();
            }
        });
        terms =(TextView)findViewById(R.id.terms);
        terms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),TermServiceActivity.class));
                finish();
            }
        });
        privacy =(TextView)findViewById(R.id.privacy);
        privacy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),PrivacyPolicyActivity.class));
                finish();
            }
        });
    }
    @Override
    public void onBackPressed() {
        startActivity(new Intent(getApplicationContext(),SettingActivity.class));
        finish();
        super.onBackPressed();
    }


}
