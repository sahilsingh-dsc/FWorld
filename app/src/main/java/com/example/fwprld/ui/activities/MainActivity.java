package com.example.fwprld.ui.activities;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterViewFlipper;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SearchView;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.fwprld.R;
import com.example.fwprld.adapters.MyCustomPager;
import com.example.fwprld.adapters.RecommendAdapter;
import com.example.fwprld.models.Recommend;
import com.example.fwprld.ui.activities.LoginActivity;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private AdapterViewFlipper adapterViewFlipper;
    private static final int[] IMAGES={R.drawable.slideimage,R.drawable.slideimage1,R.drawable.slideimage2,R.drawable.slideimage3};
    LinearLayout profile;
    RecyclerView recyclerView;
    List<Recommend> recommendList;
    private MyCustomPager adapter;
    int count=0;
    AlertDialog alertDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SearchView searchView=(SearchView)findViewById(R.id.search);
        profile=(LinearLayout)findViewById(R.id.profile);
        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Show();

            }
        });
        adapterViewFlipper=(AdapterViewFlipper) findViewById(R.id.viewpager);
        adapter= new MyCustomPager(getApplicationContext(),IMAGES);
        adapterViewFlipper.setAdapter(adapter);
        adapterViewFlipper.setAutoStart(true);
        recyclerView=(RecyclerView)findViewById(R.id.recycleView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        recommendList = new ArrayList<>();
        recommendList.add(
                new Recommend(
                        1,
                        "Coca Cola Tu",
                        "Tony Kakkar",
                        R.drawable.homepage1));

        recommendList.add(
                new Recommend(
                        1,
                        "Sab Tera",
                        "Tony Kakkar",
                        R.drawable.homepage2));
        recommendList.add(
                new Recommend(
                        1,
                        "Agar Tu Hota",
                        "Ankit Tiwari",
                        R.drawable.homepage3));

        //creating recyclerview adapter
        RecommendAdapter adapter1 = new RecommendAdapter(this, recommendList);

        //setting adapter to recyclerview
        recyclerView.setAdapter(adapter1);
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
                startActivity(new Intent(getApplicationContext(), LoginActivity.class));
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

