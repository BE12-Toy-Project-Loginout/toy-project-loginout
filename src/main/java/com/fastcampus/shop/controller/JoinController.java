package com.fastcampus.shop.controller;

import com.fastcampus.shop.dto.Member2;
import com.fastcampus.shop.service.MemberService2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.net.URLEncoder;

@Controller
public class JoinController {

    @Autowired
    private MemberService2 memberService;

    @GetMapping("/feature/join")
    public String join() {
        return "join";
    }

    @PostMapping("/feature/home")
    public String info(@ModelAttribute("user") Member2 member, Model m, HttpSession session) throws Exception {
        if (!memberService.isValidId(member.getId())) {
            String msg = URLEncoder.encode("이미 존재하는 아이디입니다.", "utf-8");
            m.addAttribute("msg", msg);
            return "redirect:/feature/join";
        }

        if (!memberService.isValidEmail(member.getEmail())) {
            String msg = URLEncoder.encode("이미 존재하는 이메일입니다.", "utf-8");
            m.addAttribute("msg", msg);
            return "redirect:/feature/join";
        }

        Boolean isVerified = (Boolean) session.getAttribute("emailVerified");
        if (isVerified == null || !isVerified) {
            String msg = URLEncoder.encode("이메일 인증이 필요합니다.", "utf-8");
            return "redirect:/feature/join?msg=" + msg;
        }

        memberService.registerMember(member);
        return "home";
    }
}