package com.example.fwprld.models.profilemodels;

public class BasicInfoData {

    private String user_avatar, user_wall, user_name, user_fid, user_gender, user_birthday, user_relationship_status, user_about, user_status;

    public BasicInfoData(String user_avatar, String user_wall, String user_name, String user_fid, String user_gender, String user_birthday, String user_relationship_status, String user_about, String user_status) {
        this.user_avatar = user_avatar;
        this.user_wall = user_wall;
        this.user_name = user_name;
        this.user_fid = user_fid;
        this.user_gender = user_gender;
        this.user_birthday = user_birthday;
        this.user_relationship_status = user_relationship_status;
        this.user_about = user_about;
        this.user_status = user_status;
    }

    public String getUser_avatar() {
        return user_avatar;
    }

    public void setUser_avatar(String user_avatar) {
        this.user_avatar = user_avatar;
    }

    public String getUser_wall() {
        return user_wall;
    }

    public void setUser_wall(String user_wall) {
        this.user_wall = user_wall;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getUser_fid() {
        return user_fid;
    }

    public void setUser_fid(String user_fid) {
        this.user_fid = user_fid;
    }

    public String getUser_gender() {
        return user_gender;
    }

    public void setUser_gender(String user_gender) {
        this.user_gender = user_gender;
    }

    public String getUser_birthday() {
        return user_birthday;
    }

    public void setUser_birthday(String user_birthday) {
        this.user_birthday = user_birthday;
    }

    public String getUser_relationship_status() {
        return user_relationship_status;
    }

    public void setUser_relationship_status(String user_relationship_status) {
        this.user_relationship_status = user_relationship_status;
    }

    public String getUser_about() {
        return user_about;
    }

    public void setUser_about(String user_about) {
        this.user_about = user_about;
    }

    public String getUser_status() {
        return user_status;
    }

    public void setUser_status(String user_status) {
        this.user_status = user_status;
    }
}
