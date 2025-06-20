package com.fastcampus.shop.dao;

import com.fastcampus.shop.dto.ProductListDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/root-context.xml"})
public class ProductListDaoTest {
    @Autowired
    private ProductListDao productListDao;

    @Test
    public void testSelectPage() throws Exception {
        Map<String, Object> param = new HashMap<>();
        param.put("offset", 0);
        param.put("pageSize", 6);

        List<ProductListDto> list = productListDao.selectPage(param);
        assertNotNull("결과가 null이면 안됨", list);
        assertTrue("목록이 비어있음", list.size() > 0);

        for (ProductListDto dto : list) {
            System.out.println(dto);
        }
    }
}