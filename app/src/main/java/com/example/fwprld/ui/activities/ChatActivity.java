package com.example.fwprld.ui.activities;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.example.fwprld.R;
import com.example.fwprld.adapters.ChatAdapter;
import com.example.fwprld.adapters.MessagesAdapter;
import com.example.fwprld.models.ChatModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ChatActivity extends AppCompatActivity {

    private TextView txtChatSenderName;
    private RecyclerView recyclerChat;
    private List<ChatModel> chatModelList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        chatModelList = new ArrayList<>();
        chatModelList.clear();

        Bundle chatBundle = getIntent().getExtras();
        assert chatBundle != null;
        String chat_sender_name = chatBundle.getString("chat_sender_name");

        txtChatSenderName = findViewById(R.id.txtChatSenderName);
        txtChatSenderName.setText(chat_sender_name);

        getAllChat();





    }

    private void getAllChat(){

        recyclerChat= (RecyclerView) findViewById(R.id.recyclerChat);
        recyclerChat.setHasFixedSize(true);
        recyclerChat.setLayoutManager(new LinearLayoutManager(ChatActivity.this));

        final DatabaseReference chatRef = FirebaseDatabase.getInstance().getReference("CHAT_DATA");
        chatRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                chatModelList.clear();
                for (DataSnapshot chatSnap : dataSnapshot.getChildren()){

                    String received_chat = (String) chatSnap.child("received_chat").getValue();
                    String sent_chat = (String) chatSnap.child("sent_chat").getValue();

                    ChatModel chatModel = new ChatModel(sent_chat, received_chat);
                    chatModelList.add(chatModel);

                }

                ChatAdapter chatAdapter = new ChatAdapter(chatModelList , ChatActivity.this);
                recyclerChat.setAdapter(chatAdapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

}
