package com.fastcampus.shop.service;


import com.fastcampus.shop.dto.User;
import org.springframework.stereotype.Service;

public interface UserService {

    public boolean validateUser(User user) throws Exception;
}
