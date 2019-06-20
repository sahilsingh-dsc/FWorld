package com.example.fwprld.adapters;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.fwprld.R;
import com.example.fwprld.models.UserInfo;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class FollowingsAdapter extends RecyclerView.Adapter<FollowingsAdapter.PopularViewHolder> {

    private Context mCtx;
//    private List<UserInfo> youmayLikeList;
    private List<UserInfo> otherfollowing;

    public FollowingsAdapter(Context mCtx, List<UserInfo> youmayLikeList_) {
        this.mCtx = mCtx;
        this.otherfollowing = youmayLikeList_;
    }

    @Override
    public FollowingsAdapter.PopularViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.custom_followings_list, null);
        return new FollowingsAdapter.PopularViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final FollowingsAdapter.PopularViewHolder holder, int position) {


        if(!TextUtils.isEmpty(otherfollowing.get(position).getUserNm()))
        {
            holder.tvUsrSubNm.setText(otherfollowing.get(position).getUserNm());
        }

        StorageReference avatarStoreRef = FirebaseStorage.getInstance().getReference("FWORLD_USER_AVATAR").child(otherfollowing.get(position).getUserId()+".jpg");
        avatarStoreRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                String  avatar_url = uri.toString();
                Log.e("","avatar_url= "+avatar_url);
                try {
                    if(TextUtils.isEmpty(avatar_url)||!avatar_url.contains("."))
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
                        Glide.with(mCtx).load(avatar_url).into(holder.ivUsrPic);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

//        switch (position)
//        {
//            case 0:
//                holder.ivUsrPic.setImageResource(R.drawable.dumm_pop_img);
//                break;
//            case 1:
//                holder.ivUsrPic.setImageResource(R.drawable.pop_image1);
//                break;
//            case 2:
//                holder.ivUsrPic.setImageResource(R.drawable.pop_image2);
//                break;
//            case 3:
//                holder.ivUsrPic.setImageResource(R.drawable.pop_image3);
//                break;
//            case 4:
//                holder.ivUsrPic.setImageResource(R.drawable.pop_image4);
//                break;
//            default:
//                holder.ivUsrPic.setImageResource(R.drawable.pop_image1);
//                break;
//        }


    }


    @Override
    public int getItemCount() {
//        return 5;
        return otherfollowing.size();
    }

    class PopularViewHolder extends RecyclerView.ViewHolder {
        TextView tvUsrSubNm, tvLike;
        CircleImageView ivUsrPic;
        LinearLayout lyFollow;
        public PopularViewHolder(View itemView) {
            super(itemView);
            ivUsrPic = itemView.findViewById(R.id.iv_user_pic);
            tvUsrSubNm = itemView.findViewById(R.id.tv_usr_nm);
            tvLike = itemView.findViewById(R.id.tv_comment);
            lyFollow = itemView.findViewById(R.id.ly_following);

        }
    }

}