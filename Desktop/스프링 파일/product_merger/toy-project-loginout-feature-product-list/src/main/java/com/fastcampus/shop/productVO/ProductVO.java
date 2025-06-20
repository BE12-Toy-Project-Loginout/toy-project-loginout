// src/main/java/com/fastcampus/shop/ProductVO/ProductVO.java
package com.fastcampus.shop.productVO;

public class ProductVO {
    private String productDetailId;
    private String productId;
    private String productDetailPrice;
    private String productDetailQuantity;
    private String productDetailDescription;
    private String productCautionLevel;
    private String productName;
    private String productPrice;
    private String imageUrl; // 이미지 URL 필드가 필요하면 추가

    public ProductVO() {}

    public String getProductDetailId() {
        return productDetailId;
    }
    public void setProductDetailId(String productDetailId) {
        this.productDetailId = productDetailId;
    }

    public String getProductId() {
        return productId;
    }
    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductDetailPrice() {
        return productDetailPrice;
    }
    public void setProductDetailPrice(String productDetailPrice) {
        this.productDetailPrice = productDetailPrice;
    }

    public String getProductDetailQuantity() {
        return productDetailQuantity;
    }
    public void setProductDetailQuantity(String productDetailQuantity) {
        this.productDetailQuantity = productDetailQuantity;
    }

    public String getProductDetailDescription() {
        return productDetailDescription;
    }
    public void setProductDetailDescription(String productDetailDescription) {
        this.productDetailDescription = productDetailDescription;
    }

    public String getProductCautionLevel() {
        return productCautionLevel;
    }
    public void setProductCautionLevel(String productCautionLevel) {
        this.productCautionLevel = productCautionLevel;
    }

    public String getProductName() {
        return productName;
    }
    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductPrice() {
        return productPrice;
    }
    public void setProductPrice(String productPrice) {
        this.productPrice = productPrice;
    }

    public String getImageUrl() {
        return imageUrl;
    }
    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
