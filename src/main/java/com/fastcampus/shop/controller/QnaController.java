package com.fastcampus.shop.controller;

import com.fastcampus.shop.dto.QnaDto;
import com.fastcampus.shop.service.QnaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/qna")
public class QnaController {
    @Autowired
    QnaService qnaService;

    @GetMapping("/list")
    public String list(int currentPage, int pageSize, Model  m, HttpServletRequest request) {
        if(!loginCheck(request))
            return "redirect:/login/login?toURL="+request.getRequestURL();  // 로그인을 안했으면 로그인 화면으로 이동

        try {
            Map map = new HashMap();
            map.put("offset", (currentPage-1)*pageSize);
            map.put("pageSize", pageSize);

            List<QnaDto> list = qnaService.getPage(map);
            m.addAttribute("list", list);
        } catch (Exception e) {
            e.printStackTrace();
        }

        // 로그인을 한 상태이면...
        return "qnaList";

    }

    private boolean loginCheck(HttpServletRequest request) {
        // 로그인 체크는 무조건 로그인 된 걸로 처리
        return true;  // 로그인 했다고 가정

        /*// 1. 세션을 얻어서
        HttpSession session = request.getSession();
        // 2. 세션에 id가 있는지 확인, 있으면 true를 반환
        return session.getAttribute("id")!=null;*/
    }
}
