package com.example.fwprld.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.example.fwprld.R;
import com.example.fwprld.models.FTalent;

import java.util.ArrayList;
import java.util.List;

public class PopularAdpt extends BaseAdapter {
    Context context;
//    int logos[];
List<FTalent> logos=new ArrayList<FTalent>() {};
    LayoutInflater inflter;
    public PopularAdpt(Context applicationContext, List<FTalent> logos_) {
        this.context = applicationContext;
//        this.logos = logos;
        logos=logos_;
        inflter = (LayoutInflater.from(applicationContext));
    }
    @Override
    public int getCount() {
//        return logos.length;
        return 2;
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
        view = inflter.inflate(R.layout.custom_popular_list, null); // inflate the layout
//        ImageView icon = (ImageView) view.findViewById(R.id.icon); // get the reference of ImageView
//        icon.setImageResource(logos[i]); // set logo images
        return view;
    }
}