package com.fastcampus.shop.controller;

import com.fastcampus.shop.dto.BackgroundImage;
import com.fastcampus.shop.service.BackgroundImageService;
import com.fastcampus.shop.service.UserService;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 * 홈 페이지 관련 기능을 처리하는 컨트롤러
 */
@Controller
public class HomeController {

    @Resource(name = "userService")
    private UserService userService;

    @Resource(name = "backgroundImageService")
    private BackgroundImageService backgroundImageService;


    @GetMapping("/home")
    public String homeView(HttpServletRequest request, Model model) {
        // 사용자 로그인 상태 및 권한 정보를 모델에 설정
        userService.setUserAttributesInModel(request, model);

        // 데이터베이스에서 메인 배경 이미지를 가져옵니다
        BackgroundImage backgroundImage = backgroundImageService.getMainBackgroundImage();
        if (backgroundImage != null) {
            // 배경 이미지가 존재하면 모델에 추가하여 뷰에서 사용할 수 있게 함
            model.addAttribute("backgroundImage", backgroundImage);
        } else {
            // 배경 이미지가 없는 경우 콘솔에 오류 메시지 출력
            System.out.println("데이터베이스에서 배경 이미지를 찾을 수 없습니다");
        }

        // home.jsp 뷰를 렌더링하여 사용자에게 반환
        return "home";
    }
    

    @GetMapping("/")
    public String root() {
        return "redirect:/home";
    }
}