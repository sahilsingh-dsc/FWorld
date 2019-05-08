package com.example.fwprld.models;

public class Recommend   {
    private String recommended_song_id;
    private String recommended_song_image;
    private String recommended_song_name;
    private String recommended_song_singer;
    private String recommended_song_url;

    public Recommend(String recommended_song_id, String recommended_song_image, String recommended_song_name, String recommended_song_singer, String recommended_song_url) {
        this.recommended_song_id = recommended_song_id;
        this.recommended_song_image = recommended_song_image;
        this.recommended_song_name = recommended_song_name;
        this.recommended_song_singer = recommended_song_singer;
        this.recommended_song_url = recommended_song_url;
    }


    public String getRecommended_song_id() {
        return recommended_song_id;
    }

    public void setRecommended_song_id(String recommended_song_id) {
        this.recommended_song_id = recommended_song_id;
    }

    public String getRecommended_song_image() {
        return recommended_song_image;
    }

    public void setRecommended_song_image(String recommended_song_image) {
        this.recommended_song_image = recommended_song_image;
    }

    public String getRecommended_song_name() {
        return recommended_song_name;
    }

    public void setRecommended_song_name(String recommended_song_name) {
        this.recommended_song_name = recommended_song_name;
    }

    public String getRecommended_song_singer() {
        return recommended_song_singer;
    }

    public void setRecommended_song_singer(String recommended_song_singer) {
        this.recommended_song_singer = recommended_song_singer;
    }

    public String getRecommended_song_url() {
        return recommended_song_url;
    }

    public void setRecommended_song_url(String recommended_song_url) {
        this.recommended_song_url = recommended_song_url;
    }
}