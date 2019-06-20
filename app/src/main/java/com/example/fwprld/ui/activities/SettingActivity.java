package com.example.fwprld.ui.activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.fwprld.R;
import com.example.fwprld.common.LoginSession;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import org.w3c.dom.Text;

import java.util.Objects;

public class SettingActivity extends AppCompatActivity {
TextView account,generalsetting,service,editprofile,about, txtLogoutUser,tvBack,tvVip,tvNoble,tvRecharge;
FirebaseUser firebaseUser;
    LoginSession  sessionParam;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        sessionParam = new LoginSession(SettingActivity.this);
        account=(TextView)findViewById(R.id.account);
        generalsetting=(TextView)findViewById(R.id.generalsetting);
        service=(TextView)findViewById(R.id.service);
        about=(TextView)findViewById(R.id.about);
        txtLogoutUser = findViewById(R.id.txtLogoutUser);

        editprofile=(TextView)findViewById(R.id.editprofile);
        tvBack = findViewById(R.id.tv_back);
        tvVip = findViewById(R.id.tv_vip);
        tvNoble= findViewById(R.id.tv_noble);
        tvRecharge= findViewById(R.id.tv_recharge);


        tvBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        editprofile.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 startActivity(new Intent(getApplicationContext(),EditProfileActivity.class));
//                 finish();
             }
         });
        account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),AccountActivity.class));
//                finish();
            }
        });
        generalsetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),GeneralActivity.class));
//                finish();
            }
        });
        service.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),CustomerServiceCenterActivity.class));
//                finish();
            }
        });
        about.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),AboutActivity.class));
//                finish();
            }
        });

        tvVip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),ActVIP.class));
            }
        });
        tvNoble.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),ActNoble.class));
            }
        });
        tvRecharge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),ActRecharge.class));
            }
        });

        txtLogoutUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder builder = null;
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.KITKAT) {
                    builder = new AlertDialog.Builder(Objects.requireNonNull(SettingActivity.this));
                }
                assert builder != null;
                builder.setMessage("Are you sure, you want to logout ?");
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                builder.setPositiveButton("Proceed", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                       new LoginSession(SettingActivity.this).LoggedInVal(SettingActivity.this,false);
                        sessionParam.clearPreferences(SettingActivity.this);
                        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
                        firebaseAuth.signOut();
                        startActivity(new Intent(SettingActivity.this, MainActivity.class));
                        finish();
                    }
                });
                final AlertDialog dialog = builder.create();
                dialog.show();
            }
        });
    }
}
