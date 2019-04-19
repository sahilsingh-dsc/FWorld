package com.example.fwprld.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.example.fwprld.R;

public class MyCustomPager extends BaseAdapter {
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

