package com.example.fwprld.ui.fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterViewFlipper;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SearchView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.fwprld.R;
import com.example.fwprld.adapters.MyCustomPager;
import com.example.fwprld.adapters.RecommendAdapter;
import com.example.fwprld.models.Recommend;
import com.example.fwprld.openlive.ui.MainActivity;
import com.example.fwprld.ui.broadcastStream.ActBroadcastMain;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment implements TabLayout.OnTabSelectedListener{

    private AdapterViewFlipper adapterViewFlipper;
    private static final int[] IMAGES={R.drawable.slideimage,R.drawable.slideimage1,R.drawable.slideimage2,R.drawable.slideimage3};
    CardView talent,club;
    public TabLayout tabLayout;
    View view;

    private MyCustomPager adapter;


    ImageView imgFLiveGIF, imgFTalentGIF, imgFClubGIF;
    public static FrameLayout frameLayout;

    public HomeFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_home, container, false);

        imgFLiveGIF = view.findViewById(R.id.imgFLiveGIF);
        Glide.with(getContext())
                .load(R.drawable.flive_bg)
                .placeholder(R.drawable.flive_bg)
                .into(imgFLiveGIF);

        imgFLiveGIF.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                startActivity(new Intent(getContext(), MainActivity.class));
                startActivity(new Intent(getContext(), ActBroadcastMain.class));
            }
        });

        imgFTalentGIF = view.findViewById(R.id.imgFTalentGIF);
        Glide.with(getContext())
                .load(R.drawable.ftalent_bg)
                .placeholder(R.drawable.ftalent_bg)
                .into(imgFTalentGIF);

        imgFClubGIF = view.findViewById(R.id.imgFClubGIF);
        Glide.with(getContext())
                .load(R.drawable.fclb_bg)
                .placeholder(R.drawable.fclb_bg)
                .into(imgFClubGIF);




        SearchView searchView=(SearchView)view.findViewById(R.id.search);
        adapterViewFlipper=(AdapterViewFlipper)view.findViewById(R.id.viewpager);
        adapter= new MyCustomPager(getContext(),IMAGES);
        adapterViewFlipper.setAdapter(adapter);
        adapterViewFlipper.setAutoStart(true);
        tabLayout = (TabLayout)view.findViewById(R.id.tabLayout);
        tabLayout.addTab(tabLayout.newTab().setText("Recommended"));
        tabLayout.addTab(tabLayout.newTab().setText("Hot"));
        tabLayout.addTab(tabLayout.newTab().setText("Treding"));
        tabLayout.addTab(tabLayout.newTab().setText("New"));
        frameLayout= (FrameLayout)view.findViewById(R.id.frame);
        tabLayout.addOnTabSelectedListener(this);
        talent=(CardView)view.findViewById(R.id.talent);
        club=(CardView)view.findViewById(R.id.club);
        talent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FTalentFragment fTalentFragment=new FTalentFragment();
                FragmentManager fragmentManager = getFragmentManager();
                assert fragmentManager != null;
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.add(R.id.frame,fTalentFragment);
                fragmentTransaction.hide(HomeFragment.this);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });
        club.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FClubFragment fClubFragment = new FClubFragment();
                FragmentManager fragmentManager = getFragmentManager();
                assert fragmentManager != null;
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.add(R.id.frame,fClubFragment);
                fragmentTransaction.hide(HomeFragment.this);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });

       getChildFragmentManager()
                .beginTransaction()
                .replace(R.id.frame, new RecommendedFragment())
                .commit();

        return view;
    }
    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        Fragment fragment = null;

        switch (tab.getPosition()) {

            case 0:
                fragment = new RecommendedFragment();
                getChildFragmentManager()
                        .beginTransaction()
                        .replace(R.id.frame, fragment)
                        .commit();
                break;

            case 1:
                fragment = new HotFragment();
                getChildFragmentManager()
                        .beginTransaction()
                        .replace(R.id.frame, fragment)
                        .commit();
                break;
            case 2:
                fragment = new TredingFragment();
                getChildFragmentManager()
                        .beginTransaction()
                        .replace(R.id.frame, fragment)
                        .commit();
                break;
            case 3:
              /*  fragment = new TredingFragment();
                getChildFragmentManager()
                        .beginTransaction()
                        .replace(R.id.frame, fragment)
                        .commit();*/
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

