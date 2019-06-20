package com.example.fwprld.ui.activities;

import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.fwprld.R;
import com.example.fwprld.common.LoginSession;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class ActVIP extends AppCompatActivity implements View.OnClickListener{
    TextView tvBack,tvUserId;
    ImageView imgProfileAvatar;
    FirebaseUser firebaseUser;
//    String fid;
    LoginSession sessionParam;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_vip);
        sessionParam = new LoginSession(this);
        initCompo();
        Listner();
        SetData();
    }

    private void SetData() {
//        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
//
//        fid = firebaseUser.getUid();

        tvUserId.setText(sessionParam.usr_id);
        StorageReference avatarStoreRef = FirebaseStorage.getInstance().getReference("FWORLD_USER_AVATAR").child(sessionParam.usr_id+".jpg");
        avatarStoreRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                try {
                  String  avatar_url = uri.toString();
                    Glide.with(ActVIP.this).load(avatar_url).into(imgProfileAvatar);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

    }

    private void Listner() {
        tvBack.setOnClickListener(this);
    }

    private void initCompo() {
        tvBack=findViewById(R.id.tv_back);
        imgProfileAvatar = findViewById(R.id.imgProfileAvatar);
        tvUserId = findViewById(R.id.tv_user_id);
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
}
