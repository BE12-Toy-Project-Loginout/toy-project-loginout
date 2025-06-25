package com.fastcampus.shop.service.impl;


import com.fastcampus.shop.dao.QnaMapper;
import com.fastcampus.shop.dto.QnaDto;
import com.fastcampus.shop.service.QnaService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.List;

@Service
public class QnaServiceImpl implements QnaService {

    @Resource
    private QnaMapper qnaMapper;

    @Override
    public List<QnaDto> getAllQna() {
        return qnaMapper.getAllQna();
    }

    @Override
    public QnaDto getQnaDetail(int qnaId) {

        return qnaMapper.getQnaDetail(qnaId);
    }
}
