package com.example.fwprld.ui.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterViewFlipper;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.fwprld.R;
import com.example.fwprld.adapters.FollowHomeAdapter;
import com.example.fwprld.adapters.MyCustomPager;
import com.example.fwprld.common.LoginSession;
import com.example.fwprld.models.SongBean;
import com.example.fwprld.models.UserInfo;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class FollowingFragmentNew extends Fragment {

    private AdapterViewFlipper adapterViewFlipper;
    private static final int[] IMAGES={R.drawable.following_slider1,R.drawable.following_slider2,R.drawable.following_slider4,R.drawable.following_slider5};
    private MyCustomPager adapter;
    RecyclerView rvFollowUsers;
    RecyclerView rvAllUsers;
    List<SongBean> youmayLikeList;
    List<UserInfo> usrInfo,followingUser;
    private LinearLayout pager_indicator;
    private int dotsCount;
    private ImageView[] dots;
//    SearchView searchView;
    String ftal_user, song_id, song_image, song_name, song_plays, song_singer, song_playtime, songby_name, songby_image;

    FirebaseUser firebaseUser=null;
    String currentUsrId="";
//    FirebaseUser firebaseUser;
    public FollowingFragmentNew() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_following_new, container, false);
//        searchView= (SearchView) v.findViewById(R.id.search);

//        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        if(new LoginSession(getContext()).IsLogin(getContext()))
//        if(firebaseUser!=null)
        {
            firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
         currentUsrId = firebaseUser.getUid();
        }

        pager_indicator = (LinearLayout)v.findViewById(R.id.viewPagerCountDots);
        rvAllUsers=(RecyclerView)v.findViewById(R.id.rv_users);
        rvFollowUsers=(RecyclerView)v.findViewById(R.id.rv_following);
        rvFollowUsers.setHasFixedSize(true);
        rvFollowUsers.setLayoutManager(new LinearLayoutManager(getContext()));
        youmayLikeList = new ArrayList<>();
        usrInfo = new ArrayList<>();
        followingUser = new ArrayList<>();

        adapterViewFlipper=(AdapterViewFlipper)v.findViewById(R.id.viewpager);
        adapter= new MyCustomPager(getContext(),IMAGES);
        adapterViewFlipper.setAdapter(adapter);
        adapterViewFlipper.setAutoStart(true);

//        setUiPageViewController();
        rvAllUsers.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, true));


//        DatabaseReference ftalentRef = FirebaseDatabase.getInstance().getReference("FTALENT_DATA");
//        ftalentRef.child("FTALENT_SONGS_DATA").addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                ftalentList.clear();
//                for (DataSnapshot ftalSnap : dataSnapshot.getChildren()){
//                    ftal_user = (String) ftalSnap.child("user_id").getValue();
//                    song_id = (String) ftalSnap.child("SONG_DETAILS").child("song_id").getValue();
//                    song_image = (String) ftalSnap.child("SONG_DETAILS").child("song_image").getValue();
//                    song_name = (String) ftalSnap.child("SONG_DETAILS").child("song_name").getValue();
//                    song_plays = (String) ftalSnap.child("SONG_DETAILS").child("song_plays").getValue();
//                    song_singer = (String) ftalSnap.child("SONG_DETAILS").child("song_singer").getValue();
//                    song_playtime = (String) ftalSnap.child("SONG_DETAILS").child("song_playtime").getValue();
//                    songby_name = (String) ftalSnap.child("SONG_DETAILS").child("songby_name").getValue();
//                    songby_image = (String) ftalSnap.child("SONG_DETAILS").child("songby_image").getValue();
//
//                    FTalent fTalent = new FTalent(ftal_user, song_id ,song_image ,song_name ,song_plays ,song_singer, song_playtime, songby_name ,songby_image);
//                    ftalentList.add(fTalent);
//                }
//
//                FollowHomeAdapter adapter = new FollowHomeAdapter(getContext(),  ftalentList);
//                rvFollowUsers.setAdapter(adapter);
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//
//            }
//        });

//        DatabaseReference ftalentRef = FirebaseDatabase.getInstance().getReference("YOU_MAY_LIKE");
//        ftalentRef.child("RECENT_LIKE").addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                youmayLikeList.clear();
//                for (DataSnapshot ftalSnap : dataSnapshot.getChildren()){
//
//                    String user_name = (String) ftalSnap.child("user_name").getValue();
//                    String  user_fid = (String) ftalSnap.child("user_id").getValue();
//                    String  user_pic = (String) ftalSnap.child("user_pic").getValue();
//
//                    String song_id = (String) ftalSnap.child("song_id").getValue();
//                    String song_image = (String) ftalSnap.child("song_image").getValue();
//                    String song_like = (String) ftalSnap.child("song_like").getValue();
//
//                    String song_plays = (String) ftalSnap.child("song_plays").getValue();
//                    String song_playtime = (String) ftalSnap.child("song_playtime").getValue();
//                    String song_singer = (String) ftalSnap.child("song_singer").getValue();
//                    String songby_image = (String) ftalSnap.child("songby_image").getValue();
//                    String songby_name = (String) ftalSnap.child("songby_name").getValue();
//                    String  song_name = (String) ftalSnap.child("song_name").getValue();
//
//                    SongBean bean =  new SongBean(song_id,song_image,
//                            song_name,song_like,song_plays,song_playtime,song_singer,songby_image
//                            ,songby_name,user_fid,user_name,user_pic);
//                    youmayLikeList.add(bean);
//                }
//
//                FollowHomeAdapter adapter = new FollowHomeAdapter(getContext(),  youmayLikeList);
//                rvFollowUsers.setAdapter(adapter);
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//
//            }
//        });


        DatabaseReference userList = FirebaseDatabase.getInstance().getReference("FWORLD_USER_DATA").child("USER_PROFILE");
        userList.child("USER_BASIC_INFO").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                usrInfo.clear();
                followingUser.clear();
                try {
                    Log.e("","dataSnapshot= "+dataSnapshot.toString());
                    for (DataSnapshot ftalSnap : dataSnapshot.getChildren()){
                        String  user_name = (String) ftalSnap.child("user_name").getValue();
                        String  fidlocal = (String) ftalSnap.child("user_fid").getValue();
                        String  avatar_url ="";
                        UserInfo usrinfo = new UserInfo(user_name, fidlocal ,avatar_url);
                        usrInfo.add(usrinfo);

                        if(TextUtils.isEmpty(currentUsrId))
                        {
                            followingUser.add(usrinfo);
                        }
                        else {
                            if (!currentUsrId.equals(fidlocal)) {
                                followingUser.add(usrinfo);
                            }
                        }


                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    Log.e("",""+e);
                }

                UsersAdapter adapter1 = new UsersAdapter(getContext(),  usrInfo);
                rvAllUsers.setAdapter(adapter1);

                FollowHomeAdapter adapter = new FollowHomeAdapter(getContext(),  followingUser);
                rvFollowUsers.setAdapter(adapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        return v;
    }

    private void getUserInfo(String fid ){

        DatabaseReference profileRef = FirebaseDatabase.getInstance().getReference("FWORLD_USER_DATA").child("USER_PROFILE");

        profileRef.child("USER_BASIC_INFO").child(fid).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                String user_name, user_fid, user_gender, user_birthday, user_relationship_status, user_about, user_status;

                user_name = (String) dataSnapshot.child("user_name").getValue();
                user_fid = (String) dataSnapshot.child("user_fid").getValue();
                user_gender = (String) dataSnapshot.child("user_gender").getValue();
                user_birthday = (String) dataSnapshot.child("user_birthday").getValue();
                user_relationship_status = (String) dataSnapshot.child("user_relationship_status").getValue();
                user_about = (String) dataSnapshot.child("user_about").getValue();
                user_status = (String) dataSnapshot.child("user_status").getValue();



            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }


    private void setUiPageViewController() {

        dotsCount = adapter.getCount();
        dots = new ImageView[dotsCount];

        for (int i = 0; i < dotsCount; i++) {
            dots[i] = new ImageView(getContext());
            dots[i].setImageDrawable(getResources().getDrawable(R.drawable.nonselecteditem_dot));

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
            );

            params.setMargins(4, 0, 4, 0);

            pager_indicator.addView(dots[i], params);
        }

        dots[0].setImageDrawable(getResources().getDrawable(R.drawable.selecteditem_dot));
    }

     class UsersAdapter extends RecyclerView.Adapter<UsersAdapter.PopularViewHolder> {

        private Context mCtx;
        private List<UserInfo> fTalentList;

        public UsersAdapter(Context mCtx, List<UserInfo> fTalentList) {
            this.mCtx = mCtx;
            this.fTalentList = fTalentList;
        }

        @Override
        public PopularViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater inflater = LayoutInflater.from(mCtx);
            View view = inflater.inflate(R.layout.custom_user_list, null);
            return new PopularViewHolder(view);
        }

        @Override
        public void onBindViewHolder(final PopularViewHolder holder, int position) {

            StorageReference avatarStoreRef = FirebaseStorage.getInstance().getReference("FWORLD_USER_AVATAR").child(fTalentList.get(position).getUserId()+".jpg");
            avatarStoreRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                @Override
                public void onSuccess(Uri uri) {
                    String  avatar_url = uri.toString();
                    Log.e("","avatar_url= "+avatar_url);
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
                }
            });
            holder.ivUsrPic.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(TextUtils.isEmpty(currentUsrId))
                    {
//                        startActivity(new Intent(mCtx,MainActivity.class));
                        Toast.makeText(mCtx, "Login", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        Toast.makeText(mCtx, "coming soon", Toast.LENGTH_SHORT).show();
                    }
                }
            });

        }


        @Override
        public int getItemCount() {
//        return 5;
            return fTalentList.size();
        }


        class PopularViewHolder extends RecyclerView.ViewHolder {
            CircleImageView ivUsrPic;
            RelativeLayout rlUser;
            public PopularViewHolder(View itemView) {
                super(itemView);

                ivUsrPic = itemView.findViewById(R.id.iv_user_pic);
//            tvUsrSubNm = itemView.findViewById(R.id.tv_usr_nm);
//            tvLike = itemView.findViewById(R.id.tv_comment);
                rlUser = itemView.findViewById(R.id.rl_usr_pic);

            }
        }

    }

    @Override
    public void onResume() {
        super.onResume();
//        Toast.makeText(getContext(), "Resume", Toast.LENGTH_SHORT).show();
    }

//    private void Show() {
//        //Creating a LayoutInflater object for the dialog box
//        final LayoutInflater li = LayoutInflater.from(getContext());
//        //Creating a view to get the dialog box
//        View confirmDialog = li.inflate(R.layout.dialogue_layout, null);
//
//        //Initizliaing confirm button fo dialog box and edittext of dialog box
//        final Button btnsub = (Button) confirmDialog.findViewById(R.id.btnsub);
//        final ImageView ivFb = (ImageView) confirmDialog.findViewById(R.id.iv_fab);
//        final ImageView ivGPlus = (ImageView) confirmDialog.findViewById(R.id.iv_gplus);
//
//        ivFb.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                LoginManager.getInstance().logInWithReadPermissions(MainActivity.this, FACEBOOK_PERMISSIONS);
//            }
//        });
//        ivGPlus.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                signIn();
//            }
//        });
//
//
//        btnsub.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                alertDialog.dismiss();
//                getSupportFragmentManager()
//                        .beginTransaction()
//                        .replace(R.id.frame, new LoginActivity())
//                        .addToBackStack(null)
//                        .commit();
//            }
//        });
//        final TextView close=(TextView)confirmDialog.findViewById(R.id.close);
//        close.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                alertDialog.dismiss();
//
//            }
//        });
//        final TextView moreways=(TextView)confirmDialog.findViewById(R.id.more);
//        final LinearLayout linearLayout=(LinearLayout)confirmDialog.findViewById(R.id.loginly);
//        moreways.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                if (count==0){
//                    linearLayout.setVisibility(View.VISIBLE);
//                    count=1;
//                }else{
//                    linearLayout.setVisibility(View.GONE);
//                    count=0;
//                }
//
//            }
//        });
//
//        AlertDialog.Builder alert = new AlertDialog.Builder(MainActivity.this);
//        alert.setView(confirmDialog);
//        // alert.setTitle("Select Donor");
//
//        //Creating an alert dialog
//        alertDialog = alert.create();
//
//        //Displaying the alert dialog
//
//        WindowManager.LayoutParams wmlp =  alertDialog.getWindow().getAttributes();
//
//        wmlp.gravity = Gravity.BOTTOM ;
//        wmlp.width = WindowManager.LayoutParams.MATCH_PARENT;
//        wmlp.height = WindowManager.LayoutParams.WRAP_CONTENT;
//        // wmlp.x = 100;   //x position
//        // wmlp.y = 0;   //y position
//        alertDialog.getWindow().setAttributes(wmlp);
//
//        alertDialog.show();
//
//    }
}
