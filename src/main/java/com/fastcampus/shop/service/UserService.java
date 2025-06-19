package com.fastcampus.shop.service;


import com.fastcampus.shop.dto.User;
import org.springframework.stereotype.Service;

public interface UserService {

    public boolean validateUser(User user) throws Exception;

    public void updateLastLogin(String userLoginId) throws Exception;

    public void logout() throws Exception;

    public int getLoginFailCount(User user) throws Exception;

    void incrementLoginFailCount(String userLoginId) throws Exception;

}
