// src/main/java/com/fastcampus/shop/controller/ProductDetailController.java
package com.fastcampus.shop.controller;

import com.fastcampus.shop.dto.ProductDetailDto;
import com.fastcampus.shop.service.ProductDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/product")
public class CartController {

    @Autowired
    ProductDetailService productdetailservice;

    @GetMapping("/cart")
    public String detail(@PathVariable String productId, Model model) {
        ProductDetailDto detailPage = productdetailservice.getProductDetail(productId);
        model.addAttribute("productDetail", detailPage);
        return "productDetail";
    }


}
