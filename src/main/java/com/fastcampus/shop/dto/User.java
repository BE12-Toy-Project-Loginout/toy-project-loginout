package com.fastcampus.shop.dto;

import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class User {
    private String userLoginId;
    private String userPassword;
    private Date lastLogin;
    private int loginFailCount;
    private String userName;
    private boolean isLock;
    public User(String userLoginId, String userPassword) {
        this.userLoginId = userLoginId;
        this.userPassword = userPassword;
    }

    public void incrementLoginFailCount() {
        this.loginFailCount++;
    }
}
