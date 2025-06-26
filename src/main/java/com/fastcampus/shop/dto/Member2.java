package com.fastcampus.shop.dto;

import lombok.Getter;
import lombok.Setter;
import java.util.Objects;

@Setter
@Getter
public class Member2 {
    private String id;
    private String pwd;
    private String verifyPwd;
    private String name;
    private String phone1;
    private String phone2;
    private String phone3;
    private String zipcode;
    private String address1;
    private String address2;
    private String email;

    public Member2(){}

    public String getFullPhone() { return phone1 + "-" + phone2 + "-" + phone3; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Member2 member = (Member2) o;
        return id.equals(member.id) && Objects.equals(name, member.name) && Objects.equals(phone1, member.phone1) && Objects.equals(phone2, member.phone2) && Objects.equals(phone3, member.phone3) && Objects.equals(email, member.email);
    }

    @Override
    public int hashCode() { return Objects.hash(id, name, phone1, phone2, phone3, email); }

    @Override
    public String toString() { return "Member{" + "id='" + id + '\'' + ", name='" + name + '\'' + ", fullAddress='" + '\'' +
            ", fullPhone='" + getFullPhone() + '\'' + ", email='" + email + '\'' + '}'; }

}
