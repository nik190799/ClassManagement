package com.creedeon.creed003.model;

public class Circular{

    private String file_name;
    private String file_path;
    private String postId;
    private String date;


    public Circular() {

    }

    public Circular(String file_name, String file_path, String postId, String date) {
        this.file_name = file_name;
        this.file_path = file_path;
        this.postId = postId;
        this.date = date;
    }

    public String getFile_name() {
        return file_name;
    }

    public String getFile_path() {
        return file_path;
    }

    public String getPostId() {
        return postId;
    }

    public String getDate() {
        return date;
    }
}
