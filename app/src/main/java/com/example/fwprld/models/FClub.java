package com.example.fwprld.models;

public class FClub {
    private int id;
    private String UserName;
    private String UserView;
    private String UserSing;
    private String UserGroupe;
    private int image;

    public FClub(int id, String username,String userview,String userSing,String userGroupe,int image) {
        this.id = id;
        this.UserName=username;
        this.UserView= userview;
        this.UserSing = userSing;
        this.UserGroupe = userGroupe;
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public String getUserName() {
        return UserName;
    }

    public String getUserView() {
        return UserView;
    }

    public String getUserSing() {
        return UserSing;
    }

    public String getUserGroupe() {
        return UserGroupe;
    }

    public int getImage() {
        return image;
    }
}