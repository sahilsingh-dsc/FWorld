package com.example.fwprld.models;

public class SongBean {
    String song_id,song_image,song_name,song_like,song_plays,song_playtime,song_singer,songby_image,songby_name,user_id,user_name,user_pic;

   public SongBean(String song_id_,String song_image_,String song_name_,String song_like_,String song_plays_,String song_playtime_
            ,String song_singer_,String songby_image_,String songby_name_,String user_id_,String user_name_,String user_pic_)
    {
        song_id=song_id_;
        song_image=song_image_;
        song_name=song_name_;
        song_like=song_like_;
        song_plays=song_plays_;
        song_playtime=song_playtime_;
        song_singer=song_singer_;
        songby_image=songby_image_;
        songby_name=songby_name_;
        user_id=user_id_;
        user_name=user_name_;
        user_pic=user_pic_;
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

    public String getSong_like() {
        return song_like;
    }

    public void setSong_like(String song_like) {
        this.song_like = song_like;
    }

    public String getSong_plays() {
        return song_plays;
    }

    public void setSong_plays(String song_plays) {
        this.song_plays = song_plays;
    }

    public String getSong_playtime() {
        return song_playtime;
    }

    public void setSong_playtime(String song_playtime) {
        this.song_playtime = song_playtime;
    }

    public String getSong_singer() {
        return song_singer;
    }

    public void setSong_singer(String song_singer) {
        this.song_singer = song_singer;
    }

    public String getSongby_image() {
        return songby_image;
    }

    public void setSongby_image(String songby_image) {
        this.songby_image = songby_image;
    }

    public String getSongby_name() {
        return songby_name;
    }

    public void setSongby_name(String songby_name) {
        this.songby_name = songby_name;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getUser_pic() {
        return user_pic;
    }

    public void setUser_pic(String user_pic) {
        this.user_pic = user_pic;
    }
}
