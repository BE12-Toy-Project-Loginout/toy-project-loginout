package com.fastcampus.shop.service;

import com.fastcampus.shop.dto.QnaDto;

import java.util.List;
import java.util.Map;

public interface QnaService {
    int getCount() throws Exception;  // 총 게시글 수
    //List<QnaDto> getPage(int page, int pageSize) throws Exception ; // 페이징 목록
    List<QnaDto> getPage(Map map) throws Exception;
    QnaDto getDetail(int qnaId) throws Exception ; // 게시글 상세
    int write(QnaDto dto) throws Exception ; // 게시글 등록
    int update(QnaDto dto) throws Exception ; // 게시글 수정
    int delete(int qnaId) throws Exception ; // 게시글 삭제
}
