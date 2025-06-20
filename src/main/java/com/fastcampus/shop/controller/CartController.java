package com.fastcampus.shop.controller;

import com.fastcampus.shop.ProductDto.ProductDto;
import com.fastcampus.shop.mapper.ProductMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class CartController {

    @Autowired
    ProductMapper productMapper;


    @GetMapping("/cart/{productId}")
    public String cart(@PathVariable("productId") String productId, Model model) {


        ProductDto detailPage = productMapper.detailPage(productId);

        model.addAttribute("productDetail", detailPage);




        return "cart";
    }

}
