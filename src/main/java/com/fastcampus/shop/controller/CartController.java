package com.fastcampus.shop.controller;

import com.fastcampus.shop.dto.CartDto;
import com.fastcampus.shop.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class CartController {

    @Autowired
    private CartService cartService;


    @GetMapping("/cart")
    public String showCart(Model model) throws Exception {

        List<CartDto> cartList = cartService.getAllCart();

        model.addAttribute("cartList", cartList);
        return "cart";
    }



    @PostMapping("/cart/delete")
    public String deleteCart(@RequestParam("cartId") Long cartId) throws Exception {
        cartService.deleteCart(cartId);
        return "redirect:/cart";
    }

    @PostMapping("/cart")
    public String upsertCart(
            @RequestParam("productId")    String productId,
            @RequestParam("productName")  String productName,
            @RequestParam("productPrice") String productPriceRaw,
            @RequestParam("quantity")     String quantityRaw,
            HttpSession session ) throws Exception {

        String memberId = (String) session.getAttribute("memberId");
        String qtyDigits   = quantityRaw.replaceAll("[^0-9]", "");
        int    qty         = qtyDigits.isEmpty() ? 1 : Integer.parseInt(qtyDigits);

        String priceDigits = productPriceRaw.replaceAll("[^0-9]", "");
        long   price       = priceDigits.isEmpty() ? 0L : Long.parseLong(priceDigits);

        long total = price * qty;

        CartDto cartDto = new CartDto();
        cartDto.setMemberId(memberId);
        cartDto.setProductId(productId);
        cartDto.setProductName(productName);
        cartDto.setProductPrice(price);
        cartDto.setQuantity(qty);
        cartDto.setTotalPrice(total);


        // 서비스 쪽에서 updateCart()를 먼저 시도하고,
        // 결과가 0이면 insertCart()를 수행하도록 처리
        cartService.upsertCart(cartDto);


        System.out.println(memberId);
        System.out.println(productId);
        System.out.println(productName);
        System.out.println(price);
        System.out.println(qty);
        System.out.println(total);

        return "redirect:/cart";
    }

}