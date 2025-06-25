package com.fastcampus.shop.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class AdminDaoImpl implements AdminDao {

    private static final String NAMESPACE = "com.fastcampus.shop.dao.AdminDao";

    @Autowired
    private SqlSession sqlSession;

    @Override
    public int existsByMemberId(Integer memberId) {
        return sqlSession.selectOne(NAMESPACE + ".existsByMemberId", memberId);
    }
}