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

import com.example.fwprld.R;
import com.example.fwprld.adapters.RecommendAdapter;
import com.example.fwprld.models.Recommend;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class TredingFragment extends Fragment {

    RecyclerView recyclerView;
    List<Recommend> recommendList;
    DatabaseReference homeFragRef;

    public TredingFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_treding, container, false);
        recyclerView=(RecyclerView) view.findViewById(R.id.recycleView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recommendList = new ArrayList<>();
        homeFragRef = FirebaseDatabase.getInstance().getReference("SONGDATA");
        homeFragRef.child("RECOMMANDED").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                recommendList.clear();
                for (DataSnapshot recSnap : dataSnapshot.getChildren()){

                    String recommended_song_id = (String) recSnap.child("song_id").getValue();
                    String recommended_song_image = (String) recSnap.child("singer_photo").getValue();
                    String recommended_song_name = (String) recSnap.child("song_title").getValue();
                    String recommended_song_singer = (String) recSnap.child("singer_name").getValue();
                    String recommended_song_url = (String) recSnap.child("song_file").getValue();

                    Recommend recommend = new Recommend(recommended_song_id, recommended_song_image ,recommended_song_name ,recommended_song_singer, recommended_song_url);
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

