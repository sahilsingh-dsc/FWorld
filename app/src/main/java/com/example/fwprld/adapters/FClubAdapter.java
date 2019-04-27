package com.example.fwprld.adapters;

import android.app.AlertDialog;
import android.content.Context;
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

import com.example.fwprld.R;
import com.example.fwprld.models.FClub;
import com.example.fwprld.models.FTalent;

import java.util.List;

public class FClubAdapter extends RecyclerView.Adapter<FClubAdapter.FClubViewHolder> {


    //this context we will use to inflate the layout
    private Context mCtx;

    //we are storing all the products in a list
    private List<FClub> fclubList;
   /* String[] Cat = {"Music Creator", "Dancing","Comedian", "Guitarist", "Story Teller", "Shero Shayari", "Others"};
    String category;*/

    //getting the context and product list with constructor
    public FClubAdapter(Context mCtx, List<FClub> fclubList) {
        this.mCtx = mCtx;
        this.fclubList = fclubList;
    }

    @Override
    public FClubAdapter.FClubViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //inflating and returning our view holder
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.fclub_card_list, null);
        return new FClubAdapter.FClubViewHolder(view);
    }

    @Override
    public void onBindViewHolder(FClubAdapter.FClubViewHolder holder, int position) {
        //getting the product of the specified position
      FClub fClub = fclubList.get(position);

        //binding the data with the viewholder views
        holder.textViewUserName.setText(fClub.getUserName());
        holder.textViewUserView.setText(fClub.getUserView());
        holder.textViewSing.setText(fClub.getUserSing());
        holder.textViewGroupeName.setText(fClub.getUserGroupe());
        holder.imageView.setImageDrawable(mCtx.getResources().getDrawable(fClub.getImage()));
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
            imageView = itemView.findViewById(R.id.imageView);
        }
    }

}