package com.fastcampus.shop.service;

import com.fastcampus.shop.dao.QnaCommentDao;
import com.fastcampus.shop.dao.QnaDao;
import com.fastcampus.shop.dto.QnaCommentDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.*;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/root-context.xml"})
public class QnaCommentServiceImplTest {

    @Autowired
    QnaCommentService qnaCommentService;
    @Autowired
    QnaCommentDao qnaCommentDao;
    @Autowired
    QnaDao qnaDao;

    @Test
    public void getCount() {
        try {
            int cnt = qnaCommentService.getCount(2065);
            System.out.println("cnt = " + cnt);
            assertTrue(cnt == 1);
        } catch (Exception e) {
            e.printStackTrace();
            fail();
        }
    }

    @Test
    public void remove() throws Exception {
        int rowCnt = qnaCommentService.remove(1, 2063, 1001);
        System.out.println("rowCnt = " + rowCnt);
        assertTrue(rowCnt == 0);
        assertTrue(qnaDao.findById(2063).getCommentCnt() == 1);

        // 일부러 예외를 발생시키고 Tx가 취소되는지 확인해야.
        /*rowCnt = qnaCommentService.remove(0, 0, 0);
        assertTrue(rowCnt==1);*/

    }

    @Test
    //@Rollback(true)
    public void write() throws Exception {
        QnaCommentDto qnaCommentDto = new QnaCommentDto(null, 1005, 255, "new answer content");
        int rowCnt = qnaCommentService.write(qnaCommentDto);
        System.out.println("rowCnt = " + rowCnt);
        assertTrue(rowCnt == 1);
        //assertTrue(qnaCommentDao.count(2063) == 3);

    }

    @Test
    public void getListByQnaId() {
        try {
            List<QnaCommentDto> list = qnaCommentService.getListByQnaId(2065);
            System.out.println("list = " + list);
            assertTrue(list.size() == 1);
        } catch (Exception e) {
            e.printStackTrace();
            fail();
        }
    }

    @Test
    public void getById() {
        try {
            QnaCommentDto qnaCommentDto = qnaCommentService.getById(20);
            System.out.println("qnaCommentDto = " + qnaCommentDto);
            assertTrue(qnaCommentDto.getAnswerId() == 20);
        } catch (Exception e) {
            e.printStackTrace();
            fail();
        }
    }

    @Test
    public void modify() {
        try {
            QnaCommentDto qnaCommentDto = qnaCommentService.getById(20);
            qnaCommentDto.setAnswerContent("new answer content");
            int rowCnt = qnaCommentService.modify(qnaCommentDto);
            assertTrue(rowCnt == 1);
        } catch (Exception e) {
            e.printStackTrace();
            fail();
        }
    }
}