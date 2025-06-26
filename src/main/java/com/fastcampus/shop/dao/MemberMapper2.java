package com.fastcampus.shop.dao;

import com.fastcampus.shop.dto.Member2;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface MemberMapper2 {
    // 아이디, 이메일 중복 확인용
    int isDuplicateId(String id) throws Exception;

    int isDuplicateEmail(String email) throws Exception;
    // 회원가입 정보 저장용
    int insertSimpleMember(Member2 member) throws Exception;
    int insertDetailedMember(Member2 member) throws Exception;


    void insertEmailAuthCode(@Param("email") String email, @Param("code") String code);
    String selectEmailAuthCode(@Param("email") String email);
    boolean isEmailVerified(@Param("email") String email);
}
