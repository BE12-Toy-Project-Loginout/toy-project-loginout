package com.fastcampus.shop.dto;


import lombok.*;

@Getter
@Setter
@EqualsAndHashCode(of = "productDetailId")
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ProductDetailDto {

    private Integer productDetailId;
    private Integer productId;
    private String productDetailPrice;
    private String productDetailQuantity;
    private String productDetailDescription;
    private String productCautionLevel;
    private String productName;
    private String productPrice;


}
