package com.example.fwprld;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterViewFlipper;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SearchView;

public class MainActivity extends AppCompatActivity {
    private AdapterViewFlipper adapterViewFlipper;
    private static final int[] IMAGES={R.drawable.slideimage,R.drawable.slideimage,R.drawable.slideimage,R.drawable.slideimage,R.drawable.slideimage};
    LinearLayout profile;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TabLayout tabLayout = (TabLayout)findViewById(R.id.tabLayout);
        tabLayout.addTab(tabLayout.newTab().setText("recommend"));
        tabLayout.addTab(tabLayout.newTab().setText("hot"));
        tabLayout.addTab(tabLayout.newTab().setText("trending"));
        tabLayout.addTab(tabLayout.newTab().setText("new"));
        SearchView searchView=(SearchView)findViewById(R.id.search);
        profile=(LinearLayout)findViewById(R.id.profile);
        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),LoginActivity.class));
            }
        });
        adapterViewFlipper=(AdapterViewFlipper) findViewById(R.id.viewpager);
        MyCustomPager adapter=new MyCustomPager(getApplicationContext(),IMAGES);
        adapterViewFlipper.setAdapter(adapter);
        adapterViewFlipper.setAutoStart(true);
       /* TabLayout tabLayout1 = (TabLayout) findViewById(R.id.tabDots);
        tabLayout.setupWithViewPager(adapterViewFlipper, true);*/
    }
    class MyCustomPager extends BaseAdapter {
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




    }
}
