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
import com.example.fwprld.models.profilemodels.EducationData;

import java.util.List;

public class EducationAdapter extends RecyclerView.Adapter<EducationAdapter.EducationViewHolder> {

    List<EducationData> educationDataList;
    Context context;

    public EducationAdapter(List<EducationData> educationDataList, Context context) {
        this.educationDataList = educationDataList;
        this.context = context;
    }

    @NonNull
    @Override
    public EducationAdapter.EducationViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.education_list_item, viewGroup, false);
        return new EducationViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EducationAdapter.EducationViewHolder educationViewHolder, int i) {

        EducationData educationData = educationDataList.get(i);
        educationViewHolder.txtSchoolName.setText(educationData.getSchool_name());
        educationViewHolder.txtStartEndDate.setText(String.format("%s -- %s", educationData.getStart_date(), educationData.getEnd_date()));

    }

    @Override
    public int getItemCount() {
        return educationDataList.size();
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
