// src/main/java/com/fastcampus/shop/controller/ProductDetailController.java
package com.fastcampus.shop.controller;

import com.fastcampus.shop.dto.ProductDetailDto;

import com.fastcampus.shop.service.ProductDetailService;

import com.fastcampus.shop.dto.ProductListDto;
import com.fastcampus.shop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;


@Controller
@RequestMapping("/product")
public class ProductDetailController {

    @Autowired
    ProductDetailService productdetailservice;

    @Resource(name = "userService")
    private UserService userService;

    @GetMapping("/detail/{productId}")
    public String detail(@PathVariable String productId, Model model, HttpServletRequest request) {
        ProductDetailDto detailPage = productdetailservice.getProductDetail(productId);
        model.addAttribute("productDetail", detailPage);

        // Set user attributes in model for sidebar login/logout button
        userService.setUserAttributesInModel(request, model);

        return "productDetail";
    }


}
