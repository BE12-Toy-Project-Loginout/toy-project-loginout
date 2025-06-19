package com.fastcampus.shop.dao;

import com.fastcampus.shop.dto.Member;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface MemberMapper {

    // DB 연결 test용
    Member selectMember(String id);
    
    // id, 이메일 중복 확인용
    int isDuplicate(String id) throws Exception;

    // 회원가입 정보 저장용
    int insertMember(Member member) throws Exception;

    // 이메일 인증 코드 저장 (인증 메일 발송 시)
    int insertEmailAuthCode(@Param("email") String email, @Param("code") String code) throws Exception;

    // 이메일 인증 코드 조회 (코드 확인 시)
    String selectEmailAuthCode(String email) throws Exception;

    // 이메일 인증 완료 처리 (인증 성공 시)
    int updateEmailVerified(String email) throws Exception;
}
