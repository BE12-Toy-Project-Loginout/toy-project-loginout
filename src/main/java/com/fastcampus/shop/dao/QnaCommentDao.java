package com.fastcampus.shop.dao;

import com.fastcampus.shop.dto.QnaCommentDto;

import java.util.List;

public interface QnaCommentDao {
    List<QnaCommentDto> findAll(Integer qnaId) throws Exception;

    QnaCommentDto findById(Integer answerId) throws Exception;

    int insert(QnaCommentDto qnaCommentDto) throws Exception;

    int update(QnaCommentDto qnaCommentDto) throws Exception;

    int delete(Integer answerId, Integer memberId) throws Exception;

    int deleteAll(Integer qnaId) throws Exception;

    int count(Integer qnaId) throws Exception;
}
