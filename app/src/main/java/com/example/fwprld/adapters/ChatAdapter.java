package com.example.fwprld.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.fwprld.R;
import com.example.fwprld.models.ChatModel;

import java.util.List;

public class ChatAdapter extends RecyclerView.Adapter<ChatAdapter.ChatViewHolder> {
    List<ChatModel> chatModelList;
    Context context;

    public ChatAdapter(List<ChatModel> chatModelList, Context context) {
        this.chatModelList = chatModelList;
        this.context = context;
    }

    @NonNull
    @Override
    public ChatAdapter.ChatViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.chat_item_list, viewGroup, false);
        return new ChatViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ChatAdapter.ChatViewHolder chatViewHolder, int i) {

        ChatModel chatModel = chatModelList.get(i);
        chatViewHolder.txtReceivedMessage.setText(chatModel.getReceived_chat());
        chatViewHolder.txtReceivedSent.setText(chatModel.getSent_chat());

    }

    @Override
    public int getItemCount() {
        return chatModelList.size();
    }

    public class ChatViewHolder extends RecyclerView.ViewHolder {

        TextView txtReceivedMessage, txtReceivedSent;

        public ChatViewHolder(@NonNull View itemView) {
            super(itemView);

            txtReceivedMessage = itemView.findViewById(R.id.txtReceivedMessage);
            txtReceivedSent = itemView.findViewById(R.id.txtReceivedSent);

        }
    }
}
