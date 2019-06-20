package com.example.fwprld.ui.fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.fwprld.R;
import com.example.fwprld.ui.activities.ActPhotoVideo;
import com.example.fwprld.ui.activities.ActPostedFreeStyle;
import com.example.fwprld.ui.activities.ActPostedVideos;
import com.example.fwprld.ui.activities.ActTextStory;

/**
 * A simple {@link Fragment} subclass.
 */
public class F_ArtistFragment extends Fragment implements View.OnClickListener {
    View view;

    TextView tvPostedFreeStyle,tvPostedVideos;


    public F_ArtistFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_f__artist, container, false);
        InitCompo();
        Listener();
        return view;
    }

    private void Listener() {

        tvPostedFreeStyle.setOnClickListener(this);
        tvPostedVideos.setOnClickListener(this);

    }

    private void InitCompo() {
        tvPostedFreeStyle = view.findViewById(R.id.tv_posted_free);
        tvPostedVideos = view.findViewById(R.id.tv_posted_videos);

    }


    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.tv_posted_free:
                startActivity(new Intent(getActivity(), ActPostedFreeStyle.class));
                break;
            case R.id.tv_posted_videos:
                startActivity(new Intent(getActivity(), ActPostedVideos.class));
                break;
//            case R.id.rl_top_fun:
//            case R.id.tv_top_fun:
//                startActivity(new Intent(getActivity(), ActTopfun.class));
//                break;
//            case R.id.rl_contribute:
//            case R.id.tv_contribute:
//                startActivity(new Intent(getActivity(), ActContribute.class));
//                break;
        }
    }
}
