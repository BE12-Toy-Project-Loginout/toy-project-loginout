package com.fastcampus.shop.dao;

import com.fastcampus.shop.dto.NoticeDto;
import com.fastcampus.shop.dto.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface NoticeMapper {

    NoticeDto getNoticeDetail(int noticeId);
    List<NoticeDto> getAllNotices();
}
