package com.fastcampus.shop.dao;

import com.fastcampus.shop.dto.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {
    String findById(String userId);
    boolean validateUser(User user);
    void updateLastLogin(String userLoginId);
}
