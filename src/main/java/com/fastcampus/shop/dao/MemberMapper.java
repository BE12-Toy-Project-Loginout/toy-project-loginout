package com.fastcampus.shop.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface MemberMapper {
    Long findMemberIdByLoginId(@Param("memberLoginId") String memberLoginId);
}
