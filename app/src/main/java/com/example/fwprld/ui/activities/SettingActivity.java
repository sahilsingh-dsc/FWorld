package com.example.fwprld.ui.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.fwprld.R;

import org.w3c.dom.Text;

public class SettingActivity extends AppCompatActivity {
TextView account,generalsetting,service,editprofile,about;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        account=(TextView)findViewById(R.id.account);
        generalsetting=(TextView)findViewById(R.id.generalsetting);
        service=(TextView)findViewById(R.id.service);
        about=(TextView)findViewById(R.id.about);
        editprofile=(TextView)findViewById(R.id.editprofile);
        editprofile.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 startActivity(new Intent(getApplicationContext(),EditProfileActivity.class));
                 finish();
             }
         });
        account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),AccountActivity.class));
                finish();
            }
        });
        generalsetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),GeneralActivity.class));
                finish();
            }
        });
        service.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),CustomerServiceCenterActivity.class));
                finish();
            }
        });
        about.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),AboutActivity.class));
                finish();
            }
        });
    }
}
