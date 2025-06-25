package com.fastcampus.shop.service;

import com.fastcampus.shop.dao.MemberDao;
import com.fastcampus.shop.dto.MemberDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemberService {

    @Autowired
    MemberDao memberDao;

    public MemberDto getMemberById(int memberId) {
        return memberDao.selectMemberById(memberId);
    }
}
