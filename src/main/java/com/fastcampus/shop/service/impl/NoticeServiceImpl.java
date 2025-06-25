package com.fastcampus.shop.service.impl;

import com.fastcampus.shop.dao.NoticeMapper;
import com.fastcampus.shop.dto.NoticeDto;
import com.fastcampus.shop.service.NoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.List;

@Service
public class NoticeServiceImpl implements NoticeService {

    @Resource
    private NoticeMapper NoticeMapper;


    @Override
    public NoticeDto getNoticeDetail(int noticeId) {
        return NoticeMapper.getNoticeDetail(noticeId);
    }

    @Override
    public List<NoticeDto> getAllNotices() {
        return NoticeMapper.getAllNotices();
    }
}
