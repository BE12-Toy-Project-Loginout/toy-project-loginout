package com.fastcampus.shop.dao;

import com.fastcampus.shop.dto.QnaCommentDto;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class QnaCommentDaoImpl implements QnaCommentDao {
    @Autowired
    @Qualifier("sqlSession")
    private SqlSession sqlSession;
    //private SqlSessionTemplate sqlSession;
    private static String NAMESPACE = "com.fastcampus.shop.dao.QnaCommentDao";

    @Override
    public List<QnaCommentDto> findAll(Integer qnaId) throws Exception {
        return sqlSession.selectList(NAMESPACE + ".findAll", qnaId);
    }

    @Override
    public QnaCommentDto findById(Integer answerId) throws Exception {
        return sqlSession.selectOne(NAMESPACE + ".findById", answerId);
    }

    @Override
    public int insert(QnaCommentDto qnaCommentDto) throws Exception {
        return sqlSession.insert(NAMESPACE + ".insert", qnaCommentDto);
    }

    @Override
    public int update(QnaCommentDto qnaCommentDto) throws Exception {
        return sqlSession.update(NAMESPACE + ".update", qnaCommentDto);
    }

    @Override
    public int delete(Integer answerId, Integer memberId) throws Exception {
        Map<String, Object> map = new HashMap<>();
        map.put("answerId", answerId);             // 게시글 번호
        map.put("memberId", memberId);   // 사용자 ID
        return sqlSession.delete(NAMESPACE + ".delete", map);
    }

    @Override
    public int deleteAll(Integer qnaId) throws Exception{
        return sqlSession.delete(NAMESPACE + ".deleteAll", qnaId);
    }

    @Override
    public int count(Integer qnaId) throws Exception{
        return sqlSession.selectOne(NAMESPACE + ".count", qnaId);
    }

}
