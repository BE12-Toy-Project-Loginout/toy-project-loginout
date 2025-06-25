package com.fastcampus.shop.dto;

public class Member {
    private Long memberId;
    private String memberLoginId;
    private String name; // 필요시 추가

    // Getters and Setters
    public Long getMemberId() {
        return memberId;
    }

    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }

    public String getMemberLoginId() {
        return memberLoginId;
    }

    public void setMemberLoginId(String memberLoginId) {
        this.memberLoginId = memberLoginId;
    }

    // 이름 등 다른 필드도 필요시 추가
}
