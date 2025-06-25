package com.fastcampus.shop.service;


import com.fastcampus.shop.dto.NoticeDto;
import com.fastcampus.shop.dto.User;
import org.springframework.ui.Model;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

public interface NoticeService {

     NoticeDto getNoticeDetail(int noticeId);

     List<NoticeDto> getAllNotices();

}
