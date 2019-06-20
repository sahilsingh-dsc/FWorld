package com.example.fwprld.adapters;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.fwprld.R;
import com.example.fwprld.models.FTalent;
import com.example.fwprld.models.PopularListBean;
import com.example.fwprld.models.SongBean;
import com.example.fwprld.ui.activities.ActPopDetail;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class PopularAdapterNew extends RecyclerView.Adapter<PopularAdapterNew.PopularViewHolder> {

    private Context mCtx;
    private List<SongBean> popularList;

    public PopularAdapterNew(Context mCtx, List<SongBean> popularList) {
        this.mCtx = mCtx;
        this.popularList = popularList;
    }

    @Override
    public PopularAdapterNew.PopularViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.custom_popular_list, null);
        return new PopularAdapterNew.PopularViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final PopularAdapterNew.PopularViewHolder holder, int position) {


Log.e("","name= "+popularList.get(position).getSong_name());

       if(!TextUtils.isEmpty(popularList.get(position).getSong_name()))
        holder.groupename.setText(popularList.get(position).getSong_name());

        if(!TextUtils.isEmpty(popularList.get(position).getUser_name()))
        holder.tvUsrSubNm.setText(popularList.get(position).getUser_name());

        if(!TextUtils.isEmpty(popularList.get(position).getSong_like()))
        holder.tvLike.setText(popularList.get(position).getSong_like());


//        switch (position)
//        {
//            case 0:
////                holder.imageView.setImageResource(R.drawable.pop_img1);
////                holder.ivUsrPic.setImageResource(R.drawable.dumm_pop_img);
//                holder.groupename.setText("Yeah Boy");
//                holder.tvUsrSubNm.setText("Gaurav_Tripa");
//                break;
//            case 1:
////                holder.imageView.setImageResource(R.drawable.pop_img2);
////                holder.ivUsrPic.setImageResource(R.drawable.pop_image1);
//                holder.groupename.setText("Pyar Hame Kis..");
//                holder.tvUsrSubNm.setText("ndn.rinku");
//                break;
//            case 2:
////                holder.imageView.setImageResource(R.drawable.pop_img3);
////                holder.ivUsrPic.setImageResource(R.drawable.pop_image2);
//                holder.groupename.setText("Top of the World");
//                holder.tvUsrSubNm.setText("Blessy");
//                break;
//            case 3:
////                holder.imageView.setImageResource(R.drawable.pop_img4);
////                holder.ivUsrPic.setImageResource(R.drawable.dumm_pop_img);
//                holder.groupename.setText("Manasu Aagadu");
//                holder.tvUsrSubNm.setText("Ravinder");
//                break;
//            case 4:
//                holder.imageView.setImageResource(R.drawable.pop_img5);
////                holder.ivUsrPic.setImageResource(R.drawable.pop_image1);
//                holder.groupename.setText("Tum Se Milkar..");
//                holder.tvUsrSubNm.setText("sds.rinku");
//                break;
////            case 5:
////                holder.imageView.setImageResource(R.drawable.pop_img6);
////                holder.ivUsrPic.setImageResource(R.drawable.pop_image2);
////                holder.groupename.setText("Top of the World");
////                holder.tvUsrSubNm.setText("Blessy");
////                break;
//        }

        if(TextUtils.isEmpty(popularList.get(position).getUser_pic())||!popularList.get(position).getUser_pic().contains("."))
                {
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
                        default:
                            holder.ivUsrPic.setImageResource(R.drawable.pop_image1);
                            break;
                    }
                }
                else {
            Glide.with(mCtx).load(popularList.get(position).getUser_pic()).into(holder.ivUsrPic);
                }

        if(TextUtils.isEmpty(popularList.get(position).getSong_image())||!popularList.get(position).getSong_image().contains("."))
        {
            switch (position)
            {
//
                        case 0:
                        holder.imageView.setImageResource(R.drawable.pop_img1);

                            break;
                        case 1:
                holder.imageView.setImageResource(R.drawable.pop_img2);
                            break;
                        case 2:
                holder.imageView.setImageResource(R.drawable.pop_img3);
                            break;
                        case 3:
                holder.imageView.setImageResource(R.drawable.pop_img4);

                            break;
                        case 4:
                            holder.imageView.setImageResource(R.drawable.pop_img5);

                            break;
                        default:
                            holder.imageView.setImageResource(R.drawable.pop_img1);
                            break;
            }
        }
        else {
            Glide.with(mCtx).load(popularList.get(position).getSong_image()).centerCrop().into(holder.imageView);
        }




//        StorageReference avatarStoreRef = FirebaseStorage.getInstance().getReference("FWORLD_USER_AVATAR").child(popularList.get(position).getUser_id()+".jpg");
//        avatarStoreRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
//            @Override
//            public void onSuccess(Uri uri) {
//                String  avatar_url = uri.toString();
//                Log.e("","avatar_url= "+avatar_url);
//                if(TextUtils.isEmpty(avatar_url)||!avatar_url.contains("."))
//                {
//                    switch (position)
//                    {
//                        case 0:
//                            holder.ivUsrPic.setImageResource(R.drawable.dumm_pop_img);
//                            break;
//                        case 1:
//                            holder.ivUsrPic.setImageResource(R.drawable.pop_image1);
//                            break;
//                        case 2:
//                            holder.ivUsrPic.setImageResource(R.drawable.pop_image2);
//                            break;
//                        case 3:
//                            holder.ivUsrPic.setImageResource(R.drawable.pop_image3);
//                            break;
//                        case 4:
//                            holder.ivUsrPic.setImageResource(R.drawable.pop_image4);
//                            break;
//                        default:
//                            holder.ivUsrPic.setImageResource(R.drawable.pop_image1);
//                            break;
//                    }
//                }
//                else {
//                    Glide.with(mCtx).load(avatar_url).into(holder.ivUsrPic);
//                }
//            }
//        });
//        StorageReference popPic = FirebaseStorage.getInstance().getReference("FWORLD_USER_AVATAR").child("pop_img"+popularList.get(position).getPopId()+".jpg");
//        popPic.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
//            @Override
//            public void onSuccess(Uri uri) {
//                String  pop_url = uri.toString();
//                Log.e("","pop_url= "+pop_url);
//                if(TextUtils.isEmpty(pop_url)||!pop_url.contains("."))
//                {
//                    switch (position)
//                    {
//
//                        case 0:
//                        holder.imageView.setImageResource(R.drawable.pop_img1);
//
//                            break;
//                        case 1:
//                holder.imageView.setImageResource(R.drawable.pop_img2);
////                holder.ivUsrPic.setImageResource(R.drawable.pop_image1);
////                            holder.groupename.setText("Pyar Hame Kis..");
////                            holder.tvUsrSubNm.setText("ndn.rinku");
//                            break;
//                        case 2:
//                holder.imageView.setImageResource(R.drawable.pop_img3);
////                holder.ivUsrPic.setImageResource(R.drawable.pop_image2);
////                            holder.groupename.setText("Top of the World");
////                            holder.tvUsrSubNm.setText("Blessy");
//                            break;
//                        case 3:
//                holder.imageView.setImageResource(R.drawable.pop_img4);
////                holder.ivUsrPic.setImageResource(R.drawable.dumm_pop_img);
////                            holder.groupename.setText("Manasu Aagadu");
////                            holder.tvUsrSubNm.setText("Ravinder");
//                            break;
//                        case 4:
//                            holder.imageView.setImageResource(R.drawable.pop_img5);
////                holder.ivUsrPic.setImageResource(R.drawable.pop_image1);
////                            holder.groupename.setText("Tum Se Milkar..");
////                            holder.tvUsrSubNm.setText("sds.rinku");
//                            break;
//                        default:
//                            holder.ivUsrPic.setImageResource(R.drawable.pop_image1);
//                            break;
//                    }
//                }
//                else {
//                    Glide.with(mCtx).load(pop_url).into(holder.imageView);
//                }
//            }
//        });


        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                ActPopDetail
                mCtx.startActivity(new Intent(mCtx, ActPopDetail.class));
//               Category();
            }
        });

    }


    @Override
    public int getItemCount() {
//        return 5;
        return popularList.size();
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