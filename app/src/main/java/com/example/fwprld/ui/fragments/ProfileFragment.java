package com.example.fwprld.ui.fragments;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.example.fwprld.R;
import com.example.fwprld.ui.activities.SettingActivity;

public class ProfileFragment extends Fragment implements TabLayout.OnTabSelectedListener{
    public TabLayout tabLayout;
    public static FrameLayout viewPager;
    ImageView imageView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v= inflater.inflate(R.layout.activity_profile, container, false);
        tabLayout = (TabLayout)v.findViewById(R.id.tabLayout);
        tabLayout.addTab(tabLayout.newTab().setText("Profile"));
        tabLayout.addTab(tabLayout.newTab().setText("MyStories"));
        tabLayout.addTab(tabLayout.newTab().setText("F Artist"));
        tabLayout.addTab(tabLayout.newTab().setText("Duet"));
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
        return v;
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
