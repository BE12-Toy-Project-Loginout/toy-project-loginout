package com.fastcampus.shop.service;

import com.fastcampus.shop.dao.MemberMapper2;
import com.fastcampus.shop.dto.Member2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.mail.internet.MimeMessage;
import java.util.Random;

@Service
public class MemberServiceImpl2 implements MemberService2 {

    private final MemberMapper2 memberMapper;
    private final BCryptPasswordEncoder passwordEncoder;
    private final JavaMailSender mailSender;

    @Autowired
    public MemberServiceImpl2(BCryptPasswordEncoder passwordEncoder,
                             MemberMapper2 memberMapper,
                             JavaMailSender mailSender) {
        this.passwordEncoder = passwordEncoder;
        this.memberMapper = memberMapper;
        this.mailSender = mailSender;
    }

    @Override
    public boolean isValidId(String id) throws Exception {
        return memberMapper.isDuplicateId(id) == 0;
    }

    @Override
    public boolean isValidEmail(String email) throws Exception {
        return memberMapper.isDuplicateEmail(email) == 0;
    }

    @Override
    public void registerMember(Member2 member) throws Exception {
        if (!memberMapper.isEmailVerified(member.getEmail())) {
            System.out.println("▶ 이메일 인증 실패");
            throw new IllegalStateException("이메일 인증이 완료되지 않았습니다.");
        }
//        String hashedPwd = passwordEncoder.encode(member.getPwd());
//        member.setPwd(hashedPwd);

        boolean hasAddress = member.getZipcode() != null && !member.getZipcode().isBlank()
                && member.getAddress1() != null && !member.getAddress1().isBlank();

        System.out.println("▶ 사용할 insert: " + (hasAddress ? "insertDetailedMember" : "insertSimpleMember"));

        if (hasAddress) {
            memberMapper.insertDetailedMember(member);  // 주소가 있을 경우
        } else {
            memberMapper.insertSimpleMember(member);    // 주소가 없을 경우
        }

        System.out.println("▶ 회원가입 완료");
    }

    // mailtrap에서 전송받을 인증코드
    private String createCode() {
        Random random = new Random();
        StringBuilder code = new StringBuilder();
        for (int i = 0; i < 6; i++) {
            code.append(random.nextInt(10));
        }
        return code.toString();
    }

    @Override
    public String sendVerificationEmail(String email) throws Exception {
        String authCode = createCode();

        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, false, "UTF-8");

        helper.setTo(email);
        helper.setSubject("회원가입 인증 코드");
        helper.setText("인증 코드는 다음과 같습니다: " + authCode, true);
        mailSender.send(message);
        memberMapper.insertEmailAuthCode(email, authCode);

        return authCode;
    }

    @Override
    public boolean verifyEmailCode(String email, String inputCode) throws Exception {
        String realCode = memberMapper.selectEmailAuthCode(email);

        System.out.println("입력 코드 : [" + inputCode + "]");
        System.out.println("DB 코드   : [" + realCode + "]");

        if (realCode != null && realCode.trim().equals(inputCode.trim())) {
            return true;
        }
        return false;
    }
}
