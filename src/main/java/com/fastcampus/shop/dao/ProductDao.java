package com.fastcampus.shop.dao;

import com.fastcampus.shop.dto.ProductDetailDto;
import com.fastcampus.shop.dto.ProductListDto;
import com.fastcampus.shop.dto.ProductListImageDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface ProductDao {
    int count() throws Exception;

    int filteredCount(Map<String, Object> countMap) throws Exception;

    List<ProductListDto> selectAllProductList(Map map) throws Exception;

    List<ProductListDto> selectFilteredSortedPage(Map<String, Object> map) throws Exception;

    // 대표 이미지 ProductListImageDto에 담아서 가져오기
    ProductListImageDto selectThumbnailImage(String productId) throws Exception;

    ProductDetailDto detailPage(String productId);

}
