package com.fastcampus.shop.controller;

import com.fastcampus.shop.domain.PageHandler;
import com.fastcampus.shop.dto.ProductListDto;
import com.fastcampus.shop.service.ProductListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/product")
public class ProductListController {
    @Autowired
    ProductListService productListService;

    @GetMapping
    public String productList(Integer page, Integer pageSize, String category, String sort,
                              Model m, HttpServletRequest request) {

        if (page == null) page = 1;
        if (pageSize == null) pageSize = 6;

        // 카테고리 변경: 이전값과 다르면 sort를 "신상품"으로 초기화
        HttpSession session = request.getSession();
        String prevCategory = (String) session.getAttribute("prevCategory");

        if (category != null && !category.equals(prevCategory)) {
            sort = "신상품";
        }

        // 현재 카테고리를 세션에 저장 (다음 요청 대비)
        session.setAttribute("prevCategory", category);

        try {
            Map<String, Object> productList = productListService.getProductList(page, pageSize, category, sort);

            m.addAttribute("productList", productList.get("productList"));
            m.addAttribute("pageHandler", productList.get("pageHandler"));
            m.addAttribute("page", page);
            m.addAttribute("pageSize", pageSize);
            m.addAttribute("category", category);
            m.addAttribute("sort", sort);
            m.addAttribute("productCount", productList.get("totalCount"));
        } catch (Exception e){
            e.printStackTrace();
        }

        return "productList";
    }

    /*
    produces = 클라이언트에게 "이 응답은 JPEG 이미지입니다" 라고 알려줌
    여러 타입이 되도록 수정 필요     ToDo: @daamont
    */
    @GetMapping(value = "/image", produces = MediaType.IMAGE_JPEG_VALUE)
    public ResponseEntity<byte[]> getProductThumbnailImage(String productId) {
        System.out.println(">> 요청 도착: productId = " + productId); // 로그

        byte[] imageData;

        try {
            imageData = productListService.getThumbnailImage(productId);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }

        if (imageData == null || imageData.length == 0) {
            return ResponseEntity.noContent().build();  // 204 No Content
        }

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_JPEG); // 필요에 따라 PNG 등으로 변경

        return new ResponseEntity<>(imageData, headers, HttpStatus.OK);
    }

}
