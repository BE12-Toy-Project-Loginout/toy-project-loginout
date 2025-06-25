package com.fastcampus.shop.service;

import com.fastcampus.shop.dao.QnaCommentDao;
import com.fastcampus.shop.dao.QnaDao;
import com.fastcampus.shop.dto.QnaCommentDto;
import com.fastcampus.shop.dto.QnaDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@Service
public class QnaCommentServiceImpl implements QnaCommentService {
    @Autowired
    QnaDao qnaDao;
    @Autowired
    QnaCommentDao qnaCommentDao;

    // 클래스 상단에 AdminService 주입 추가
    @Autowired
    private AdminService adminService;

//    @Autowired
//    public QnaCommentServiceImpl(QnaCommentDao qnaCommentDao, QnaDao qnaDao) {
//        this.qnaCommentDao = qnaCommentDao;
//        this.qnaDao = qnaDao;
//    }

    @Override
    public int getCount(Integer qnaId) throws Exception {
        return qnaCommentDao.count(qnaId);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public int remove(Integer answerId, Integer qnaId, Integer memberId) throws Exception {
        System.out.println("answerId: " + answerId);
        System.out.println("qnaId: " + qnaId);
        System.out.println("memberId: " + memberId);
        int rowCnt = qnaDao.updateCommentCnt(qnaId, -1);
        System.out.println("updateCommentCnt - rowCnt = " + rowCnt);
        //throw new Exception("test");
        rowCnt = qnaCommentDao.delete(answerId, memberId);
        System.out.println("rowCnt = " + rowCnt);
        return rowCnt;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public int write(QnaCommentDto qnaCommentDto) throws Exception {
        Integer qnaId = qnaCommentDto.getQnaId();
        QnaDto qnaDto = qnaDao.findById(qnaId);
        Integer writerId = qnaDto.getMemberId();
        Integer commentWriterId = qnaCommentDto.getMemberId();

        // 글 작성자 또는 관리자만 댓글 작성 가능
        if (!Objects.equals(writerId, commentWriterId) && !adminService.isAdmin(commentWriterId)) {
            return -1; // 실패
        }

        qnaDao.updateCommentCnt(qnaId, 1);
        return qnaCommentDao.insert(qnaCommentDto);
    }

    @Override
    public List<QnaCommentDto> getListByQnaId(Integer qnaId) throws Exception {
        return qnaCommentDao.findAll(qnaId);
    }

    @Override
    public QnaCommentDto getById(Integer answerId) throws Exception {
        return qnaCommentDao.findById(answerId);
    }

    @Override
    public int modify(QnaCommentDto qnaCommentDto) throws Exception {
        return qnaCommentDao.update(qnaCommentDto);
    }
}