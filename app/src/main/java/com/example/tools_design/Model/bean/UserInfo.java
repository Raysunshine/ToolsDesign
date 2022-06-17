package com.example.tools_design.Model.bean;

import androidx.annotation.NonNull;

//用户信息的bean类
public class UserInfo {

    private String userName = "";
    private String nickName = userName;
    private String password = "";
    private Integer isOnline = 0;

    public UserInfo() {
    }

    public UserInfo(String userName, String password, int isOnline) {
        this.userName = userName;
        this.nickName = userName;
        this.password = password;
        this.isOnline = isOnline;
    }

    @NonNull
    @Override
    public String toString() {
        return "UserInfo{" +
                "userName='" + userName + '\'' +
                ", nickName='" + nickName + '\'' +
                ", password='" + password + '\'' +
                ", isOnline=" + isOnline +
                '}';
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getIsOnline() {
        return isOnline;
    }

    public void setIsOnline(Integer isOnline) {
        this.isOnline = isOnline;
    }
}
