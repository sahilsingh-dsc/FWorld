package com.example.fwprld.ui.fragments;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterViewFlipper;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.fwprld.R;
import com.example.fwprld.adapters.FTalentAdapter;
import com.example.fwprld.adapters.MyCustomPager;
import com.example.fwprld.adapters.PopularAdapterNew;
import com.example.fwprld.adapters.PopularAdpt;
import com.example.fwprld.common.SpacesItemDecoration;
import com.example.fwprld.models.FTalent;
import com.example.fwprld.models.Popular;
import com.example.fwprld.models.PopularListBean;
import com.example.fwprld.models.SongBean;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class PopularFragment extends Fragment {
//    GridView gvPopular;
    private AdapterViewFlipper adapterViewFlipper;
    private static final int[] IMAGES={R.drawable.slideimage,R.drawable.slideimage1,R.drawable.slideimage2,R.drawable.slideimage3};
    private MyCustomPager adapter;
    RecyclerView recyclerView;
    List<FTalent> ftalentList;

//    List<String > popularUser;
    List<SongBean> popularList;
    String ftal_user, song_id, song_image, song_name, song_plays, song_singer, song_playtime, songby_name, songby_image;
    android.widget.SearchView searchView;

    public PopularFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

       View v = inflater.inflate(R.layout.fragment_popular, container, false);
        adapterViewFlipper=(AdapterViewFlipper)v.findViewById(R.id.viewpager);
        searchView= (android.widget.SearchView) v.findViewById(R.id.search);
        adapter= new MyCustomPager(getContext(),IMAGES);
        adapterViewFlipper.setAdapter(adapter);
        adapterViewFlipper.setAutoStart(true);


        recyclerView=(RecyclerView)v.findViewById(R.id.recycleView);
        recyclerView.setHasFixedSize(true);
//        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(),2);
        recyclerView.setLayoutManager(gridLayoutManager);
        ftalentList = new ArrayList<>();
        popularList = new ArrayList<>();
//        gvPopular=(GridView)v.findViewById(R.id.gv_popular);
//        ftalentList.clear();
        //                PopularAdpt
//        PopularAdpt customAdapter = new PopularAdpt(getActivity(), ftalentList);
//        gvPopular.setAdapter(customAdapter);



//        DatabaseReference ftalentRef = FirebaseDatabase.getInstance().getReference("FTALENT_DATA");//11
//        ftalentRef.child("FTALENT_SONGS_DATA").addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                ftalentList.clear();
//                for (DataSnapshot ftalSnap : dataSnapshot.getChildren()){
//                    ftal_user = (String) ftalSnap.child("user_id").getValue();
//                    song_id = (String) ftalSnap.child("SONG_DETAILS").child("song_id").getValue();
//                    song_image = (String) ftalSnap.child("SONG_DETAILS").child("song_image").getValue();
//                    song_name = (String) ftalSnap.child("SONG_DETAILS").child("song_name").getValue();
//                    song_plays = (String) ftalSnap.child("SONG_DETAILS").child("song_plays").getValue();
//                    song_singer = (String) ftalSnap.child("SONG_DETAILS").child("song_singer").getValue();
//                    song_playtime = (String) ftalSnap.child("SONG_DETAILS").child("song_playtime").getValue();
//                    songby_name = (String) ftalSnap.child("SONG_DETAILS").child("songby_name").getValue();
//                    songby_image = (String) ftalSnap.child("SONG_DETAILS").child("songby_image").getValue();
//
//                    FTalent fTalent = new FTalent(ftal_user, song_id ,song_image ,song_name ,song_plays ,song_singer, song_playtime, songby_name ,songby_image);
//                    ftalentList.add(fTalent);
//                }
//
//                FTalentAdapter adapter = new FTalentAdapter(getContext(),  ftalentList);
////                PopularAdapterNew adapter = new PopularAdapterNew(getContext(),  ftalentList);
////                int spacingInPixels = getResources().getDimensionPixelSize(R.dimen.spacing);
////                recyclerView.addItemDecoration(new SpacesItemDecoration(spacingInPixels));
//                recyclerView.setAdapter(adapter);
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//
//            }
//        });

        DatabaseReference popular = FirebaseDatabase.getInstance().getReference("POPULAR_USERS");
        popular.child("POPULAR_IN_MP").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                popularList.clear();
                for (DataSnapshot ftalSnap : dataSnapshot.getChildren()){

                    String user_name = (String) ftalSnap.child("user_name").getValue();
                    String  user_fid = (String) ftalSnap.child("user_id").getValue();
                    String  user_pic = (String) ftalSnap.child("user_pic").getValue();

                    String song_id = (String) ftalSnap.child("song_id").getValue();
                    String song_image = (String) ftalSnap.child("song_image").getValue();
                    String song_like = (String) ftalSnap.child("song_like").getValue();

                    String song_plays = (String) ftalSnap.child("song_plays").getValue();
                    String song_playtime = (String) ftalSnap.child("song_playtime").getValue();
                    String song_singer = (String) ftalSnap.child("song_singer").getValue();
                    String songby_image = (String) ftalSnap.child("songby_image").getValue();
                    String songby_name = (String) ftalSnap.child("songby_name").getValue();
                    String  song_name = (String) ftalSnap.child("song_name").getValue();

//                    popularUser.add(pop_userid);
//                    PopularListBean bean =  new PopularListBean(user_fid,user_name,"",pop_id);
//                    popularList.add(bean);

                    SongBean bean =  new SongBean(song_id,song_image,
                            song_name,song_like,song_plays,song_playtime,song_singer,songby_image
                            ,songby_name,user_fid,user_name,user_pic);
                    popularList.add(bean);


                }

                PopularAdapterNew adapter = new PopularAdapterNew(getContext(),  popularList);
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        return v;
    }
    public void gePoptUserData()
    {

    }

}
