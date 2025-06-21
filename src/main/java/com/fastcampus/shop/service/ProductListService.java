package com.fastcampus.shop.service;

import com.fastcampus.shop.dao.ProductDao;
import com.fastcampus.shop.domain.PageHandler;
import com.fastcampus.shop.dto.ProductListDto;
import com.fastcampus.shop.dto.ProductListImageDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ProductListService {

    @Autowired
    ProductDao productDao;

    public int getCount()  throws Exception{
        return productDao.count();
    }

    public int getFilteredCount(Map<String, Object> countMap) throws Exception {
        return productDao.filteredCount(countMap);
    }

    public List<ProductListDto> getPage(Map countMap) throws Exception{
        return productDao.selectAllProductList(countMap);
    }

    public List<ProductListDto> getFilteredSortedPage(Map<String, Object> countMap) throws Exception {
        return productDao.selectFilteredSortedPage(countMap);
    }

    // 대표 이미지 dto에 담아 온 다음에 byte타입으로 반환
    public byte[] getThumbnailImage(String productId) throws Exception {
        ProductListImageDto dto = productDao.selectThumbnailImage(productId);
        return dto != null ? dto.getImage_data() : null;
    }

    // 상품 가져와서 정렬
    public Map<String, Object> getProductList(Integer page, Integer pageSize, String category, String sort) throws Exception {
        // 페이지 count
        Map<String, Object> result = new HashMap<>();
        if (category != null && !"전체보기".equals(category)) {
            result.put("product_category", category);
        }

        int totalCount = (category != null && !"전체보기".equals(category))
                ? getFilteredCount(result)
                : getCount();

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
            productList = getFilteredSortedPage(map);
        } else {
            productList = getPage(map);
        }

        result.put("productList", productList);
        result.put("pageHandler", pageHandler);
        result.put("totalCount", totalCount);

        return result;
    }

}
