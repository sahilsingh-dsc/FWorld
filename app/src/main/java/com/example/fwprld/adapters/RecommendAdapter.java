package com.example.fwprld.adapters;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.fwprld.R;
import com.example.fwprld.models.Recommend;
import com.example.fwprld.ui.activities.Camera2VideoImageActivity;
import com.example.fwprld.ui.activities.SoloActivity;
import com.example.fwprld.ui.activities.SoloVideoActivity;

import java.util.List;

public class RecommendAdapter extends RecyclerView.Adapter<RecommendAdapter.RecommendViewHolder> {


    //this context we will use to inflate the layout
    private Context mCtx;

    //we are storing all the products in a list
    private List<Recommend> recommendList;

    //getting the context and product list with constructor
    public RecommendAdapter(Context mCtx, List<Recommend> recommendList) {
        this.mCtx = mCtx;
        this.recommendList = recommendList;
    }

    @Override
    public RecommendViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.recommend_list, null);
        return new RecommendViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecommendViewHolder holder, int position) {

        final Recommend recommend = recommendList.get(position);
        holder.textViewTitle.setText(recommend.getRecommended_song_name());
        holder.textViewSingerName.setText(recommend.getRecommended_song_singer());
        Glide.with(mCtx).load(recommend.getRecommended_song_image()).into(holder.imageView);
        holder.txtSingSong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (recommend.getRecommended_song_url() != null){
                    Intent songIntent = new Intent(mCtx, Camera2VideoImageActivity.class);
                    Bundle songBundle = new Bundle();
                    songBundle.putString("recommended_song_name", recommend.getRecommended_song_name());
                    songBundle.putString("recommended_song_singer", recommend.getRecommended_song_singer());
                    songBundle.putString("song_url", recommend.getRecommended_song_url());
                    songIntent.putExtras(songBundle);
                    mCtx.startActivity(songIntent);

                }

            }
        });

    }


    @Override
    public int getItemCount() {
        return recommendList.size();
    }


    class RecommendViewHolder extends RecyclerView.ViewHolder {

        TextView textViewSingerName, textViewTitle, txtSingSong;
        ImageView imageView;

        public RecommendViewHolder(View itemView) {
            super(itemView);
            textViewTitle = itemView.findViewById(R.id.textViewTitle);
            textViewSingerName = itemView.findViewById(R.id.textViewSingerName);
            imageView = itemView.findViewById(R.id.imgProfileAvatar);
            txtSingSong = itemView.findViewById(R.id.txtSingSong);
        }
    }
}