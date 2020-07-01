package com.creedeon.creed003.model;

public class Student {

    private String sid;
    private String spass;
    private String classes;
    private String sname;

    public Student(){

    }

    public Student(String sid, String spass, String classes, String sname) {
        this.sid = sid;
        this.spass = spass;
        this.classes = classes;
        this.sname = sname;
    }

    public String getSid() {
        return sid;
    }

    public String getSpass() {
        return spass;
    }

    public String getClasses() {
        return classes;
    }

    public String getSname() {
        return sname;
    }
}
