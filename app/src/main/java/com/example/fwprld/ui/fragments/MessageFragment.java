package com.example.fwprld.ui.fragments;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.fwprld.R;
import com.example.fwprld.adapters.MessagesAdapter;
import com.example.fwprld.models.profilemodels.UserMessages;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MessageFragment extends Fragment {


    View view;
    List<UserMessages> userMessagesList;
    RecyclerView recyclerMessage;
    private String sender_image, sender_name, message_received, sending_time;
    DatabaseReference messageRef;


    public MessageFragment() {
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_message, container, false);

        messageRef = FirebaseDatabase.getInstance().getReference("MESSAGE");

        userMessagesList = new ArrayList<>();
        userMessagesList.clear();

        getAllMessages();

        return view;
    }

    private void getAllMessages(){

        recyclerMessage= (RecyclerView) view.findViewById(R.id.recyclerMessage);
        recyclerMessage.setHasFixedSize(true);
        recyclerMessage.setLayoutManager(new LinearLayoutManager(getContext()));

        messageRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                userMessagesList.clear();
                for (DataSnapshot messageSnap : dataSnapshot.getChildren()){

                    sender_image = (String) messageSnap.child("sender_image").getValue();
                    sender_name = (String) messageSnap.child("sender_name").getValue();
                    message_received = (String) messageSnap.child("message_received").getValue();
                    sending_time = (String) messageSnap.child("sending_time").getValue();
                    UserMessages userMessages = new UserMessages(sender_image, sender_name, message_received ,sending_time);
                    userMessagesList.add(userMessages);
                }
                MessagesAdapter messagesAdapter = new MessagesAdapter(userMessagesList ,getContext());
                recyclerMessage.setAdapter(messagesAdapter);


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });



    }

}
