package com.fastcampus.shop.dao;

import com.fastcampus.shop.dto.QnaDto;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class QnaDaoImpl implements QnaDao {
    @Autowired
    private SqlSession sqlSession;
    private static String NAMESPACE = "com.fastcampus.shop.dao.QnaDao";

   @Override
   public List<QnaDto> findAll() {
       return sqlSession.selectList(NAMESPACE + ".findAll");
   }

    @Override
    public QnaDto findById(Integer qnaId) {
        return sqlSession.selectOne(NAMESPACE + ".findById", qnaId);
    }

    @Override
    public List<QnaDto> findByMemberId(Integer memberId) {
        return sqlSession.selectList(NAMESPACE + ".findByMemberId", memberId);
    }

    @Override
    public void insert(QnaDto qnaDto) {
        sqlSession.insert(NAMESPACE + ".insert", qnaDto);
    }

    @Override
    public void update(QnaDto qnaDto) {
        sqlSession.update(NAMESPACE + ".update", qnaDto);
    }

    @Override
    public void delete(Integer qnaId) {
        sqlSession.delete(NAMESPACE + ".delete", qnaId);
    }


}
