package com.fastcampus.shop.dao;

import com.fastcampus.shop.dto.QnaDto;
import com.fastcampus.shop.dto.SearchCondition;
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
   public List<QnaDto> findAll() throws Exception {
       return sqlSession.selectList(NAMESPACE + ".findAll");
   }

    @Override
    public QnaDto findById(Integer qnaId) throws Exception {
        return sqlSession.selectOne(NAMESPACE + ".findById", qnaId);
    }

    @Override
    public List<QnaDto> findByMemberId(Integer memberId) throws Exception {
        return sqlSession.selectList(NAMESPACE + ".findByMemberId", memberId);
    }

    @Override
    public int insert(QnaDto qnaDto) throws Exception {
        return sqlSession.insert(NAMESPACE + ".insert", qnaDto);
    }

    @Override
    public int update(QnaDto qnaDto) throws Exception {
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
    public int deleteAll() throws Exception{
        return sqlSession.delete(NAMESPACE + ".deleteAll");
    }

    @Override
    public int count() throws Exception{
        return sqlSession.selectOne(NAMESPACE + ".count");
    }

    /*@Override
    public List<QnaDto> findPage(Map<String, Object> params) {
        return sqlSession.selectList(NAMESPACE + ".findPage", params);
    }*/

    @Override
    public List<QnaDto> selectPage(Map map) throws Exception{
        return sqlSession.selectList(NAMESPACE + ".selectPage", map);
    }

    @Override
    public int searchResultCnt(SearchCondition sc) throws Exception{
        return sqlSession.selectOne(NAMESPACE + ".searchResultCnt", sc);
    }

    @Override
    public List<QnaDto> searchSelectPage(SearchCondition sc) throws Exception{
        return sqlSession.selectList(NAMESPACE + ".searchSelectPage", sc);
    }

    @Override
    public int updateCommentCnt(Integer qnaId, Integer commentCnt) throws Exception {
        Map  map = new HashMap<>();
        map.put("commentCnt", commentCnt);
        map.put("qnaId", qnaId);
        return sqlSession.update(NAMESPACE + ".updateComment", map);
    }

    @Override
    public int increaseViewCnt(Integer bno) throws Exception {
        return sqlSession.update(NAMESPACE+".increaseViewCnt", bno);
    } // int update(String statement, Object parameter)
}