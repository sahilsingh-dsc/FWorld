package com.example.fwprld.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.fwprld.R;
import com.example.fwprld.models.Popular;
import com.example.fwprld.models.Recommend;
import com.example.fwprld.ui.fragments.ProfileFragment;

import java.util.List;

public class PopularAdapter extends RecyclerView.Adapter<PopularAdapter.PopularViewHolder> {


    //this context we will use to inflate the layout
    private Context mCtx;

    //we are storing all the products in a list
    private List<Popular> popularList;

    //getting the context and product list with constructor
    public PopularAdapter(Context mCtx, List<Popular> popularList) {
        this.mCtx = mCtx;
        this.popularList = popularList;
    }

    @Override
    public PopularAdapter.PopularViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //inflating and returning our view holder
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.popular_cardlist, null);
        return new PopularAdapter.PopularViewHolder(view);
    }

    @Override
    public void onBindViewHolder(PopularAdapter.PopularViewHolder holder, int position) {
        //getting the product of the specified position
       Popular popular = popularList.get(position);

        //binding the data with the viewholder views

        holder.textViewUserName.setText(popular.getUserName());

        holder.imageView.setImageDrawable(mCtx.getResources().getDrawable(popular.getImage()));

    }


    @Override
    public int getItemCount() {
        return popularList.size();
    }


    class PopularViewHolder extends RecyclerView.ViewHolder {

        TextView textViewUserName;
        ImageView imageView;

        public PopularViewHolder(View itemView) {
            super(itemView);
            textViewUserName = itemView.findViewById(R.id.username);
            imageView = itemView.findViewById(R.id.userimage);
        }
    }
}