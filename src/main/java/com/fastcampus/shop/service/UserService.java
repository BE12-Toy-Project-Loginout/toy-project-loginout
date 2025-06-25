package com.fastcampus.shop.service;


import com.fastcampus.shop.dto.User;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

public interface UserService {

     User validateUser(User user) throws Exception;

     void updateLastLogin(String userLoginId) throws Exception;

     void logout() throws Exception;

     int getLoginFailCount(User user) throws Exception;

    void incrementLoginFailCount(String userLoginId) throws Exception;

    void isLocked(User user) throws Exception;

    String getUserStatus(User user) throws Exception;

    boolean setUserAttributesInModel(HttpServletRequest request, Model model);

    Map<String, Object> processLogin(User user, HttpServletRequest request);

    boolean isAdmin(HttpServletRequest request);

    List<User> getLockedUsers();

    boolean unlockUser(String userLoginId);
}
