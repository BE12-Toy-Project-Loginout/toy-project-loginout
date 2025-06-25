package com.fastcampus.shop.dao;


import com.fastcampus.shop.dto.QnaDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface QnaMapper {
    public List<QnaDto> getAllQna();

    QnaDto getQnaDetail(int qnaId);
}
