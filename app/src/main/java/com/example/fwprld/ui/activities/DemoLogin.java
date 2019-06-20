package com.example.fwprld.ui.activities;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.IntentSender;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.fwprld.R;
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
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.common.Scopes;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.plus.Plus;
import com.google.android.gms.plus.model.people.Person;

import org.json.JSONObject;

import java.security.MessageDigest;
import java.util.Arrays;
import java.util.List;

public class DemoLogin extends Activity implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener{//implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener

    String FBretUser, Fbretid, FBretFNAME, FBretEmail, FBretLNAME,FBgender="";
    CallbackManager callbackManager;
    private ProfileTracker mProfileTracker;
    private AccessTokenTracker mAccestokentracker;
    private static final List<String> FACEBOOK_PERMISSIONS = Arrays.asList("public_profile", "email");
    private String gmailUserID;
    private String gmailUsername;
    private String gmailUseremail;
    private String gmailUserimgurl;
    private static final int RC_SIGN_IN = 0;
    private static final int PROFILE_PIC_SIZE = 400;
    private GoogleApiClient mGoogleApiClient;
    private boolean mIntentInProgress;
    private boolean mSignInClicked;
    private ConnectionResult mConnectionResult;
    private static final String TAG = "PreLoginAcivity";

    ProgressDialog pbGmail=null;

    ImageButton IbGplus,fbbutton;
    RelativeLayout rlGplus;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getApplicationContext());
        callbackManager = CallbackManager.Factory.create();
        LoginManager.getInstance().registerCallback(callbackManager,
                new FacebookCallback<LoginResult>() {
                    @Override
                    public void onSuccess(LoginResult loginResult) {
                        // App code
//									LoginManager.getInstance().logInWithReadPermissions(LoginFragment.this, FACEBOOK_PERMISSIONS);
                        Log.e("-->", Arrays.asList("public_profile", "user_friends").toString());
//                        Toast.makeText(getApplication(), "success", Toast.LENGTH_SHORT).show();

//                        AppConstants.ACCESS_TOKENS=loginResult.getAccessToken().getToken();
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
//                                            Appconstants.FBId = "https://graph.facebook.com/" + Fbretid + "/picture?type=large";
//                                            Appconstants.FBusername = FBretFNAME;
//                                            LoginJson(Fbretid,FBurl,"FACEBOOK",FBretEmail,"123456");

//                                            Intent i = new Intent(PreLoginAcivity.this, HomeScreenActivity.class);
//                                            startActivity(i);
//                                            finish();
//                                            LoginManager.getInstance().logOut();
//                                            if (Support.isConnectingToInternet(PreLoginAcivity.this)) {
//                                                callSocialAPI();
//                                            } else {
//                                                Toast.makeText(PreLoginAcivity.this, PreLoginAcivity.this.getResources().getString(R.string.net_connection), Toast.LENGTH_SHORT).show();
//                                            }


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


        setContentView(R.layout.act_login_demo);
        GetHashKey();

        mGoogleApiClient = new GoogleApiClient.Builder(DemoLogin.this).
                addConnectionCallbacks(DemoLogin.this)
                .addOnConnectionFailedListener(DemoLogin.this)
                .addApi(Plus.API)
                .addScope(new Scope(Scopes.PROFILE))
                //.addApi(Drive.API)
                //.addScope(Drive.SCOPE_FILE)
                .build();
        InitCompo();
        Listner();
    }

    private void Listner() {
        fbbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                LoginManager.getInstance().logInWithReadPermissions(DemoLogin.this, FACEBOOK_PERMISSIONS);
            }
        });
        rlGplus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signInWithGplus();
            }
        });
    }

    private void InitCompo() {
        rlGplus=findViewById(R.id.rl_gplus);
        fbbutton=findViewById(R.id.fb_ib);

    }

    @Override
    protected void onActivityResult(int requestCode, int responseCode,
                                    Intent intent) {
//        callbackManager.onActivityResult(requestCode, responseCode, intent);
        if (requestCode == RC_SIGN_IN) {
            if (responseCode != RESULT_OK) {
                mSignInClicked = false;
            }
            mIntentInProgress = false;
            if (!mGoogleApiClient.isConnecting()) {
                mGoogleApiClient.connect();
            }
        }else {
            callbackManager.onActivityResult(requestCode, responseCode, intent);
        }
    }
    public void GetHashKey()
    {
        try {
            PackageInfo info = getPackageManager().getPackageInfo(
                    "com.example.fwprld",
                    PackageManager.GET_SIGNATURES);
            for (Signature signature : info.signatures) {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                Log.d("KeyHash:", Base64.encodeToString(md.digest(), Base64.DEFAULT));
            }
        } catch (Exception e) {

        }
    }
    private void signInWithGplus() {
        if (!mGoogleApiClient.isConnecting()) {
            mSignInClicked = true;
            resolveSignInError();
        }
    }
    protected void onStart() {
        super.onStart();
        mGoogleApiClient.connect();
    }

    protected void onStop() {
        super.onStop();
        if (mGoogleApiClient.isConnected()) {
            mGoogleApiClient.disconnect();
        }
    }
    public void StopProgress()
    {
        if(pbGmail!=null)
        {
            pbGmail.dismiss();
        }
    }
    private void resolveSignInError() {
        if (mConnectionResult.hasResolution()) {
            try {
                mIntentInProgress = true;
                mConnectionResult.startResolutionForResult(this, RC_SIGN_IN);
            } catch (IntentSender.SendIntentException e) {
                mIntentInProgress = false;
                mGoogleApiClient.connect();
                StopProgress();
            }
        }
    }

    @Override
    public void onConnected(Bundle bundle) {
        mSignInClicked = false;
//		Toast.makeText(this, "User is connected!", Toast.LENGTH_LONG).show();
        getProfileInformationFromGmail();

    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(ConnectionResult result) {
        if (!result.hasResolution()) {
            GooglePlayServicesUtil.getErrorDialog(result.getErrorCode(), this,
                    0).show();
            return;
        }
        if (!mIntentInProgress) {
            mConnectionResult = result;
            if (mSignInClicked) {
                resolveSignInError();
            }
        }
    }
    private void getProfileInformationFromGmail() {
        try {
//			if (Plus.PeopleApi.getCurrentPerson(mGoogleApiClient) != null)
            if (mGoogleApiClient != null)
            {
                Person currentPerson = Plus.PeopleApi
                        .getCurrentPerson(mGoogleApiClient);
                gmailUserID = currentPerson.getId();
                gmailUsername = currentPerson.getDisplayName();
                String personPhotoUrl = currentPerson.getImage().getUrl();
                gmailUseremail = Plus.AccountApi.getAccountName(mGoogleApiClient);

                gmailUserimgurl = personPhotoUrl.substring(0,personPhotoUrl.length() - 2)+ PROFILE_PIC_SIZE;
                Log.e("gmail info", "id= " + gmailUserID + "Name: "
                        + gmailUsername + ", email: " + gmailUseremail
                        + ", Image: " + gmailUserimgurl);
                StopProgress();
//                callSocialAPI();
//                Intent i = new Intent(PreLoginAcivity.this, HomeScreenActivity.class);
//                startActivity(i);
//                finish();
            } else {
                Toast.makeText(getApplicationContext(),
                        "Person information is null", Toast.LENGTH_LONG).show();
                StopProgress();
//                if(progress_Dialog1!=null) {
//                    progress_Dialog1.dialog_dismiss();
//                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            StopProgress();
//            if(progress_Dialog1!=null) {
//                progress_Dialog1.dialog_dismiss();
//            }
        }
    }
    private void revokeGplusAccess() {
        if (mGoogleApiClient.isConnected()) {
            Plus.AccountApi.clearDefaultAccount(mGoogleApiClient);
            Plus.AccountApi.revokeAccessAndDisconnect(mGoogleApiClient)
                    .setResultCallback(new ResultCallback<Status>() {
                        @Override
                        public void onResult(Status arg0) {
                            Log.e(TAG, "User access revoked!");
                            mGoogleApiClient.connect();
//							updateUI(false);
                        }

                    });
        }
    }


}
