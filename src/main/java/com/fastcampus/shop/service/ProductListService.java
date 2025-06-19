package com.fastcampus.shop.service;

import com.fastcampus.shop.dao.ProductListDao;
import com.fastcampus.shop.dto.ProductListDto;
import com.fastcampus.shop.dto.ProductListImageDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ProductListService {

    @Autowired
    ProductListDao productListDao;

    public int getCount()  throws Exception{
        return productListDao.count();
    }

    public int getFilteredCount(Map<String, Object> countMap) throws Exception {
        return productListDao.filteredCount(countMap);
    }

    public List<ProductListDto> getPage(Map countMap) throws Exception{
        return productListDao.selectPage(countMap);
    }

    public List<ProductListDto> getFilteredSortedPage(Map<String, Object> countMap) throws Exception {
        return productListDao.selectFilteredSortedPage(countMap);
    }

    // 대표 이미지 dto에 담아 온 다음에 byte타입으로 반환
    public byte[] getThumbnailImage(String productId) throws Exception {
        ProductListImageDto dto = productListDao.selectThumbnailImage(productId);
        return dto != null ? dto.getImage_data() : null;
    }

}
