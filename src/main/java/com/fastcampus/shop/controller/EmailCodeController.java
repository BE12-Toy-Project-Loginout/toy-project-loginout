package com.fastcampus.shop.controller;

import com.fastcampus.shop.service.MemberService2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

@RestController
@RequestMapping("/feature")
public class EmailCodeController {

    @Autowired
    private MemberService2 memberService;

    @PostMapping(value = "/sendAuth", produces = "text/plain; charset=UTF-8")
    @ResponseBody
    public String sendAuthCode(@RequestParam("email") String email) throws Exception {
        String code = memberService.sendVerificationEmail(email);
        return code;
    }

    @PostMapping(value = "/sendEmail", produces = "text/plain; charset=UTF-8")
    public String sendEmail(@RequestBody Map<String, String> request) throws Exception {
        String email = request.get("to");
        memberService.sendVerificationEmail(email);
        return "이메일 전송 완료!";
    }
}
