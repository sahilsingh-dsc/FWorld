package com.example.fwprld.ui.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.fwprld.R;

public class ContactUsActivity extends AppCompatActivity implements View.OnClickListener{
    TextView tvBack;
TextView tvEmail,tvContact,tvAddress;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_us);
        InitCompo();
        Listner();
    }

    private void Listner() {
        tvBack.setOnClickListener(this);
    }

    private void SetValue() {
        tvEmail.setText("Email:xyz@gmail.com");
        tvContact.setText("Contact Number: +91 9800000000");
        tvAddress.setText("Address: xyz");
    }

    private void InitCompo() {
        tvEmail=findViewById(R.id.tv_email);
        tvContact=findViewById(R.id.tv_contact);
        tvAddress=findViewById(R.id.tv_address);
        tvBack=findViewById(R.id.tv_back);
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
//        startActivity(new Intent(getApplicationContext(),AboutActivity.class));
//        finish();
//        super.onBackPressed();
//    }
}
