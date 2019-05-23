package com.example.fwprld.adapters;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.fwprld.R;
import com.example.fwprld.models.profilemodels.UserMessages;
import com.example.fwprld.ui.activities.ChatActivity;

import java.util.List;

public class MessagesAdapter extends RecyclerView.Adapter<MessagesAdapter.MessagesViewHolder> {

    private List<UserMessages> userMessagesList;
    private Context context;

    public MessagesAdapter(List<UserMessages> userMessagesList, Context context) {
        this.userMessagesList = userMessagesList;
        this.context = context;
    }

    @NonNull
    @Override
    public MessagesAdapter.MessagesViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.message_item_list, viewGroup, false);
        return new MessagesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MessagesAdapter.MessagesViewHolder messagesViewHolder, int i) {

        final UserMessages userMessages = userMessagesList.get(i);

        Glide.with(context).load(userMessages.getSender_image()).into(messagesViewHolder.imgSenderPhoto);
        messagesViewHolder.txtSenderName.setText(userMessages.getSender_name());
        messagesViewHolder.txtMessage.setText(userMessages.getMessage_received());
        messagesViewHolder.txtMessageRecTime.setText(userMessages.getSending_time());
        int message_count = userMessagesList.size();
        messagesViewHolder.txtMessageCount.setText(""+message_count);
        messagesViewHolder.constraintMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent chatIntent = new Intent(context, ChatActivity.class);
                Bundle chatBundle = new Bundle();
                chatBundle.putString("chat_sender_name", userMessages.getSender_name());
                chatIntent.putExtras(chatBundle);
                context.startActivity(chatIntent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return userMessagesList.size();
    }

    public class MessagesViewHolder extends RecyclerView.ViewHolder {

        ImageView imgSenderPhoto;
        TextView txtSenderName, txtMessage, txtMessageRecTime, txtMessageCount;
        ConstraintLayout constraintMessage;

        public MessagesViewHolder(@NonNull View itemView) {
            super(itemView);

            imgSenderPhoto = itemView.findViewById(R.id.imgSenderPhoto);
            txtSenderName = itemView.findViewById(R.id.txtSenderName);
            txtMessage = itemView.findViewById(R.id.txtMessage);
            txtMessageRecTime = itemView.findViewById(R.id.txtMessageRecTime);
            txtMessageCount = itemView.findViewById(R.id.txtMessageCount);
            constraintMessage = itemView.findViewById(R.id.constraintMessage);

        }
    }
}
