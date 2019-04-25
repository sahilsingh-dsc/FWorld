package com.example.fwprld.ui.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;
import android.widget.Toast;

import com.example.fwprld.R;
import com.example.fwprld.adapters.FTalentAdapter;
import com.example.fwprld.models.FTalent;

import java.util.ArrayList;
import java.util.List;


public class FollowingFragment extends Fragment {
    RecyclerView recyclerView;
    List<FTalent> ftalentList;
    android.widget.SearchView searchView;
    public FollowingFragment() {
        // Required empty public constructor
    }




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_following, container, false);
        searchView= (android.widget.SearchView) v.findViewById(R.id.search);
        recyclerView=(RecyclerView)v.findViewById(R.id.recycleView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        ftalentList = new ArrayList<>();
        ftalentList.add(
                new FTalent(
                        1,
                        "Shubham",
                        "Coca Cola Tu",
                        "1:40 4k plays",
                        R.drawable.backimgpro));

        ftalentList.add(
                new FTalent(
                        2,
                        "Varsha",
                        "Sab Tera",
                        "2:12 8k plays",
                        R.drawable.backimgpro));
        ftalentList.add(
                new FTalent(
                        3,
                        "Dharmendra",
                        "Tu Mera Dost Hai",
                        "1:25 6k plays",
                        R.drawable.backimgpro));

        //creating recyclerview adapter
        FTalentAdapter adapter = new FTalentAdapter(getContext(),  ftalentList);

        //setting adapter to recyclerview
        recyclerView.setAdapter(adapter);

        return v;
    }



}
