package com.example.fwprld.common;

import android.content.Context;
import android.content.SharedPreferences;

import org.json.JSONObject;


public class SessionParam {
    String PREF_NAME = "fworld";

public  String usr_id,fname,usr_email,usr_city,usr_mobi;

      public SessionParam(JSONObject jsonObject) {
        if (jsonObject != null) {
            usr_id = jsonObject.optString("id", "");
            fname = jsonObject.optString("name", "");
            usr_email = jsonObject.optString("emailid", "");
            usr_city = jsonObject.optString("city", "");
            usr_mobi = jsonObject.optString("mobile", "");

//            usr_id = jsonObject.optString("usr_id", "");
//            fname = jsonObject.optString("fname", "");
//            usr_email = jsonObject.optString("usr_email", "");
        }
    }

    public SessionParam(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(
                PREF_NAME, Context.MODE_PRIVATE);
        this.usr_id = sharedPreferences.getString("usr_id", "");
        this.fname = sharedPreferences.getString("fname", "");
        this.usr_email = sharedPreferences.getString("usr_email", "");

        this.usr_city = sharedPreferences.getString("city", "");
        this.usr_mobi = sharedPreferences.getString("mobile", "");
          }

    public void persist(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(
                PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor prefsEditor = sharedPreferences.edit();
        prefsEditor.putString("usr_id", usr_id);
        prefsEditor.putString("fname", fname);
        prefsEditor.putString("usr_email", usr_email);

        prefsEditor.putString("city", usr_city);
        prefsEditor.putString("mobile", usr_mobi);
        prefsEditor.commit();

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
