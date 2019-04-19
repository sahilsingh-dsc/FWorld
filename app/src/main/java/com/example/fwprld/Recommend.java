package com.example.fwprld;

public class Recommend   {
    private int id;
    private String Title;
    private String SingerName;
    private int image;

    public Recommend(int id, String title,String singername, int image) {
        this.id = id;
        this.Title=title;
        this.SingerName = singername;
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return Title;
    }

    public String getSingerName() {
        return SingerName;
    }

    public int getImage() {
        return image;
    }
}