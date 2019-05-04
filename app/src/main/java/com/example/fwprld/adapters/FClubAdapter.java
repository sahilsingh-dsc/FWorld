package com.example.fwprld.adapters;

import android.app.AlertDialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.fwprld.R;
import com.example.fwprld.models.FClub;
import com.example.fwprld.models.FTalent;

import java.util.List;

public class FClubAdapter extends RecyclerView.Adapter<FClubAdapter.FClubViewHolder> {

    private Context mCtx;
    private List<FClub> fclubList;

    public FClubAdapter(Context mCtx, List<FClub> fclubList) {
        this.mCtx = mCtx;
        this.fclubList = fclubList;
    }

    @NonNull
    @Override
    public FClubAdapter.FClubViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.fclub_card_list, null);
        return new FClubAdapter.FClubViewHolder(view);
    }

    @Override
    public void onBindViewHolder(FClubAdapter.FClubViewHolder holder, int position) {
        FClub fClub = fclubList.get(position);

        holder.textViewUserName.setText(fClub.getRoom_song());
        holder.textViewUserView.setText(fClub.getRoom_current_viewers());
        holder.textViewSing.setText(fClub.getRoom_queue());
        holder.textViewGroupeName.setText(fClub.getRoom_name());
        Glide.with(mCtx).load(fClub.getRoom_image()).into(holder.imageView);
    }


    @Override
    public int getItemCount() {
        return fclubList.size();
    }


    class FClubViewHolder extends RecyclerView.ViewHolder {

        TextView textViewUserName,textViewUserView, textViewSing,textViewGroupeName;
        ImageView imageView;

        public FClubViewHolder(View itemView) {
            super(itemView);
            textViewUserName = itemView.findViewById(R.id.username);
            textViewUserView = itemView.findViewById(R.id.userview);
            textViewSing = itemView.findViewById(R.id.usersing);
            textViewGroupeName = itemView.findViewById(R.id.groupename);
            imageView = itemView.findViewById(R.id.imgProfileAvatar);
        }
    }

}