package com.fastcampus.shop.service.impl;

import com.fastcampus.shop.dao.UserMapper;
import com.fastcampus.shop.dto.User;
import com.fastcampus.shop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("userService")
public class UserServiceImpl implements UserService {

    private final UserMapper userMapper;

    public UserServiceImpl(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Override
    public boolean validateUser(User user) throws Exception {
        return userMapper.validateUser(user);
    }
}
