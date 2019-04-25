package com.example.fwprld.models;

public class FTalent  {
    private int id;
    private String UserName;
    private String SongName;
    private String Playtime;
    private int image;

    public FTalent(int id, String username,String songname,String playtime,int image) {
        this.id = id;
        this.UserName=username;
        this.SongName= songname;
        this.Playtime = playtime;
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public String getUserName() {
        return UserName;
    }

    public String getSongName() {
        return SongName;
    }

    public String getPlaytime() {
        return Playtime;
    }

    public int getImage() {
        return image;
    }
}