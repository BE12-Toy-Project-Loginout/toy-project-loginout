package com.fastcampus.shop.Service;

import com.fastcampus.shop.dto.Member;

public interface MemberService {
    boolean isValidId(String id) throws Exception;
    boolean isValidEmail(String email) throws Exception;
    void registerMember(Member member) throws Exception;
}
