package com.example.fwprld.ui.activities;

import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.fwprld.R;
import com.example.fwprld.ui.fragments.HomeFragment;
import com.example.fwprld.ui.fragments.ProfileFragment;

public class MainActivity extends AppCompatActivity {
    LinearLayout profile;
    int count=0;
    AlertDialog alertDialog;
    FrameLayout frameLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        profile=(LinearLayout)findViewById(R.id.profile);
        frameLayout=(FrameLayout)findViewById(R.id.frame);
        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Show();

            }
        });

                getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.frame, new HomeFragment())
                .commit();

    }
    private void Show() {
        //Creating a LayoutInflater object for the dialog box
        final LayoutInflater li = LayoutInflater.from(MainActivity.this);
        //Creating a view to get the dialog box
        View confirmDialog = li.inflate(R.layout.dialogue_layout, null);


        //Initizliaing confirm button fo dialog box and edittext of dialog box

        final Button btnsub = (Button) confirmDialog.findViewById(R.id.btnsub);
        btnsub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.frame, new ProfileFragment())
                        .commit();
            }
        });
        final TextView close=(TextView)confirmDialog.findViewById(R.id.close);
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();

            }
        });
        final TextView moreways=(TextView)confirmDialog.findViewById(R.id.more);
        final LinearLayout linearLayout=(LinearLayout)confirmDialog.findViewById(R.id.loginly);
        moreways.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (count==0){
                    linearLayout.setVisibility(View.VISIBLE);
                    count=1;
                }else{
                    linearLayout.setVisibility(View.GONE);
                    count=0;
                }

            }
        });

        AlertDialog.Builder alert = new AlertDialog.Builder(MainActivity.this);
        alert.setView(confirmDialog);
        // alert.setTitle("Select Donor");

        //Creating an alert dialog
       alertDialog = alert.create();

        //Displaying the alert dialog

        WindowManager.LayoutParams wmlp =  alertDialog.getWindow().getAttributes();

        wmlp.gravity = Gravity.BOTTOM ;
        wmlp.width = WindowManager.LayoutParams.MATCH_PARENT;
        wmlp.height = WindowManager.LayoutParams.WRAP_CONTENT;
       // wmlp.x = 100;   //x position
       // wmlp.y = 0;   //y position
        alertDialog.getWindow().setAttributes(wmlp);

        alertDialog.show();

    }

}
