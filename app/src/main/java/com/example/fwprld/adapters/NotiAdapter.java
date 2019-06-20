package com.example.fwprld.adapters;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.fwprld.R;
import com.example.fwprld.models.NotificaitonBean;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class NotiAdapter extends RecyclerView.Adapter<NotiAdapter.PopularViewHolder> {

    private Context mCtx;
    private List<NotificaitonBean> notiList;

    public NotiAdapter(Context mCtx, List<NotificaitonBean> youmayLikeList_) {
        this.mCtx = mCtx;
        this.notiList = youmayLikeList_;
    }

    @Override
    public NotiAdapter.PopularViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.custom_noti_list, null);
        return new NotiAdapter.PopularViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final NotiAdapter.PopularViewHolder holder, int position) {
        if(!TextUtils.isEmpty(notiList.get(position).getTitle()))
        {
            holder.tvTitle.setText(notiList.get(position).getTitle());
        }
        if(!TextUtils.isEmpty(notiList.get(position).getMsg()))
        {
            holder.tvMsg.setText(notiList.get(position).getMsg());
        }

    }


    @Override
    public int getItemCount() {
        return notiList.size();
    }


    class PopularViewHolder extends RecyclerView.ViewHolder {
        TextView tvTitle, tvMsg;
//        CircleImageView ivUsrPic;
        ImageView ivPic;
//        CardView CvMain;
        public PopularViewHolder(View itemView) {
            super(itemView);
            ivPic = itemView.findViewById(R.id.iv_photo);
            tvTitle = itemView.findViewById(R.id.tv_title);
            tvMsg = itemView.findViewById(R.id.tv_msg);
//            CvMain = itemView.findViewById(R.id.cd_main);
        }
    }

}