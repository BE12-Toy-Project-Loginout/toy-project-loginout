package com.fastcampus.shop.dao;

import com.fastcampus.shop.dto.QnaCommentDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;
import java.util.Objects;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/root-context.xml"})
public class QnaCommentDaoImplTest {
    @Autowired
    QnaCommentDao qnaCommentDao;

    @Test
    public void count() throws Exception {
        qnaCommentDao.deleteAll(1);
        assertTrue(qnaCommentDao.count(1)==0);
    }

    @Test
    @Rollback(true)
    public void delete() throws Exception {
        qnaCommentDao.deleteAll(2063);
       /* QnaCommentDto qnaCommentDto = new QnaCommentDto(1, 1001, 2063, "new answer content");
        assertTrue(qnaCommentDao.insert(qnaCommentDto)==1);
        assertTrue(qnaCommentDao.count(2063)==1);*/
    }

    @Test
    @Rollback(true)
    public void insert() throws Exception {
        qnaCommentDao.deleteAll(2064);
        QnaCommentDto qnaCommentDto = new QnaCommentDto(0, 1003, 2065, "new answer content");
        assertTrue(qnaCommentDao.insert(qnaCommentDto)==1);
        assertTrue(qnaCommentDao.count(2065)==1);

       /* qnaCommentDto = new QnaCommentDto(1, 1001, 2063, "new answer content");
        assertTrue(qnaCommentDao.insert(qnaCommentDto)==1);
        assertTrue(qnaCommentDao.count(1)==2);*/
    }

    @Test
    @Rollback(false)
    public void findAll() throws Exception {
        qnaCommentDao.deleteAll(2065);
        QnaCommentDto qnaCommentDto = new QnaCommentDto(0, 1003, 2065, "new answer content");
        assertTrue(qnaCommentDao.insert(qnaCommentDto)==1);
        assertTrue(qnaCommentDao.count(2065)==1);

        List<QnaCommentDto> list = qnaCommentDao.findAll(2065);
        assertTrue(list.size()==1);

       /* qnaCommentDto = new QnaCommentDto(1, 0, 2063, "new answer content");
        assertTrue(qnaCommentDao.insert(qnaCommentDto)==1);
        assertTrue(qnaCommentDao.count(1)==2);

        list = qnaCommentDao.findAll(1);
        assertTrue(list.size()==2);*/
    }

    @Test
    public void select() throws Exception {
        qnaCommentDao.deleteAll(2065);
        QnaCommentDto qnaCommentDto = new QnaCommentDto(0, 1003, 2065, "new answer content");
        assertTrue(qnaCommentDao.insert(qnaCommentDto)==1);
        assertTrue(qnaCommentDao.count(2065)==1);

        List<QnaCommentDto> list = qnaCommentDao.findAll(2065);
        String comment = list.get(0).getAnswerContent();
        // 안전하게 Integer 언박싱
        Integer commenter = list.get(0).getMemberId();
        assertTrue(comment.equals(qnaCommentDto.getAnswerContent()));
        assertTrue(Objects.equals(commenter, qnaCommentDto.getMemberId()));  // 안전하게 비교
    }

    @Test
    public void update() throws Exception {
        qnaCommentDao.deleteAll(2065);
        QnaCommentDto qnaCommentDto = new QnaCommentDto(0, 1003, 2065, "new answer content");
        assertTrue(qnaCommentDao.insert(qnaCommentDto)==1);
        assertTrue(qnaCommentDao.count(2065)==1);

        List<QnaCommentDto> list = qnaCommentDao.findAll(2065);
        qnaCommentDto.setAnswerId(list.get(0).getAnswerId());
        qnaCommentDto.setAnswerContent("comment2");
        assertTrue(qnaCommentDao.update(qnaCommentDto)==1);

        list = qnaCommentDao.findAll(2065);
        String comment = list.get(0).getAnswerContent();
        // 안전하게 Integer 언박싱
        Integer commenter = list.get(0).getMemberId();
        assertTrue(comment.equals(qnaCommentDto.getAnswerContent()));
        assertTrue(Objects.equals(commenter, qnaCommentDto.getMemberId()));  // 안전하게 비교
    }

}