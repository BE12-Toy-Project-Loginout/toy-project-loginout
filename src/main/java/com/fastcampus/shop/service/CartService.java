package com.fastcampus.shop.service;

import com.fastcampus.shop.dao.CartDao;
import com.fastcampus.shop.dto.CartDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CartService {

    @Autowired
    private CartDao cartDao;

    public List<CartDto> getAllCart() throws Exception {
        return cartDao.getAllCart();
    }

    public List<CartDto> getMyCart(Long memberId) throws Exception {
        return cartDao.getMyCart(memberId);
    }

    public List<CartDto> getBySessionToken(String sessionToken) throws Exception {
        return cartDao.getBySessionToken(sessionToken);
    }



//    public void insertCart(CartDto cartDto) throws Exception {
//        cartDao.insertCart(cartDto); // CartDto 객체를 받아서 DB에 삽입
//    }

    public void deleteCart(Long cartId) throws Exception {
        cartDao.deleteCart(cartId);
    }

//    public void updateCart(CartDto cartDto) throws Exception {
//        int updated = cartDao.updateCart(cartDto);
//        if (updated == 0) {
//            cartDao.insertCart(cartDto);
//        }
//    }



//update+insert
@Transactional
    public void upsertCart(CartDto dto) throws Exception {
        int updated = cartDao.updateCart(dto);
    System.out.println("▶ updateCart returned: " + updated);

        if (updated == 0) {
    System.out.println("▶ insertCart 실행");
            cartDao.insertCart(dto);
        }
    }




}
