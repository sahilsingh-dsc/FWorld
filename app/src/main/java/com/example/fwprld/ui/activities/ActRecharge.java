package com.example.fwprld.ui.activities;

import android.app.AlertDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fwprld.R;
import com.example.fwprld.models.profilemodels.EducationData;
import com.google.firebase.database.FirebaseDatabase;

public class ActRecharge extends AppCompatActivity implements View.OnClickListener{
    TextView tvBack,tvContinue,tvCoin,tvAmountBottom;
    RelativeLayout rlFirstRecharge,rlSecondRecharge,rlBottomMain,rlBottomTop;

    RelativeLayout rlThirdRecharge,rlFourthRecharge,rlFivthRecharge,rlSixRecharge;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_recharge);
        initCompo();
        Listner();
    }

    private void Listner() {
        tvBack.setOnClickListener(this);
        rlBottomTop.setOnClickListener(this);
        rlFirstRecharge.setOnClickListener(this);
        rlSecondRecharge.setOnClickListener(this);

        rlThirdRecharge.setOnClickListener(this);
        rlFourthRecharge.setOnClickListener(this);
        rlFivthRecharge.setOnClickListener(this);
        rlSixRecharge.setOnClickListener(this);

    }

    private void initCompo() {
        tvBack=findViewById(R.id.tv_back);
        rlFirstRecharge=findViewById(R.id.rl_amount);
        rlSecondRecharge=findViewById(R.id.rl_amount1);

        rlThirdRecharge=findViewById(R.id.rl_amount2);
        rlFourthRecharge=findViewById(R.id.rl_amount3);
        rlFivthRecharge=findViewById(R.id.rl_amount4);
        rlSixRecharge=findViewById(R.id.rl_amount5);

        rlBottomMain=findViewById(R.id.rl_bottom_main);
        rlBottomTop=findViewById(R.id.rl_bottom_top);

        tvContinue=findViewById(R.id.tv_continue);
        tvCoin=findViewById(R.id.tv_coint_count);
        tvAmountBottom=findViewById(R.id.tv_amount_bottom);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.tv_continue:
                Toast.makeText(this, "continue comming soon", Toast.LENGTH_SHORT).show();
                break;
           case R.id.tv_back:
                finish();
                break;
            case R.id.rl_amount:
//                ConfirmRecharge();
//                showCustomDialog("","");
                rlBottomMain.setVisibility(View.VISIBLE);
                SetData(1);
                break;
           case R.id.rl_amount1:
                rlBottomMain.setVisibility(View.VISIBLE);
                SetData(2);
                break;
           case R.id.rl_amount2:
                rlBottomMain.setVisibility(View.VISIBLE);
                SetData(3);
                break;
           case R.id.rl_amount3:
                rlBottomMain.setVisibility(View.VISIBLE);
                SetData(4);
                break;
          case R.id.rl_amount4:
                rlBottomMain.setVisibility(View.VISIBLE);
                SetData(5);
                break;
         case R.id.rl_amount5:
                rlBottomMain.setVisibility(View.VISIBLE);
                SetData(6);
                break;
            case R.id.rl_bottom_top:
//                ConfirmRecharge();
//                showCustomDialog("","");
                rlBottomMain.setVisibility(View.GONE);

                break;
        }
    }
    public void SetData(int pos)
    {
        switch (pos)
        {
            case 1:
                tvCoin.setText(getString(R.string.recharge_first)+" Coins");
                tvAmountBottom.setText(getString(R.string.recharge_amount));
                break;
            case 2:
                tvCoin.setText(getString(R.string.recharge_first1)+" Coins");
                tvAmountBottom.setText(getString(R.string.recharge_amount1));
                break;
            case 3:
                tvCoin.setText(getString(R.string.recharge_first2)+" Coins");
                tvAmountBottom.setText(getString(R.string.recharge_amount2));
                break;
            case 4:
                tvCoin.setText(getString(R.string.recharge_first3)+" Coins");
                tvAmountBottom.setText(getString(R.string.recharge_amount3));
                break;
            case 5:
                tvCoin.setText(getString(R.string.recharge_first4)+" Coins");
                tvAmountBottom.setText(getString(R.string.recharge_amount4));
                break;
        }
    }

//    private void ConfirmRecharge(){
//
//        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(ActRecharge.this);
//        LayoutInflater inflater = ActRecharge.this.getLayoutInflater();
//        View dialogView = inflater.inflate(R.layout.recharge_confirm_alert, null);
//        dialogBuilder.setView(dialogView);
//        final AlertDialog eduDialog = dialogBuilder.create();
//
////        final EditText etxtSchoolName = dialogView.findViewById(R.id.etxtSchoolName);
////
////        txtStartDate = dialogView.findViewById(R.id.txtStartDate);
////
////        txtStartDate.setOnClickListener(new View.OnClickListener() {
////            @Override
////            public void onClick(View v) {
////                datetag = "EDUSTART";
////                getDatePicker();
////
////            }
////        });
////        txtEndDate = dialogView.findViewById(R.id.txtEndDate);
////        txtEndDate.setOnClickListener(new View.OnClickListener() {
////            @Override
////            public void onClick(View v) {
////                datetag = "EDUEND";
////                getDatePicker();
////            }
////        });
//
//
////
////        TextView txtSaveEdu = dialogView.findViewById(R.id.txtSaveEdu);
////        txtSaveEdu.setOnClickListener(new View.OnClickListener() {
////            @Override
////            public void onClick(View v) {
////
////                school_name = etxtSchoolName.getText().toString();
////
////                if (TextUtils.isEmpty(school_name)){
////                    Toast.makeText(EditProfileActivity.this, "Please enter school name", Toast.LENGTH_SHORT).show();
////                    return;
////                }
////
////                start_date = txtStartDate.getText().toString();
////
////                if (TextUtils.isEmpty(start_date)){
////                    Toast.makeText(EditProfileActivity.this, "Please tell when you started here", Toast.LENGTH_SHORT).show();
////                    return;
////                }
////
////                end_date = txtEndDate.getText().toString();
////
////                if (TextUtils.isEmpty(end_date)){
////                    end_date = "Present";
////                }
////
////                EducationData educationData = new EducationData(school_name, start_date ,end_date);
////
////                profileRef = FirebaseDatabase.getInstance().getReference("FWORLD_USER_DATA").child("USER_PROFILE");
////                profileRef.child("USER_EDUCATION").child(fid).push().setValue(educationData);
////
////
////
////                eduDialog.dismiss();
////                Toast.makeText(EditProfileActivity.this, "Saved", Toast.LENGTH_SHORT).show();
////
////            }
////        });
//
//        eduDialog.show();
//    }
//
//
//    private void showCustomDialog(String coin,String amount) {
//        //before inflating the custom alert dialog layout, we will get the current activity viewgroup
//        ViewGroup viewGroup = findViewById(android.R.id.content);
//
//        //then we will inflate the custom alert dialog xml that we created
////        View dialogView = LayoutInflater.from(this).inflate(R.layout.my_dialog, viewGroup, false);
//        View dialogView = LayoutInflater.from(this).inflate(R.layout.recharge_confirm_alert, viewGroup, false);
//
//        TextView tvcoin=dialogView.findViewById(R.id.tv_coint_count);
//        TextView tvAmount=dialogView.findViewById(R.id.tv_amount);
////        TextView tvcoin=dialogView.findViewById(R.id.tv_coint_count);
//
//
////        tvcoin.setText(coin+" Coins");
////        tvAmount.setText(amount);
//        //Now we need an AlertDialog.Builder object
//        AlertDialog.Builder builder = new AlertDialog.Builder(this);
//
//        //setting the view of the builder to our custom view that we already inflated
//        builder.setView(dialogView);
//
//        //finally creating the alert dialog and displaying it
//        AlertDialog alertDialog = builder.create();
//        alertDialog.show();
//    }





}
