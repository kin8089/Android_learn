package com.cookandroid.post1;

public class Post {
    private int id;
    private String test;
    private String test_text;


    public int getId() {
        return id;
    }

    public String getTest(){
        return test;

    }

    public Post(String test_text) {
        this.test_text = test_text;
    }

    public String getTest_text() {
        return test_text;
    }
}
