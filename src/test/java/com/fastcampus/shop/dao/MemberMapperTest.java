package com.fastcampus.shop.dao;

import com.fastcampus.shop.dto.Member;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
        "file:src/main/webapp/WEB-INF/spring/applicationContext.xml"
})
public class MemberMapperTest {

    @Autowired
    private MemberMapper memberMapper;

    @Test
    public void testSelectMemberById() {
        String testId = "user1001";  // 실제 DB에 이 ID가 있어야 함
        Member member = memberMapper.selectMember(testId);

        assertNotNull("해당 ID의 회원이 존재하지 않습니다.", member);
        System.out.println("회원 이름: " + member.getName());
        System.out.println("회원 주소: " + member.getAddress1());
    }
}
