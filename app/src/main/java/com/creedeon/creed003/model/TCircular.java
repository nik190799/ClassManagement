package com.creedeon.creed003.model;

public class TCircular{

    private String cname;
    private String postId;
    private String date;

    public TCircular(){
    }

    public TCircular(String cname, String postId, String date) {
        this.cname = cname;
        this.postId = postId;
        this.date = date;
    }

    public String getCname() {
        return cname;
    }

    public String getPostId() {
        return postId;
    }

    public String getDate() {
        return date;
    }
}
