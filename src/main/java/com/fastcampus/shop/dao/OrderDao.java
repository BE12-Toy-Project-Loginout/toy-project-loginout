package com.fastcampus.shop.dao;

import com.fastcampus.shop.dto.OrderDetailDto;
import com.fastcampus.shop.dto.OrderDto;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface OrderDao {
    int insertOrder(OrderDto order) throws Exception;

    int insertOrderDetail(OrderDetailDto orderDetail) throws Exception;
}
