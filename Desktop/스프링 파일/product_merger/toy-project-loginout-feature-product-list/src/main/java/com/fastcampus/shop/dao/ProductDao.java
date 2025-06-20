
package com.fastcampus.shop.dao;

import com.fastcampus.shop.productVO.ProductVO;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ProductDao {
    @Autowired
    private SqlSession sqlSession;

    private static final String NAMESPACE = "com.fastcampus.shop.dao.ProductMapper.";

    public ProductVO detailPage(String productId) {
        return sqlSession.selectOne(NAMESPACE + "detailPage", productId);
    }

    public List<ProductVO> list() {
        return sqlSession.selectList(NAMESPACE + "list");
    }

    public int productInsert(ProductVO productDetail) {
        return sqlSession.insert(NAMESPACE + "insert", productDetail);
    }

    public int productUpdate(ProductVO productDetail) {
        return sqlSession.update(NAMESPACE + "update", productDetail);
    }

    public int productDelete(int productDetailId) {
        return sqlSession.delete(NAMESPACE + "delete", productDetailId);
    }
}
