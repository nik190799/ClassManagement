package com.creedeon.creed003.model;

public class Homework {
    private String cname;
    private String tname;
    private String postId;
    private String sclass;
    private String date;
    private String teacher;



    public Homework() {
    }

    public Homework(String name, String tname, String postId, String date, String sclass, String teacher) {

        this.cname = name;
        this.tname = tname;
        this.postId  = postId;
        this.sclass = sclass;
        this.date = date;
        this.teacher = teacher;

    }

    public String getCname() {
        return cname;
    }

    public String getTname() {
        return tname;
    }

    public String getPostId() {
        return postId;
    }

    public String getDate() {
        return date;
    }

    public String getSclass(){
        return  sclass;
    }
    public String getTeacher() {
        return teacher;
    }
}
