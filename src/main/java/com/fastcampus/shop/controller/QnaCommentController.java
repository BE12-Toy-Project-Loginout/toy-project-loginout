package com.fastcampus.shop.controller;

import com.fastcampus.shop.dto.QnaCommentDto;
import com.fastcampus.shop.service.QnaCommentService;
import org.springframework.beans.factory.annotation.Autowired;
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

    //댓글을 수정하는 메서드
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
    }

    // 댓글을 등록하는 메서드
    @PostMapping("/comments") //comments?qnaId=2063 POST 방식
    public ResponseEntity<String> write(@RequestBody QnaCommentDto qnaCommentDto, Integer qnaId, HttpSession session) {
        //RequestBody - json로 받은 걸 java 객체로 변환
        session.setAttribute("memberId", 1001);
        Integer memberId = (Integer) session.getAttribute("memberId");

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
    // 지정된 댓글을 삭제하는 메서드
    @DeleteMapping("/comments/{answerId}") //comments/1 <-- 삭제할 댓글 번호
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
    }

    // 지정된 게시물의 모든 댓글을 가져오는 메서드
    @GetMapping("/comments") //comments?qnaId=2063 GET방식
    //ResponseEntity가 없을 때는 실패든 성공이든 항상 200으로 가기 때문에
    // 따로 지정하고 상태까지 같이 넘겨준다
    @ResponseBody public ResponseEntity<List<QnaCommentDto>> list(Integer qnaId) {
        List<QnaCommentDto> list = null;
        try {
            list = qnaCommentService.getListByQnaId(qnaId);
            return new ResponseEntity<List<QnaCommentDto>>(list, HttpStatus.OK); //200

        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<List<QnaCommentDto>>(HttpStatus.BAD_REQUEST); //400
    }

}
