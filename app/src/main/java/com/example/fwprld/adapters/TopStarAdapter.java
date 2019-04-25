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
import com.example.fwprld.models.TopStar;

import java.util.List;

public class TopStarAdapter extends RecyclerView.Adapter<TopStarAdapter.TopStarViewHolder> {


    //this context we will use to inflate the layout
    private Context mCtx;

    //we are storing all the products in a list
    private List<TopStar> topStarList;

    //getting the context and product list with constructor
    public TopStarAdapter(Context mCtx, List<TopStar> topStarList) {
        this.mCtx = mCtx;
        this.topStarList = topStarList;
    }

    @Override
    public TopStarAdapter.TopStarViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //inflating and returning our view holder
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.topstar_card_list, null);
        return new TopStarAdapter.TopStarViewHolder(view);
    }

    @Override
    public void onBindViewHolder(TopStarAdapter.TopStarViewHolder holder, int position) {
        //getting the product of the specified position
        TopStar topStar = topStarList.get(position);

        //binding the data with the viewholder views
        holder.textViewUsername.setText(topStar.getName());
        holder.imageView.setImageDrawable(mCtx.getResources().getDrawable(topStar.getPicture()));

    }


    @Override
    public int getItemCount() {
        return topStarList.size();
    }


    class TopStarViewHolder extends RecyclerView.ViewHolder {

        TextView textViewUsername;
        ImageView imageView;

        public TopStarViewHolder(View itemView) {
            super(itemView);
            textViewUsername = itemView.findViewById(R.id.name);
            imageView = itemView.findViewById(R.id.picture);
        }
    }
}