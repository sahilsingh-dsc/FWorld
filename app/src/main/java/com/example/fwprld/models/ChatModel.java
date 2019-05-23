package com.example.fwprld.models;

public class ChatModel {

    String sent_chat, received_chat;

    public ChatModel(String sent_chat, String received_chat) {
        this.sent_chat = sent_chat;
        this.received_chat = received_chat;
    }

    public String getSent_chat() {
        return sent_chat;
    }

    public void setSent_chat(String sent_chat) {
        this.sent_chat = sent_chat;
    }

    public String getReceived_chat() {
        return received_chat;
    }

    public void setReceived_chat(String received_chat) {
        this.received_chat = received_chat;
    }
}
