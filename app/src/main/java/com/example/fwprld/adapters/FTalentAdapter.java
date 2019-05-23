package com.example.fwprld.adapters;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.fwprld.R;
import com.example.fwprld.models.FTalent;
import com.example.fwprld.models.Recommend;

import java.util.List;

public class FTalentAdapter extends RecyclerView.Adapter<FTalentAdapter.FTalentViewHolder> {

    private Context mCtx;
    private List<FTalent> fTalentList;
    String[] Cat = {"Music Creator", "Dancing","Comedian", "Story Teller", "Others"};
    String category;

    public FTalentAdapter(Context mCtx, List<FTalent> fTalentList) {
        this.mCtx = mCtx;
        this.fTalentList = fTalentList;
    }

    @Override
    public FTalentAdapter.FTalentViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.ftalent_card_list, null);
        return new FTalentAdapter.FTalentViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final FTalentAdapter.FTalentViewHolder holder, int position) {

        FTalent fTalent = fTalentList.get(position);

        holder.textViewUserName.setText(fTalent.getSinger_name());
        holder.textViewSongName.setText(fTalent.getSong_name());
        holder.textViewPlayTime.setText(fTalent.getSong_playtime());
        holder.txtSongPlaysCount.setText(fTalent.getSong_plays());
        Glide.with(mCtx).load(fTalent.getSinger_image()).into(holder.imageViewuser);
        Glide.with(mCtx).load(fTalent.getSong_image()).into(holder.imgThumb1);

        holder.textViewCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               Category();
                //holder.relativeLayout.setVisibility(View.VISIBLE);
            }
        });

    }


    @Override
    public int getItemCount() {
        return fTalentList.size();
    }


    class FTalentViewHolder extends RecyclerView.ViewHolder {

        TextView textViewUserName,textViewSongName, textViewPlayTime,textViewCreate, txtSongPlaysCount;
        ImageView imageView, imageViewuser, imgThumb1;
       // RelativeLayout relativeLayout;

        public FTalentViewHolder(View itemView) {
            super(itemView);
            textViewUserName = itemView.findViewById(R.id.textViewUserName);
            textViewSongName = itemView.findViewById(R.id.textViewSongName);
            textViewPlayTime = itemView.findViewById(R.id.txtSongPlayTime);
            txtSongPlaysCount = itemView.findViewById(R.id.txtSongPlaysCount);
            textViewCreate = itemView.findViewById(R.id.btncreate);
            imageView = itemView.findViewById(R.id.imgProfileAvatar);
            imgThumb1 = itemView.findViewById(R.id.imgThumb1);
            imageViewuser = itemView.findViewById(R.id.imageViewuser);
            //relativeLayout=itemView.findViewById(R.id.rl);
        }
    }
    @SuppressLint("NewApi")
    private void Category() {
        final LayoutInflater li = LayoutInflater.from(mCtx);

        View confirmDialog = li.inflate(R.layout.arc_menu, null);
       // confirmDialog.setBackgroundColor(mCtx.getColor(R.color.transparent));
       /* final ListView listView = (ListView) confirmDialog.findViewById(R.id.list);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(mCtx, android.R.layout.simple_list_item_1, Cat);
        listView.setAdapter(arrayAdapter);*/
        AlertDialog.Builder alert = new AlertDialog.Builder(mCtx);
        alert.setView(confirmDialog);
        final AlertDialog alertDialog = alert.create();
        alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        alertDialog.show();
      /*  listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                alertDialog.dismiss();
                category = ((TextView) view).getText().toString();
                Toast.makeText(mCtx, ""+category, Toast.LENGTH_SHORT).show();

            }
        });*/
    }
}