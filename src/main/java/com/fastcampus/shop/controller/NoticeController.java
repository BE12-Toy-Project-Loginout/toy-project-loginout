package com.fastcampus.shop.controller;

import com.fastcampus.shop.dao.NoticeMapper;
import com.fastcampus.shop.dto.NoticeDto;
import com.fastcampus.shop.service.NoticeService;
import com.fastcampus.shop.service.impl.NoticeServiceImpl;
import com.fastcampus.shop.dao.TestMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Controller
public class NoticeController {

    @Autowired
    private NoticeService noticeService;


    @GetMapping("/notice")
    public String noticeView(Model model) {
        List<NoticeDto> noticeList = noticeService.getAllNotices();
        model.addAttribute("noticeList", noticeList);
        return "notice";
    }

    @GetMapping("/notice/{noticeId}")
    public String noticeDetail(Model model, @PathVariable int noticeId) {
        NoticeDto notice = noticeService.getNoticeDetail(noticeId);
        model.addAttribute("notice", notice);
        return "noticeDetail";
    }
}
