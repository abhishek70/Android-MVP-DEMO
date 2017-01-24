package com.example.abhishek.android_mvp_demo.data;

/**
 * Created by abhishek on 1/16/17.
 */

public class User {

    private String mUsername,mPassword;

    public User(String username, String password) {
        mUsername = username;
        mPassword = password;
    }

    public void setUsername(String username) {
        mUsername = username;
    }

    public void setPassword(String password) {
        mPassword = password;
    }

    public String getUsername() {
        return mUsername;
    }

    public String getPassword() {
        return mPassword;
    }
}
