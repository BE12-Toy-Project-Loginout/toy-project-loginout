package com.fastcampus.shop.dao;

import com.fastcampus.shop.dto.User;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;

@Mapper
public interface UserMapper {
    String findById(String userId);
    User getUserByCredentials(User user);
    void updateLastLogin(String userLoginId);
    int getLoginFailCount(String userLoginId);
    void incrementLoginFailCount(String userLoginId);
    void resetLoginFailCount(String userLoginId);
    void isUserLock(User user);
    List<User> getLockedUsers();
}
