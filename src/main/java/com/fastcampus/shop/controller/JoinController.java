package com.fastcampus.shop.controller;

import com.fastcampus.shop.dto.Member;
import com.fastcampus.shop.dao.MemberMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import java.net.URLEncoder;

@Controller
public class JoinController {

    @Autowired
    private MemberMapper memberMapper;

    // 신규회원 가입
    @GetMapping("/feature/join")
    public String join() {
        return "join";
    }

    // 회원가입시 아이디 중복체크 - js와는 별개작업
    @PostMapping("/feature/home")
    public String info(@ModelAttribute("member") Member member, Model m) throws Exception {
        if (!isValid(member)) {
            String msg = URLEncoder.encode("이미 존재하는 아이디입니다.", "utf-8");
            m.addAttribute("msg", msg);
            return "redirect:/feature/join";
        }

        // TODO: 여기서 DB에 회원 INSERT 로직 추가 필요

        return "home";
    }



    private boolean isValid(Member member) throws Exception {
        return memberMapper.isDuplicate(member.getId()) == 0;
    }



//    int isDuplicate(String id) throws Exception;
//
//    // 회원가입 정보 저장용
//    int insertMember(Member member) throws Exception;
//
//    // 이메일 인증 코드 저장 (인증 메일 발송 시)
//    int insertEmailAuthCode(@Param("email") String email, @Param("code") String code) throws Exception;
//
//    // 이메일 인증 코드 조회 (코드 확인 시)
//    String selectEmailAuthCode(String email) throws Exception;
//
//    // 이메일 인증 완료 처리 (인증 성공 시)
//    int updateEmailVerified(String email) throws Exception;


}