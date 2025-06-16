package com.fastcampus.shop.controller;

import com.fastcampus.shop.mapper.TestMapper;
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

/*@Controller
public class TestController {

    @GetMapping("/db-check")
    public void check(HttpServletResponse response) throws IOException {
        System.out.println(">>>>> /db-check 컨트롤러 호출됨");
        response.setContentType("text/plain;charset=UTF-8");
        response.getWriter().write("컨트롤러 정상작동");
    }
}*/
