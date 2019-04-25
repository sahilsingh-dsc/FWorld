package com.example.fwprld.models;

public class Popular  {
    private int id;
    private String UserName;
    private int image;

    public Popular(int id,String username, int image) {
        this.id = id;
        this.UserName = username;
        this.image = image;
    }


    public int getId() {
        return id;
    }

    public String getUserName() {
        return UserName;
    }

    public int getImage() {
        return image;
    }
}