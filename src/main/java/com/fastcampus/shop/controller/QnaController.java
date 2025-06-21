package com.fastcampus.shop.controller;

import com.fastcampus.shop.dto.PageHandler;
import com.fastcampus.shop.dto.QnaDto;
import com.fastcampus.shop.service.QnaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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

    @PostMapping("/remove")
    public String remove(@RequestParam("qnaId") Integer qnaId,
                         @RequestParam("memberId") Integer memberId,
                         @RequestParam("currentPage") Integer currentPage,
                         @RequestParam("pageSize") Integer pageSize, Model m, HttpSession session, RedirectAttributes rattr) {
        // 테스트용 memberId 하드코딩
        //Integer memberId = 1001; // ← 실제 존재하는 FK 값

        /*// 로그인 없이 세션을 가짜로 채우고 싶다면
        session.setAttribute("memberId", 1001);
        Integer memberId = (Integer) session.getAttribute("memberId");*/

        //String writer = (String)session.getAttribute("memberId");


        try {
            m.addAttribute("currentPage", currentPage);
            m.addAttribute("pageSize", pageSize);

            int rowCnt = qnaService.remove(qnaId, memberId);

            if(rowCnt != 1) {
                throw new Exception("board remove error");
            }
            rattr.addFlashAttribute("msg", "DEL_OK"); //모델에 한번만 전달됨-session에 한 번 잠깐 저장하는 것

        } catch (Exception e) {
            e.printStackTrace();
            rattr.addFlashAttribute("msg", "DEL_ERR");

        }

        return "redirect:/qna/list"; //모델사용하면 url뒤에 따로 붙여줄 필요 없음
    }

    @GetMapping("/read")
    public String read(Integer qnaId, Integer currentPage, Integer pageSize, Model m) {
        try {
            QnaDto qnaDto = qnaService.read(qnaId);
            m.addAttribute(qnaDto);
            m.addAttribute("currentPage", currentPage);
            m.addAttribute("pageSize", pageSize);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return "qna";
    }

    @GetMapping("/list")
    public String list(Integer currentPage, Integer pageSize, Model m, HttpServletRequest request) {
        if (!loginCheck(request))
            return "redirect:/login/login?toURL=" + request.getRequestURL();  // 로그인을 안했으면 로그인 화면으로 이동

        // null 체크 및 기본값 할당
        if (currentPage == null || currentPage < 1) {
            currentPage = 1;
        }
        if (pageSize == null || pageSize < 1) {
            pageSize = 10; // 원하는 기본 페이지 사이즈 값으로 변경 가능
        }

        try {

            int totalCnt = qnaService.getCount();
            PageHandler pageHandler = new PageHandler(totalCnt, currentPage, pageSize);

            Map<String, Object> map = new HashMap<>();
            map.put("offset", (currentPage - 1) * pageSize);
            map.put("pageSize", pageSize);

            List<QnaDto> list = qnaService.getPage(map);
            m.addAttribute("list", list);
            m.addAttribute("ph", pageHandler);
            m.addAttribute("currentPage", currentPage);
            m.addAttribute("pageSize", pageSize);


        } catch (Exception e) {
            e.printStackTrace();
        }

        // 로그인을 한 상태이면...
        return "qnaList";
    }

    private boolean loginCheck(HttpServletRequest request) {
        // 로그인 체크는 무조건 로그인 된 걸로 처리
        return true;  // 로그인 했다고 가정
    }
}