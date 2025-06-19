package com.fastcampus.shop.controller;

import com.fastcampus.shop.Service.MemberService;
import com.fastcampus.shop.dto.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.net.URLEncoder;

@Controller
public class JoinController {

    @Autowired
    private MemberService memberService;

    @GetMapping("/feature/join")
    public String join() {
        return "join";
    }

    @PostMapping("/feature/home")
    public String info(@ModelAttribute("user") Member member, Model m) throws Exception {
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

        memberService.registerMember(member);
        return "home";
    }

    @GetMapping(value = "/feature/checkId", produces = "text/plain;charset=UTF-8")
    @ResponseBody
    public String checkId(@RequestParam("id") String id) throws Exception {
        boolean isAvailable = memberService.isValidId(id);
        return isAvailable ? "사용 가능한 아이디입니다." : "이미 사용 중인 아이디입니다.";
    }
}
