package com.fastcampus.shop.dao;

import com.fastcampus.shop.dto.ProductListDto;
import com.fastcampus.shop.dto.ProductListImageDto;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

// Mapper 애너테이션 사용으로 필요 없어졌지만 기록을 위해 남겨둠
@Repository
public class ProductDaoImpl implements ProductDao {

    @Autowired
    private SqlSession sqlSession;

    private static String namespace = "com.fastcampus.shop.dao.ProductListMapper.";

    @Override
    public int count() throws Exception{
        return sqlSession.selectOne(namespace+"count");
    }

    @Override
    public int filteredCount(Map<String, Object> countMap) throws Exception {
        return sqlSession.selectOne(namespace+"filteredCount", countMap);
    }

    @Override
    public List<ProductListDto> selectAllProductList(Map map) throws Exception{
        return sqlSession.selectList(namespace+"selectAllProductList", map);
    }

    @Override
    public List<ProductListDto> selectFilteredSortedPage(Map<String, Object> map) throws Exception{
        return sqlSession.selectList(namespace+"selectFilteredSortedPage", map);
    }

    // 대표 이미지 ProductListImageDto에 담아서 가져오기
    @Override
    public ProductListImageDto selectThumbnailImage(String productId) throws Exception {
        return sqlSession.selectOne(namespace + "selectThumbnailImage", productId);
    }

    @Override
    public ProductListDto detailPage(String productId) {
        return sqlSession.selectOne(namespace + "detailPage", productId);
    }


}

