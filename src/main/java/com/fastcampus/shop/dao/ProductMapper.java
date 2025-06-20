package com.fastcampus.shop.dao;


import com.fastcampus.shop.ProductDto.ProductDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ProductMapper {

    ProductDto detailPage(String productId);

    // 전체 조회 (필요 시 페이지네이션 추가)
    List<ProductDto> list();

    // Insert (필요 시 사용)
    int ProductInsert(ProductDto productDetail);

    // Update (필요 시)
    int ProductUpdate(ProductDto productDetail);

    // Delete (필요 시)
    int ProductDelete(int productDetailId);
}


