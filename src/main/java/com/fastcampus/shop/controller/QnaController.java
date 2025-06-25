package com.fastcampus.shop.controller;

<<<<<<< HEAD
import com.fastcampus.shop.dto.PageHandler;
import com.fastcampus.shop.dto.QnaCommentDto;
import com.fastcampus.shop.dto.QnaDto;
import com.fastcampus.shop.dto.SearchCondition;
import com.fastcampus.shop.service.AdminService;
import com.fastcampus.shop.service.QnaCommentService;
import com.fastcampus.shop.service.QnaService;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;

@Controller
@RequestMapping("/qna")
public class QnaController {
    @Autowired
    QnaService qnaService;

    @Autowired
    AdminService adminService;

    @PostMapping("/modify")
    public String modify(QnaDto qnaDto, Model m, HttpSession session, RedirectAttributes rattr){
        session.setAttribute("memberId", 1001);
        Integer memberId = (Integer) session.getAttribute("memberId");
        qnaDto.setMemberId(memberId);

        boolean isAdmin = adminService.isAdmin(memberId);


        try {

            QnaDto before = qnaService.read(qnaDto.getQnaId());
            // 관리자이거나 글쓴이만 수정 가능
            if (!isAdmin && !before.getMemberId().equals(memberId)) {
                rattr.addFlashAttribute("msg", "MOD_AUTH_ERR");
                return "redirect:/qna/read?qnaId=" + qnaDto.getQnaId();
            }
            // 수정 시 원래 글쓴이 유지
            qnaDto.setMemberId(before.getMemberId());

            int rowCnt = qnaService.modify(qnaDto); //insert

            if(rowCnt != 1)
                throw new Exception("board modify error");

            rattr.addFlashAttribute("msg", "MOD_OK");

            return "redirect:/qna/list";

        } catch (Exception e) {
            e.printStackTrace();
            m.addAttribute(qnaDto);
            m.addAttribute("msg", "MOD_ERR");
            return "qna";
        }
    }

    @PostMapping("/write")
    public String write(QnaDto qnaDto, Model m, HttpSession session, RedirectAttributes rattr){
        session.setAttribute("memberId", 1001);
        Integer memberId = (Integer) session.getAttribute("memberId");
        qnaDto.setMemberId(memberId);

        try {
            int rowCnt = qnaService.write(qnaDto); //insert

            if(rowCnt != 1)
                throw new Exception("board write error");

            rattr.addFlashAttribute("msg", "WRT_OK");
            return "redirect:/qna/read?qnaId=" + qnaDto.getQnaId();

            //return "redirect:/qna/list";

        } catch (Exception e) {
            e.printStackTrace();
            m.addAttribute(qnaDto);
            m.addAttribute("msg", "WRT_ERR");
            return "qna";
        }
    }

    @GetMapping("/write")
    public String write(Model m) {
        m.addAttribute("mode", "new"); //읽기와 쓰기에 사용. 쓰기 사용할 때는 mode=new
        return "qna";
    }

    @PostMapping("/remove")
    public String remove(@RequestParam("qnaId") Integer qnaId,
                         /*@RequestParam("memberId") Integer memberId,*/
                         SearchCondition sc, Model m, HttpSession session, RedirectAttributes rattr) {
        // 테스트용 memberId 하드코딩
        //Integer memberId = 1001; // ← 실제 존재하는 FK 값

        // 로그인 없이 세션을 가짜로 채우고 싶다면
        session.setAttribute("memberId", 1001);
        Integer memberId = (Integer) session.getAttribute("memberId");
        boolean isAdmin = adminService.isAdmin(memberId);

        //String writer = (String)session.getAttribute("memberId");


        try {
            QnaDto qnaDto = qnaService.read(qnaId);

            // 본인 또는 관리자만 삭제 가능
            if (!isAdmin && !qnaDto.getMemberId().equals(memberId)) {
                rattr.addFlashAttribute("msg", "DEL_AUTH_ERR");
                return "redirect:/qna/read?qnaId=" + qnaId;
            }

            //int rowCnt = qnaService.remove(qnaId, memberId);
            int rowCnt;
            if (isAdmin) {
                rowCnt = qnaService.removeByAdmin(qnaId);
            } else {
                rowCnt = qnaService.remove(qnaId, memberId);
            }


            if(rowCnt != 1) {
                throw new Exception("board remove error");
            }
            rattr.addFlashAttribute("msg", "DEL_OK"); //모델에 한번만 전달됨-session에 한 번 잠깐 저장하는 것

        } catch (Exception e) {
            e.printStackTrace();
            rattr.addFlashAttribute("msg", "DEL_ERR");

        }

        return "redirect:/qna/list"+sc.getQueryString(); //모델사용하면 url뒤에 따로 붙여줄 필요 없음
    }

    @GetMapping("/read")
    public String read(Integer qnaId, SearchCondition sc, Model m, HttpSession session, RedirectAttributes rattr) {

        session.setAttribute("memberId", 1001);
        Integer memberId = (Integer) session.getAttribute("memberId");
        boolean isAdmin = adminService.isAdmin(memberId);


        try {
            QnaDto qnaDto = qnaService.read(qnaId);
            m.addAttribute(qnaDto);
            m.addAttribute("sc", sc);
            m.addAttribute("mode", "view");          // <== 여기 추가!!
            m.addAttribute("loginId", memberId);
            m.addAttribute("isAdmin", isAdmin);


        } catch (Exception e) {
            e.printStackTrace();
            rattr.addFlashAttribute("msg", "READ_ERR");
            return "redirect:/qna/list"+sc.getQueryString();
        }
        return "qna";
    }

    @GetMapping("/list")
    public String list(SearchCondition sc, Model m, HttpServletRequest request) {
        if (!loginCheck(request))
            return "redirect:/login/login?toURL=" + request.getRequestURL();  // 로그인을 안했으면 로그인 화면으로 이동
        System.out.println("### sc.getCurrentPage() = " + sc.getCurrentPage());

        try {

            int totalCnt = qnaService.getSearchResultCnt(sc);
            m.addAttribute("totalCnt", totalCnt);

            PageHandler pageHandler = new PageHandler(totalCnt, sc);

            List<QnaDto> list = qnaService.getSearchResultPage(sc);
            m.addAttribute("list", list);
            m.addAttribute("ph", pageHandler);

            Instant startOfToday = LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant();
            m.addAttribute("startOfToday", startOfToday.toEpochMilli());

        } catch (Exception e) {
            e.printStackTrace();
            m.addAttribute("msg", "LIST_ERR");
            m.addAttribute("totalCnt", 0);
        }

        // 로그인을 한 상태이면...
        return "qnaList";
    }

    private boolean loginCheck(HttpServletRequest request) {
        // 로그인 체크는 무조건 로그인 된 걸로 처리
        return true;  // 로그인 했다고 가정
    }
}
=======

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
>>>>>>> develop
