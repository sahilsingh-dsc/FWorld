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
import com.example.fwprld.common.LoginSession;
import com.example.fwprld.models.UserInfo;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class FollowHomeAdapter extends RecyclerView.Adapter<FollowHomeAdapter.PopularViewHolder> {

    private Context mCtx;
    private List<UserInfo> otherfollowing;
    LoginSession sessionParam;

    public FollowHomeAdapter(Context mCtx, List<UserInfo> youmayLikeList_) {
        this.mCtx = mCtx;
        this.otherfollowing = youmayLikeList_;
        sessionParam = new LoginSession(mCtx);
    }

    @Override
    public FollowHomeAdapter.PopularViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.custom_home_following_list, null);
        return new FollowHomeAdapter.PopularViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final FollowHomeAdapter.PopularViewHolder holder, int position) {


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

        holder.lyFollow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UserInfo uerid=otherfollowing.get(position);
//                SetFollowers(uerid);
            }
        });


    }


    @Override
    public int getItemCount() {
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

    public void SetFollowers(UserInfo followerUserId)
    {
        DatabaseReference followers = FirebaseDatabase.getInstance().getReference("FOLLOWERS");
        followers.child(sessionParam.usr_id).push().setValue(followerUserId);
    }

}