package com.fastcampus.shop.dao;

import com.fastcampus.shop.dto.ProductListDto;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@Repository
public class ProductListDao {

    @Autowired
    private SqlSession sqlSession;

    private static String namespace = "com.fastcampus.shop.dao.ProductListMapper.";

//    public List<ProductListDto> selectProductList() throws Exception {
//        return sqlSession.selectList(namespace+".selectAllProductList");
//    }

    public int count() throws Exception{
        return sqlSession.selectOne(namespace+"count");
    }

    public int filteredCount(Map<String, Object> countMap) {
        return sqlSession.selectOne(namespace+"filteredCount", countMap);
    }

    public List<ProductListDto> selectPage(Map map) throws Exception{
        return sqlSession.selectList(namespace+"selectAllProductList", map);
    }

    public List<ProductListDto> selectFilteredSortedPage(Map<String, Object> map) {
        return sqlSession.selectList(namespace+"selectFilteredSortedPage", map);
    }
}
