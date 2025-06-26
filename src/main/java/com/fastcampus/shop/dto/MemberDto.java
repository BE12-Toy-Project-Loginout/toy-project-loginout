package com.fastcampus.shop.dto;

import lombok.*;

@Getter
@Setter
@EqualsAndHashCode(of = "memberId")
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class MemberDto {
    private Integer memberId;
    private String memberLoginId;
    private String memberName;
    private String memberEmail;
    private String memberPhonenumber;
    private String memberZipcode;
    private String memberAddress;
    private String memberAddressDetail;
}
