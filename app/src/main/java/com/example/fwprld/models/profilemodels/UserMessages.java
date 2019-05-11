package com.example.fwprld.models.profilemodels;

public class UserMessages {

    String sender_image, sender_name, message_received, sending_time;



    public UserMessages(String sender_image, String sender_name, String message_received, String sending_time) {
        this.sender_image = sender_image;
        this.sender_name = sender_name;
        this.message_received = message_received;
        this.sending_time = sending_time;
    }

    public String getSender_image() {
        return sender_image;
    }

    public void setSender_image(String sender_image) {
        this.sender_image = sender_image;
    }

    public String getSender_name() {
        return sender_name;
    }

    public void setSender_name(String sender_name) {
        this.sender_name = sender_name;
    }

    public String getMessage_received() {
        return message_received;
    }

    public void setMessage_received(String message_received) {
        this.message_received = message_received;
    }

    public String getSending_time() {
        return sending_time;
    }

    public void setSending_time(String sending_time) {
        this.sending_time = sending_time;
    }
}
