package com.example.fwprld.ui.activities;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterViewFlipper;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SearchView;

import com.example.fwprld.R;
import com.example.fwprld.adapters.MyCustomPager;
import com.example.fwprld.adapters.RecommendAdapter;
import com.example.fwprld.models.Recommend;
import com.example.fwprld.ui.activities.LoginActivity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private AdapterViewFlipper adapterViewFlipper;
    private static final int[] IMAGES={R.drawable.slideimage,R.drawable.slideimage1,R.drawable.slideimage2,R.drawable.slideimage3};
    LinearLayout profile;
    RecyclerView recyclerView;
    List<Recommend> recommendList;
    private MyCustomPager adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SearchView searchView=(SearchView)findViewById(R.id.search);
        profile=(LinearLayout)findViewById(R.id.profile);
        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), LoginActivity.class));
            }
        });
        adapterViewFlipper=(AdapterViewFlipper) findViewById(R.id.viewpager);
        adapter= new MyCustomPager(getApplicationContext(),IMAGES);
        adapterViewFlipper.setAdapter(adapter);
        adapterViewFlipper.setAutoStart(true);
        recyclerView=(RecyclerView)findViewById(R.id.recycleView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
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
        RecommendAdapter adapter1 = new RecommendAdapter(this, recommendList);

        //setting adapter to recyclerview
        recyclerView.setAdapter(adapter1);
    }
   /* class MyCustomPager extends BaseAdapter {
        Context otx;
        int [] images;
        LayoutInflater layoutInflater;

        public MyCustomPager(Context otx,int []myimages){
            this.otx=otx;
            this.images= myimages;
            this.layoutInflater=LayoutInflater.from(otx);

        }
        @Override
        public int getCount() {
            return images.length;

        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            view=layoutInflater.inflate(R.layout.slideimage,null);
            ImageView imageView=(ImageView)view.findViewById(R.id.slideimage);
            imageView.setImageResource(images[i]);
            return view;
        }




    }*/
}
