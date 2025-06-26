package com.fastcampus.shop.dto;

import lombok.*;

import java.util.Date;
import java.util.Objects;

@Getter
@ToString
@EqualsAndHashCode(of = "productId")
@NoArgsConstructor
@AllArgsConstructor
public class ProductListDto {

    private Integer productId;
    private String productName;
    private String productDescription;
    private String productCategory;
    private Integer productPrice;
    private byte[] productImage;
    private Integer productSalesVolume;
    private Date productPostedAt;
    private String imagePath;

}
