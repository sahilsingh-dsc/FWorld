package com.example.fwprld.ui.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.fwprld.R;
import com.example.fwprld.adapters.NotiAdapter;
import com.example.fwprld.common.Constant;
import com.example.fwprld.common.LoginSession;
import com.example.fwprld.common.RequestQueueService;
import com.example.fwprld.models.NotificaitonBean;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NotificationFragmentNew extends Fragment implements View.OnClickListener {

    View view;
    TextView tvBack,tvSendNoti,tvNoData;
    RecyclerView rvNoti;
    List<NotificaitonBean> listNoti;
    Context context;
    LoginSession  sessionParam;
    public NotificationFragmentNew() {}

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.frag_notification, container, false);
        context=getContext();
        sessionParam = new LoginSession(context);
        Log.e("","usr_mobi= "+sessionParam.usr_mobi+" fname="+sessionParam.fname+" usr_id="+sessionParam.usr_id+" usr_pic= "+sessionParam.usr_pic);
            initCompo();
            Listener();
            getData();

        return view;
    }


    private void getData() {
        if(!TextUtils.isEmpty(sessionParam.usr_id)) {
            DatabaseReference userList = FirebaseDatabase.getInstance().getReference("NOTIFICATION");
            userList.child(sessionParam.usr_id).addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    listNoti.clear();
                    try {
//                    Log.e("","dataSnapshot= "+dataSnapshot.toString());
                        NotificaitonBean bean;
                        for (DataSnapshot ftalSnap : dataSnapshot.getChildren()) {
                            String notiTitle = (String) ftalSnap.child("notiTitle").getValue();
                            String notiMsg = (String) ftalSnap.child("notiMsg").getValue();
                            String notiImg = (String) ftalSnap.child("notiImg").getValue();
                              bean=new NotificaitonBean();
                              bean.setTitle(notiTitle);
                              bean.setMsg(notiMsg);
                              bean.setImgUrl(notiImg);
                        listNoti.add(bean);


                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                        Log.e("", "" + e);
                    }

                    NotiAdapter adapter = new NotiAdapter(getContext(), listNoti);
                    rvNoti.setAdapter(adapter);

                    if(listNoti.size()>0)
                    {
                        tvNoData.setVisibility(View.GONE);
                    }
                    else
                    {
                        tvNoData.setVisibility(View.VISIBLE);
                    }

                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
        }
        else
        {
            Toast.makeText(context, "not logged in user", Toast.LENGTH_SHORT).show();
        }
    }

    private void Listener() {
        tvBack.setOnClickListener(this);
        tvSendNoti.setOnClickListener(this);
    }

    private void initCompo() {
        tvBack = view.findViewById(R.id.tv_back);
        tvNoData = view.findViewById(R.id.tv_no_data);
        tvSendNoti = view.findViewById(R.id.tv_sent_noti);
        rvNoti=(RecyclerView)view.findViewById(R.id.rv_notification);
        rvNoti.setHasFixedSize(true);
        rvNoti.setLayoutManager(new LinearLayoutManager(getContext()));
        listNoti = new ArrayList<>();
    }

    @Override
    public void onClick(View v) {
switch (v.getId())
{
    case R.id.tv_back:
        getFragmentManager().popBackStack();
        break;
        case R.id.tv_sent_noti:
            if(new LoginSession(getActivity()).isNetworkAvailable(getActivity())) {

                SendNoti();
            }
            else {
                Toast.makeText(getContext(), "Please check your network connection", Toast.LENGTH_SHORT).show();
            }
//            Toast.makeText(getContext(), "send noti", Toast.LENGTH_SHORT).show();
        break;
}

    }

    public void SendNoti()
    {
        JSONObject jsObj=new JSONObject();
        JSONObject jsObjdata=new JSONObject();
        try {
            jsObjdata.put("title","Notification2");
            jsObjdata.put("message","Say hello2");
            jsObjdata.put("image_url","https://docs.centroida.co/wp-content/uploads/2017/05/notification.png");


//            jsObj.put("to","dq6bDVO-MDg:APA91bGnjHOLElpPW85ty3tZApN3nwQluOdBXM6gWQBIzAAnAfC96AgRUDv49XSXy1KUL14uJwkpXpbEnunmP579X74hrSmAvrZ7C9kATma59QeJ6Nl4_TuXgOQHkSZhp9rKhXaFzC1F");
            jsObj.put("to",new LoginSession(getActivity()).GetToken(getActivity()));
            jsObj.put("data",jsObjdata);

Log.e("","entput data"+jsObjdata.toString());

        } catch (JSONException e) {
            e.printStackTrace();
        }

        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.POST,
                Constant.NOTI_URL, jsObj,
                new Response.Listener() {
                    @Override
                    public void onResponse(Object response) {
                        Log.e("","success= "+response.toString()+""+((JSONObject) response).toString());
                        Toast.makeText(getContext(), "send noti", Toast.LENGTH_SHORT).show();
                    }

                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("","error= "+error);
                    }
                })
        {
            /** Passing some request headers* */
            @Override
            public Map getHeaders() throws AuthFailureError {
                HashMap headers = new HashMap();
                headers.put("Content-Type", "application/json");
                headers.put("Authorization", "key=AAAAEdK7HHQ:APA91bEuCxSNap8wTa_W3dZsy9TcLcA7DtklS8xp1K-sykmVilIxV57DZb-zBSryqdHUoFJXjDL8_QHux0LpaDlnHKFAlvZQkyr88vM4bslDF7fveEfs_9fyeJVZyCwGZE2vEc5ELyR8");
                return headers;
            }
        };
// Adding the request to the queue along with a unique string tag
//        MyApplication.getInstance().addToRequestQueue(jsonObjReq, "headerRequest");
        RequestQueueService.getInstance(context).addToRequestQueue(jsonObjReq.setShouldCache(false));
    }
}
