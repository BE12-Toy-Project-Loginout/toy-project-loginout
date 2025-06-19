package com.fastcampus.shop.dto;

import java.util.Date;

public class User {
    private String userLoginId;
    private String userPassword;
    private Date lastLogin;
    private int loginFailCount;

    public User (){}
    public User (String userLoginId, String userPassword){
        this.userLoginId = userLoginId;
        this.userPassword = userPassword;
    }
    public String getUserLoginId() {
        return userLoginId;
    }
    public void setUserLoginId(String userLoginId) {
        this.userLoginId = userLoginId;
    }
    public String getUserPassword() {
        return userPassword;
    }
    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }
    public Date getLastLogin() {
        return lastLogin;
    }
    public void setLastLogin(Date lastLogin) {
        this.lastLogin = lastLogin;
    }

    public int getLoginFailCount() {
        return loginFailCount;
    }
    public void setLoginFailCount(int loginFailCount) {
        this.loginFailCount = loginFailCount;
    }

    public void incrementLoginFailCount() {
        this.loginFailCount++;
    }

    @Override
    public String toString() {
        return "User [userLoginId=" + userLoginId +"]";
    }

}
