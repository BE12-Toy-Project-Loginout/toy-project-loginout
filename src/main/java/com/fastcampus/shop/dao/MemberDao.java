package com.fastcampus.shop.dao;

import com.fastcampus.shop.dto.MemberDto;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MemberDao {
    MemberDto selectMemberById(int memberId);
}
