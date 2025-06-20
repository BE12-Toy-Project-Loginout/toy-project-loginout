package com.fastcampus.shop.controller;

import com.fastcampus.shop.dto.BackgroundImage;
import com.fastcampus.shop.dto.User;
import com.fastcampus.shop.service.BackgroundImageService;
import com.fastcampus.shop.service.UserService;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/home")
    public String homeView(HttpServletRequest request, Model model) {
        userService.setUserAttributesInModel(request, model);

        // 데이터베이스에서 메인 배경 이미지를 가져옵니다
        BackgroundImage backgroundImage = backgroundImageService.getMainBackgroundImage();
        if (backgroundImage != null) {
            // 배경 이미지가 존재하면 모델에 추가하여 뷰에서 사용할 수 있게 함
            model.addAttribute("backgroundImage", backgroundImage);
            // 배경 이미지가 없는 경우 콘솔에 오류 메시지 출력
        } else {
            System.out.println("데이터베이스에서 배경 이미지를 찾을 수 없습니다");
        }
        //리턴
        return "home";
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
        // 로그인 확인
        if (!userService.setUserAttributesInModel(request, model)) {
            return "redirect:/login";
        }

        // 관리자 권한 확인
        if (!userService.isAdmin(request)) {
            return "redirect:/home";
        }

        // 관리자 페이지 표시
        model.addAttribute("isAdmin", true);
        return "admin";
    }
}
