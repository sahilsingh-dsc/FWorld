package com.example.fwprld.ui.activities;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fwprld.R;
import com.example.fwprld.common.RecyclerSectionItemDecoration;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ActCountryList extends AppCompatActivity implements View.OnClickListener{
    TextView tvBack;
    private RecyclerView mRecyclerView;
    private CountryAdapter adapter;
    RelativeLayout rlNodata;
    TextView tvCountry;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_country_list);
        initCompo();
        Listner();
        SetAdpter();
        SetPreviousData();

    }

    private void SetPreviousData() {
       String countrynm= getIntent().getStringExtra("previous_country");
        tvCountry.setText(countrynm);
    }

    private void Listner() {
        tvBack.setOnClickListener(this);

//        adapter.setOnItemClickListener(new CountryAdapter.ClickListener() {
//            @Override
//            public void onItemClick(int position, View v) {
//                Log.d(TAG, "onItemClick position: " + position);
//            }
//
//            @Override
//            public void onItemLongClick(int position, View v) {
//                Log.d(TAG, "onItemLongClick pos = " + position);
//            }
//        });
//        mRecyclerView.addOnItemTouchListener(new RecyclerTouchListener(this,
//                recyclerView, new ClickListener() {
//            @Override
//            public void onClick(View view, final int position) {
//                //Values are passing to activity & to fragment as well
//                Toast.makeText(MainActivity.this, "Single Click on position :"+position,
//                        Toast.LENGTH_SHORT).show();
////                ImageView picture=(ImageView)view.findViewById(R.id.picture);
////                picture.setOnClickListener(new View.OnClickListener() {
////                    @Override
////                    public void onClick(View v) {
////                        Toast.makeText(MainActivity.this, "Single Click on Image :"+position,
////                                Toast.LENGTH_SHORT).show();
////                    }
////                });
//            }
//
//            @Override
//            public void onLongClick(View view, int position) {
//                Toast.makeText(MainActivity.this, "Long press on position :"+position,
//                        Toast.LENGTH_LONG).show();
//            }
//        }));
    }

    private void initCompo() {

        rlNodata=findViewById(R.id.rl_no_data);
        tvBack=findViewById(R.id.tv_back);
        tvCountry=findViewById(R.id.tv_country);
        mRecyclerView=findViewById(R.id.rv_country);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
    public void SetAdpter()
    {


        adapter = new CountryAdapter(this,getData());
        mRecyclerView.setAdapter(adapter);

        RecyclerSectionItemDecoration sectionItemDecoration =
                new RecyclerSectionItemDecoration(getResources().getDimensionPixelSize(R.dimen.header),
                        true,
                        getSectionCallback(getData()));
        mRecyclerView.addItemDecoration(sectionItemDecoration);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        NoSelection();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.tv_back:
                NoSelection();
                break;
        }
    }
    public void NoSelection()
    {
        Intent returnIntent = new Intent();
        setResult(Activity.RESULT_CANCELED, returnIntent);
        finish();
    }
    String countryname="";//countryname
    public void SelectedData()
    {
        Intent returnIntent = new Intent();
        returnIntent.putExtra("result",countryname);
        setResult(Activity.RESULT_OK,returnIntent);
        finish();
    }
    public ArrayList<String> getData() {
        String[] strings = getResources().getStringArray(R.array.country);
        ArrayList<String> list = new ArrayList<String>(Arrays.asList(strings));
        return list;
    }

    public RecyclerSectionItemDecoration.SectionCallback getSectionCallback(final List<String> people) {
        return new RecyclerSectionItemDecoration.SectionCallback() {
            @Override
            public boolean isSection(int position) {
                return position == 0
                        || people.get(position)
                        .charAt(0) != people.get(position - 1)
                        .charAt(0);
            }

            @Override
            public CharSequence getSectionHeader(int position) {
                return people.get(position)
                        .subSequence(0,
                                1);
            }
        };
    }



    public class CountryAdapter extends RecyclerView.Adapter<CountryAdapter.Holder>   {

        Context mContext;
        ArrayList<String> mArrayListString;

        public CountryAdapter(Context mContext, ArrayList<String> mArrayListString) {
            this.mContext = mContext;
            this.mArrayListString = mArrayListString;
        }

        @Override
        public CountryAdapter.Holder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(mContext).inflate(R.layout.view_item, parent, false);

            return new CountryAdapter.Holder(view);
        }

        @Override
        public void onBindViewHolder(CountryAdapter.Holder holder, int position) {
            holder.mTextView.setText(mArrayListString.get(position));

            holder.mTextView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
//                    Toast.makeText(mContext, ""+mArrayListString.get(position), Toast.LENGTH_SHORT).show();
                    countryname=mArrayListString.get(position);
                    SelectedData();
                }
            });
        }

        @Override
        public int getItemCount() {
            return mArrayListString.size();
        }


        public class Holder extends RecyclerView.ViewHolder {

            private final TextView mTextView;

            public Holder(View itemView) {
                super(itemView);
                mTextView = (TextView) itemView.findViewById(R.id.tv_item);

            }
        }
    }
}
