package com.example.fwprld.ui.activities;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fwprld.R;
import com.example.fwprld.ui.fragments.HomeFragment;
import com.example.fwprld.ui.fragments.LoginActivity;
import com.example.fwprld.ui.fragments.MessageFragment;
import com.example.fwprld.ui.fragments.MomentDiscoverFragment;
import com.example.fwprld.ui.fragments.NotificationFragment;
import com.example.fwprld.ui.fragments.NotificationFragmentNew;
import com.example.fwprld.ui.fragments.ProfileFragment;
import com.facebook.AccessToken;
import com.facebook.AccessTokenTracker;
import com.facebook.CallbackManager;
import com.facebook.FacebookAuthorizationException;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.Profile;
import com.facebook.ProfileTracker;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.google.android.gms.auth.api.Auth;

import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.Scopes;
import com.google.android.gms.common.SignInButton;

import com.google.android.gms.common.api.GoogleApiClient;

import com.google.android.gms.common.api.ResultCallback;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;


import org.json.JSONObject;

import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity implements
        GoogleApiClient.OnConnectionFailedListener, GoogleApiClient.ConnectionCallbacks {
    LinearLayout profile,moment, mic, noti, mess;
    int count=0;
    AlertDialog alertDialog;
    FrameLayout frameLayout;
    FirebaseUser firebaseUser;

    String FBretUser, Fbretid, FBretFNAME, FBretEmail, FBretLNAME,FBgender="";
    CallbackManager callbackManager;
    private ProfileTracker mProfileTracker;
    private AccessTokenTracker mAccestokentracker;
    private static final List<String> FACEBOOK_PERMISSIONS = Arrays.asList("public_profile", "email");
    private String gmailUserID;
    private String gmailUsername;
    private String gmailUseremail;
    private String gmailUserimgurl;

//    SessionParam sessionParam;


    private static final String TAG = MainActivity.class.getSimpleName();
    private static final int RC_SIGN_IN = 007;

    private GoogleApiClient mGoogleApiClient;
    private ProgressDialog mProgressDialog;

    private SignInButton btnSignIn;
    private Button btnSignOut, btnRevokeAccess;
    private LinearLayout llProfileLayout;
    private ImageView imgProfilePic;
    private TextView txtName, txtEmail;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        InitFb();
//        sessionParam = new SessionParam(this);
//       String userid= sessionParam.usr_id;
        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();

        profile=(LinearLayout)findViewById(R.id.profile);
        moment=(LinearLayout)findViewById(R.id.moment);
        mic = (LinearLayout) findViewById(R.id.mic);
        noti = (LinearLayout) findViewById(R.id.noti);
        mess = (LinearLayout) findViewById(R.id.mess);

        mic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.frame, new HomeFragment())
                        .addToBackStack(null)
                        .commit();
            }
        });

        noti.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.frame, new NotificationFragmentNew())
                        .addToBackStack(null)
                        .commit();
//                getSupportFragmentManager()
//                        .beginTransaction()
//                        .replace(R.id.frame, new NotificationFragment())
//                        .addToBackStack(null)
//                        .commit();
            }
        });

        mess.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.frame, new MessageFragment())
                        .addToBackStack(null)
                        .commit();
            }
        });

        frameLayout=(FrameLayout)findViewById(R.id.frame);
        moment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.frame, new MomentDiscoverFragment())
                        .addToBackStack(null)
                        .commit();
            }
        });
        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (firebaseUser != null){

                    getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.frame, new ProfileFragment())
                            .addToBackStack(null)
                            .commit();

                }else {
                    Show();
                }

            }
        });

                getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.frame, new HomeFragment())
                .commit();
        InitGplus();
//        GetRegistration();
    }

    public void  Show() {
        //Creating a LayoutInflater object for the dialog box
        final LayoutInflater li = LayoutInflater.from(MainActivity.this);
        //Creating a view to get the dialog box
        View confirmDialog = li.inflate(R.layout.dialogue_layout, null);

        //Initizliaing confirm button fo dialog box and edittext of dialog box
        final Button btnsub = (Button) confirmDialog.findViewById(R.id.btnsub);
        final ImageView ivFb = (ImageView) confirmDialog.findViewById(R.id.iv_fab);
        final ImageView ivGPlus = (ImageView) confirmDialog.findViewById(R.id.iv_gplus);

        ivFb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoginManager.getInstance().logInWithReadPermissions(MainActivity.this, FACEBOOK_PERMISSIONS);
            }
        });
        ivGPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signIn();
            }
        });


        btnsub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.frame, new LoginActivity())
                        .addToBackStack(null)
                        .commit();
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

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
//        if (requestCode == RC_SIGN_IN) {
//            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
//            handleSignInResult(result);
//        }
//
////        if (requestCode == RC_SIGN_IN) {
////            if (responseCode != RESULT_OK) {
////                mSignInClicked = false;
////            }
////            mIntentInProgress = false;
////            if (!mGoogleApiClient.isConnecting()) {
////                mGoogleApiClient.connect();
////            }
////        }

        if (requestCode == RC_SIGN_IN) {
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            handleSignInResult(result);
        }
        else
            {
            callbackManager.onActivityResult(requestCode, resultCode, data);
        }
    }


//  fbIntegration
    public void InitFb()
    {
        FacebookSdk.sdkInitialize(getApplicationContext());
        callbackManager = CallbackManager.Factory.create();
        LoginManager.getInstance().registerCallback(callbackManager,
                new FacebookCallback<LoginResult>() {
                    @Override
                    public void onSuccess(LoginResult loginResult) {
                          Log.e("-->", Arrays.asList("public_profile", "user_friends").toString());
                        GraphRequest request = GraphRequest.newMeRequest(
                                loginResult.getAccessToken(), new GraphRequest.GraphJSONObjectCallback() {
                                    @Override
                                    public void onCompleted(JSONObject object, GraphResponse response) {
                                        if (Profile.getCurrentProfile() == null) {
                                            mProfileTracker = new ProfileTracker() {
                                                @Override
                                                protected void onCurrentProfileChanged(Profile profile, Profile profile2) {
                                                    Log.v("facebook - profile", profile2.getFirstName());
                                                    mProfileTracker.stopTracking();
                                                }
                                            };
                                            mProfileTracker.startTracking();
                                            if (mAccestokentracker != null) {
                                                mAccestokentracker.startTracking();
                                            }
                                        } else {
                                            Profile profile = Profile.getCurrentProfile();
//                                            FBusername = profile.getFirstName();
                                            Fbretid = profile.getId();

                                            FBretFNAME = object.optString("first_name");
                                            FBretLNAME = object.optString("last_name");
                                            FBgender = object.optString("gender");


                                            FBretEmail = object.optString("email");
                                            gmailUseremail = object.optString("email");
                                            gmailUserID = profile.getId();
                                            gmailUsername = FBretFNAME;//+" "+FBretLNAME

                                            Log.v("facebook - profile", profile.getFirstName());
                                            Log.v("facebook -  FBretEmail", FBretEmail);
                                            Log.v("facebook - FBretFNAME", FBretFNAME);
                                            Log.v("facebook - FBretLNAME", FBretLNAME);
                                            Log.v("facebook - FBgender", FBgender);

//											Log.v("facebook -  email", email);
                                            Log.v("facebook - id", Fbretid);
                                            String FBurl = "https://graph.facebook.com/" + Fbretid + "/picture?type=large";
                                            NevigateMobileScreen();

                                        }
                                    }
                                });

                        Bundle parameters = new Bundle();
                        parameters.putString("fields", "id,name, email, birthday,gender,first_name,last_name");
                        request.setParameters(parameters);
                        request.executeAsync();

                    }

                    @Override
                    public void onCancel() {
                        // App code
                        Toast.makeText(getApplication(), "fail", Toast.LENGTH_SHORT).show();
                    }


                    @Override
                    public void onError(FacebookException exception) {
                        // App code
                        if (exception instanceof FacebookAuthorizationException) {
                            if (AccessToken.getCurrentAccessToken() != null) {
                                LoginManager.getInstance().logOut();
                            }
                        }

                        Log.d("fbexception", String.valueOf(exception));
                        Toast.makeText(getApplication(), "Check your network connection", Toast.LENGTH_SHORT).show();

                    }
                });

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }
//    gplus integration
    public void InitGplus()
    {
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this, this)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();

    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {

    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    private void signIn() {
        Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }


    private void signOut() {
        Auth.GoogleSignInApi.signOut(mGoogleApiClient).setResultCallback(
                new ResultCallback<Status>() {
                    @Override
                    public void onResult(Status status) {
//                        updateUI(false);
                    }
                });
    }

    private void revokeAccess() {
        Auth.GoogleSignInApi.revokeAccess(mGoogleApiClient).setResultCallback(
                new ResultCallback<Status>() {
                    @Override
                    public void onResult(Status status) {
//                        updateUI(false);
                    }
                });
    }

    private void handleSignInResult(GoogleSignInResult result) {
        Log.d(TAG, "handleSignInResult:" + result.isSuccess());
        if (result.isSuccess()) {
            // Signed in successfully, show authenticated UI.
            GoogleSignInAccount acct = result.getSignInAccount();

            Log.e(TAG, "display name: " + acct.getDisplayName());

            String personName = acct.getDisplayName();
            String personPhotoUrl = acct.getPhotoUrl().toString();
            String email = acct.getEmail();

            Log.e(TAG, "Name: " + personName + ", email: " + email
                    + ", Image: " + personPhotoUrl);

            txtName.setText(personName);
            txtEmail.setText(email);
//            Glide.with(getApplicationContext()).load(personPhotoUrl)
//                    .thumbnail(0.5f)
//                    .crossFade()
//                    .diskCacheStrategy(DiskCacheStrategy.ALL)
//                    .into(imgProfilePic);

//            updateUI(true);
        } else {
            // Signed out, show unauthenticated UI.
//            updateUI(false);
        }
        NevigateMobileScreen();
    }
    public void NevigateMobileScreen()
    {
        if(alertDialog!=null) {
            alertDialog.dismiss();
        }
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.frame, new LoginActivity())
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void onBackPressed() {
//        super.onBackPressed();
        ShowMessage();
    }
    public void ShowMessage()
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(false);
        builder.setMessage("Do you want to Exit?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });
        builder.setNegativeButton("No",new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        AlertDialog alert=builder.create();
        alert.show();
    }
    /*public void GetRegistration()
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
//
                    }
                });

    }*/
}

