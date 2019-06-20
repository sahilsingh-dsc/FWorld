package com.example.fwprld.ui.activities;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fwprld.R;
import com.example.fwprld.adapters.ChatAdapter;
import com.example.fwprld.adapters.MessagesAdapter;
import com.example.fwprld.models.ChatMessage;
import com.example.fwprld.models.ChatModel;
import com.google.firebase.auth.FirebaseAuth;
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
    private EditText etMessage;
    private ImageView ivSend;

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
        etMessage = findViewById(R.id.editText2);
        ivSend = findViewById(R.id.imageView15);


        txtChatSenderName.setText(chat_sender_name);

        getAllChat();
        ivSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                if(TextUtils.isEmpty(etMessage.getText().toString()))
                {
                    Toast.makeText(ChatActivity.this, "Please pull text", Toast.LENGTH_SHORT).show();
                }
                else {
                    SendMessage();
                }
            }
        });

    }

    private void getAllChat(){

        recyclerChat= (RecyclerView) findViewById(R.id.recyclerChat);
        recyclerChat.setHasFixedSize(true);
        recyclerChat.setLayoutManager(new LinearLayoutManager(ChatActivity.this));

        final DatabaseReference chatRef = FirebaseDatabase.getInstance().getReference("CHAT_DATA");//14
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
    public void SendMessage()
    {

        // Read the input field and push a new instance
        // of ChatMessage to the Firebase database
//        FirebaseDatabase.getInstance()
//                .getReference()
//                .push()
//                .setValue(new ChatMessage(etMessage.getText().toString(),
//                        FirebaseAuth.getInstance()
//                                .getCurrentUser()
//                                .getDisplayName())
//                );

        etMessage.setText("");

    }

}
