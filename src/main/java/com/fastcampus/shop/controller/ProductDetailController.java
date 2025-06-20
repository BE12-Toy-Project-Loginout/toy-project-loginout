package com.fastcampus.shop.controller;

import com.fastcampus.shop.ProductVO.ProductVO;
import com.fastcampus.shop.mapper.ProductMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class ProductDetailController {


    private final ProductMapper productMapper;
    public ProductDetailController(ProductMapper productMapper) {
        this.productMapper = productMapper;
    }

    @GetMapping("/detail/{productId}")
    public String detail(@PathVariable("productId") String productId, HttpServletRequest request, Model model) {


          ProductVO detailPage = productMapper.detailPage(productId);

           model.addAttribute("productDetail", detailPage);


        return "ProductDetail";

    }

}
