package com.example.fwprld.ui.fragments;


import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.fwprld.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class MomentFragment extends Fragment implements TabLayout.OnTabSelectedListener{

    public TabLayout tabLayout;

    public MomentFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
         View v= inflater.inflate(R.layout.fragment_moment, container, false);
        tabLayout = (TabLayout)v.findViewById(R.id.tabLayout);
        tabLayout.addTab(tabLayout.newTab().setText("Following"));
        tabLayout.addTab(tabLayout.newTab().setText("Popular"));
        getChildFragmentManager()
                .beginTransaction()
                .replace(R.id.frame, new FollowingFragmentNew())
                .commit();
        tabLayout.setOnTabSelectedListener(this);
        return v;
    }

    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        Fragment fragment = null;

        switch (tab.getPosition()) {

            case 0:
//                fragment = new FollowingFragment();
                fragment = new FollowingFragmentNew();
                getChildFragmentManager()
                        .beginTransaction()
                        .replace(R.id.frame, fragment)
                        .commit();
                break;

            case 1:
                fragment = new PopularFragment();
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
