package com.example.fwprld.ui.fragments;


import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.example.fwprld.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class MomentDiscoverFragment extends Fragment implements TabLayout.OnTabSelectedListener{

    public TabLayout tabLayout;
    public static FrameLayout frameLayout;
    public MomentDiscoverFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_moment_discover, container, false);
        tabLayout = (TabLayout)view.findViewById(R.id.tabLayout);
        tabLayout.addTab(tabLayout.newTab().setText("Moments"));
        tabLayout.addTab(tabLayout.newTab().setText("Discover"));
        frameLayout= (FrameLayout)view.findViewById(R.id.frame);
        tabLayout.addOnTabSelectedListener(this);
        getChildFragmentManager()
                .beginTransaction()
                .replace(R.id.frame, new  MomentFragment())
                .commit();
     return view;
    }

    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        Fragment fragment = null;

        switch (tab.getPosition()) {

            case 0:
                fragment = new MomentFragment();
                getChildFragmentManager()
                        .beginTransaction()
                        .replace(R.id.frame, fragment)
                        .commit();
                break;

            case 1:
                fragment = new DiscoverFragment();
                getChildFragmentManager()
                        .beginTransaction()
                        .replace(R.id.frame, fragment)
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
