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
import com.example.fwprld.models.FTalent;
import com.example.fwprld.models.Recommend;

import java.util.List;

public class FTalentAdapter extends RecyclerView.Adapter<FTalentAdapter.FTalentViewHolder> {


    //this context we will use to inflate the layout
    private Context mCtx;

    //we are storing all the products in a list
    private List<FTalent> fTalentList;
    String[] Cat = {"Music Creator", "Dancing","Comedian", "Guitarist", "Story Teller", "Shero Shayari", "Others"};
    String category;

    //getting the context and product list with constructor
    public FTalentAdapter(Context mCtx, List<FTalent> fTalentList) {
        this.mCtx = mCtx;
        this.fTalentList = fTalentList;
    }

    @Override
    public FTalentAdapter.FTalentViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //inflating and returning our view holder
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.ftalent_card_list, null);
        return new FTalentAdapter.FTalentViewHolder(view);
    }

    @Override
    public void onBindViewHolder(FTalentAdapter.FTalentViewHolder holder, int position) {
        //getting the product of the specified position
        FTalent fTalent = fTalentList.get(position);

        //binding the data with the viewholder views
        holder.textViewUserName.setText(fTalent.getUserName());
        holder.textViewSongName.setText(fTalent.getSongName());
        holder.textViewPlayTime.setText(fTalent.getPlaytime());
        holder.imageView.setImageDrawable(mCtx.getResources().getDrawable(fTalent.getImage()));
        holder.textViewCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Category();
            }
        });

    }


    @Override
    public int getItemCount() {
        return fTalentList.size();
    }


    class FTalentViewHolder extends RecyclerView.ViewHolder {

        TextView textViewUserName,textViewSongName, textViewPlayTime,textViewCreate;
        ImageView imageView;

        public FTalentViewHolder(View itemView) {
            super(itemView);
            textViewUserName = itemView.findViewById(R.id.textViewUserName);
            textViewSongName = itemView.findViewById(R.id.textViewSongName);
            textViewPlayTime = itemView.findViewById(R.id.textViewPlayTime);
            textViewCreate = itemView.findViewById(R.id.btncreate);
            imageView = itemView.findViewById(R.id.imageView);
        }
    }
    private void Category() {
        final LayoutInflater li = LayoutInflater.from(mCtx);
        //Creating a view to get the dialog box
        View confirmDialog = li.inflate(R.layout.activity_create_dialog, null);

        //Initizliaing confirm button fo dialog box and edittext of dialog box

        final ListView listView = (ListView) confirmDialog.findViewById(R.id.list);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(mCtx, android.R.layout.simple_list_item_1, Cat);
        listView.setAdapter(arrayAdapter);
        //Creating an alertdialog builder
        AlertDialog.Builder alert = new AlertDialog.Builder(mCtx);

        //Adding our dialog box to the view of alert dialog
        alert.setView(confirmDialog);

        //Creating an alert dialog
        final AlertDialog alertDialog = alert.create();

        //Displaying the alert dialog
        alertDialog.show();
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                alertDialog.dismiss();
                category = ((TextView) view).getText().toString();
                Toast.makeText(mCtx, ""+category, Toast.LENGTH_SHORT).show();

            }
        });
    }
}