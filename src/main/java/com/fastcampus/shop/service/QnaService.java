package com.fastcampus.shop.service;

import com.fastcampus.shop.dto.QnaDto;
<<<<<<< HEAD
import com.fastcampus.shop.dto.SearchCondition;

import java.util.List;
import java.util.Map;

public interface QnaService {
    int getCount() throws Exception;  // 총 게시글 수

    //List<QnaDto> getPage(int page, int pageSize) throws Exception ; // 페이징 목록

    List<QnaDto> getPage(Map map) throws Exception;

    QnaDto read(Integer qnaId) throws Exception ; // 게시글 상세

    int write(QnaDto dto) throws Exception ; // 게시글 등록

    int modify(QnaDto dto) throws Exception ; // 게시글 수정

    //int delete(int qnaId) throws Exception ; // 게시글 삭제
    //int remove(Integer qnaId, Integer memberId) throws Exception;
    int remove(Integer qnaId, Integer memberId) throws Exception;

    List<QnaDto> getSearchResultPage(SearchCondition sc) throws Exception;

    int getSearchResultCnt(SearchCondition sc) throws Exception;

    int removeByAdmin(Integer qnaId) throws Exception;
=======

import java.util.List;

public interface QnaService {
    List<QnaDto> getAllQna();
    QnaDto getQnaDetail(int qnaId);
>>>>>>> develop
}
