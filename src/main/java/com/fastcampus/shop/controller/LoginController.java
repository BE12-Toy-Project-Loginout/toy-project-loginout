package com.fastcampus.shop.controller;

import com.fastcampus.shop.dto.BackgroundImage;
import com.fastcampus.shop.dto.User;
import com.fastcampus.shop.service.BackgroundImageService;
import com.fastcampus.shop.service.UserService;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Controller
public class LoginController {

    @Resource(name = "userService")
    private UserService userService;

    @Resource(name = "backgroundImageService")
    private BackgroundImageService backgroundImageService;

    @GetMapping("/login")
    public String loginView(HttpServletRequest request, Model model) {
        userService.setUserAttributesInModel(request, model);
        return "login";
    }

    @PostMapping(value = "/loginCheck")
    @ResponseBody
    public Map<String, Object> login(@RequestBody User user, HttpServletRequest request) {
        // Jackson 라이브러리가 담당하며, JSON 키(key)가 `User` 클래스의 필드 이름과 일치하면
        // 값이 자동으로 매핑됩니다.
        return userService.processLogin(user, request);
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request) {
        try {
            userService.logout(request);
            return "redirect:/login";
        } catch (Exception e) {
            System.err.println("로그아웃 오류: " + e.getMessage());
            e.printStackTrace();
            return "redirect:/";
        }
    }

    @GetMapping("/admin")
    public String adminView(HttpServletRequest request, Model model) {
        // 로그인 확인
        if (!userService.setUserAttributesInModel(request, model)) {
            return "redirect:/login";
        }

        // 관리자 권한 확인
        if (!userService.isAdmin(request)) {
            return "redirect:/home";
        }

        // 잠긴 사용자 목록 가져오기
        List<User> lockedUsers = userService.getLockedUsers();
        model.addAttribute("lockedUsers", lockedUsers);

        // 관리자 페이지 표시
        model.addAttribute("isAdmin", true);
        return "admin";
    }

    @GetMapping("/admin/locked-users")
    @ResponseBody
    public ResponseEntity<?> getLockedUsers(HttpServletRequest request) {
        // 관리자 권한 확인
        if (!userService.isAdmin(request)) {
            return ResponseEntity.status(403).body("관리자 권한이 필요합니다.");
        }

        List<User> lockedUsers = userService.getLockedUsers();
        return ResponseEntity.ok(lockedUsers);
    }

    @PostMapping("/admin/unlock-user")
    @ResponseBody
    public ResponseEntity<?> unlockUser(@RequestParam("userLoginId") String userLoginId, HttpServletRequest request) {
        // 관리자 권한 확인
        if (!userService.isAdmin(request)) {
            return ResponseEntity.status(403).body("관리자 권한이 필요합니다.");
        }

        Map<String, Object> response = new HashMap<>();
        boolean success = userService.unlockUser(userLoginId);

        if (success) {
            response.put("success", true);
            response.put("message", "사용자 계정 잠금이 해제되었습니다.");
        } else {
            response.put("success", false);
            response.put("message", "사용자 계정 잠금 해제에 실패했습니다.");
        }

        return ResponseEntity.ok(response);
    }
}
