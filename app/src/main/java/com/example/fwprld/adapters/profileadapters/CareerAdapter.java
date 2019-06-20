package com.example.fwprld.adapters.profileadapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.fwprld.R;
import com.example.fwprld.models.profilemodels.CareerData;

import java.util.List;

public class CareerAdapter extends RecyclerView.Adapter<CareerAdapter.EducationViewHolder> {

    List<CareerData> careerDatalist;
    Context context;

    public CareerAdapter(List<CareerData> careerDatalist, Context context) {
        this.careerDatalist = careerDatalist;
        this.context = context;
    }

    @NonNull
    @Override
    public CareerAdapter.EducationViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.education_list_item, viewGroup, false);
        return new EducationViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CareerAdapter.EducationViewHolder educationViewHolder, int i) {

        CareerData CareerData = careerDatalist.get(i);
        educationViewHolder.txtSchoolName.setText(CareerData.getCompany_name());
        educationViewHolder.txtStartEndDate.setText(String.format("%s -- %s", CareerData.getJoin_date(), CareerData.getLeave_date()));

    }

    @Override
    public int getItemCount() {
        return careerDatalist.size();
    }

    public class EducationViewHolder extends RecyclerView.ViewHolder {

        ConstraintLayout constrainEducation;
        TextView txtSchoolName, txtStartEndDate;

        public EducationViewHolder(@NonNull View itemView) {
            super(itemView);
            constrainEducation = itemView.findViewById(R.id.constrainEducation);
            txtSchoolName = itemView.findViewById(R.id.txtStartDate);
            txtStartEndDate = itemView.findViewById(R.id.txtStartEndDate);


        }
    }
}
