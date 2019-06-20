package com.example.fwprld.models;

public class UserInfo {
    String userId,profilePic,userNm;

    public UserInfo(String user_name, String user_fid, String user_pic) {
        this.userNm = user_name;
        this.userId = user_fid;
        this.profilePic = user_pic;
    }


    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getProfilePic() {
        return profilePic;
    }

    public void setProfilePic(String profilePic) {
        this.profilePic = profilePic;
    }

    public String getUserNm() {
        return userNm;
    }

    public void setUserNm(String userNm) {
        this.userNm = userNm;
    }
}
