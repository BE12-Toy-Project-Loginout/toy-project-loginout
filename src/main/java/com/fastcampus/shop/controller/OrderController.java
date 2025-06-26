package com.fastcampus.shop.controller;

import com.fastcampus.shop.dto.MemberDto;
import com.fastcampus.shop.dto.ProductListDto;
import com.fastcampus.shop.dto.ReceiverInfoDto;
import com.fastcampus.shop.service.MemberService;
import com.fastcampus.shop.service.OrderService;
import com.fastcampus.shop.service.ProductListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/order")
public class OrderController {

    @Autowired
    ProductListService productListService;
    @Autowired
    MemberService memberService;
    @Autowired
    OrderService orderService;

    @PostMapping
    public String orderProduct(
            @RequestParam("productId") List<Integer> productIds,
            @RequestParam("quantity") List<Integer> quantities,
            @RequestParam(value = "addrType", defaultValue = "member") String addrType,
            HttpSession session,
            Model m) {
        System.out.println("productId = " + productIds);
        System.out.println("quantity = " + quantities);

        // 세션에 가짜 로그인 정보 삽입
//        MemberDto mockLoginMember = new MemberDto();
//        mockLoginMember.setMemberId(1001); // 실제 DB에 있는 회원 ID
//        session.setAttribute("loginMember", mockLoginMember);

        String loginId = (String) session.getAttribute("userLoginId");
        if (loginId == null) {
            m.addAttribute("message", "로그인이 필요합니다.");
            m.addAttribute("redirectUrl", "/login");
            return "redirectWithAlert"; // ✅ 위 JSP 이름과 일치해야 합니다.
        }

        MemberDto member = memberService.getMemberByLoginId(loginId);  // 여기가 핵심
        System.out.println("loginId in session = " + loginId);
        System.out.println("조회된 멤버: " + member); // 👉 null 이라면 쿼리 실패


        if (member == null) {
            throw new IllegalStateException("해당 loginId에 대한 회원 정보가 없습니다: " + loginId);}


        String phone = member.getMemberPhonenumber();
        if (phone != null && phone.contains("-")) {
            String[] parts = phone.split("-");
            if (parts.length == 3) {
                m.addAttribute("phone1", parts[0]);
                m.addAttribute("phone2", parts[1]);
                m.addAttribute("phone3", parts[2]);
            }
        }

        String email = member.getMemberEmail();
        if (email != null && email.contains("@")) {
            System.out.println("email = " + email);
            String[] parts = email.split("@");
            m.addAttribute("email1", parts[0]);
            m.addAttribute("email2", parts[1]);
        }

        ReceiverInfoDto receiver = new ReceiverInfoDto();

        if (addrType.equals("member")) {
            receiver.setReceiverName(member.getMemberName());
            receiver.setReceiverEmail(member.getMemberEmail());
            receiver.setReceiverPhoneNumber(member.getMemberPhonenumber());
            receiver.setReceiverZipcode(member.getMemberZipcode());
            receiver.setReceiverAddress(member.getMemberAddress());
            receiver.setReceiverAddressDetail(member.getMemberAddressDetail());
        }

        List<ProductListDto> products = new ArrayList<>();
        for (Integer productId : productIds) {
            ProductListDto product = productListService.getProductById(productId);
            products.add(product);
        }

        m.addAttribute("member", member);
        m.addAttribute("receiverInfo", receiver);
        m.addAttribute("products", products);
        m.addAttribute("quantities", quantities);
        m.addAttribute("productsIds", productIds);
        m.addAttribute("addrType", addrType);

        return "order";
    }

    @PostMapping("/complete")
    public String completeOrder(
            @RequestParam("productIds") List<Integer> productIds,
            @RequestParam("quantities") List<Integer> quantities,
            @RequestParam("receiver") String receiverName,
            @RequestParam("zipcode") String zipcode,
            @RequestParam("address1") String address1,
            @RequestParam("address2") String addressDetail,
            @RequestParam("phone1") String phone1,
            @RequestParam("phone2") String phone2,
            @RequestParam("phone3") String phone3,
            @RequestParam("email1") String email1,
            @RequestParam("email2") String email2,
            HttpSession session, RedirectAttributes redirectAttributes
    ) {

        String loginId = (String) session.getAttribute("userLoginId");
        MemberDto member = memberService.getMemberByLoginId(loginId);

        ReceiverInfoDto receiver = new ReceiverInfoDto();
        receiver.setReceiverName(receiverName);
        receiver.setReceiverZipcode(zipcode);
        receiver.setReceiverAddress(address1);
        receiver.setReceiverAddressDetail(addressDetail);
        receiver.setReceiverEmail(email1+"@"+email2);
        receiver.setReceiverPhoneNumber(phone1+"-"+phone2+"-"+phone3);

        // 주문 저장
        try {
            orderService.saveOrder(member, productIds, quantities, receiver);
        } catch (Exception e) {
            e.printStackTrace();
        }

        redirectAttributes.addFlashAttribute("productIds", productIds);
        redirectAttributes.addFlashAttribute("quantities", quantities);

        return "redirect:/order/complete";
    }

    @GetMapping("/complete")
    public String showCompletePage(@ModelAttribute("productIds") List<Integer> productIds,
                                   @ModelAttribute("quantities") List<Integer> quantities,
                                   Model model) {
        List<Map<String, Object>> orderedItems = new ArrayList<>();

        for (int i = 0; i < productIds.size(); i++) {
            Map<String, Object> item = new HashMap<>();
            item.put("id", productIds.get(i));
            item.put("name", productListService.getProductById(productIds.get(i)).getProductName());
            item.put("quantity", quantities.get(i));
            orderedItems.add(item);
        }

        model.addAttribute("orderedItems", orderedItems);
        return "orderComplete";
    }
}
