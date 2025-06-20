package com.fastcampus.shop.controller;

import com.fastcampus.shop.dto.BackgroundImage;
import com.fastcampus.shop.dto.User;
import com.fastcampus.shop.service.BackgroundImageService;
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

    @Resource(name = "backgroundImageService")
    private BackgroundImageService backgroundImageService;


    private boolean setCommonModelAttributes(HttpServletRequest request, Model model) {
        HttpSession session = request.getSession(false);
        if (session != null && session.getAttribute("userLoginId") != null) {
            model.addAttribute("isLoggedIn", true);
            String userName = (String) session.getAttribute("userName");
            model.addAttribute("userName", userName);

            // Add isAdmin attribute to the model
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

    @GetMapping("/login")
    public String loginView(HttpServletRequest request, Model model) {
        setCommonModelAttributes(request, model);
        return "login";
    }

    @GetMapping("/home")
    public String homeView(HttpServletRequest request, Model model) {
        setCommonModelAttributes(request, model);

        // 데이터베이스에서 메인 배경 이미지를 가져옵니다
        BackgroundImage backgroundImage = backgroundImageService.getMainBackgroundImage();
        if (backgroundImage != null) {
            model.addAttribute("backgroundImage", backgroundImage);
        } else {
            System.out.println("데이터베이스에서 배경 이미지를 찾을 수 없습니다");
        }

        return "home";
    }


    @PostMapping ( value = "/loginCheck")
    @ResponseBody
    public Map<String, Object> login(@RequestBody User user, HttpServletRequest request) {
        // 1.- Jackson 라이브러리- 가 담당하며, JSON 키(key)가 `User` 클래스의 필드 이름과 일치하면
        // 값이 자동으로 매핑됩니다.
        Map<String, Object> response = new HashMap<>();

        try {
            User validatedUser = userService.validateUser(user);

            if (validatedUser != null) {
                // 세션 생성
                HttpSession session = request.getSession(true);
                session.setAttribute("userName", validatedUser.getUserName());
                session.setAttribute("userLoginId", validatedUser.getUserLoginId());
                String status = userService.getUserStatus(validatedUser);
                session.setAttribute("userStatus", status);


                // 마지막 로그인 시간 업데이트
                userService.updateLastLogin(validatedUser.getUserLoginId());
                response.put("success", true);
                response.put("message", "로그인 성공");
                response.put("userName", validatedUser.getUserName());

                // 관리자 여부 추가
                boolean isAdmin = "ADMIN".equals(status);
                response.put("isAdmin", isAdmin);
            } else{
                userService.incrementLoginFailCount(user.getUserLoginId());

                int failCount = userService.getLoginFailCount(user);
                if (failCount >= 3) {
                        response.put("success", false);
                        response.put("message", "경고: 로그인 시도 횟수 3회 초과! 계정이 잠겼습니다.");
                        userService.isLocked(user);
                    }else {
                    response.put("success", false);
                    response.put("message", "로그인 실패. 남은 시도 횟수: " + (3 - failCount));
                }


            }
            //return loginAt;
        } catch (Exception e) {
            System.err.println("로그인 오류: " + e.getMessage());
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
            System.err.println("로그아웃 오류: " + e.getMessage());
            e.printStackTrace();
            return "redirect:/";
        }
    }

    @GetMapping("/admin")
    public String adminView(HttpServletRequest request, Model model) {
        // Check if user is logged in
        if (!setCommonModelAttributes(request, model)) {
            // 로그인하지 않은 경우 로그인 페이지로 리다이렉트
            return "redirect:/login";
        }

        // Get session to check if user is admin
        HttpSession session = request.getSession(false);
        String userStatus = (String) session.getAttribute("userStatus");

        // 관리자가 아닌 경우 홈으로 리다이렉트
        if (!"ADMIN".equals(userStatus)) {
            return "redirect:/home";
        }

        // Admin user - ensure isAdmin is true for admin page
        model.addAttribute("isAdmin", true);
        return "admin";
    }
}
