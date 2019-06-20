package com.example.fwprld.ui.activities;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.fwprld.R;
import com.example.fwprld.adapters.FollowerAdapter;
import com.example.fwprld.common.LoginSession;
import com.example.fwprld.models.UserInfo;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ActFollowers extends AppCompatActivity implements View.OnClickListener{
    TextView tvBack;
    RelativeLayout rlNodata;
    RecyclerView rvFollowUsers;
    List<UserInfo> followingUser;
    LoginSession sessionParam;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_followers);
        sessionParam = new LoginSession(this);
        initCompo();
        Listner();
        GetData();
//        SetAdapter();
    }

    private void Listner() {
        tvBack.setOnClickListener(this);
    }

    private void initCompo() {
        tvBack=findViewById(R.id.tv_back);
        rlNodata=findViewById(R.id.rl_no_data);
        rvFollowUsers=findViewById(R.id.rv_followers);

        rvFollowUsers.setHasFixedSize(true);
        rvFollowUsers.setLayoutManager(new LinearLayoutManager(this));
        followingUser = new ArrayList<>();
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

    public void GetData()
    {
        DatabaseReference userList = FirebaseDatabase.getInstance().getReference("FWORLD_USER_DATA").child("USER_PROFILE");
        userList.child("USER_BASIC_INFO").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                followingUser.clear();
                try {
                    Log.e("","dataSnapshot= "+dataSnapshot.toString());
                    for (DataSnapshot ftalSnap : dataSnapshot.getChildren()){
                        String  user_name = (String) ftalSnap.child("user_name").getValue();
                        String  fidlocal = (String) ftalSnap.child("user_fid").getValue();
                        String  avatar_url ="";
                        UserInfo usrinfo = new UserInfo(user_name, fidlocal ,avatar_url);

                        if (!sessionParam.usr_id.equals(fidlocal)) {
                            followingUser.add(usrinfo);
                        }


                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    Log.e("",""+e);
                }

                SetAdapter();


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
    public void SetAdapter()
    {
        FollowerAdapter adapter = new FollowerAdapter(getApplicationContext(),  followingUser);
        rvFollowUsers.setAdapter(adapter);
    }
}
