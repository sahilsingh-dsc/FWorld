package com.example.fwprld.models;

public class FTalent  {

    private String ftal_user;
    private String song_id;
    private String song_image;
    private String song_name;
    private String song_plays;
    private String song_singer;
    private String song_playtime;
    private String singer_name;
    private String singer_image;

    public FTalent(String ftal_user, String song_id, String song_image, String song_name, String song_plays, String song_singer, String song_playtime, String singer_name, String singer_image) {
        this.ftal_user = ftal_user;
        this.song_id = song_id;
        this.song_image = song_image;
        this.song_name = song_name;
        this.song_plays = song_plays;
        this.song_singer = song_singer;
        this.song_playtime = song_playtime;
        this.singer_name = singer_name;
        this.singer_image = singer_image;
    }

    public String getFtal_user() {
        return ftal_user;
    }

    public void setFtal_user(String ftal_user) {
        this.ftal_user = ftal_user;
    }

    public String getSong_id() {
        return song_id;
    }

    public void setSong_id(String song_id) {
        this.song_id = song_id;
    }

    public String getSong_image() {
        return song_image;
    }

    public void setSong_image(String song_image) {
        this.song_image = song_image;
    }

    public String getSong_name() {
        return song_name;
    }

    public void setSong_name(String song_name) {
        this.song_name = song_name;
    }

    public String getSong_plays() {
        return song_plays;
    }

    public void setSong_plays(String song_plays) {
        this.song_plays = song_plays;
    }

    public String getSong_singer() {
        return song_singer;
    }

    public void setSong_singer(String song_singer) {
        this.song_singer = song_singer;
    }

    public String getSong_playtime() {
        return song_playtime;
    }

    public void setSong_playtime(String song_playtime) {
        this.song_playtime = song_playtime;
    }

    public String getSinger_name() {
        return singer_name;
    }

    public void setSinger_name(String singer_name) {
        this.singer_name = singer_name;
    }

    public String getSinger_image() {
        return singer_image;
    }

    public void setSinger_image(String singer_image) {
        this.singer_image = singer_image;
    }
}