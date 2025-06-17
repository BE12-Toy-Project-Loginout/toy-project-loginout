package com.fastcampus.shop.controller;


import com.fastcampus.shop.dto.User;
import com.fastcampus.shop.service.UserService;
import javax.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ImportResource;
import org.springframework.jmx.export.annotation.ManagedResource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
//@RequestMapping(value = "/login")
public class LoginController {

    @Resource
    private UserService userService;

    @GetMapping("/login")
    public String loginView() {
        return "login";
    }


    @PostMapping ( value = "/loginCheck")
    @ResponseBody
    public boolean login(@RequestBody User user) {
        try {
            boolean loginAt = userService.validateUser(user);
            System.out.println("로그인 접근");
            return loginAt;
        } catch (Exception e) {
            System.err.println("Login error: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }
}
