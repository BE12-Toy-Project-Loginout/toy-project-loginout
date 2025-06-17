package com.fastcampus.shop.dto;

public class User {
    private String userLoginId;
    private String userPassword;

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
    @Override
    public String toString() {
        return "User [userLoginId=" + userLoginId +"]";
    }

}
