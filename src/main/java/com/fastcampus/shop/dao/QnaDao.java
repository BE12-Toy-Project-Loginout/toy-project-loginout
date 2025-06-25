package com.fastcampus.shop.dao;

import com.fastcampus.shop.dto.QnaDto;

import java.util.List;
import java.util.Map;

import com.fastcampus.shop.dto.SearchCondition;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface QnaDao {
    List<QnaDto> findAll() throws Exception ;

    QnaDto findById(Integer qnaId) throws Exception ;

    List<QnaDto> findByMemberId(Integer memberId) throws Exception ;

    int insert(QnaDto qnaDto) throws Exception ;

    int update(QnaDto qnaDto) throws Exception ;

    //int delete(Integer qnaId) throws Exception ;
    // @Param 어노테이션 추가
    int delete(@Param("qnaId") Integer qnaId, @Param("memberId") Integer memberId) throws Exception;


    int deleteAll() throws Exception ;

    int count() throws Exception ;

    //List<QnaDto> findPage(Map<String, Object> params) throws Exception ;

    List<QnaDto> selectPage(Map map) throws Exception;

    int searchResultCnt(SearchCondition sc) throws Exception;

    List<QnaDto> searchSelectPage(SearchCondition sc) throws Exception;

    int increaseViewCnt(Integer bno) throws Exception;

    int updateCommentCnt(@Param("qnaId") Integer qnaId, @Param("commentCnt") Integer commentCnt) throws Exception;

    int deleteByAdmin(@Param("qnaId") Integer qnaId) throws Exception;

    //List<QnaDto> findAllWithMemberName() throws Exception;
}