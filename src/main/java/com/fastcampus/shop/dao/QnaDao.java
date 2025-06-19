package com.fastcampus.shop.dao;

import com.fastcampus.shop.dto.QnaDto;

import java.util.List;

public interface QnaDao {
    List<QnaDto> findAll();

    QnaDto findById(Integer qnaId);

    List<QnaDto> findByMemberId(Integer memberId);

    void insert(QnaDto qnaDto);

    void update(QnaDto qnaDto);

    void delete(Integer qnaId);
}
