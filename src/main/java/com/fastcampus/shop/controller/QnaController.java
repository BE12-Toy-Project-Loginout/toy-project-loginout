package com.fastcampus.shop.controller;


import com.fastcampus.shop.dto.QnaDto;
import com.fastcampus.shop.service.QnaService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.annotation.Resource;

@Controller
public class QnaController {

    @Resource
    private QnaService qnaService;

    @GetMapping("/qna")
    public String qnaView(Model model) {
        model.addAttribute("qnaList", qnaService.getAllQna());

        return "qna";
    }

    @GetMapping("/qna/{qnaId}")
    public String qnaDetail(Model model, @PathVariable int qnaId) {
        QnaDto qna = qnaService.getQnaDetail(qnaId);
        model.addAttribute("qna", qna);
        return "qnaDetail";
    }
}
