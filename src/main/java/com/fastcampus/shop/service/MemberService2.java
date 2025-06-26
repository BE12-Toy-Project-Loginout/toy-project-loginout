package com.fastcampus.shop.service;

import com.fastcampus.shop.dto.Member2;

public interface MemberService2 {
    boolean isValidId(String id) throws Exception;
    boolean isValidEmail(String email) throws Exception;
    void registerMember(Member2 member) throws Exception;
    String sendVerificationEmail(String email) throws Exception;
    boolean verifyEmailCode(String email, String inputCode) throws Exception;
}
