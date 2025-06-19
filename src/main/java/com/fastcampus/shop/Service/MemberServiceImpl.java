package com.fastcampus.shop.Service;

import com.fastcampus.shop.dao.MemberMapper;
import com.fastcampus.shop.dto.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemberServiceImpl implements MemberService {

    @Autowired
    private MemberMapper memberMapper;

    @Override
    public boolean isValidId(String id) throws Exception {
        return memberMapper.isDuplicateId(id) == 0;
    }

    @Override
    public boolean isValidEmail(String email) throws Exception {
        return memberMapper.isDuplicateEmail(email) == 0;
    }

    @Override
    public void registerMember(Member member) throws Exception {
        memberMapper.insertMember(member);
    }
}
