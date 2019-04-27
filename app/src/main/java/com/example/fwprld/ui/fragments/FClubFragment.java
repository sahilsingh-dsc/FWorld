package com.example.fwprld.ui.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.fwprld.R;
import com.example.fwprld.adapters.FClubAdapter;
import com.example.fwprld.adapters.FTalentAdapter;
import com.example.fwprld.models.FClub;
import com.example.fwprld.models.FTalent;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class FClubFragment extends Fragment {
    RecyclerView recyclerView;
    List<FClub> fClubList;

    public FClubFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       View v=inflater.inflate(R.layout.fragment_fclub, container, false);
        recyclerView=(RecyclerView)v.findViewById(R.id.recycleViewclub);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(),2));
        fClubList = new ArrayList<>();
        fClubList.add(
                new FClub(
                        1,
                        "Shubham",
                        "48",
                        "54",
                        "Happygroupe",
                        R.drawable.backimgpro));

        fClubList.add(
                new FClub(
                        1,
                        "Varsha",
                        "56",
                        "29",
                        "Familygroupe",
                        R.drawable.backimgpro));
        fClubList.add(
                new FClub(
                        3,
                        "Dharmendra",
                        "32",
                        "23",
                        "Partygroupe",
                        R.drawable.backimgpro));
        fClubList.add(
                new FClub(
                        4,
                        "Sahil",
                        "32",
                        "23",
                        "Friendsgroupe",
                        R.drawable.backimgpro));
        fClubList.add(
                new FClub(
                        4,
                        "Mustafa",
                        "32",
                        "46",
                        "clubgroupe",
                        R.drawable.backimgpro));
        fClubList.add(
                new FClub(
                        4,
                        "Ankit",
                        "36",
                        "5",
                        "partygroupe",
                        R.drawable.backimgpro));

        //creating recyclerview adapter
        FClubAdapter adapter = new FClubAdapter(getContext(),  fClubList);

        //setting adapter to recyclerview
        recyclerView.setAdapter(adapter);
        return v;
    }


}
