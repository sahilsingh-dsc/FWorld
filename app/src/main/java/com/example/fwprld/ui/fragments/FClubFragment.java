package com.example.fwprld.ui.fragments;


import android.app.AlertDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.fwprld.R;
import com.example.fwprld.adapters.FClubAdapter;
import com.example.fwprld.adapters.FTalentAdapter;
import com.example.fwprld.models.FClub;
import com.example.fwprld.models.FTalent;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import dmax.dialog.SpotsDialog;

public class FClubFragment extends Fragment {
    RecyclerView recyclerView;
    List<FClub> fClubList;
    DatabaseReference fclubRef;
    private AlertDialog loadingDialog;

    public FClubFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
       View v=inflater.inflate(R.layout.fragment_fclub, container, false);

        loadingDialog = new SpotsDialog.Builder().setContext(getContext())
                .setTheme(R.style.loading)
                .setMessage("Please Wait")
                .setCancelable(false)
                .build();

        loadingDialog.show();

        recyclerView = (RecyclerView) v.findViewById(R.id.recycleViewclub);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(),2));
        fClubList = new ArrayList<>();
        fClubList.clear();

        fclubRef = FirebaseDatabase.getInstance().getReference("FCLUB_DATA");
        fclubRef.child("FCLUB_ROOMS_DATA").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                fClubList.clear();
                for (DataSnapshot roomSnap : dataSnapshot.getChildren()){

                    String room_id = (String) roomSnap.child("room_id").getValue();
                    String room_name = (String) roomSnap.child("room_name").getValue();
                    String room_song = (String) roomSnap.child("room_song").getValue();
                    String room_image = (String) roomSnap.child("room_image").getValue();
                    String room_current_viewers = String.valueOf(roomSnap.child("room_current_viewers").getChildrenCount());
                    String room_queue = String.valueOf(roomSnap.child("room_queue").getChildrenCount());

                    FClub fClub = new FClub(room_id, room_name, room_song, room_image, room_current_viewers ,room_queue);

                    fClubList.add(fClub);
                    loadingDialog.dismiss();
                }

                FClubAdapter adapter = new FClubAdapter(getContext(),  fClubList);
                recyclerView.setAdapter(adapter);
                loadingDialog.dismiss();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(getContext(), "Database Error", Toast.LENGTH_SHORT).show();
                loadingDialog.dismiss();
            }
        });

        return v;
    }


}
