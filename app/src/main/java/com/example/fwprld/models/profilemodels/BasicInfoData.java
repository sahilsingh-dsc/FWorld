package com.example.fwprld.models.profilemodels;

public class BasicInfoData {

    private String user_name, user_fid, user_gender, user_birthday, user_relationship_status, user_about, user_status
            , followers, followings, rank_record,bio,pop_id="1";


    public BasicInfoData(String user_name, String user_fid, String user_gender, String user_birthday, String user_relationship_status
            , String user_about, String user_status, String usr_follower, String user_following,
                         String user_rank_record, String bio_) {
        this.user_name = user_name;
        this.user_fid = user_fid;
        this.user_gender = user_gender;
        this.user_birthday = user_birthday;
        this.user_relationship_status = user_relationship_status;
        this.user_about = user_about;
        this.user_status = user_status;

        this.followers = usr_follower;
        this.followings = user_following;
        this.rank_record = user_rank_record;
        this.bio = bio_;
        pop_id="1";

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

    public String getFollowers() {
        return followers;
    }

    public void setFollowers(String followers) {
        this.followers = followers;
    }

    public String getFollowings() {
        return followings;
    }

    public void setFollowings(String followings) {
        this.followings = followings;
    }

    public String getRank_record() {
        return rank_record;
    }

    public void setRank_record(String rank_record) {
        this.rank_record = rank_record;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public String getPop_id() {
        return pop_id;
    }

    public void setPop_id(String pop_id) {
        this.pop_id = pop_id;
    }
}
