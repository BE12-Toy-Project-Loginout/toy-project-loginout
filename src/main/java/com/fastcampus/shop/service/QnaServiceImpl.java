package com.fastcampus.shop.service;
import com.fastcampus.shop.dao.QnaCommentDao;
import com.fastcampus.shop.dao.QnaDao;
import com.fastcampus.shop.dto.QnaDto;
import com.fastcampus.shop.dto.SearchCondition;
import org.apache.taglibs.standard.lang.jstl.test.beans.PublicBean1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class QnaServiceImpl implements QnaService {
    @Autowired
    QnaDao qnaDao;

    @Autowired
    QnaCommentDao qnaCommentDao;   // 댓글(답변) DAO 주입


    @Override
    public int getCount() throws Exception {
        return qnaDao.count();
    }

  /*  @Override
    public List<QnaDto> getPage(int page, int pageSize) throws Exception {
        int offset = (page - 1) * pageSize;
        Map<String, Object> params = new HashMap<>();
        params.put("offset", offset);
        params.put("pageSize", pageSize);
        return qnaDao.findPage(params);
    }*/

    @Override
    public List<QnaDto> getPage(Map map) throws Exception {
        return qnaDao.selectPage(map);
    }

    @Override
    public QnaDto read(Integer qnaId) throws Exception {
        //System.out.println("Service에서 받은 qnaId: " + qnaId); // 1번 로그
        QnaDto dto = qnaDao.findById(qnaId);
        qnaDao.increaseViewCnt(qnaId);
        //System.out.println("DAO에서 반환된 dto: " + dto); // 2번 로그
        return dto;

        /*return qnaDao.findById(qnaId);*/
    }
    @Override
    public int write(QnaDto dto) throws Exception {
        /*throw new Exception("test");*/
        return qnaDao.insert(dto);
    }
    @Override
    public int modify(QnaDto dto) throws Exception {
        return qnaDao.update(dto);
    }
    /*@Override
    public int delete(int qnaId) throws Exception {
        return qnaDao.delete(qnaId);
    }*/
    /*@Override
    public int remove(Integer qnaId, Integer memberId) throws Exception {
        return qnaDao.delete(qnaId, memberId);
    }*/
    @Override
    @Transactional  // 여러 DAO 호출 시 트랜잭션으로 감싼다
    public int remove(Integer qnaId, Integer memberId) throws Exception {
        // 1. 자식 테이블(댓글/답변) 먼저 삭제
        qnaCommentDao.deleteAll(qnaId);

        // 2. 부모 테이블(질문) 삭제
        return qnaDao.delete(qnaId, memberId);
    }


    @Override
    public List<QnaDto> getSearchResultPage(SearchCondition sc) throws Exception {
        return qnaDao.searchSelectPage(sc);
    }

    @Override
    public int getSearchResultCnt(SearchCondition sc) throws Exception {
        return qnaDao.searchResultCnt(sc);
    }

    @Override
    public int removeByAdmin(Integer qnaId) throws Exception {
        return qnaDao.deleteByAdmin(qnaId);
    }

    @Override
    public List<QnaDto> getAllWithMemberName() throws Exception {
        return qnaDao.findAllWithMemberName();
    }


}
