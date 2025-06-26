package com.fastcampus.shop.service;

import com.fastcampus.shop.dto.Member;

public interface MemberService2 {
    boolean isValidId(String id) throws Exception;
    boolean isValidEmail(String email) throws Exception;
    void registerMember(Member member) throws Exception;
    String sendVerificationEmail(String email) throws Exception;
    boolean verifyEmailCode(String email, String inputCode) throws Exception;
}
