package com.fastcampus.shop.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@EqualsAndHashCode(of = "orderId")
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class OrderDto {
    private Integer orderId;
    private Integer memberId;
    private Integer totalProductPrice;
    private Integer totalOrderPrice;
    private Integer totalItemQty;
    private String receiverName;
    private String receiverPhone;
    private String receiverEmail;
    private String receiverAddress;
    private String receiverAddressDetail;
    private String receiverZipcode;
    private LocalDateTime createdAt;
}
