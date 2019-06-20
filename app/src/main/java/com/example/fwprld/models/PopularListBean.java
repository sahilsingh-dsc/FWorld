package com.example.fwprld.models;

public class PopularListBean {
    public String user_id,user_name,userpic,popId;
    public PopularListBean(String user_id_, String user_name_,String user_pic_,String pop_id_)
    {
        user_id=user_id_;
        user_name=user_name_;
        userpic=user_pic_;
        popId=pop_id_;

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

    public String getUserpic() {
        return userpic;
    }

    public void setUserpic(String userpic) {
        this.userpic = userpic;
    }

    public String getPopId() {
        return popId;
    }

    public void setPopId(String popId) {
        this.popId = popId;
    }
}
