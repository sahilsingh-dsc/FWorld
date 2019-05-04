package com.example.fwprld.ui.fragments;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.fwprld.R;
import com.hbb20.CountryCodePicker;

public class LoginActivity extends Fragment {
 Button btnNextToOTP;
 CountryCodePicker spin_coun_code;
 EditText etxtUserMobile;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.activity_login, container, false);

        spin_coun_code = v.findViewById(R.id.spin_coun_code);
        btnNextToOTP = (Button) v.findViewById(R.id.btnNextToOTP);
        etxtUserMobile = v.findViewById(R.id.etxtUserMobile);

        btnNextToOTP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String countryCodeWithPlus  = spin_coun_code.getSelectedCountryCodeWithPlus();
                String number = etxtUserMobile.getText().toString();

                if (TextUtils.isEmpty(number)){
                    Toast.makeText(getContext(), "Please enter mobile number", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (!TextUtils.isDigitsOnly(number)){
                    Toast.makeText(getContext(), "Please enter a valid mobile number", Toast.LENGTH_SHORT).show();
                    return;
                }

                String mobile_number = countryCodeWithPlus+number;

                sendOtpCode(mobile_number);

            }
        });

        return v;
    }


    private void sendOtpCode(String mobile_number){

        if (mobile_number != null){
            Bundle mobileBundle = new Bundle();
            mobileBundle.putString("mobile_number", mobile_number);
            OTPFragment otpFragment = new OTPFragment();
            otpFragment.setArguments(mobileBundle);
            FragmentManager fragmentManager = getFragmentManager();
            assert fragmentManager != null;
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.add(R.id.frame, otpFragment);
            fragmentTransaction.hide(LoginActivity.this);
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();

        }

    }

}
