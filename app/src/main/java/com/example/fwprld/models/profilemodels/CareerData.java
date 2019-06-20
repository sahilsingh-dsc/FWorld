package com.example.fwprld.models.profilemodels;

public class CareerData {

    private String position,company_name, join_date, leave_date;

    public CareerData(String position,String company_name, String join_date, String leave_date) {
        this.position = position;
        this.company_name = company_name;
        this.join_date = join_date;
        this.leave_date = leave_date;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getCompany_name() {
        return company_name;
    }

    public void setCompany_name(String company_name) {
        this.company_name = company_name;
    }

    public String getJoin_date() {
        return join_date;
    }

    public void setjoin_date(String join_date) {
        this.join_date = join_date;
    }

    public String getLeave_date() {
        return leave_date;
    }

    public void setLeave_date(String leave_date) {
        this.leave_date = leave_date;
    }

}
