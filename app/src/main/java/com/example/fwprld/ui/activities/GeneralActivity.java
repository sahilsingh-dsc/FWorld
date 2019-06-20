package com.example.fwprld.ui.activities;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.fwprld.R;
import com.example.fwprld.common.LoginSession;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.json.JSONException;
import org.json.JSONObject;

public class GeneralActivity extends AppCompatActivity implements View.OnClickListener{
    TextView tvBack,tvCountry,tvLangu,tvCountryNm;
    LoginSession sessionParam;
    RelativeLayout rlCountry;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_general);
        sessionParam = new LoginSession(this);
        initCompo();
        Listner();
        getCountry();
    }

    private void Listner() {
        tvBack.setOnClickListener(this);
        tvCountry.setOnClickListener(this);
        tvLangu.setOnClickListener(this);
        rlCountry.setOnClickListener(this);
    }

    private void initCompo() {
        tvBack=findViewById(R.id.tv_back);
        tvCountry=findViewById(R.id.tv_country);
        tvLangu=findViewById(R.id.tv_language);
        tvCountryNm=findViewById(R.id.tv_count_nam);
        rlCountry=findViewById(R.id.rl_country);
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
            case R.id.rl_country:
            case R.id.tv_country:
                Intent i = new Intent(this, ActCountryList.class);
                i.putExtra("previous_country",tvCountryNm.getText().toString());
                startActivityForResult(i, 1);
                break;
           case R.id.tv_language:
               Intent ii = new Intent(this, ActLanguageList.class);
               startActivityForResult(ii, 2);
                break;
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == 1) {
            if(resultCode == Activity.RESULT_OK){
                String result=data.getStringExtra("result");
                tvCountryNm.setText(result);
                SetCountry(result);
            }
            if (resultCode == Activity.RESULT_CANCELED) {
                //Write your code if there's no result
                getCountry();
            }
        }
       else if (requestCode == 2) {
            if(resultCode == Activity.RESULT_OK){
                String result=data.getStringExtra("result");
//                tvCountryNm.setText(result);
                SetDefaultLang(result);
            }
            if (resultCode == Activity.RESULT_CANCELED) {
                //Write your code if there's no result
                getDefaultLang();
            }
        }
    }
    public void SetCountry(String country)
    {
        DatabaseReference profileRef = FirebaseDatabase.getInstance().getReference("FWORLD_USER_DATA").child("USER_PROFILE");
        profileRef.child("USER_COUNTRY").child(sessionParam.usr_id).setValue(country);
    }
    public void getCountry()
    {
        DatabaseReference profileRef = FirebaseDatabase.getInstance().getReference("FWORLD_USER_DATA").child("USER_PROFILE");
        profileRef.child("USER_COUNTRY").child(sessionParam.usr_id).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

               String country_name = (String) dataSnapshot.getValue();
                tvCountryNm.setText(country_name);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public void SetDefaultLang(String lang)
    {

        DatabaseReference profileRef = FirebaseDatabase.getInstance().getReference("FWORLD_USER_DATA").child("USER_PROFILE");
        profileRef.child("USER_DEFAULT_LANGUAGE").child(sessionParam.usr_id).setValue(lang);
    }
    public void getDefaultLang()
    {
        DatabaseReference profileRef = FirebaseDatabase.getInstance().getReference("FWORLD_USER_DATA").child("USER_PROFILE");
        profileRef.child("USER_DEFAULT_LANGUAGE").child(sessionParam.usr_id).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

//               String country_name = (String) dataSnapshot.getValue();
//                tvCountryNm.setText(country_name);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
