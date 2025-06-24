package com.fastcampus.shop.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberDto {
    private Long memberId;
    private String memberLoginId;
    private String memberName;
    private String memberEmail;
    private String memberPhonenumber;
    private String memberZipcode;
    private String memberAddress;
    private String memberAddressDetail;
}
