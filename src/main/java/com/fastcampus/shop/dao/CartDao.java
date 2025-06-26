package com.fastcampus.shop.dao;

import com.fastcampus.shop.dto.CartDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CartDao {

    // 장바구니 목록 가져오기
    List<CartDto> getAllCart() throws Exception;

    // 장바구니에 상품 등록
  void insertCart(CartDto cartDto) throws Exception;

    // 장바구니에 수량 추가
    int updateCart(CartDto cartDto) throws Exception;

    // 장바구니에 상품 삭제
  void deleteCart(Long cartId) throws Exception;



}
