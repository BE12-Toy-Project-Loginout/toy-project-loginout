package com.fastcampus.shop.service;

import com.fastcampus.shop.dao.ProductDao;
import com.fastcampus.shop.dto.ProductDetailDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductDetailService {
    @Autowired
    ProductDao productDao;

    public ProductDetailDto getProductDetail(String productId) {
        return productDao.detailPage(productId);

    }

}
