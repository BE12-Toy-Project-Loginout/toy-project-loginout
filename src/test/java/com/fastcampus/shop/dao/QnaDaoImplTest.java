package com.fastcampus.shop.dao;

import com.fastcampus.shop.dto.QnaDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/root-context.xml"})
@Transactional  // 모든 테스트는 트랜잭션 안에서 실행됨
public class QnaDaoImplTest {

    @Autowired
    QnaDao qnaDao;

    /*@Test
    @Transactional
    @Rollback
    public void insert() throws Exception {
        QnaDto dto = new QnaDto();
        dto.setQnaCategory("배송문의");
        dto.setTitle("배송 언제 오나요?");
        dto.setContent("이 제품 배송이 늦어지고 있어요.");
        dto.setIsSecret(false);
        dto.setMemberId(1001);     // FK 대상이 존재해야 함
        dto.setProductId(1);    // FK 대상이 존재해야 함

        qnaDao.insert(dto);
        System.out.println("삽입 성공");
    }*/

/*    @Test
    @Transactional
    @Rollback(false)
    public void insertMultipleQnas() throws Exception {
        for (int i = 11; i <= 250; i++) {
            QnaDto dto = new QnaDto();
            dto.setQnaCategory("상품");
            dto.setTitle("문의 제목" + i);
            dto.setContent("문의 내용" + i);
            dto.setIsSecret(i % 2 == 0); // 짝수는 비밀글

            // member_id: 1001 ~ 1010만 사용 (총 10명)
            dto.setMemberId(1000 + ((i - 1) % 10) + 1);

            // product_id: 1 ~ 10 순환
            dto.setProductId(((i - 1) % 10) + 1);

            qnaDao.insert(dto);
        }
        System.out.println("240개의 QnA 데이터 삽입 성공 (member_id: 1001~1010, product_id: 1~10)");
    }*/


    @Test
    public void findAll() throws Exception  {
        List<QnaDto> list = qnaDao.findAll();
        assertNotNull(list);
        for (QnaDto dto : list) {
            System.out.println(dto);
        }
    }

    @Test
    public void findById() throws Exception {
        QnaDto dto = qnaDao.findById(1);
        assertNotNull(dto);
        assertEquals(Integer.valueOf(1), dto.getQnaId());
        System.out.println(dto);
    }

    @Test
    public void findByMemberId() throws Exception {
        List<QnaDto> list = qnaDao.findByMemberId(1001);
        assertNotNull(list);
        System.out.println(list);
    }

    @Test
    @Rollback
    public void update() throws Exception {
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
    @Rollback(false)
    public void delete() throws Exception {
        qnaDao.delete(481, 1001); // 존재하는 ID 사용
        System.out.println("삭제 성공");
    }

    @Test
    @Transactional
    @Rollback
    public void deleteAllQnaTest() throws Exception {
        int deletedCount = qnaDao.deleteAll();
        System.out.println("삭제된 QnA 수: " + deletedCount);
        assertTrue(deletedCount >= 0);  // 삭제 수가 0 이상인지 확인
    }

    @Test
    @Transactional
    @Rollback(false)// DB에 실제 반영할 경우 false, 테스트 후 롤백할 경우 true
    public void insert240QnasWithAllFields() throws Exception {
        qnaDao.deleteAll();
        for (int i = 1; i <= 240; i++) {
            QnaDto dto = new QnaDto();
            dto.setQnaCategory("상품");
            dto.setTitle("문의 제목 " + i);
            dto.setContent("문의 내용 " + i);
            boolean isSecret = i % 2 == 0;
            dto.setIsSecret(isSecret);
            dto.setPassword(isSecret ? "1234" : null);  // 비밀번호 확인용
            System.out.println("[" + i + "] password = " + dto.getPassword());

            dto.setMemberId(1000 + ((i - 1) % 10) + 1);
            dto.setProductId(((i - 1) % 10) + 1);

            qnaDao.insert(dto);
        }

        int total = qnaDao.count();
        System.out.println("총 등록된 QnA 개수 = " + total);
        assertEquals(240, total);
    }

}
