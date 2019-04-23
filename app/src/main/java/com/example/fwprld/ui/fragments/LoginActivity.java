package com.example.fwprld.ui.fragments;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.fwprld.R;

public class LoginActivity extends Fragment {
Button btnnext;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.activity_login, container, false);
        btnnext = (Button) v.findViewById(R.id.btnnext);
        btnnext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              getChildFragmentManager()
                        .beginTransaction()
                        .replace(R.id.frame, new ProfileFragment())
                        .commit();
            }
        });
        return v;
    }
}
