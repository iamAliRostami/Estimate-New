package com.leon.estimate_new.tables;

/**
 * Created by Leon on 12/17/2017.
 */

public class LoginFeedBack {
    public String access_token;
    public String refresh_token;

    public LoginFeedBack(String access_token, String refresh_token) {
        this.access_token = access_token;
        this.refresh_token = refresh_token;
    }
}
