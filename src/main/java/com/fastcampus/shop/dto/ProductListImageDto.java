package com.fastcampus.shop.dto;

import lombok.Getter;

import java.util.Arrays;
import java.util.Objects;

@Getter
public class ProductListImageDto {
    private Integer imageId;
    private Integer productId;
    private String filename;
    private String contentType;
    private byte[] imageData;
    private boolean isThumbnail;

    public ProductListImageDto() {}
    public ProductListImageDto(Integer imageId, Integer productId, String filename,
                               String contentType, byte[] imageData, boolean isThumbnail) {
        this.imageId = imageId;
        this.productId = productId;
        this.filename = filename;
        this.contentType = contentType;
        this.imageData = imageData;
        this.isThumbnail = isThumbnail;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        ProductListImageDto that = (ProductListImageDto) o;
        return Objects.equals(imageId, that.imageId) && Objects.equals(productId, that.productId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(imageId, productId);
    }

    @Override
    public String toString() {
        return "ProductImageDto{" +
                "imageId=" + imageId +
                ", productId=" + productId +
                ", filename='" + filename + '\'' +
                ", contentType='" + contentType + '\'' +
                ", imageData=" + Arrays.toString(imageData) +
                ", isThumbnail=" + isThumbnail +
                '}';
    }
}
