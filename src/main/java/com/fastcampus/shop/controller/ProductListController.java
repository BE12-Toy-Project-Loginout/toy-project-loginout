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
                              Model m, HttpServletRequest resquest) {

        if (page == null) page = 1;
        if (pageSize == null) pageSize = 6;

        try {
            // 페이지 count
            Map<String, Object> countMap = new HashMap<>();
            if (category != null && !"전체보기".equals(category)) {
                countMap.put("product_category", category);
            }

            int totalCount = (category != null && !"전체보기".equals(category))
                    ? productListService.getFilteredCount(countMap)
                    : productListService.getCount();

            PageHandler pageHandler = new PageHandler(totalCount, page, pageSize);

            // 조건 조회
            Map<String, Object> map = new HashMap<>();
            map.put("offset", (page -1)*pageSize);
            map.put("pageSize", pageSize);

            if (category != null && !"전체보기".equals(category)) {
                map.put("product_category", category);
            }

            if (sort != null){
                switch (sort){
                    case "신상품": map.put("orderColumn", "product_posted_at"); map.put("orderDir", "DESC"); break;
                    case "상품명": map.put("orderColumn", "product_name"); map.put("orderDir", "ASC"); break;
                    case "낮은가격": map.put("orderColumn", "product_price"); map.put("orderDir", "ASC"); break;
                    case "높은가격": map.put("orderColumn", "product_price"); map.put("orderDir", "DESC"); break;
                    case "인기상품": map.put("orderColumn", "product_sales_volume"); map.put("orderDir", "DESC"); break;
                }
            }
            System.out.println("파라미터 맵: " + map);

            List<ProductListDto> productList;
            if (category !=null || sort != null){
                productList = productListService.getFilteredSortedPage(map);
            } else {
                productList = productListService.getPage(map);
            }

            m.addAttribute("productList", productList);
            m.addAttribute("pageHandler", pageHandler);
            m.addAttribute("page", page);
            m.addAttribute("pageSize", pageSize);
            m.addAttribute("category", category);
            m.addAttribute("sort", sort);
            m.addAttribute("productCount", totalCount);
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
