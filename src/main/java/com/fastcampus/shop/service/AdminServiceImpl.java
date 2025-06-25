package com.fastcampus.shop.service;

import com.fastcampus.shop.dao.AdminDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminServiceImpl implements AdminService {
    @Autowired
    private AdminDao adminDao;

    @Override
    public boolean isAdmin(Integer memberId) {
        boolean result = adminDao.existsByMemberId(memberId) > 0;
        System.out.println("[isAdmin] memberId=" + memberId + ", isAdmin=" + result);
        return result;
    }
}