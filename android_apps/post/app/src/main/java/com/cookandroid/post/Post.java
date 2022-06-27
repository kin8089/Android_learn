package com.cookandroid.post;

public class Post {

    private String userName;
    private String emailAddress;

    public Post(String userName, String emailAddress) {
        this.userName = userName;
        this.emailAddress = emailAddress;
    }

    public String getUserName() {
        return userName;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

}

