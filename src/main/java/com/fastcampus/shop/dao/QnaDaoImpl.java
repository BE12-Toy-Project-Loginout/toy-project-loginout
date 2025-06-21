package com.fastcampus.shop.dao;

import com.fastcampus.shop.dto.QnaDto;
import org.apache.ibatis.session.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class QnaDaoImpl implements QnaDao {
    @Autowired
    @Qualifier("sqlSession")
    private SqlSession sqlSession;
    //private SqlSessionTemplate sqlSession;
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
    public int insert(QnaDto qnaDto) {
        return sqlSession.insert(NAMESPACE + ".insert", qnaDto);
    }

    @Override
    public int update(QnaDto qnaDto) {
        return sqlSession.update(NAMESPACE + ".update", qnaDto);
    }

    /*@Override
    public int delete(Integer qnaId) {
        return sqlSession.delete(NAMESPACE + ".delete", qnaId);
    }*/
    @Override
    public int delete(Integer qnaId, Integer memberId) throws Exception {
        Map<String, Object> map = new HashMap<>();
        map.put("qnaId", qnaId);             // 게시글 번호
        map.put("memberId", memberId);   // 사용자 ID
        return sqlSession.delete(NAMESPACE + ".delete", map);
    }


    @Override
    public int deleteAll(){
        return sqlSession.delete(NAMESPACE + ".deleteAll");
    }

    @Override
    public int count() {
        return sqlSession.selectOne(NAMESPACE + ".count");
    }

    /*@Override
    public List<QnaDto> findPage(Map<String, Object> params) {
        return sqlSession.selectList(NAMESPACE + ".findPage", params);
    }*/

    @Override
    public List<QnaDto> selectPage(Map map) {
        return sqlSession.selectList(NAMESPACE + ".selectPage", map);
    }
}