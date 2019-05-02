package com.example.fwprld.models;

public class FClub {

    String room_id, room_name, room_song, room_image, room_current_viewers, room_queue;

    public FClub(String room_id, String room_name, String room_song, String room_image, String room_current_viewers, String room_queue) {
        this.room_id = room_id;
        this.room_name = room_name;
        this.room_song = room_song;
        this.room_image = room_image;
        this.room_current_viewers = room_current_viewers;
        this.room_queue = room_queue;
    }

    public String getRoom_id() {
        return room_id;
    }

    public String getRoom_name() {
        return room_name;
    }

    public String getRoom_song() {
        return room_song;
    }

    public String getRoom_image() {
        return room_image;
    }

    public String getRoom_current_viewers() {
        return room_current_viewers;
    }

    public String getRoom_queue() {
        return room_queue;
    }
}