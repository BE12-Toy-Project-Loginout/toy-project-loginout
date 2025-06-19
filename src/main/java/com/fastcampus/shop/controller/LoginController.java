package com.fastcampus.shop.controller;

import com.fastcampus.shop.dto.User;
import com.fastcampus.shop.service.UserService;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Controller
public class LoginController {

    @Resource(name = "userService")
    private UserService userService;

    @GetMapping("/login")
    public String loginView(HttpServletRequest request, Model model) {
        HttpSession session = request.getSession(false);
        if (session != null && session.getAttribute("userLoginId") != null) {
            model.addAttribute("isLoggedIn", true);
        }
        return "login";
    }

    @GetMapping("/home")
    public String homeView(HttpServletRequest request, Model model) {
        HttpSession session = request.getSession(false);
        if (session != null && session.getAttribute("userLoginId") != null) {
            model.addAttribute("isLoggedIn", true);
        }
        return "home";}


    @PostMapping ( value = "/loginCheck")
    @ResponseBody
    public Map<String, Object> login(@RequestBody User user, HttpServletRequest request) {
        // 1.- Jackson 라이브러리- 가 담당하며, JSON 키(key)가 `User` 클래스의 필드 이름과 일치하면
        // 값이 자동으로 매핑됩니다.
        Map<String, Object> response = new HashMap<>();

        try {
            boolean loginAt = userService.validateUser(user);

            if (loginAt) {
                // Create session
                HttpSession session = request.getSession(true);
                session.setAttribute("userLoginId", user.getUserLoginId());

                // Update last login timestamp
                userService.updateLastLogin(user.getUserLoginId());
                response.put("success", true);
                response.put("message", "로그인 성공");

            } else{
                userService.incrementLoginFailCount(user.getUserLoginId());

                int failCount = userService.getLoginFailCount(user);
                if (failCount >= 3) {
                        response.put("success", false);
                        response.put("message", "경고: 로그인 시도 횟수 3회 초과! 계정이 잠겼습니다.");
                    }else {
                    response.put("success", false);
                    response.put("message", "로그인 실패. 남은 시도 횟수: " + (3 - failCount));
                }


            }
            //return loginAt;
        } catch (Exception e) {
            System.err.println("Login error: " + e.getMessage());
            e.printStackTrace();

            response.put("success", false);
            response.put("message", "로그인 처리 중 오류가 발생했습니다.");

        }
        return response;
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request) {
        try {
            userService.logout();
            return "redirect:/login";
        } catch (Exception e) {
            System.err.println("Logout error: " + e.getMessage());
            e.printStackTrace();
            return "redirect:/";
        }
    }
}
