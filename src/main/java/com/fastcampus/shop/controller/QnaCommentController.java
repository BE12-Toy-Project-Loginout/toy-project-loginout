package com.fastcampus.shop.controller;

import com.fastcampus.shop.dto.QnaCommentDto;
import com.fastcampus.shop.dto.QnaDto;
import com.fastcampus.shop.service.AdminService;
import com.fastcampus.shop.service.QnaCommentService;
import com.fastcampus.shop.service.QnaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;
/*@ResponseBody
@Controller*/
@RestController
public class QnaCommentController {
    @Autowired
    QnaCommentService qnaCommentService;

    @Autowired
    QnaService qnaService;

    @Autowired
    private AdminService adminService;

    // 댓글을 등록하는 메서드
    @PostMapping("/comments") //comments?qnaId=2063 POST 방식
    public ResponseEntity<String> write(@RequestBody QnaCommentDto qnaCommentDto, @RequestParam Integer qnaId, HttpSession session) {
        // 실제 환경에서는 아래 setAttribute 코드 제거!
        // session.setAttribute("memberId", 1001);
        //한글 인코딩
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "text/plain; charset=UTF-8");

        Integer memberId = (Integer) session.getAttribute("memberId");
        if (memberId == null) {
            return new ResponseEntity<>("로그인이 필요합니다.", headers, HttpStatus.UNAUTHORIZED);
        }

        // 글 작성자 본인인지 확인
        QnaDto qnaDto;
        try {
            qnaDto = qnaService.read(qnaId);
            if (qnaDto == null) {
                return new ResponseEntity<>("존재하지 않는 글입니다.", headers, HttpStatus.BAD_REQUEST);
            }
            /*if (!memberId.equals(qnaDto.getMemberId())) {
                return new ResponseEntity<>("글 작성자만 댓글을 작성할 수 있습니다.", headers, HttpStatus.FORBIDDEN);
            }*/
            // 관리자이거나 글 작성자만 댓글(답변) 작성 가능
            boolean isAdmin = adminService.isAdmin(memberId);
            if (!memberId.equals(qnaDto.getMemberId()) && !isAdmin) {
                return new ResponseEntity<>("댓글(답변) 작성 권한이 없습니다.", headers, HttpStatus.FORBIDDEN);
            }

        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("서버 오류", headers, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        qnaCommentDto.setMemberId(memberId);
        qnaCommentDto.setQnaId(qnaId);
        System.out.println("qnaCommentDto = " + qnaCommentDto);
        try {
            if(qnaCommentService.write(qnaCommentDto) != 1)
                throw new Exception("Comment write failed");

            return new ResponseEntity<>("WRT_OK", HttpStatus.OK);

        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("WRT_ERR", HttpStatus.BAD_REQUEST);
        }
    }

    //댓글을 수정하는 메서드
    @PatchMapping("/comments/{answerId}") //comments/27 PATCH
    public ResponseEntity<String> modify(@PathVariable Integer answerId, @RequestBody QnaCommentDto qnaCommentDto, HttpSession session) {
        // 실제 환경에서는 아래 setAttribute 코드 제거!
        // session.setAttribute("memberId", 1001);
        //한글 인코딩
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "text/plain; charset=UTF-8");

        Integer memberId = (Integer) session.getAttribute("memberId");
        if (memberId == null) {
            return new ResponseEntity<>("로그인이 필요합니다.", headers, HttpStatus.UNAUTHORIZED);
        }

        // 관리자 권한 체크
        boolean isAdmin = adminService.isAdmin(memberId);


        // 작성자 본인만 댓글 수정 가능 (댓글 작성자 검증)
        QnaCommentDto originComment;
        try {
            originComment = qnaCommentService.getById(answerId);
            if (originComment == null) {
                return new ResponseEntity<>("존재하지 않는 댓글입니다.", headers, HttpStatus.NOT_FOUND);
            }
            /*if (!memberId.equals(originComment.getMemberId())) {
                return new ResponseEntity<>("본인이 작성한 댓글만 수정할 수 있습니다.", headers, HttpStatus.FORBIDDEN);
            }*/
            // 작성자 또는 관리자만 수정 가능하도록 변경
            if (!memberId.equals(originComment.getMemberId()) && !isAdmin) {
                return new ResponseEntity<>("수정 권한이 없습니다.", headers, HttpStatus.FORBIDDEN);
            }

        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("서버 오류", headers, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        qnaCommentDto.setMemberId(memberId);
        qnaCommentDto.setAnswerId(answerId);

        System.out.println("qnaCommentDto = " + qnaCommentDto);

        try {
            if(qnaCommentService.modify(qnaCommentDto) != 1)
                throw new Exception("Comment update failed");

            return new ResponseEntity<>("MOD_OK", headers, HttpStatus.OK);

        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("MOD_ERR", headers, HttpStatus.BAD_REQUEST);
        }
    }
    // 지정된 댓글을 삭제하는 메서드
    @DeleteMapping("/comments/{answerId}") //comments/1 <-- 삭제할 댓글 번호
    public ResponseEntity<String> remove(@PathVariable Integer answerId, @RequestParam("qnaId") Integer qnaId, HttpSession session) {
        // session.setAttribute("memberId", 1001); // 실제 환경에서는 제거!
        //한글 인코딩
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "text/plain; charset=UTF-8");

        Integer memberId = (Integer) session.getAttribute("memberId");
        if (memberId == null) {
            return new ResponseEntity<>("로그인이 필요합니다.", headers, HttpStatus.UNAUTHORIZED);
        }

        // 관리자 권한 체크
        boolean isAdmin = adminService.isAdmin(memberId);


        // 댓글 작성자 본인만 삭제 가능
        QnaCommentDto originComment;
        try {
            originComment = qnaCommentService.getById(answerId);
            if (originComment == null) {
                return new ResponseEntity<>("존재하지 않는 댓글입니다.", headers, HttpStatus.NOT_FOUND);
            }
            /*if (!memberId.equals(originComment.getMemberId())) {
                return new ResponseEntity<>("본인이 작성한 댓글만 삭제할 수 있습니다.", headers, HttpStatus.FORBIDDEN);
            }*/
            // 작성자 또는 관리자만 수정 가능하도록 변경
            if (!memberId.equals(originComment.getMemberId()) && !isAdmin) {
                return new ResponseEntity<>("삭제 권한이 없습니다.", headers, HttpStatus.FORBIDDEN);
            }

        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("서버 오류", headers, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        try {
            int rowCnt = qnaCommentService.remove(answerId, qnaId, memberId);

            if(rowCnt != 1) {
                throw new Exception("Comment delete failed");
            }
            return new ResponseEntity<>("DEL_OK", headers, HttpStatus.OK);

        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("DEL_ERR", headers, HttpStatus.BAD_REQUEST);
        }
    }
    /*//댓글을 수정하는 메서드
    @PatchMapping("/comments/{answerId}") //comments/27 PATCH
    public ResponseEntity<String> modify(@PathVariable Integer answerId, @RequestBody QnaCommentDto qnaCommentDto, HttpSession session) {
        session.setAttribute("memberId", 1001);
        Integer memberId = (Integer) session.getAttribute("memberId");
        qnaCommentDto.setMemberId(memberId);
        qnaCommentDto.setAnswerId(answerId);
        System.out.println("qnaCommentDto = " + qnaCommentDto);

        try {
            if(qnaCommentService.modify(qnaCommentDto) != 1)
                throw new Exception("Comment update failed");

            return new ResponseEntity<>("MOD_OK", HttpStatus.OK);

        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("MOD_ERR", HttpStatus.BAD_REQUEST);
        }
    }*/

    /*// 댓글을 등록하는 메서드
    @PostMapping("/comments") //comments?qnaId=2063 POST 방식
    public ResponseEntity<String> write(@RequestBody QnaCommentDto qnaCommentDto, Integer qnaId, HttpSession session) {
        //RequestBody - json로 받은 걸 java 객체로 변환
        session.setAttribute("memberId", 1001);
        Integer memberId = (Integer) session.getAttribute("memberId");
       *//* if (memberId == null) {
            return new ResponseEntity<>("로그인 필요", HttpStatus.UNAUTHORIZED);
        }*//*

        qnaCommentDto.setMemberId(memberId);
        qnaCommentDto.setQnaId(qnaId);
        System.out.println("qnaCommentDto = " + qnaCommentDto);
        try {
            if(qnaCommentService.write(qnaCommentDto) != 1)
                throw new Exception("Comment write failed");

            return new ResponseEntity<>("WRT_OK", HttpStatus.OK);

        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("WRT_ERR", HttpStatus.BAD_REQUEST);
        }
    }*/
    // 지정된 댓글을 삭제하는 메서드
    /*@DeleteMapping("/comments/{answerId}") //comments/1 <-- 삭제할 댓글 번호
    public ResponseEntity<String> remove(@PathVariable Integer answerId, @RequestParam("qnaId") Integer qnaId, HttpSession session) {
        session.setAttribute("memberId", 1001);
        Integer memberId = (Integer) session.getAttribute("memberId");

        try {
            int rowCnt = qnaCommentService.remove(answerId, qnaId, memberId);

            if(rowCnt != 1) {
                throw new Exception("Comment delete failed");
            }
            return new ResponseEntity<>("DEL_OK", HttpStatus.OK);

        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("DEL_ERR", HttpStatus.BAD_REQUEST);
        }
    }*/

    // 지정된 게시물의 모든 댓글을 가져오는 메서드
    @GetMapping("/comments") //comments?qnaId=2063 GET방식
    //ResponseEntity가 없을 때는 실패든 성공이든 항상 200으로 가기 때문에
    // 따로 지정하고 상태까지 같이 넘겨준다
    @ResponseBody public ResponseEntity<List<QnaCommentDto>> list(Integer qnaId) {
        List<QnaCommentDto> list = null;
        System.out.println("QnaCommentController.list() 호출");

        try {
            list = qnaCommentService.getListByQnaId(qnaId);
            return new ResponseEntity<List<QnaCommentDto>>(list, HttpStatus.OK); //200

        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<List<QnaCommentDto>>(HttpStatus.BAD_REQUEST); //400
    }

}
