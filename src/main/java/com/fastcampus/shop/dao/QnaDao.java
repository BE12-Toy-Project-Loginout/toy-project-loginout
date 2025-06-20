package com.fastcampus.shop.dao;

import com.fastcampus.shop.dto.QnaDto;

import java.util.List;
import java.util.Map;

public interface QnaDao {
    List<QnaDto> findAll() throws Exception ;

    QnaDto findById(Integer qnaId) throws Exception ;

    List<QnaDto> findByMemberId(Integer memberId) throws Exception ;

    int insert(QnaDto qnaDto) throws Exception ;

    int update(QnaDto qnaDto) throws Exception ;

    int delete(Integer qnaId) throws Exception ;

    int deleteAll() throws Exception ;

    int count() throws Exception ;

    //List<QnaDto> findPage(Map<String, Object> params) throws Exception ;

    List<QnaDto> selectPage(Map map) throws Exception;
}
