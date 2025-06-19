package com.fastcampus.shop.dao;

import com.fastcampus.shop.dto.Member;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MemberMapper {
    // 아이디, 이메일 중복 확인용
    int isDuplicateId(String id) throws Exception;

    int isDuplicateEmail(String email) throws Exception;
    // 회원가입 정보 저장용
    int insertMember(Member member) throws Exception;

}
