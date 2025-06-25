package com.fastcampus.shop.service.impl;

import com.fastcampus.shop.dao.MemberMapper;
import com.fastcampus.shop.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("memberService")
public class MemberServiceImpl implements MemberService {

    private final MemberMapper memberMapper;

    @Autowired
    public MemberServiceImpl(MemberMapper memberMapper) {
        this.memberMapper = memberMapper;
    }

    @Override
    public Long getMemberIdByLoginId(String memberLoginId) {
        return memberMapper.findMemberIdByLoginId(memberLoginId);
    }
}
