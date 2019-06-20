package com.example.fwprld.ui.fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.fwprld.R;
import com.example.fwprld.ui.activities.ActDuetProfile;
import com.example.fwprld.ui.activities.ActPostedFreeStyle;
import com.example.fwprld.ui.activities.ActPostedVideos;

/**
 * A simple {@link Fragment} subclass.
 */
public class DuetFragment extends Fragment implements View.OnClickListener {
    View view;

    TextView tvPostedDuet;
    
    public DuetFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {//ActDuetProfile
        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_duet, container, false);

        view = inflater.inflate(R.layout.fragment_duet, container, false);
        InitCompo();
        Listener();
        return view;
    }

    private void Listener() {

        tvPostedDuet.setOnClickListener(this);
//        tvPostedVideos.setOnClickListener(this);

    }

    private void InitCompo() {
        tvPostedDuet = view.findViewById(R.id.tv_duet);
//        tvPostedVideos = view.findViewById(R.id.tv_posted_videos);

    }


    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.tv_duet:
                startActivity(new Intent(getActivity(), ActDuetProfile.class));
                break;
        }
    }

}
