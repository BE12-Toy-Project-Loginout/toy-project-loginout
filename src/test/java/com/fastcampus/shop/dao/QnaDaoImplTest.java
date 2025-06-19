package com.fastcampus.shop.dao;

import com.fastcampus.shop.dto.QnaDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/root-context.xml"})
@Transactional  // 모든 테스트는 트랜잭션 안에서 실행됨
public class QnaDaoImplTest {

    @Autowired
    QnaDao qnaDao;

    @Test
    @Rollback  // 테스트 후 롤백 (DB에 반영 안 됨)
    public void insert() {
        QnaDto dto = new QnaDto();
        dto.setQnaCategory("배송문의");
        dto.setTitle("배송 언제 오나요?");
        dto.setContent("이 제품 배송이 늦어지고 있어요.");
        dto.setIsSecret(false);
        dto.setMemberId(1001);
        dto.setProductId(2001);

        qnaDao.insert(dto);
        System.out.println("삽입 성공");
    }

    @Test
    public void findAll() {
        List<QnaDto> list = qnaDao.findAll();
        assertNotNull(list);
        for (QnaDto dto : list) {
            System.out.println(dto);
        }
    }

    @Test
    public void findById() {
        QnaDto dto = qnaDao.findById(1);
        assertNotNull(dto);
        assertEquals(Integer.valueOf(1), dto.getQnaId());
        System.out.println(dto);
    }

    @Test
    public void findByMemberId() {
        List<QnaDto> list = qnaDao.findByMemberId(1001);
        assertNotNull(list);
        System.out.println(list);
    }

    @Test
    @Rollback
    public void update() {
        QnaDto dto = new QnaDto();
        dto.setQnaId(1); // 존재하는 ID로 테스트
        dto.setTitle("수정된 제목");
        dto.setContent("수정된 내용");
        dto.setQnaCategory("교환문의");
        dto.setIsSecret(true);
        dto.setPassword("1234");

        qnaDao.update(dto);
        System.out.println("수정 성공");
    }

    @Test
    @Rollback
    public void delete() {
        qnaDao.delete(1); // 존재하는 ID 사용
        System.out.println("삭제 성공");
    }
}
