package com.fastcampus.shop.service;

import com.fastcampus.shop.dao.MemberMapper;
import com.fastcampus.shop.service.QMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("qmemberService")
public class QMemberServiceImpl implements QMemberService {

    private final MemberMapper memberMapper;

    @Autowired
    public QMemberServiceImpl(MemberMapper memberMapper) {
        this.memberMapper = memberMapper;
    }

    @Override
    public Long getMemberIdByLoginId(String memberLoginId) {
        return memberMapper.findMemberIdByLoginId(memberLoginId);
    }
}