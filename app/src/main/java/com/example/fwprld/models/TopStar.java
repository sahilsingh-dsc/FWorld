package com.example.fwprld.models;

public class TopStar {
    private int id;
    private String Name;
    private int Picture;

    public TopStar(int id, String name, int picture) {
        this.id = id;
        this.Name = name;
        this.Picture = picture;
    }


    public int getId() {
        return id;
    }

    public String getName() {
        return Name;
    }

    public int getPicture() {
        return Picture;
    }
}
