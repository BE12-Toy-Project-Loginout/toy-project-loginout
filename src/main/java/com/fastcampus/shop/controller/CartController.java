package com.fastcampus.shop.controller;

import com.fastcampus.shop.dto.CartDto;
import com.fastcampus.shop.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private CartService cartService;

    @RequestMapping(value = "", method = {RequestMethod.GET, RequestMethod.POST})
    public String rootCart(HttpSession session) {
        Integer memberId = (Integer) session.getAttribute("memberId");
        return (memberId != null)
                ? "redirect:/cart/" + memberId
                : "redirect:/cart/guest";
    }


    @GetMapping("/{memberId}")
    public String showMemberCart(
            @PathVariable Long memberId,
            HttpSession session,
            Model model) throws Exception {

        Integer sessionMemberId = (Integer) session.getAttribute("memberId");
        if (sessionMemberId == null || !Long.valueOf(sessionMemberId).equals(memberId)) {
            return "redirect:/cart/guest";
        }

        List<CartDto> cartList = cartService.getMyCart(memberId);
        model.addAttribute("cartList", cartList);
        return "cart";
    }

    @GetMapping("/guest")
    public String showGuestCart(HttpSession session, Model model) throws Exception {
        String token = session.getId();
        List<CartDto> cartList = cartService.getBySessionToken(token);
        model.addAttribute("cartList", cartList);
        return "cart";
    }

    @PostMapping("/{memberId}")
    public String upsertCartForMember(
            @PathVariable Long memberId,
            @RequestParam String productId,
            @RequestParam String productName,
            @RequestParam String productPrice,
            @RequestParam String quantity,
            HttpSession session) throws Exception {

        Integer sessionMemberId = (Integer) session.getAttribute("memberId");
        if (sessionMemberId == null || !Long.valueOf(sessionMemberId).equals(memberId)) {
            return "redirect:/cart/guest";
        }

        int qty = Integer.parseInt(quantity.replaceAll("[^0-9]", ""));
        long price = Long.parseLong(productPrice.replaceAll("[^0-9]", ""));
        long total = price * qty;

        CartDto dto = new CartDto();
        dto.setMemberId(memberId);
        dto.setProductId(productId);
        dto.setProductName(productName);
        dto.setProductPrice(price);
        dto.setQuantity(qty);
        dto.setTotalPrice(total);
        dto.setSessionToken(null);  // 회원일 땐 sessionToken 사용 안 함

        cartService.upsertCart(dto);
        return "redirect:/cart/" + memberId;
    }

    @PostMapping("/guest")
    public String upsertCartForGuest(
            @RequestParam String productId,
            @RequestParam String productName,
            @RequestParam String productPrice,
            @RequestParam String quantity,
            HttpSession session) throws Exception {

        int qty = Integer.parseInt(quantity.replaceAll("[^0-9]", ""));
        long price = Long.parseLong(productPrice.replaceAll("[^0-9]", ""));
        long total = price * qty;

        CartDto dto = new CartDto();
//        dto.setMemberId();      // 비회원일 땐 memberId 사용 안 함
        dto.setProductId(productId);
        dto.setProductName(productName);
        dto.setProductPrice(price);
        dto.setQuantity(qty);
        dto.setTotalPrice(total);
        dto.setSessionToken(session.getId());

        cartService.upsertCart(dto);
        return "redirect:/cart/guest";
    }

    @PostMapping("/{memberId}/delete")
    public String deleteMemberCart(
            @PathVariable Long memberId,
            @RequestParam Long cartId,
            HttpSession session) throws Exception {

        Integer sessionMemberId = (Integer) session.getAttribute("memberId");
        if (sessionMemberId == null || !Long.valueOf(sessionMemberId).equals(memberId)) {
            return "redirect:/cart/guest";
        }

        cartService.deleteCart(cartId);
        return "redirect:/cart/" + memberId;
    }


    @PostMapping("/guest/delete")
    public String deleteGuestCart(@RequestParam Long cartId) throws Exception {
        cartService.deleteCart(cartId);
        return "redirect:/cart/guest";
    }
}
