package com.fastcampus.shop.ProductVO;


public class ProductVO {

    private String productDetailId;
    private String productId;
    private String productDetailPrice;
    private String productDetailQuantity;
    private String productDetailDescription;
    private String productCautionLevel;
    private String productName;
    private String productPrice;
    //lombok을 쓰지 않으므로, 수동으로 getter/setter를 입력해야 함



    //생성자
    public ProductVO() {}


    // get set 세팅

    public String getProductPrice() {
        return productPrice;
    }
    public void setProductPrice(String productPrice) {
        this.productPrice = productPrice;
    }



    public String getProductName() {
        return productName;
    }
    public void setProductName(String productName) {
        this.productName = productName;
    }



    public String getProductId() {
        return productId;
    }
    public void setProductId(String productId) {
        this.productId = productId;
    }



    public String getProductDetailId() {
        return productDetailId;
    }
    public void setProductDetailId(String productDetailId) {
        this.productDetailId = productDetailId;
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
        this.productDetailQuantity=productDetailQuantity;
    }


    public String getProductDetailDescription() {
        return productDetailDescription;
    }
    public void setProductDetailDescription(String productDetailDescription) {
        this.productDetailDescription=productDetailDescription;
    }





    public String getProductCautionLevel() {
        return productCautionLevel;
    }
    public void setProductCautionLevel(String productCautionLevel) {
        this.productCautionLevel=productCautionLevel;
    }
}


