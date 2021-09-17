package com.leon.estimate_new.tables;

/**
 * Created by Leon on 12/18/2017.
 */

public class LoginInfo {
    public final String username;
    public final String password;
    public final String deviceId;

    public LoginInfo(String device_id, String username, String password) {
        this.deviceId = device_id;
        this.username = username;
        this.password = password;
    }
}
