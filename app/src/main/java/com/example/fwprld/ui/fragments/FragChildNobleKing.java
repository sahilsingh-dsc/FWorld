package com.example.fwprld.ui.fragments;



import android.app.Fragment;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.fwprld.R;


public class FragChildNobleKing extends Fragment implements View.OnClickListener {
    View view;
//    TextView txtuserid,txtbio,tvContribute,tvStatus,tvGallery,tvTopFun;
//    RelativeLayout rlTopFun,rlContribute;
//    String user_id;
    public FragChildNobleKing() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.frag_noble_king, container, false);

        InitCompo();
//        SetData();
        Listener();
        return view;
    }

    private void Listener() {

//        tvStatus.setOnClickListener(this);
//        tvGallery.setOnClickListener(this);
//        tvTopFun.setOnClickListener(this);
//        tvContribute.setOnClickListener(this);
//
//        rlTopFun.setOnClickListener(this);
//        rlContribute.setOnClickListener(this);
    }

//    private void SetData() {
//        FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
//        if (firebaseUser !=null){
//            user_id = firebaseUser.getUid();
//            txtuserid.setText(user_id);
//            DatabaseReference nameRef = FirebaseDatabase.getInstance().getReference("FWORLD_USER_DATA");
//            nameRef.child("USER_PROFILE").child("USER_BASIC_INFO").child(user_id).addValueEventListener(new ValueEventListener() {
//                @Override
//                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                    String bio =dataSnapshot.child("bio").getValue().toString();
//                    txtbio.setText(bio);
//                }
//
//                @Override
//                public void onCancelled(@NonNull DatabaseError databaseError) {
//
//                }
//            });
//        }
//    }

    private void InitCompo() {
//        txtuserid = view.findViewById(R.id.userid);
//        txtbio = view.findViewById(R.id.bio);//
//        tvStatus = view.findViewById(R.id.tv_status);
//        tvGallery = view.findViewById(R.id.tv_gallery);
//        tvTopFun = view.findViewById(R.id.tv_top_fun);
//        tvContribute = view.findViewById(R.id.tv_contribute);
//        rlTopFun = view.findViewById(R.id.rl_top_fun);
//        rlContribute = view.findViewById(R.id.rl_contribute);
    }


    @Override
    public void onClick(View v) {
          switch (v.getId())
          {
//              case R.id.tv_status:
//                  startActivity(new Intent(getActivity(), ActStatus.class));
//                  break;
//              case R.id.tv_gallery:
//                  startActivity(new Intent(getActivity(), ActGallery.class));
//                  break;
//              case R.id.rl_top_fun:
//              case R.id.tv_top_fun:
//                  startActivity(new Intent(getActivity(), ActTopfun.class));
//                  break;
//             case R.id.rl_contribute:
//             case R.id.tv_contribute:
//                  startActivity(new Intent(getActivity(), ActContribute.class));
//                  break;
          }
    }
}

