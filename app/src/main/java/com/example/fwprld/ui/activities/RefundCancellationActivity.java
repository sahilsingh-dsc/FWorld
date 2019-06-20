package com.example.fwprld.ui.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.fwprld.R;

public class RefundCancellationActivity extends AppCompatActivity implements View.OnClickListener{
    TextView tvBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_refund_cancellation);
        initCompo();
        Listner();
    }

    private void Listner() {
        tvBack.setOnClickListener(this);
    }

    private void initCompo() {
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
