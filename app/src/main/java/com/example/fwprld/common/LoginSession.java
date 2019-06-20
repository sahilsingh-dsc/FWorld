package com.example.fwprld.common;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import org.json.JSONObject;


public class LoginSession {
     String PREF_NAME = "fworld";

public  String usr_id,fname,usr_mobi,usr_pic;//usr_email,usr_city,

      public LoginSession(JSONObject jsonObject) {
        if (jsonObject != null) {
            usr_id = jsonObject.optString("id", "");
            fname = jsonObject.optString("name", "");
            usr_pic = jsonObject.optString("pic", "");
            usr_mobi = jsonObject.optString("mobile", "");
        }
    }

    public LoginSession(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(
                PREF_NAME, Context.MODE_PRIVATE);
        this.usr_id = sharedPreferences.getString("user_id", "");
        this.fname = sharedPreferences.getString("user_name", "");
        this.usr_mobi = sharedPreferences.getString("user_mobile", "");
        this.usr_pic = sharedPreferences.getString("user_pic", "");
          }

    public void persist(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(
                PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor prefsEditor = sharedPreferences.edit();
        prefsEditor.putString("user_id", usr_id);
        prefsEditor.putString("user_name", fname);
        prefsEditor.putString("user_mobile", usr_mobi);
        prefsEditor.putString("user_pic", usr_pic);
        prefsEditor.commit();

    }
//    public static void LoggedUserInVal(Context context,String usr_id,String usr_name,String usr_pic) {
//        SharedPreferences sharedPreferences = context.getSharedPreferences(
//                PREF_NAME, Context.MODE_PRIVATE);
//        SharedPreferences.Editor prefsEditor = sharedPreferences.edit();
//        prefsEditor.putString("user_id", usr_id);
//        prefsEditor.putString("user_name", usr_name);
//        prefsEditor.putString("user_pic", usr_pic);
//        prefsEditor.commit();
//
//    }

    public  void LoggedInVal(Context context,boolean check) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(
                PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor prefsEditor = sharedPreferences.edit();
        prefsEditor.putBoolean("logged_in", check);
        prefsEditor.commit();

    }
    public  boolean IsLogin(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(
                PREF_NAME, Context.MODE_PRIVATE);
        boolean loggedin = sharedPreferences.getBoolean("logged_in", false);
        return  loggedin;
    }
    public  void TokenInVal(Context context,String devToken) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(
                PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor prefsEditor = sharedPreferences.edit();
        prefsEditor.putString("dev_token", devToken);
        prefsEditor.commit();

    }
    public  String GetToken(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(
                PREF_NAME, Context.MODE_PRIVATE);
        String loggedin = sharedPreferences.getString("dev_token", "");
        return  loggedin;
    }


    public  String  GetMobileNo(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(
                PREF_NAME, Context.MODE_PRIVATE);
        String mobiNo = sharedPreferences.getString("user_mobile", "");
        return  mobiNo;
    }


    public void  clearPreferences(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(
                PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor prefsEditor = sharedPreferences.edit();
        prefsEditor.clear();
        prefsEditor.commit();
    }



    @Override
    public String toString() {
        return "SessionParam [name=" + "]";
    }


    public boolean isNetworkAvailable(Context context) {
        ConnectivityManager connectivityManager
                = (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

//public String UsrId(Context context)
//{
//    SharedPreferences sharedPreferences = context.getSharedPreferences(
//            PREF_NAME, Context.MODE_PRIVATE);
//   usr_id = sharedPreferences.getString("usr_id", "");
//    return usr_id;
//}
//    public String UsrNm(Context context)
//    {
//        SharedPreferences sharedPreferences = context.getSharedPreferences(
//                PREF_NAME, Context.MODE_PRIVATE);
//         fname = sharedPreferences.getString("fname", "");
//        return fname;
//    }
//    public String UsrEmail(Context context)
//    {
//        SharedPreferences sharedPreferences = context.getSharedPreferences(
//                PREF_NAME, Context.MODE_PRIVATE);
//        usr_email = sharedPreferences.getString("usr_email", "");
//        return usr_email;
//    }

}
