package com.example.fwprld.ui.fragments;


import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fwprld.R;
import com.example.fwprld.ui.activities.MainActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.FirebaseTooManyRequestsException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.concurrent.TimeUnit;

import dmax.dialog.SpotsDialog;

public class OTPFragment extends Fragment {

    View view;
    String mobile_number;
    TextView num_id;
    private EditText code_one, code_two, code_three, code_four, code_five, code_six;
    private Button continuebutton;
    FirebaseAuth firebaseAuth;
    private PhoneAuthProvider.ForceResendingToken mResendToken;
    private PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks;
    private String mVerificationId;
    private String reset_mobile, reset_password;
    private AlertDialog loadingDialog;

    public OTPFragment() {}

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_ot, container, false);

        Bundle mobileBundle = this.getArguments();
        if (mobileBundle != null){
            mobile_number = mobileBundle.getString("mobile_number");
        }

        firebaseAuth = FirebaseAuth.getInstance();

        num_id = view.findViewById(R.id.num_id);
        num_id.setText(mobile_number);

        code_one = view.findViewById(R.id.code_one);
        code_two = view.findViewById(R.id.code_two);
        code_three = view.findViewById(R.id.code_three);
        code_four = view.findViewById(R.id.code_four);
        code_five = view.findViewById(R.id.code_five);
        code_six = view.findViewById(R.id.code_six);


        loadingDialog = new SpotsDialog.Builder().setContext(getContext())
                .setTheme(R.style.loading)
                .setMessage("Verifing Code")
                .setCancelable(false)
                .build();

        continuebutton = view.findViewById(R.id.continuebutton);
        continuebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String otpcode = code_one.getText().toString() +
                        code_two.getText().toString() +
                        code_three.getText().toString() +
                        code_four.getText().toString() +
                        code_five.getText().toString() +
                        code_six.getText().toString();
                verifyPhoneNumberWithCode(mVerificationId, otpcode);
            }
        });

        TextView txtResendCode = view.findViewById(R.id.didntcode_id);
        txtResendCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resendVerificationCode(mobile_number, mResendToken);
            }
        });


        code_one.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (!code_one.getText().toString().equals("")) {
                    code_one.clearFocus();
                    code_two.requestFocus();
                    code_two.setCursorVisible(true);
                }

            }
        });

        code_two.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                code_two.requestFocus();
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (!code_two.getText().toString().equals("")) {
                    code_two.clearFocus();
                    code_three.requestFocus();
                    code_three.setCursorVisible(true);
                }

            }
        });

        code_three.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                code_three.requestFocus();
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            //6594 otp
            @Override
            public void afterTextChanged(Editable s) {
                if (!code_three.getText().toString().equals("")) {
                    code_three.clearFocus();
                    code_four.requestFocus();
                    code_four.setCursorVisible(true);
                }

            }
        });

        code_four.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                code_four.requestFocus();
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            //6594 otp
            @Override
            public void afterTextChanged(Editable s) {
                if (!code_four.getText().toString().equals("")) {
                    code_four.clearFocus();
                    code_five.requestFocus();
                    code_five.setCursorVisible(true);
                }

            }
        });

        code_five.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                code_five.requestFocus();
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            //6594 otp
            @Override
            public void afterTextChanged(Editable s) {
                if (!code_five.getText().toString().equals("")) {
                    code_five.clearFocus();
                    code_six.requestFocus();
                    code_six.setCursorVisible(true);
                }

            }
        });

        code_six.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                code_six.requestFocus();
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (!code_six.getText().toString().equals("")) {
                    hideKeyboard((Activity) getContext());
                    code_six.clearFocus();
                }

            }
        });


        mCallbacks = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
            @Override
            public void onVerificationCompleted(PhoneAuthCredential credential) {
                signInWithPhoneAuthCredential(credential);
            }

            @Override
            public void onVerificationFailed(FirebaseException e) {
                Toast.makeText(getContext(), "" + e, Toast.LENGTH_SHORT).show();
                if (e instanceof FirebaseAuthInvalidCredentialsException) {
                    Toast.makeText(getContext(), "Enter valid mobile number", Toast.LENGTH_SHORT).show();
                } else if (e instanceof FirebaseTooManyRequestsException) {
                    Toast.makeText(getContext(), "Quota Exceeded", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCodeSent(String verificationId,
                                   PhoneAuthProvider.ForceResendingToken token) {
                mVerificationId = verificationId;
                mResendToken = token;
            }
        };

        startPhoneNumberVerification(mobile_number);


        return view;

    }

    private void signInWithPhoneAuthCredential(PhoneAuthCredential credential) {
        loadingDialog.show();
        firebaseAuth.signInWithCredential(credential)
                .addOnCompleteListener(getActivity(), new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){

                           final String uid  = firebaseAuth.getCurrentUser().getUid();

                            final DatabaseReference profileRef = FirebaseDatabase.getInstance().getReference("FWORLD_USER_DATA").child("USER_PROFILE");

                            profileRef.child("USER_BASIC_INFO").child(uid).addValueEventListener(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                    if (dataSnapshot.hasChildren()){

                                    }else {

                                        final DatabaseReference fidCounterRef = FirebaseDatabase.getInstance().getReference("FWORLD_VARIABLES");
                                        fidCounterRef.child("USER_ID_COUNTER").addValueEventListener(new ValueEventListener() {
                                            @Override
                                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                                                String fid_counter =  (String) dataSnapshot.child("fid_counter").getValue();
                                                assert fid_counter != null;
                                                int fid_to_int = Integer.parseInt(fid_counter);
                                                int fid_updated = fid_to_int+1;
                                                String fid = "FW"+fid_updated;
                                                Toast.makeText(getContext(), ""+fid, Toast.LENGTH_SHORT).show();
                                                profileRef.child("USER_BASIC_INFO").child(uid).child("user_fid").setValue(fid);
                                                fidCounterRef.child("USER_ID_COUNTER").setValue(fid_updated);
                                                startActivity(new Intent(getContext() , MainActivity.class));
                                                loadingDialog.dismiss();
                                            }

                                            @Override
                                            public void onCancelled(@NonNull DatabaseError databaseError) {

                                            }
                                        });

                                    }
                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError databaseError) {

                                }
                            });
//

//
                            loadingDialog.dismiss();
                        }else {
                            Toast.makeText(getContext(), "" + task.getException(), Toast.LENGTH_SHORT).show();
                            if (task.getException() instanceof FirebaseAuthInvalidCredentialsException) {
                                Toast.makeText(getContext(), "Invalid code", Toast.LENGTH_SHORT).show();
                                loadingDialog.dismiss();
                            }
                        }
                    }
                });
    }

    private void startPhoneNumberVerification(String phoneNumber) {
        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                phoneNumber,        // Phone number to verify
                60,                 // Timeout duration
                TimeUnit.SECONDS,   // Unit of timeout
                getActivity(),               // Activity (for callback binding)
                mCallbacks);        // OnVerificationStateChangedCallbacks
    }

    private void verifyPhoneNumberWithCode(String verificationId, String code) {
        PhoneAuthCredential credential = PhoneAuthProvider.getCredential(verificationId, code);
        signInWithPhoneAuthCredential(credential);
    }

    private void resendVerificationCode(String phoneNumber,PhoneAuthProvider.ForceResendingToken token) {
        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                phoneNumber,        // Phone number to verify
                60,                 // Timeout duration
                TimeUnit.SECONDS,   // Unit of timeout
                getActivity(),               // Activity (for callback binding)
                mCallbacks,         // OnVerificationStateChangedCallbacks
                token);             // ForceResendingToken from callbacks
        Toast.makeText(getContext(), "Code Resent", Toast.LENGTH_SHORT).show();
    }

    public static void hideKeyboard(Activity activity) {

        InputMethodManager imm = (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
        View view = activity.getCurrentFocus();
        if (view == null) {
            view = new View(activity);
        }
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }


}
