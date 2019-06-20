package com.example.fwprld.ui.fragments;


import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.fwprld.R;
import com.example.fwprld.common.LoginSession;
import com.example.fwprld.common.SessionParam;
import com.example.fwprld.models.profilemodels.BasicInfoData;
import com.example.fwprld.ui.activities.EditProfileActivity;
import com.example.fwprld.ui.activities.MainActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.FirebaseTooManyRequestsException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.util.concurrent.TimeUnit;

import dmax.dialog.SpotsDialog;

public class OTPFragment extends Fragment {

    View view;
    String mobile_number;
    TextView num_id;
    private EditText code_one, code_two, code_three, code_four, code_five, code_six;
    FirebaseAuth firebaseAuth;
    private PhoneAuthProvider.ForceResendingToken mResendToken;
    private PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks;
    private String mVerificationId;
    private AlertDialog loadingDialog;
    private FirebaseFirestore userDB;

    public OTPFragment() {}

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_ot, container, false);

        Bundle mobileBundle = this.getArguments();
        if (mobileBundle != null){
            mobile_number = mobileBundle.getString("mobile_number");
        }

        firebaseAuth = FirebaseAuth.getInstance();
        userDB = FirebaseFirestore.getInstance();

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

        Button continuebutton = view.findViewById(R.id.continuebutton);
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
                Log.e("",""+e);
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
                Log.e("","shyam token= "+token);
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
                        Log.e("","AuthResult= "+task.toString()+" credential= "+credential.toString());
                        if (task.isSuccessful()){
//                            final DocumentReference userRef = userDB.collection("APP_PARAMETERS").document("COUNTERS");
//                            userRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
//                                @Override
//                                public void onComplete(@NonNull Task<DocumentSnapshot> task) {
//
//                                    if (task.isSuccessful()){
//                                        DocumentSnapshot counter = task.getResult();
//                                        Toast.makeText(getContext(), ""+counter.get("user_counter"), Toast.LENGTH_SHORT).show();
//                                        String counter_value = (String) counter.get("user_counter");
//                                        int counter_value_int = Integer.parseInt(counter_value);
//                                        int updated_counter = counter_value_int+1;
//                                        userRef.set(updated_counter);
//                                        Toast.makeText(getContext(), ""+updated_counter, Toast.LENGTH_SHORT).show();
//                                        startActivity(new Intent(getContext() , MainActivity.class));
//                                        loadingDialog.dismiss();
//
//                                    }else {
//
//                                        Toast.makeText(getContext(), "Database Error", Toast.LENGTH_SHORT).show();
//                                        loadingDialog.dismiss();
//
//                                    }
//
//                                }
//                            });


//                            String token = task.getResult().getToken();
//                            String msg = getString(R.string.fcm_token, token);
//                            Log.d("otp verify", "shyam fcm token= "+token);

//                            LoginSession.LoggedInVal(getActivity(),true);
////                         startActivity(new Intent(getContext() , MainActivity.class));
//
//                            Intent intent = new Intent(getContext(),
//                                    MainActivity.class);
//                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//                            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                            startActivity(intent);
//
//                            loadingDialog.dismiss();
                              GetRegistration();
//                            SessionParam sessionParam = new SessionParam(array.optJSONObject(0));
//                            sessionParam.persist(getContext());//for session manage at splash,login screen
                        }else {
                            Toast.makeText(getContext(), "" + task.getException(), Toast.LENGTH_SHORT).show();
                            Log.e("","AuthResult= "+task.toString());
                            if (task.getException() instanceof FirebaseAuthInvalidCredentialsException) {
                                Toast.makeText(getContext(), "Invalid code", Toast.LENGTH_SHORT).show();

                                loadingDialog.dismiss();
                            }
                        }
                    }
                });
    }
    String TAG="OTP Verify";
    public void GetRegistration()
    {
        FirebaseInstanceId.getInstance().getInstanceId()
                .addOnCompleteListener(new OnCompleteListener<InstanceIdResult>() {
                    @Override
                    public void onComplete(@NonNull Task<InstanceIdResult> task) {
                        if (!task.isSuccessful()) {
//To do//
                            return;
                        }

// Get the Instance ID token//
                        String token = task.getResult().getToken();
                        String msg = getString(R.string.fcm_token, token);
                        Log.d(TAG, "shyam fcm token= "+token);

                                                getUserInfo(token);

//////////////////////////////// dummy data //////////////////////////////////////////
//                        String user_name = "fworld_User";
//                        String user_gender1 = "fworld_User";
//                        String user_birthday = "2019-6-10";
//                        String user_relationship_status = "fworld_dummy_data";
//                        String user_about = "fworld_dummy_data";
//                        String user_status = "fworld_dummy_data";
//                        String followers = "0";
//                        String followings = "0";
//                        String rank_record = "0";
//                        String bio = "foody,music listner fun friends";
//                        String profile_pic = "https://firebasestorage.googleapis.com/v0/b/fworld-3c022.appspot.com/o/FWORLD_USER_AVATAR%2Fdummy_profile.png?alt=media&token=7acd42a5-4c94-44c8-951d-83c32d5a7cbc";
//
//                        FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
//                        String fid = firebaseUser.getUid();
//
//                        Bitmap myLogo = BitmapFactory.decodeResource(getActivity().getResources(), R.drawable.dummy_profile);
//                        ByteArrayOutputStream baos = new ByteArrayOutputStream();
//                        myLogo.compress(Bitmap.CompressFormat.JPEG, 100, baos);
//                        byte[] data = baos.toByteArray();
//
//                        StorageReference avatarStoreRef = FirebaseStorage.getInstance().getReference("FWORLD_USER_AVATAR").child(fid+".jpg");
////                        UploadTask uploadTask = avatarStoreRef.putFile(avatarPath);
//                        UploadTask uploadTask = avatarStoreRef.putBytes(data);
//                        uploadTask.addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
//                            @Override
//                            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
//                                Log.e("","photo uploaded");
////                                loadingDialog.dismiss();
////                                Toast.makeText(getContext(), "Upload successful", Toast.LENGTH_SHORT).show();
//
//
//                            }
//                        }).addOnFailureListener(new OnFailureListener() {
//                            @Override
//                            public void onFailure(@NonNull Exception e) {
//                                Log.e("","error photo upload");
////                                loadingDialog.dismiss();
////                                Toast.makeText(getContext(), "Upload Failed" + e, Toast.LENGTH_SHORT).show();
//                            }
//                        });
//
//
//   BasicInfoData basicInfoData = new BasicInfoData(user_name,fid,user_gender1,user_birthday,user_relationship_status, user_about,user_status,followers,followings,rank_record,bio);
//
//       DatabaseReference  profileRef = FirebaseDatabase.getInstance().getReference("FWORLD_USER_DATA").child("USER_PROFILE");
//                        profileRef.child("USER_BASIC_INFO").child(fid).setValue(basicInfoData);
//
//                        JSONObject jsLogin=null;
//                        try {
//                            jsLogin=new JSONObject();
//                            jsLogin.put("id",fid);
//                            jsLogin.put("name",user_name);
//                            jsLogin.put("pic",profile_pic);
//                            jsLogin.put("mobile",mobile_number);
//                        } catch (JSONException e) {
//                            e.printStackTrace();
//                        }
//
//                        LoginSession sessionParam = new LoginSession(jsLogin);
//                        sessionParam.persist(getContext());//for session manage at splash,login screen
//
//                        new LoginSession(getContext()).LoggedInVal(getActivity(),true);
//                        new LoginSession(getContext()).TokenInVal(getActivity(),token);
////                         startActivity(new Intent(getContext() , MainActivity.class));
//                        Intent intent = new Intent(getContext(),
//                                MainActivity.class);
//                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                        startActivity(intent);
//                        loadingDialog.dismiss();

//////////////////////////////// dummy data //////////////////////////////////////////
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
    String profile_pic="";
    private void getUserInfo(String token){
        FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
       String fid = firebaseUser.getUid();

        StorageReference profilePic = FirebaseStorage.getInstance().getReference("FWORLD_USER_AVATAR").child(fid+".jpg");
        profilePic.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                profile_pic = uri.toString();

            }
        });


        DatabaseReference profileRef = FirebaseDatabase.getInstance().getReference("FWORLD_USER_DATA").child("USER_PROFILE");
        profileRef.child("USER_BASIC_INFO").child(fid).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                String user_name="";
                user_name = (String) dataSnapshot.child("user_name").getValue();

  if(TextUtils.isEmpty(user_name)||user_name.equals("null")||user_name.equals(null))
  {
       user_name = "fworld_User";
      String user_gender1 = "fworld_User";
      String user_birthday = "2019-6-10";
      String user_relationship_status = "fworld_dummy_data";
      String user_about = "fworld_dummy_data";
      String user_status = "fworld_dummy_data";
      String followers = "0";
      String followings = "0";
      String rank_record = "0";
      String bio = "foody,music listner fun friends";
       profile_pic = "https://firebasestorage.googleapis.com/v0/b/fworld-3c022.appspot.com/o/FWORLD_USER_AVATAR%2Fdummy_profile.png?alt=media&token=7acd42a5-4c94-44c8-951d-83c32d5a7cbc";



      FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
      String fid = firebaseUser.getUid();

      Bitmap myLogo = BitmapFactory.decodeResource(getActivity().getResources(), R.drawable.dummy_profile);
      ByteArrayOutputStream baos = new ByteArrayOutputStream();
      myLogo.compress(Bitmap.CompressFormat.JPEG, 100, baos);
      byte[] data = baos.toByteArray();

      StorageReference avatarStoreRef = FirebaseStorage.getInstance().getReference("FWORLD_USER_AVATAR").child(fid+".jpg");
//                        UploadTask uploadTask = avatarStoreRef.putFile(avatarPath);
      UploadTask uploadTask = avatarStoreRef.putBytes(data);
      uploadTask.addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
          @Override
          public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
              Log.e("","photo uploaded");
//              profile_pic = uri.toString();


          }
      }).addOnFailureListener(new OnFailureListener() {
          @Override
          public void onFailure(@NonNull Exception e) {
              Log.e("","error photo upload");
          }
      });


      BasicInfoData basicInfoData = new BasicInfoData(user_name,fid,user_gender1,user_birthday,user_relationship_status, user_about,user_status,followers,followings,rank_record,bio);

//      DatabaseReference  profileRef = FirebaseDatabase.getInstance().getReference("FWORLD_USER_DATA").child("USER_PROFILE");
      profileRef.child("USER_BASIC_INFO").child(fid).setValue(basicInfoData);

  }


                JSONObject jsLogin=null;
                try {
                    jsLogin=new JSONObject();
                    jsLogin.put("id",fid);
                    jsLogin.put("name",user_name);
                    jsLogin.put("pic",profile_pic);
                    jsLogin.put("mobile",mobile_number);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                LoginSession sessionParam = new LoginSession(jsLogin);
                sessionParam.persist(getContext());//for session manage at splash,login screen

                new LoginSession(getContext()).LoggedInVal(getActivity(),true);
                new LoginSession(getContext()).TokenInVal(getActivity(),token);
//                         startActivity(new Intent(getContext() , MainActivity.class));
                Intent intent = new Intent(getContext(),
                        MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                loadingDialog.dismiss();




//                user_fid = (String) dataSnapshot.child("user_fid").getValue();
//                user_gender = (String) dataSnapshot.child("user_gender").getValue();
//                user_birthday = (String) dataSnapshot.child("user_birthday").getValue();
//                user_relationship_status = (String) dataSnapshot.child("user_relationship_status").getValue();
//                user_about = (String) dataSnapshot.child("user_about").getValue();
//                user_status = (String) dataSnapshot.child("user_status").getValue();
//
//
//                JSONObject jsLogin=null;
//                try {
//                    jsLogin=new JSONObject();
//                    jsLogin.put("id",fid);
//                    jsLogin.put("name",user_name);
//                    jsLogin.put("pic","");
//                    jsLogin.put("mobile","");
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//
//                LoginSession sessionParam = new LoginSession(jsLogin);
//                sessionParam.persist(getContext());//for session manage at splash,login screen
//
//                new LoginSession(getContext()).LoggedInVal(getActivity(),true);
////                         startActivity(new Intent(getContext() , MainActivity.class));
//                Intent intent = new Intent(getContext(),
//                        MainActivity.class);
//                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                startActivity(intent);
//                loadingDialog.dismiss();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }
}
