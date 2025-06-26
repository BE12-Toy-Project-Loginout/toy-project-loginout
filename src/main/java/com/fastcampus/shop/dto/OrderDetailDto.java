package com.fastcampus.shop.dto;

import lombok.*;

@Getter
@Setter
@EqualsAndHashCode(of = "orderDetailId")
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class OrderDetailDto {
    private Integer orderDetailId;
    private Integer orderId;
    private Integer productId;
    private int quantity;
    private Integer eachPrice;

}
