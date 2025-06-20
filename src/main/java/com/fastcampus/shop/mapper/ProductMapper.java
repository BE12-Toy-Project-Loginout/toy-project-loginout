package com.fastcampus.shop.mapper;


import com.fastcampus.shop.ProductVO.ProductVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ProductMapper {

    ProductVO detailPage(String productId);

    // 전체 조회 (필요 시 페이지네이션 추가)
    List<ProductVO> list();

    // Insert (필요 시 사용)
    int insert(ProductVO productDetail);

    // Update (필요 시)
    int update(ProductVO productDetail);

    // Delete (필요 시)
    int delete(int productDetailId);
}


