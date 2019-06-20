package com.example.fwprld.ui.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.SearchView;
import android.widget.Toast;

import com.example.fwprld.R;
import com.example.fwprld.adapters.FTalentAdapter;
import com.example.fwprld.adapters.PopularAdpt;
import com.example.fwprld.models.FTalent;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


public class FollowingFragment extends Fragment {

    RecyclerView recyclerView;
    List<FTalent> ftalentList;
    android.widget.SearchView searchView;
    String ftal_user, song_id, song_image, song_name, song_plays, song_singer, song_playtime, songby_name, songby_image;
    public FollowingFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_following, container, false);
        searchView= (android.widget.SearchView) v.findViewById(R.id.search);


        recyclerView=(RecyclerView)v.findViewById(R.id.recycleView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        ftalentList = new ArrayList<>();




        DatabaseReference ftalentRef = FirebaseDatabase.getInstance().getReference("FTALENT_DATA");
        ftalentRef.child("FTALENT_SONGS_DATA").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                ftalentList.clear();
                for (DataSnapshot ftalSnap : dataSnapshot.getChildren()){

                    ftal_user = (String) ftalSnap.child("user_id").getValue();
                    song_id = (String) ftalSnap.child("SONG_DETAILS").child("song_id").getValue();
                    song_image = (String) ftalSnap.child("SONG_DETAILS").child("song_image").getValue();
                    song_name = (String) ftalSnap.child("SONG_DETAILS").child("song_name").getValue();
                    song_plays = (String) ftalSnap.child("SONG_DETAILS").child("song_plays").getValue();
                    song_singer = (String) ftalSnap.child("SONG_DETAILS").child("song_singer").getValue();
                    song_playtime = (String) ftalSnap.child("SONG_DETAILS").child("song_playtime").getValue();
                    songby_name = (String) ftalSnap.child("SONG_DETAILS").child("songby_name").getValue();
                    songby_image = (String) ftalSnap.child("SONG_DETAILS").child("songby_image").getValue();


                    FTalent fTalent = new FTalent(ftal_user, song_id ,song_image ,song_name ,song_plays ,song_singer, song_playtime, songby_name ,songby_image);
                    ftalentList.add(fTalent);

                }

                FTalentAdapter adapter = new FTalentAdapter(getContext(),  ftalentList);
                recyclerView.setAdapter(adapter);


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        return v;
    }



}
