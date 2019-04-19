package com.example.fwprld.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.fwprld.R;
import com.example.fwprld.models.Recommend;

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
        //inflating and returning our view holder
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.recommend_list, null);
        return new RecommendViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecommendViewHolder holder, int position) {
        //getting the product of the specified position
        Recommend recommend = recommendList.get(position);

        //binding the data with the viewholder views
        holder.textViewTitle.setText(recommend.getTitle());
        holder.textViewSingerName.setText(recommend.getSingerName());

        holder.imageView.setImageDrawable(mCtx.getResources().getDrawable(recommend.getImage()));

    }


    @Override
    public int getItemCount() {
        return recommendList.size();
    }


    class RecommendViewHolder extends RecyclerView.ViewHolder {

        TextView textViewSingerName, textViewTitle;
        ImageView imageView;

        public RecommendViewHolder(View itemView) {
            super(itemView);
            textViewTitle = itemView.findViewById(R.id.textViewTitle);
            textViewSingerName = itemView.findViewById(R.id.textViewSingerName);
            imageView = itemView.findViewById(R.id.imageView);
        }
    }
}