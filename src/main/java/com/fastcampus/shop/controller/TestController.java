package com.fastcampus.shop.controller;

import com.fastcampus.shop.dao.TestMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
public class TestController {

    @Autowired
    private TestMapper testMapper;

    @GetMapping("/db-check")
    public void checkDb(HttpServletResponse response) throws IOException {
        String result = testMapper.testConnection();
        response.setContentType("text/plain;charset=UTF-8");
        response.getWriter().write("DB 연결 결과: " + result);
    }
}


