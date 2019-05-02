package com.example.fwprld.ui.fragments;

import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AutoCompleteTextView;

import com.example.fwprld.R;
import com.example.fwprld.adapters.FTalentAdapter;
import com.example.fwprld.adapters.RecommendAdapter;
import com.example.fwprld.models.FTalent;
import com.example.fwprld.models.Recommend;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


public class FTalentFragment extends Fragment {
    RecyclerView recyclerView;
    List<FTalent> ftalentList;
    View view;
    public FTalentFragment(){

    }
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_ftalent, container, false);
        recyclerView= (RecyclerView) view.findViewById(R.id.recycleView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        ftalentList = new ArrayList<>();
        ftalentList.clear();

        DatabaseReference ftalentRef = FirebaseDatabase.getInstance().getReference("FTALENT_DATA");
        ftalentRef.child("FTALENT_SONGS_DATA").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                ftalentList.clear();
                for (DataSnapshot ftalSnap : dataSnapshot.getChildren()){

                    String ftal_user = (String) ftalSnap.child("user_id").getValue();
                    String song_id = (String) ftalSnap.child("SONG_DETAILS").child("song_id").getValue();
                    String song_image = (String) ftalSnap.child("SONG_DETAILS").child("song_image").getValue();
                    String song_name = (String) ftalSnap.child("SONG_DETAILS").child("song_name").getValue();
                    String song_plays = (String) ftalSnap.child("SONG_DETAILS").child("song_plays").getValue();
                    String song_singer = (String) ftalSnap.child("SONG_DETAILS").child("song_singer").getValue();
                    String song_playtime = (String) ftalSnap.child("SONG_DETAILS").child("song_playtime").getValue();

                    DatabaseReference userRef = FirebaseDatabase.getInstance().getReference("USER_DATA");
                    assert ftal_user != null;
                    userRef.child("USER_PROFILE").child(ftal_user).addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                         //   String user_name
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });

                    FTalent fTalent = new FTalent(ftal_user, song_id ,song_image ,song_name ,song_plays ,song_singer, song_playtime);

                    ftalentList.add(fTalent);

                }

                FTalentAdapter adapter = new FTalentAdapter(getContext(),  ftalentList);
                recyclerView.setAdapter(adapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        return view;
    }


}
