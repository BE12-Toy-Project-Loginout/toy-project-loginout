package com.fastcampus.shop.service.impl;

import com.fastcampus.shop.dao.UserMapper;
import com.fastcampus.shop.dto.User;
import com.fastcampus.shop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Service("userService")
public class UserServiceImpl implements UserService {

    private final UserMapper userMapper;

    public UserServiceImpl(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Override
    public boolean validateUser(User user) throws Exception {
        Integer count = userMapper.validateUser(user);
        if (count == null|| count == 0) {
            return false;
        }

        userMapper.resetLoginFailCount(user.getUserLoginId());

        return true;
    }

    @Override
    public void updateLastLogin(String userLoginId) throws Exception {
        userMapper.updateLastLogin(userLoginId);
    }

    @Override
    public void logout() throws Exception {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
    }

    @Override
    public int getLoginFailCount(User user) throws Exception {
        return userMapper.getLoginFailCount(user.getUserLoginId());
    }

    @Override
    public void incrementLoginFailCount(String userLoginId) throws Exception {
        userMapper.incrementLoginFailCount(userLoginId);

    }


}
