package com.example.xiedongdong.app02.po;

import cn.bmob.v3.BmobObject;

/**
 * Created by xiedongdong on 16/6/1.
 */
public class User extends BmobObject {
    private String username;
    private String password;
    private String email;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
