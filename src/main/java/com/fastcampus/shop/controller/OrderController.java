package com.fastcampus.shop.controller;

import com.fastcampus.shop.dto.MemberDto;
import com.fastcampus.shop.dto.ProductListDto;
import com.fastcampus.shop.dto.ReceiverInfoDto;
import com.fastcampus.shop.service.MemberService;
import com.fastcampus.shop.service.ProductListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/order")
public class OrderController {

    @Autowired
    ProductListService productListService;
    @Autowired
    MemberService memberService;

    @PostMapping
    public String orderProduct(
            @RequestParam("productId") Long productId,
            @RequestParam("quantity") int quantity,
            @RequestParam(value = "addrType", defaultValue = "member") String addrType,
            HttpSession session,
            Model m) {
        System.out.println("productId = " + productId);
        System.out.println("quantity = " + quantity);

        // 세션에 가짜 로그인 정보 삽입
        MemberDto mockLoginMember = new MemberDto();
        mockLoginMember.setMemberId(1002L); // 실제 DB에 있는 회원 ID
        session.setAttribute("loginMember", mockLoginMember);

        MemberDto sessionMember = (MemberDto) session.getAttribute("loginMember");

        MemberDto member = memberService.getMemberById(sessionMember.getMemberId());

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

        ProductListDto product = productListService.getProductById(productId);

        m.addAttribute("member", member);
        m.addAttribute("receiverInfo", receiver);
        m.addAttribute("product", product);
        m.addAttribute("quantity", quantity);

        return "order";
    }
}
