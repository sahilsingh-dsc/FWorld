package com.example.fwprld.ui.fragments;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fwprld.R;
import com.example.fwprld.ui.activities.ActDraft;
import com.example.fwprld.ui.activities.ActFollowers;
import com.example.fwprld.ui.activities.ActFollowings;
import com.example.fwprld.ui.activities.ActNoble;
import com.example.fwprld.ui.activities.ActRecharge;
import com.example.fwprld.ui.activities.SettingActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ProfileFragment extends Fragment implements TabLayout.OnTabSelectedListener,View.OnClickListener{

    public TabLayout tabLayout;
    public static FrameLayout viewPager;
    ImageView imageView;
    ImageView ivDraft,ivRecharge,ivNoble;
    ImageView ivBadge,ivLive,ivIncome;

    TextView tvDraft,tvRecharge,tvNoble;
    TextView tvBadge,tvLive,tvIncome;

    String user_id;
    TextView follower,following,rank;
    TextView followerTxt,followingTxt,rankTxt;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v= inflater.inflate(R.layout.activity_profile, container, false);
        tabLayout = (TabLayout)v.findViewById(R.id.tabLayout);
        tabLayout.addTab(tabLayout.newTab().setText("Profile"));
        tabLayout.addTab(tabLayout.newTab().setText("MyStories"));
        tabLayout.addTab(tabLayout.newTab().setText("F Artist"));
        tabLayout.addTab(tabLayout.newTab().setText("Duet"));

        follower=v.findViewById(R.id.follower);
        following=v.findViewById(R.id.following);
        rank=v.findViewById(R.id.rank);

        followerTxt=v.findViewById(R.id.tv_followers_txt);
        followingTxt=v.findViewById(R.id.tv_followings_txt);
        rankTxt=v.findViewById(R.id.tv_ranks_txt);




        ivDraft=v.findViewById(R.id.iv_draft);
        ivRecharge=v.findViewById(R.id.iv_recharge);
        ivNoble=v.findViewById(R.id.iv_noble);
        ivBadge=v.findViewById(R.id.iv_badge);
        ivLive=v.findViewById(R.id.iv_live);
        ivIncome=v.findViewById(R.id.iv_income);

        tvDraft=v.findViewById(R.id.tv_draft);
        tvRecharge=v.findViewById(R.id.tv_recharge);
        tvNoble=v.findViewById(R.id.tv_noble);
        tvBadge=v.findViewById(R.id.tv_badge);
        tvLive=v.findViewById(R.id.tv_live);
        tvIncome=v.findViewById(R.id.tv_income);


        imageView=(ImageView)v.findViewById(R.id.setting);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), SettingActivity.class));
            }
        });
        viewPager = (FrameLayout)v.findViewById(R.id.pager);
        getChildFragmentManager()
                .beginTransaction()
                .replace(R.id.pager, new ChildProfileFragment())
                .commit();
        tabLayout.setOnTabSelectedListener(this);
        FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();

        if (firebaseUser !=null){

            user_id = firebaseUser.getUid();

            DatabaseReference nameRef = FirebaseDatabase.getInstance().getReference("FWORLD_USER_DATA");
            nameRef.child("USER_PROFILE").child("USER_BASIC_INFO").child(user_id).addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                    String Followers =dataSnapshot.child("followers").getValue().toString();
                    String Following = dataSnapshot.child("followings").getValue().toString();
                    String Rank = dataSnapshot.child("rank_record").getValue().toString();
                    follower.setText(Followers);
                    following.setText(Following);
                    rank.setText(Rank);
                   /* TextView textView14 = view.findViewById(R.id.textView14);
                    textView14.setText(String.format("Hello, %s", name));*/

//                    Toast.makeText(getContext(), "Followers "+Followers+" Following="+Following+" Rank="+Rank, Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
        }

Listener();

        return v;
    }

    private void Listener() {
        ivDraft.setOnClickListener(this);
        ivRecharge.setOnClickListener(this);
        ivNoble.setOnClickListener(this);
        ivBadge.setOnClickListener(this);
        ivLive.setOnClickListener(this);
        ivIncome.setOnClickListener(this);

        tvDraft.setOnClickListener(this);
        tvRecharge.setOnClickListener(this);
        tvNoble.setOnClickListener(this);
        tvBadge.setOnClickListener(this);
        tvLive.setOnClickListener(this);
        tvIncome.setOnClickListener(this);

        follower.setOnClickListener(this);
        following.setOnClickListener(this);
        rank.setOnClickListener(this);
        followerTxt.setOnClickListener(this);
        followingTxt.setOnClickListener(this);
        rankTxt.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.iv_draft:
            case R.id.tv_draft:
                startActivity(new Intent(getActivity(), ActDraft.class));
                break;

            case R.id.iv_recharge:
            case R.id.tv_recharge:
                startActivity(new Intent(getActivity(), ActRecharge.class));
                break;

            case R.id.iv_noble:
            case R.id.tv_noble:
                startActivity(new Intent(getActivity(), ActNoble.class));
                break;
            case R.id.iv_badge:
            case R.id.tv_badge:
//                startActivity(new Intent(getActivity(), ActB.class));
                break;

            case R.id.iv_live:
            case R.id.tv_live:
                break;

            case R.id.iv_income:
            case R.id.tv_income:
                break;

            case R.id.follower:
            case R.id.tv_followers_txt:
                startActivity(new Intent(getActivity(), ActFollowers.class));
                break;

            case R.id.following:
            case R.id.tv_followings_txt:
                startActivity(new Intent(getActivity(), ActFollowings.class));
                break;

            case R.id.rank:
            case R.id.tv_ranks_txt:
                break;

        }

    }
    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        Fragment fragment = null;

        switch (tab.getPosition()) {

            case 0:
                fragment = new ChildProfileFragment();
                getChildFragmentManager()
                        .beginTransaction()
                        .replace(R.id.pager, fragment)
                        .commit();
                break;

            case 1:
                fragment = new MyStoriesFragment();
                getChildFragmentManager()
                        .beginTransaction()
                        .replace(R.id.pager, fragment)
                        .commit();
                break;

            case 2:
                fragment = new F_ArtistFragment();
                getChildFragmentManager()
                        .beginTransaction()
                        .replace(R.id.pager, fragment)
                        .commit();
                break;

            case 3:
                fragment = new DuetFragment();
                getChildFragmentManager()
                        .beginTransaction()
                        .replace(R.id.pager, fragment)
                        .commit();

                break;
        }


    }


    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }



}
//    public TabLayout tabLayout;
//    public static FrameLayout viewPager;
//    ImageView imageView;
//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container,
//                             Bundle savedInstanceState) {
//        View v= inflater.inflate(R.layout.activity_profile, container, false);
//        tabLayout = (TabLayout)v.findViewById(R.id.tabLayout);
//        tabLayout.addTab(tabLayout.newTab().setText("Profile"));
//        tabLayout.addTab(tabLayout.newTab().setText("MyStories"));
//        tabLayout.addTab(tabLayout.newTab().setText("F Artist"));
//        tabLayout.addTab(tabLayout.newTab().setText("Duet"));
//        imageView=(ImageView)v.findViewById(R.id.setting);
//        imageView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//               startActivity(new Intent(getContext(), SettingActivity.class));
//            }
//        });
//        viewPager = (FrameLayout)v.findViewById(R.id.pager);
//         getChildFragmentManager()
//                .beginTransaction()
//                .replace(R.id.pager, new ChildProfileFragment())
//                .commit();
//        tabLayout.setOnTabSelectedListener(this);
//        return v;
//    }
//
//    @Override
//    public void onTabSelected(TabLayout.Tab tab) {
//        Fragment fragment = null;
//
//        switch (tab.getPosition()) {
//
//            case 0:
//                fragment = new ChildProfileFragment();
//                getChildFragmentManager()
//                        .beginTransaction()
//                        .replace(R.id.pager, fragment)
//                        .commit();
//                break;
//
//            case 1:
//                fragment = new MyStoriesFragment();
//              getChildFragmentManager()
//                        .beginTransaction()
//                        .replace(R.id.pager, fragment)
//                        .commit();
//                break;
//
//            case 2:
//                fragment = new F_ArtistFragment();
//         getChildFragmentManager()
//                        .beginTransaction()
//                        .replace(R.id.pager, fragment)
//                        .commit();
//                break;
//
//            case 3:
//                fragment = new DuetFragment();
//               getChildFragmentManager()
//                        .beginTransaction()
//                        .replace(R.id.pager, fragment)
//                        .commit();
//
//                break;
//        }
//
//
//    }
//
//
//    @Override
//    public void onTabUnselected(TabLayout.Tab tab) {
//
//    }
//
//    @Override
//    public void onTabReselected(TabLayout.Tab tab) {
//
//}
//
//
//
//}
