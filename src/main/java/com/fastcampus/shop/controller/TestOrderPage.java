package com.fastcampus.shop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class TestOrderPage {

    // 테스트용 컨트롤러 //
    // 테스트용 컨트롤러 //
    // 장바구니 -> [전체 상품 구매] 의 productId와 quantity(수량)을 받아옴 //

    @PostMapping("/order")
    public String orderAll(
            @RequestParam("productId") String productId,
            @RequestParam("quantity")  String quantity,
            @RequestParam("productId") List<String> productIds,
            @RequestParam("quantity")  List<Integer> quantities
    ) {

        System.out.println("productId = " + productId);
        System.out.println("quantity  = " + quantity);
        System.out.println("productIds = " + productIds);
        System.out.println("quantities = " + quantities);

        return "TestOrder";


    }
}
