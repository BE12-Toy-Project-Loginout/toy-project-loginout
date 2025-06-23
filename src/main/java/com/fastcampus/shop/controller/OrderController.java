package com.fastcampus.shop.controller;

import com.fastcampus.shop.dto.ProductListDto;
import com.fastcampus.shop.service.ProductListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/order")
public class OrderController {

    @Autowired
    ProductListService productListService;

    @PostMapping
    public String orderProduct(
            @RequestParam("productId") Long productId,
            @RequestParam("quantity") int quantity,
            Model m) {
        System.out.println("productId = " + productId);
        System.out.println("quantity = " + quantity);

        ProductListDto product = productListService.getProductById(productId);

        m.addAttribute("product", product);
        m.addAttribute("quantity", quantity);
        return "order";
    }
}
