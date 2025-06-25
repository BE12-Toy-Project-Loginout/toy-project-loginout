package com.fastcampus.shop.dao;

import com.fastcampus.shop.dto.CartDto;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CartDaoImpl implements CartDao {

    @Autowired
    private SqlSession sqlSession;
    private static final String NAMESPACE = "com.fastcampus.shop.dao.CartDao.";

    @Override
    public List<CartDto> getAllCart() throws Exception {
        return sqlSession.selectList(NAMESPACE + "getAllCart");
    }

    @Override
    public void insertCart(CartDto cartDto) throws Exception {
        sqlSession.insert(NAMESPACE + "insertCart", cartDto);
    }

    @Override
    public int updateCart(CartDto cartDto) throws Exception {
        return sqlSession.update(NAMESPACE + "updateCart", cartDto);
    }

    @Override
    public void deleteCart(Long cartId) throws Exception {
        sqlSession.delete(NAMESPACE + "deleteCart", cartId);
    }

}