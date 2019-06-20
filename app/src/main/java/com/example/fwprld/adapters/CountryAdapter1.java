package com.example.fwprld.adapters;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.fwprld.R;

import java.util.ArrayList;

public class CountryAdapter1 extends RecyclerView.Adapter<CountryAdapter1.Holder>  implements View.OnClickListener {

    Context mContext;
    ArrayList<String> mArrayListString;

    public CountryAdapter1(Context mContext, ArrayList<String> mArrayListString) {
        this.mContext = mContext;
        this.mArrayListString = mArrayListString;
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.view_item, parent, false);
        view.setOnClickListener(this);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(Holder holder, int position) {
        holder.mTextView.setText(mArrayListString.get(position));
    }

    @Override
    public int getItemCount() {
        return mArrayListString.size();
    }

    @Override
    public void onClick(View v) {
//        clickListener.onClick(v, getPosition());
        int position  =   getAdapterPosition();


        switch (v.getId()){
            case R.id.tv_item:
                Log.w("", "Selected"+position);
                break;
        }
    }
int adapterPosition=0;
    public int getAdapterPosition() {
        return adapterPosition;
    }

    public void setAdapterPosition(int adapterPosition) {
        this.adapterPosition = adapterPosition;
    }

    public class Holder extends RecyclerView.ViewHolder {

        private final TextView mTextView;

        public Holder(View itemView) {
            super(itemView);
            mTextView = (TextView) itemView.findViewById(R.id.tv_item);

        }
    }
}

/*public class CountryAdapter {
}*/
