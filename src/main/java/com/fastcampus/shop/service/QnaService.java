package com.fastcampus.shop.service;

import com.fastcampus.shop.dto.QnaDto;

import java.util.List;

public interface QnaService {
    List<QnaDto> getAllQna();
    QnaDto getQnaDetail(int qnaId);
}
