// src/main/java/com/fastcampus/shop/controller/ProductDetailController.java
package com.fastcampus.shop.controller;

import com.fastcampus.shop.productVO.ProductVO;
import com.fastcampus.shop.dao.ProductDao;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/product")
public class ProductDetailController {

    private final ProductDao productDao;
    public ProductDetailController(ProductDao productDao) {
        this.productDao = productDao;
    }

    @GetMapping("/detail/{productId}")
    public String detail(@PathVariable String productId, Model model) {
        ProductVO detailPage = productDao.detailPage(productId);
        model.addAttribute("productDetail", detailPage);
        return "productDetail";
    }

    @GetMapping("/list")
    public String list(Model model) {
        // 필요시 페이징/필터 파라미터 추가
        model.addAttribute("products", productDao.list());
        return "productList";
    }
}
