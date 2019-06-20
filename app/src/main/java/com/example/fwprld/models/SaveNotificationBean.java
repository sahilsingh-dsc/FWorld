package com.example.fwprld.models;

public class SaveNotificationBean {
    String userid,userName,userPic,notiTitle,notiMsg,notiImg;
    public SaveNotificationBean(String userid_,String userName_,String userPic_,String notiTitle_,String notiMsg_,String notiImg_)
    {
      userid=userid_;
        userName=userName_;
        userPic=userPic_;
        notiTitle=notiTitle_;
        notiMsg=notiMsg_;
        notiImg=notiImg_;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPic() {
        return userPic;
    }

    public void setUserPic(String userPic) {
        this.userPic = userPic;
    }

    public String getNotiTitle() {
        return notiTitle;
    }

    public void setNotiTitle(String notiTitle) {
        this.notiTitle = notiTitle;
    }

    public String getNotiMsg() {
        return notiMsg;
    }

    public void setNotiMsg(String notiMsg) {
        this.notiMsg = notiMsg;
    }

    public String getNotiImg() {
        return notiImg;
    }

    public void setNotiImg(String notiImg) {
        this.notiImg = notiImg;
    }
}
