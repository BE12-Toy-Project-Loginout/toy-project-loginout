package com.fastcampus.shop.service.impl;

import com.fastcampus.shop.dao.UserMapper;
import com.fastcampus.shop.dto.User;
import com.fastcampus.shop.service.MemberService;
import com.fastcampus.shop.service.QMemberService;
import com.fastcampus.shop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("userService")
public class UserServiceImpl implements UserService {
    @Autowired
    private QMemberService memberService;

    private final UserMapper userMapper;

    public UserServiceImpl(UserMapper userMapper) {
        this.userMapper = userMapper;
    }


    @Override
    public User validateUser(User user) throws Exception {
        User validatedUser = userMapper.getUserByCredentials(user);
        if (validatedUser != null) {
            userMapper.resetLoginFailCount(validatedUser.getUserLoginId());
            return validatedUser;
        }
        return null;
    }


    @Override
    public void updateLastLogin(String userLoginId) throws Exception {
        userMapper.updateLastLogin(userLoginId);
    }


    @Override
    public void logout(HttpServletRequest request) throws Exception {
        //HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
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

    @Override
    public void isLocked(User user) throws Exception {
        userMapper.isUserLock(user.getUserLoginId());
    }

    @Override
    public String getUserStatus(User user) throws Exception {
        User loginUser = userMapper.getUserByCredentials(user);
        return loginUser.getStatusType();
    }


    @Override
    public boolean setUserAttributesInModel(HttpServletRequest request, Model model) {
        HttpSession session = request.getSession(false);
        if (session != null && session.getAttribute("userLoginId") != null) {
            model.addAttribute("isLoggedIn", true);
            String userName = (String) session.getAttribute("userName");
            model.addAttribute("userName", userName);
            String userStatus = (String) session.getAttribute("userStatus");
            boolean isAdmin = "ADMIN".equals(userStatus);
            model.addAttribute("isAdmin", isAdmin);
            return true;
        } else {
            model.addAttribute("isLoggedIn", false);
            model.addAttribute("isAdmin", false);
            return false;
        }
    }


    @Override
    public Map<String, Object> processLogin(User user, HttpServletRequest request) {
        Map<String, Object> response = new HashMap<>();

        User validatedUser = null;
        try {
            validatedUser = validateUser(user);

            if (validatedUser != null && !validatedUser.isLock()) {
                // 세션 생성
                HttpSession session = request.getSession(true);
                session.setAttribute("userName", validatedUser.getUserName());
                session.setAttribute("userLoginId", validatedUser.getUserLoginId());

                // ✅ [추가된 부분] memberId 조회 후 세션에 저장
                Long memberId = memberService.getMemberIdByLoginId(validatedUser.getUserLoginId());
                session.setAttribute("memberId", memberId.intValue()); // ✅ Integer로 저장


                String status = getUserStatus(validatedUser);
                session.setAttribute("userStatus", status);

                // 마지막 로그인 시간 업데이트
                updateLastLogin(validatedUser.getUserLoginId());
                response.put("success", true);
                response.put("message", "로그인 성공");
                response.put("userName", validatedUser.getUserName());

                // 관리자 여부 추가
                boolean isAdmin = "ADMIN".equals(status);
                response.put("isAdmin", isAdmin);

            } else {
                incrementLoginFailCount(user.getUserLoginId());

                int failCount = (validatedUser != null) ? getLoginFailCount(validatedUser) : getLoginFailCount(user);

                if (failCount >= 3) {
                    response.put("success", false);
                    response.put("message", "경고: 로그인 시도 횟수 3회 초과! 계정이 잠겼습니다.");
                    isLocked(user);
                } else {
                    response.put("success", false);
                    response.put("message", "비밀번호를 잘못 입력하셨습니다. 남은 시도 횟수: " + (3 - failCount));
                }
            }
        } catch (Exception e) {
            System.err.println("로그인 오류: " + e.getMessage());
            e.printStackTrace();
            if (user.getUserLoginId() != null) {
                response.put("success", false);
                response.put("message", "ID를 잘못 입력하셨습니다.");
            }else {
                response.put("success", false);
                response.put("message", "로그인 처리 중 오류가 발생했습니다.");
            }
        }
        return response;
    }

    @Override
    public boolean isAdmin(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null && session.getAttribute("userStatus") != null) {
            String userStatus = (String) session.getAttribute("userStatus");
            return "ADMIN".equals(userStatus);
        }
        return false;
    }

    @Override
    public List<User> getLockedUsers() {
        try {
            return userMapper.getLockedUsers();
        } catch (Exception e) {
            System.err.println("잠긴 사용자 목록 조회 오류: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean unlockUser(String userLoginId) {
        try {
            userMapper.resetLoginFailCount(userLoginId);
            return true;
        } catch (Exception e) {
            System.err.println("사용자 잠금 해제 오류: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }
}
