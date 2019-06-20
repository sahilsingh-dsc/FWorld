package com.example.fwprld.ui.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterViewFlipper;

import com.example.fwprld.R;
import com.example.fwprld.adapters.MyCustomPager;
import com.example.fwprld.adapters.PopularAdapter;
import com.example.fwprld.adapters.RecommendAdapter;
import com.example.fwprld.adapters.TopStarAdapter;
import com.example.fwprld.models.Popular;
import com.example.fwprld.models.Recommend;
import com.example.fwprld.models.TopStar;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class DiscoverFragment extends Fragment {

    private AdapterViewFlipper adapterViewFlipper;
    private static final int[] IMAGES={R.drawable.slideimage,R.drawable.slideimage1,R.drawable.slideimage2,R.drawable.slideimage3};
    private MyCustomPager adapter;
    RecyclerView recyclerView,recyclerViewftalent,recyclerViewshared,recyclerViewtopstar,recyclerViewtopfriend,recyclerViewtopgifted;
    List<Popular> popularList;
    List<TopStar> topStarList;
    public DiscoverFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_discover, container, false);
        adapterViewFlipper=(AdapterViewFlipper)v.findViewById(R.id.viewpager);
        adapter= new MyCustomPager(getContext(),IMAGES);
        adapterViewFlipper.setAdapter(adapter);
        adapterViewFlipper.setAutoStart(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        LinearLayoutManager layoutManager1 = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        LinearLayoutManager layoutManager2 = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        LinearLayoutManager layoutManager3 = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        LinearLayoutManager layoutManager4 = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        LinearLayoutManager layoutManager5 = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        recyclerView = v.findViewById(R.id.recycleViewpopular);
        recyclerView.setLayoutManager(layoutManager);
        recyclerViewftalent = v.findViewById(R.id.recycleViewftalent);
        recyclerViewftalent.setLayoutManager(layoutManager1);
        recyclerViewshared = v.findViewById(R.id.recycleViewshare);
        recyclerViewshared.setLayoutManager(layoutManager2);
        recyclerViewtopstar = v.findViewById(R.id.recycleViewtopstar);
        recyclerViewtopstar.setLayoutManager(layoutManager3);
        recyclerViewtopfriend = v.findViewById(R.id.recycleViewtopfriend);
        recyclerViewtopfriend.setLayoutManager(layoutManager4);
        recyclerViewtopgifted = v.findViewById(R.id.recycleViewtopgifted);
        recyclerViewtopgifted.setLayoutManager(layoutManager5);
        popularList = new ArrayList<>();
        popularList.add(
                new Popular(
                        1,
                        "Shailesh",
                        R.drawable.pop_img1));

        popularList.add(
                new Popular(
                        2,
                        "Ajay",
                        R.drawable.pop_img2));
        popularList.add(
                new Popular(
                        3,
                        "surbhi",
                        R.drawable.pop_img3));

        //creating recyclerview adapter
       PopularAdapter adapter = new PopularAdapter(getContext(), popularList);
        recyclerView.setAdapter(adapter);//popular in mp
        recyclerViewftalent.setAdapter(adapter);//ftalent
        recyclerViewshared.setAdapter(adapter);//most shared

        topStarList = new ArrayList<>();
        topStarList.add(
                new TopStar(
                        1,
                        "Shailesh",
                        R.drawable.pop_img4));

        topStarList.add(
                new TopStar(
                        2,
                        "Ajay",
                        R.drawable.pop_img2));
        topStarList.add(
                new TopStar(
                        3,
                        "Rohan",
                        R.drawable.pop_img6));
        topStarList.add(
                new TopStar(
                        4,
                        "Aliya",
                        R.drawable.pop_img1));
        TopStarAdapter adapter1 = new TopStarAdapter(getContext(),topStarList);
        recyclerViewtopstar.setAdapter(adapter1);//top star
        recyclerViewtopfriend.setAdapter(adapter1);//top friends
        recyclerViewtopgifted.setAdapter(adapter1);//top gifted
        return v;
    }

}
