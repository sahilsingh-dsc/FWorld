package com.example.fwprld.ui.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
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

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {

    private AdapterViewFlipper adapterViewFlipper;
    private static final int[] IMAGES={R.drawable.slideimage,R.drawable.slideimage1,R.drawable.slideimage2,R.drawable.slideimage3};

    RecyclerView recyclerView;
    List<Recommend> recommendList;
    private MyCustomPager adapter;
    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
         View v= inflater.inflate(R.layout.fragment_home, container, false);
        SearchView searchView=(SearchView)v.findViewById(R.id.search);
        adapterViewFlipper=(AdapterViewFlipper)v.findViewById(R.id.viewpager);
        adapter= new MyCustomPager(getContext(),IMAGES);
        adapterViewFlipper.setAdapter(adapter);
        adapterViewFlipper.setAutoStart(true);
        recyclerView=(RecyclerView)v.findViewById(R.id.recycleView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recommendList = new ArrayList<>();
        recommendList.add(
                new Recommend(
                        1,
                        "Coca Cola Tu",
                        "Tony Kakkar",
                        R.drawable.homepage1));

        recommendList.add(
                new Recommend(
                        1,
                        "Sab Tera",
                        "Tony Kakkar",
                        R.drawable.homepage2));
        recommendList.add(
                new Recommend(
                        1,
                        "Agar Tu Hota",
                        "Ankit Tiwari",
                        R.drawable.homepage3));

        //creating recyclerview adapter
        RecommendAdapter adapter1 = new RecommendAdapter(getContext(), recommendList);

        //setting adapter to recyclerview
        recyclerView.setAdapter(adapter1);
        return v;
    }

}
