package com.fastcampus.shop.service;

import com.fastcampus.shop.dao.OrderDao;
import com.fastcampus.shop.dto.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class OrderService {

    @Autowired
    ProductListService productListService;
    @Autowired
    OrderDao orderDao;

    public void saveOrder(MemberDto member, List<Integer> productIds, List<Integer> quantities, ReceiverInfoDto receiver) throws Exception {
        OrderDto order = new OrderDto();
        order.setMemberId(member.getMemberId());
        order.setReceiverName(receiver.getReceiverName());
        order.setReceiverEmail(receiver.getReceiverEmail());
        order.setReceiverPhone(receiver.getReceiverPhoneNumber());
        order.setReceiverAddress(receiver.getReceiverAddress());
        order.setReceiverAddressDetail(receiver.getReceiverAddressDetail());
        order.setCreatedAt(LocalDateTime.now());

        int totalQty = 0;
        int totalPrice = 0;

        for (int i = 0; i < productIds.size(); i++) {
            int quantity = quantities.get(i);
            ProductListDto product = productListService.getProductById(productIds.get(i));

            totalQty += quantity;
            totalPrice += product.getProductPrice() * quantity;
        }

        order.setTotalItemQty(totalQty);
        order.setTotalProductPrice(totalPrice);

        int result = orderDao.insertOrder(order);
        if (result > 0) {
            System.out.println("✅ 주문 저장 성공: orderId = " + order.getOrderId());
        } else {
            System.out.println("❌ 주문 저장 실패");
        }

        Integer orderId = order.getOrderId();

        for (int i = 0; i < productIds.size(); i++) {
            Integer productId = productIds.get(i);
            int quantity = quantities.get(i);
            ProductListDto product = productListService.getProductById(productId);

            OrderDetailDto orderDetail = new OrderDetailDto();
            orderDetail.setProductId(productId);
            orderDetail.setQuantity(quantity);
            orderDetail.setOrderId(orderId);
            orderDetail.setEachPrice(product.getProductPrice());

            int detailResult = orderDao.insertOrderDetail(orderDetail);
            if (detailResult > 0) {
                System.out.println("  ▶ 주문 상세 저장 완료: productId = " + productId);
            } else {
                System.out.println("  ❌ 주문 상세 저장 실패: productId = " + productId);
            }
        }
    }
}
