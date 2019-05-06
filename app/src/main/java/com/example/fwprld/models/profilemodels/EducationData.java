package com.example.fwprld.models.profilemodels;

public class EducationData {

    private String school_name, start_date, end_date;

    public EducationData(String school_name, String start_date, String end_date) {
        this.school_name = school_name;
        this.start_date = start_date;
        this.end_date = end_date;
    }

    public String getSchool_name() {
        return school_name;
    }

    public void setSchool_name(String school_name) {
        this.school_name = school_name;
    }

    public String getStart_date() {
        return start_date;
    }

    public void setStart_date(String start_date) {
        this.start_date = start_date;
    }

    public String getEnd_date() {
        return end_date;
    }

    public void setEnd_date(String end_date) {
        this.end_date = end_date;
    }

}
