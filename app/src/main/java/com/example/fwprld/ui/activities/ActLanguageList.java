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

import com.example.fwprld.R;
import com.example.fwprld.common.RecyclerSectionItemDecoration;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ActLanguageList extends AppCompatActivity implements View.OnClickListener{
    TextView tvBack;
    private RecyclerView mRecyclerView;
    private DefaultLanguAdapter adapter;
    RelativeLayout rlNodata;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_language_list);
        initCompo();
        Listner();
        SetAdpter();
    }

    private void Listner() {
        tvBack.setOnClickListener(this);
    }

    private void initCompo() {

        rlNodata=findViewById(R.id.rl_no_data);
        tvBack=findViewById(R.id.tv_back);
        mRecyclerView=findViewById(R.id.rv_language);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
    public void SetAdpter()
    {
        adapter = new DefaultLanguAdapter(this,getData());
        mRecyclerView.setAdapter(adapter);

//        RecyclerSectionItemDecoration sectionItemDecoration =
//                new RecyclerSectionItemDecoration(getResources().getDimensionPixelSize(R.dimen.header),
//                        true,
//                        getSectionCallback(getData()));
//        mRecyclerView.addItemDecoration(sectionItemDecoration);
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
    String language="";
    public void SelectedData()
    {
        Intent returnIntent = new Intent();
        returnIntent.putExtra("result",language);
        setResult(Activity.RESULT_OK,returnIntent);
        finish();
    }
    public ArrayList<String> getData() {
        String[] strings = getResources().getStringArray(R.array.language);
        ArrayList<String> list = new ArrayList<String>(Arrays.asList(strings));
        return list;
    }




    public class DefaultLanguAdapter extends RecyclerView.Adapter<DefaultLanguAdapter.PopularViewHolder> {

        private Context mCtx;
        private List<String> notiList;

        public DefaultLanguAdapter(Context mCtx, List<String> youmayLikeList_) {
            this.mCtx = mCtx;
            this.notiList = youmayLikeList_;
        }

        @Override
        public DefaultLanguAdapter.PopularViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater inflater = LayoutInflater.from(mCtx);
            View view = inflater.inflate(R.layout.custom_language_list, null);
            return new DefaultLanguAdapter.PopularViewHolder(view);
        }

        @Override
        public void onBindViewHolder(final DefaultLanguAdapter.PopularViewHolder holder, int position) {

            holder.tvTitle.setText(notiList.get(position));
            holder.tvTitle.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
//                    Toast.makeText(mContext, ""+mArrayListString.get(position), Toast.LENGTH_SHORT).show();
                    language=notiList.get(position);
                    SelectedData();
                }
            });

        }


        @Override
        public int getItemCount() {
            return notiList.size();
        }


        class PopularViewHolder extends RecyclerView.ViewHolder {
            TextView tvTitle;
            public PopularViewHolder(View itemView) {
                super(itemView);
                tvTitle = itemView.findViewById(R.id.tv_langu);
            }
        }

    }
}
