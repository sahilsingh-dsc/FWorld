package com.example.fwprld.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.fwprld.R;
import com.example.fwprld.models.FTalent;
import com.example.fwprld.ui.activities.ActPopDetail;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class CommentsAdptr extends RecyclerView.Adapter<CommentsAdptr.PopularViewHolder> {

    private Context mCtx;
    private List<FTalent> fTalentList;

    public CommentsAdptr(Context mCtx, List<FTalent> fTalentList) {
        this.mCtx = mCtx;
        this.fTalentList = fTalentList;
    }

    @Override
    public CommentsAdptr.PopularViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.custom_comment_list, null);
        return new CommentsAdptr.PopularViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final CommentsAdptr.PopularViewHolder holder, int position) {

        switch (position)
        {
            case 0:
                holder.ivUsrPic.setImageResource(R.drawable.dumm_pop_img);
                break;
            case 1:
                holder.ivUsrPic.setImageResource(R.drawable.pop_image1);
                break;
            case 2:
                holder.ivUsrPic.setImageResource(R.drawable.pop_image2);
                break;
            case 3:
                holder.ivUsrPic.setImageResource(R.drawable.pop_image3);
                break;
            case 4:
                holder.ivUsrPic.setImageResource(R.drawable.pop_image4);
                break;
//            case 5:
//                holder.ivUsrPic.setImageResource(R.drawable.pop_image5);
//                break;
        }

//        FTalent fTalent = fTalentList.get(position);
//        holder.groupename.setText(fTalent.getSinger_name());
//        holder.tvUsrSubNm.setText(fTalent.getSong_name());

//        holder.textViewPlayTime.setText(fTalent.getSong_playtime());
//        holder.txtSongPlaysCount.setText(fTalent.getSong_plays());
//        Glide.with(mCtx).load(fTalent.getSinger_image()).into(holder.imageViewuser);
//        Glide.with(mCtx).load(fTalent.getSong_image()).into(holder.imgThumb1);
//        holder.imageView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
////                ActPopDetail
//                mCtx.startActivity(new Intent(mCtx, ActPopDetail.class));
////               Category();
//            }
//        });

    }


    @Override
    public int getItemCount() {
        return 2;
//        return fTalentList.size();
    }


    class PopularViewHolder extends RecyclerView.ViewHolder {

        TextView groupename,tvUsrSubNm, tvLike;
        ImageView imageView;
        CircleImageView ivUsrPic;

        public PopularViewHolder(View itemView) {
            super(itemView);

            groupename = itemView.findViewById(R.id.groupename);
            tvUsrSubNm = itemView.findViewById(R.id.tv_usr_sub_nm);
            tvLike = itemView.findViewById(R.id.tv_like);
            imageView = itemView.findViewById(R.id.imgProfileAvatar);
            ivUsrPic = itemView.findViewById(R.id.iv_user_pic);


        }
    }
//    @SuppressLint("NewApi")
//    private void Category() {
//        final LayoutInflater li = LayoutInflater.from(mCtx);
//
//        View confirmDialog = li.inflate(R.layout.arc_menu, null);
//       // confirmDialog.setBackgroundColor(mCtx.getColor(R.color.transparent));
//       /* final ListView listView = (ListView) confirmDialog.findViewById(R.id.list);
//        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(mCtx, android.R.layout.simple_list_item_1, Cat);
//        listView.setAdapter(arrayAdapter);*/
//        AlertDialog.Builder alert = new AlertDialog.Builder(mCtx);
//        alert.setView(confirmDialog);
//        final AlertDialog alertDialog = alert.create();
//        alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
//        alertDialog.show();
//      /*  listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                alertDialog.dismiss();
//                category = ((TextView) view).getText().toString();
//                Toast.makeText(mCtx, ""+category, Toast.LENGTH_SHORT).show();
//
//            }
//        });*/
//    }
}