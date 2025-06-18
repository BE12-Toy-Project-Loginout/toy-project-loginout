package com.fastcampus.shop.controller;


import com.fastcampus.shop.dto.User;
import com.fastcampus.shop.service.UserService;
import javax.annotation.Resource;
import javax.annotation.Resources;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ImportResource;
import org.springframework.jmx.export.annotation.ManagedResource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
//@RequestMapping(value = "/login")
public class LoginController {

    @Resource(name = "userService")
    private UserService userService;

    @GetMapping("/login")
    public String loginView(HttpServletRequest request, org.springframework.ui.Model model) {
        HttpSession session = request.getSession(false);
        if (session != null && session.getAttribute("userLoginId") != null) {
            model.addAttribute("isLoggedIn", true);
        }
        return "login";
    }


    @PostMapping ( value = "/loginCheck")
    @ResponseBody
    public boolean login(@RequestBody User user, HttpServletRequest request) {
        // 1.- Jackson 라이브러리- 가 담당하며, JSON 키(key)가 `User` 클래스의 필드 이름과 일치하면
        // 값이 자동으로 매핑됩니다.

        try {
            boolean loginAt = userService.validateUser(user);
            if (loginAt) {
                // Create session
                HttpSession session = request.getSession(true);
                session.setAttribute("userLoginId", user.getUserLoginId());

                // Update last login timestamp
                userService.updateLastLogin(user.getUserLoginId());
            }
            System.out.println("로그인 접근");
            return loginAt;
        } catch (Exception e) {
            System.err.println("Login error: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
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
