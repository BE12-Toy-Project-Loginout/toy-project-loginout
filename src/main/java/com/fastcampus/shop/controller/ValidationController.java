package com.fastcampus.shop.controller;

import com.fastcampus.shop.service.MemberService2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@RestController
public class ValidationController {

    @Autowired
    private MemberService2 memberService;

    @GetMapping(value = "/checkId", produces = "text/plain;charset=UTF-8")
    public String checkId(@RequestParam("id") String id) throws Exception {
        boolean isAvailable = memberService.isValidId(id);
        return isAvailable ? "사용 가능한 아이디입니다." : "이미 사용 중인 아이디입니다.";
    }

    @PostMapping(value = "/verifyEmail", produces = "text/plain;charset=UTF-8")
    @ResponseBody
    public String verifyEmail(@RequestParam("email") String email,
                              @RequestParam("code") String code,
                              HttpSession session) throws Exception {
        email = email.trim();
        code = code.trim();

        boolean isVerified = memberService.verifyEmailCode(email, code);

        if (isVerified) {
            session.setAttribute("emailVerified", true);
            return "인증 성공";
        } else {
            session.setAttribute("emailVerified", false);
            return "코드가 일치하지 않습니다.";
        }
    }
}
