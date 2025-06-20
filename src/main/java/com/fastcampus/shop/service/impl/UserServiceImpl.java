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

    /**
     * 사용자 인증을 수행하는 메소드
     * 사용자 자격 증명을 확인하고 로그인 실패 횟수를 초기화합니다.
     */
    @Override
    public User validateUser(User user) throws Exception {
        User validatedUser = userMapper.getUserByCredentials(user);
        if (validatedUser != null) {
            userMapper.resetLoginFailCount(user.getUserLoginId());
            return validatedUser;
        }
        return null;
    }

    /**
     * 사용자의 마지막 로그인 시간을 업데이트하는 메소드
     * 로그인 성공 시 사용자의 마지막 로그인 시간을 현재 시간으로 갱신합니다.
     */
    @Override
    public void updateLastLogin(String userLoginId) throws Exception {
        userMapper.updateLastLogin(userLoginId);
    }

    /**
     * 사용자 로그아웃을 처리하는 메소드
     * 현재 HTTP 세션을 무효화하여 사용자를 로그아웃 상태로 만듭니다.
     */
    @Override
    public void logout() throws Exception {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
    }

    /**
     * 사용자의 로그인 실패 횟수를 조회하는 메소드
     * 특정 사용자의 로그인 실패 횟수를 데이터베이스에서 가져옵니다.
     */
    @Override
    public int getLoginFailCount(User user) throws Exception {
        return userMapper.getLoginFailCount(user.getUserLoginId());
    }

    /**
     * 사용자의 로그인 실패 횟수를 증가시키는 메소드
     * 로그인 실패 시 해당 사용자의 실패 횟수를 1 증가시킵니다.
     */
    @Override
    public void incrementLoginFailCount(String userLoginId) throws Exception {
        userMapper.incrementLoginFailCount(userLoginId);
    }

    @Override
    public void isLocked(User user) throws Exception {
        userMapper.isUserLock(user);
    }

    @Override
    public String getUserStatus(User user) throws Exception {
        User loginUser = userMapper.getUserByCredentials(user);
        return loginUser.getStatusType();
    }


}
