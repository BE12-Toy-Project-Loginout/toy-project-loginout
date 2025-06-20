package com.fastcampus.shop.dao;

import com.fastcampus.shop.dto.ProductListDto;
import com.fastcampus.shop.dto.ProductListImageDto;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class ProductListDao {

    @Autowired
    private SqlSession sqlSession;

    private static String namespace = "com.fastcampus.shop.dao.ProductListMapper.";

    public int count() throws Exception{
        return sqlSession.selectOne(namespace+"count");
    }

    public int filteredCount(Map<String, Object> countMap) {
        return sqlSession.selectOne(namespace+"filteredCount", countMap);
    }

    public List<ProductListDto> selectPage(Map map) throws Exception{
        return sqlSession.selectList(namespace+"selectAllProductList", map);
    }

    public List<ProductListDto> selectFilteredSortedPage(Map<String, Object> map) throws Exception{
        return sqlSession.selectList(namespace+"selectFilteredSortedPage", map);
    }

    // 대표 이미지 ProductListImageDto에 담아서 가져오기
    public ProductListImageDto selectThumbnailImage(String productId) throws Exception {
        return sqlSession.selectOne(namespace + "selectThumbnailImage", productId);
    }

    public ProductListDto detailPage(String productId) {
        return sqlSession.selectOne(namespace + "detailPage", productId);
    }

    public List<ProductListDto> list() {
        return sqlSession.selectList(namespace + "list");
    }

    public int productInsert(ProductListDto productDetail) {
        return sqlSession.insert(namespace + "insert", productDetail);
    }

    public int productUpdate(ProductListDto productDetail) {
        return sqlSession.update(namespace + "update", productDetail);
    }

    public int productDelete(int productDetailId) {
        return sqlSession.delete(namespace + "delete", productDetailId);
    }
}

