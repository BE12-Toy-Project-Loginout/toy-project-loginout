package com.fastcampus.shop.dto;


import lombok.Builder;
import lombok.Data;

@Data

public class CartDto {

        private String cartId;
        private long memberId;
        private String productDetailId;
        private String productId;
        private long productPrice;
        private String productName;
        private String sessionToken;
        private int quantity;
        private long totalPrice;
        private String status;
        private String createdAt;
        private String updatedAt;
        private String expiresAt;


}
