package com.example.fwprld.ui.fragments;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterViewFlipper;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.fwprld.R;
import com.example.fwprld.adapters.FTalentAdapter;
import com.example.fwprld.adapters.MyCustomPager;
import com.example.fwprld.models.FTalent;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class PopularFragment extends Fragment {

    private AdapterViewFlipper adapterViewFlipper;
    private static final int[] IMAGES={R.drawable.slideimage,R.drawable.slideimage1,R.drawable.slideimage2,R.drawable.slideimage3};
    private MyCustomPager adapter;
    RecyclerView recyclerView;
    List<FTalent> ftalentList;
    String ftal_user, song_id, song_image, song_name, song_plays, song_singer, song_playtime, user_fullname, user_image;
    android.widget.SearchView searchView;
    public PopularFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       View v = inflater.inflate(R.layout.fragment_popular, container, false);
        adapterViewFlipper=(AdapterViewFlipper)v.findViewById(R.id.viewpager);
        searchView= (android.widget.SearchView) v.findViewById(R.id.search);
        adapter= new MyCustomPager(getContext(),IMAGES);
        adapterViewFlipper.setAdapter(adapter);
        adapterViewFlipper.setAutoStart(true);
        recyclerView=(RecyclerView)v.findViewById(R.id.recycleView);
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

                    ftal_user = (String) ftalSnap.child("user_id").getValue();
                    song_id = (String) ftalSnap.child("SONG_DETAILS").child("song_id").getValue();
                    song_image = (String) ftalSnap.child("SONG_DETAILS").child("song_image").getValue();
                    song_name = (String) ftalSnap.child("SONG_DETAILS").child("song_name").getValue();
                    song_plays = (String) ftalSnap.child("SONG_DETAILS").child("song_plays").getValue();
                    song_singer = (String) ftalSnap.child("SONG_DETAILS").child("song_singer").getValue();
                    song_playtime = (String) ftalSnap.child("SONG_DETAILS").child("song_playtime").getValue();

                    DatabaseReference userRef = FirebaseDatabase.getInstance().getReference("USER_DATA");
                    assert ftal_user != null;
                    userRef.child("USER_PROFILE").child(ftal_user).addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            user_fullname = (String) dataSnapshot.child("user_fullname").getValue();
                            user_image = (String) dataSnapshot.child("user_image").getValue();
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {
                            Toast.makeText(getContext(), "Database Error", Toast.LENGTH_SHORT).show();
                        }
                    });

                    FTalent fTalent = new FTalent(ftal_user, song_id ,song_image ,song_name ,song_plays ,song_singer, song_playtime, user_fullname ,user_image);
                    ftalentList.add(fTalent);

                }

                FTalentAdapter adapter = new FTalentAdapter(getContext(),  ftalentList);
                recyclerView.setAdapter(adapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        FTalentAdapter adapter = new FTalentAdapter(getContext(),  ftalentList);
        recyclerView.setAdapter(adapter);

        return v;
    }

}
