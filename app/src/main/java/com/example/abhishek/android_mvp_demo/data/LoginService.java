package com.example.abhishek.android_mvp_demo.data;

/**
 * Created by abhishek on 1/16/17.
 * Dummy Login Service class
 */

public class LoginService {

    public LoginService(){}

    public boolean authenticate(String username, String password) {
        return "admin".equals(username) && "demo".equals(password);
    }

}
