package com.fastcampus.shop.dto;

import lombok.Data;
import lombok.Getter;

import java.util.Date;
import java.util.Objects;

@Data
public class ProductListDto {


    private Integer product_id;
    private String product_name;
    private String product_description;
    private String product_category;
    private Integer product_price;
    private byte[] product_image;
    private Integer product_sales_volume;
    private Date product_posted_at;

    public ProductListDto() {}
    public ProductListDto(Integer product_id, String product_name,
                          String product_description, String product_category,
                          Integer product_price, byte[] product_image,
                          Integer product_sales_volume, Date product_posted_at) {
        this.product_id = product_id;
        this.product_name = product_name;
        this.product_description = product_description;
        this.product_category = product_category;
        this.product_price = product_price;
        this.product_image = product_image;
        this.product_sales_volume = product_sales_volume;
        this.product_posted_at = product_posted_at;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        ProductListDto that = (ProductListDto) o;
        return Objects.equals(product_id, that.product_id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(product_id);
    }

    @Override
    public String toString() {
        return "ProductListDto{" +
                "productId=" + product_id +
                ", productName='" + product_name + '\'' +
                ", productDescription='" + product_description + '\'' +
                ", productCategory='" + product_category + '\'' +
                ", productPrice=" + product_price +
                ", productImage='" + product_image + '\'' +
                ", productSalesVolume=" + product_sales_volume +
                ", productPostedAt=" + product_posted_at +
                '}';
    }
}
