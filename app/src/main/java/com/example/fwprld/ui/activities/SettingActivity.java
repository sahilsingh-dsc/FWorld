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
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import org.w3c.dom.Text;

import java.util.Objects;

public class SettingActivity extends AppCompatActivity {
TextView account,generalsetting,service,editprofile,about, txtLogoutUser;
FirebaseUser firebaseUser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();

        account=(TextView)findViewById(R.id.account);
        generalsetting=(TextView)findViewById(R.id.generalsetting);
        service=(TextView)findViewById(R.id.service);
        about=(TextView)findViewById(R.id.about);
        txtLogoutUser = findViewById(R.id.txtLogoutUser);

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
