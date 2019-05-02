package com.example.fwprld.ui.fragments;


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
import android.widget.LinearLayout;
import android.widget.SearchView;

import com.example.fwprld.R;
import com.example.fwprld.adapters.MyCustomPager;
import com.example.fwprld.adapters.RecommendAdapter;
import com.example.fwprld.models.Recommend;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    private AdapterViewFlipper adapterViewFlipper;
    private static final int[] IMAGES={R.drawable.slideimage,R.drawable.slideimage1,R.drawable.slideimage2,R.drawable.slideimage3};
    CardView talent,club;
    public TabLayout tabLayout;
    View view;
    RecyclerView recyclerView;
    List<Recommend> recommendList;
    private MyCustomPager adapter;
    DatabaseReference homeFragRef;

    public HomeFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_home, container, false);
        SearchView searchView=(SearchView)view.findViewById(R.id.search);
        adapterViewFlipper=(AdapterViewFlipper)view.findViewById(R.id.viewpager);
        adapter= new MyCustomPager(getContext(),IMAGES);
        adapterViewFlipper.setAdapter(adapter);
        adapterViewFlipper.setAutoStart(true);
        recyclerView=(RecyclerView) view.findViewById(R.id.recycleView);
        tabLayout = (TabLayout)view.findViewById(R.id.tabLayout);
        tabLayout.addTab(tabLayout.newTab().setText("Recommended"));
        tabLayout.addTab(tabLayout.newTab().setText("Hot"));
        tabLayout.addTab(tabLayout.newTab().setText("Treding"));
        tabLayout.addTab(tabLayout.newTab().setText("New"));
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
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recommendList = new ArrayList<>();
        recommendList.clear();

        homeFragRef = FirebaseDatabase.getInstance().getReference("VIEWTAB_DATA");
        homeFragRef.child("RECOMMENDED_DATA").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                recommendList.clear();
                for (DataSnapshot recSnap : dataSnapshot.getChildren()){

                    String recommended_song_id = (String) recSnap.child("recommended_song_id").getValue();
                    String recommended_song_image = (String) recSnap.child("recommended_song_image").getValue();
                    String recommended_song_name = (String) recSnap.child("recommended_song_name").getValue();
                    String recommended_song_singer = (String) recSnap.child("recommended_song_singer").getValue();

                    Recommend recommend = new Recommend(recommended_song_id, recommended_song_image ,recommended_song_name ,recommended_song_singer);
                    recommendList.add(recommend);
                }

                RecommendAdapter adapter1 = new RecommendAdapter(getContext(), recommendList);
                recyclerView.setAdapter(adapter1);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        return view;
    }

}
