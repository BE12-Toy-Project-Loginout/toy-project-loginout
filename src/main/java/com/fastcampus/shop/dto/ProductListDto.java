package com.fastcampus.shop.dto;

import lombok.Data;
import lombok.Getter;

import java.util.Date;
import java.util.Objects;

@Getter
public class ProductListDto {

    private Integer productId;
    private String productName;
    private String productDescription;
    private String productCategory;
    private Integer productPrice;
    private byte[] productImage;
    private Integer productSalesVolume;
    private Date productPostedAt;

    public ProductListDto() {}
    public ProductListDto(Integer productId, String productName,
                          String productDescription, String productCategory,
                          Integer productPrice, byte[] productImage,
                          Integer productSalesVolume, Date productPostedAt) {
        this.productId = productId;
        this.productName = productName;
        this.productDescription = productDescription;
        this.productCategory = productCategory;
        this.productPrice = productPrice;
        this.productImage = productImage;
        this.productSalesVolume = productSalesVolume;
        this.productPostedAt = productPostedAt;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        ProductListDto that = (ProductListDto) o;
        return Objects.equals(productId, that.productId);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(productId);
    }

    @Override
    public String toString() {
        return "ProductListDto{" +
                "productId=" + productId +
                ", productName='" + productName + '\'' +
                ", productDescription='" + productDescription + '\'' +
                ", productCategory='" + productCategory + '\'' +
                ", productPrice=" + productPrice +
                ", productImage='" + productImage + '\'' +
                ", productSalesVolume=" + productSalesVolume +
                ", productPostedAt=" + productPostedAt +
                '}';
    }
}
