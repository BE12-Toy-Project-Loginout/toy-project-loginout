package com.fastcampus.shop.dto;


import lombok.Data;

//ProductListDto에서 분리 함 .
@Data
public class ProductDetailDto {

    private String productDetailId;
    private String productId;
    private String productDetailPrice;
    private String productDetailQuantity;
    private String productDetailDescription;
    private String productCautionLevel;
    private String productName;
    private String productPrice;


}
