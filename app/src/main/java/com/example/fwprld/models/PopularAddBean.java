package com.example.fwprld.models;

public class PopularAddBean {
    public String user_id,user_qunic;
    public PopularAddBean(String user_id_,String user_qunic_)
    {
        user_id=user_id_;
        user_qunic=user_qunic_;

    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getUser_qunic() {
        return user_qunic;
    }

    public void setUser_qunic(String user_qunic) {
        this.user_qunic = user_qunic;
    }
}
