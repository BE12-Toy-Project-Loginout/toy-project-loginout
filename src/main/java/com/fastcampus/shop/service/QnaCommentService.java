package com.fastcampus.shop.service;

import com.fastcampus.shop.dto.QnaCommentDto;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface QnaCommentService {
    int getCount(Integer qnaId) throws Exception;

    @Transactional(rollbackFor = Exception.class)
    int remove(Integer answerId, Integer qnaId, Integer memberId) throws Exception;

    @Transactional(rollbackFor = Exception.class)
    int write(QnaCommentDto qnaCommentDto) throws Exception;

    List<QnaCommentDto> getListByQnaId(Integer qnaId) throws Exception;

    QnaCommentDto getById(Integer answerId) throws Exception;

    int modify(QnaCommentDto qnaCommentDto) throws Exception;
}
