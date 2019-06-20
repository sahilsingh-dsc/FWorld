package com.example.fwprld.ui.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.fwprld.R;

public class AboutActivity extends AppCompatActivity implements View.OnClickListener{
TextView tvBack;
TextView about,contact,refund,terms,privacy;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        initCompo();
        Listner();
    }


    private void Listner() {
        tvBack.setOnClickListener(this);
        about.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),ChildAboutActivity.class));
//                finish();
            }
        });

        contact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),ContactUsActivity.class));
//                finish();
            }
        });

        refund.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),RefundCancellationActivity.class));
//                finish();
            }
        });

        terms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),TermServiceActivity.class));
//                finish();
            }
        });

        privacy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),PrivacyPolicyActivity.class));
//                finish();
            }
        });
    }

    private void initCompo() {
        tvBack=findViewById(R.id.tv_back);
        about =(TextView)findViewById(R.id.aboutus);
        contact =(TextView)findViewById(R.id.contactus);
        refund =(TextView)findViewById(R.id.refund);
        terms =(TextView)findViewById(R.id.terms);
        privacy =(TextView)findViewById(R.id.privacy);


    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.tv_back:
                finish();
                break;
        }
    }
//    @Override
//    public void onBackPressed() {
//        startActivity(new Intent(getApplicationContext(),SettingActivity.class));
//        finish();
//        super.onBackPressed();
//    }


}
