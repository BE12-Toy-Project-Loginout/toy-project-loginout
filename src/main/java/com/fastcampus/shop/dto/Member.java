package com.fastcampus.shop.dto;

import java.util.Objects;

public class Member {
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

    public Member(){}

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getPwd() { return pwd; }
    public void setPwd(String pwd) { this.pwd = pwd; }

    public String getVerifyPwd() { return verifyPwd; }
    public void setVerifyPwd(String verifyPwd) { this.verifyPwd = verifyPwd; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getPhone1() { return phone1; }
    public void setPhone1(String phone1) { this.phone1 = phone1; }

    public String getPhone2() { return phone2; }
    public void setPhone2(String phone2) { this.phone2 = phone2; }

    public String getPhone3() { return phone3; }
    public void setPhone3(String phone3) { this.phone3 = phone3; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getFullPhone() { return phone1 + "-" + phone2 + "-" + phone3; }

    public String getAddress2() { return address2; }
    public void setAddress2(String address2) { this.address2 = address2; }

    public String getAddress1() { return address1; }
    public void setAddress1(String address1) { this.address1 = address1; }

    public String getZipcode() { return zipcode; }
    public void setZipcode(String zipcode) { this.zipcode = zipcode; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Member member = (Member) o;
        return id.equals(member.id) && Objects.equals(name, member.name) && Objects.equals(phone1, member.phone1) && Objects.equals(phone2, member.phone2) && Objects.equals(phone3, member.phone3) && Objects.equals(email, member.email);
    }

    @Override
    public int hashCode() { return Objects.hash(id, name, phone1, phone2, phone3, email); }

    @Override
    public String toString() { return "Member{" + "id='" + id + '\'' + ", name='" + name + '\'' + ", fullAddress='" + '\'' +
            ", fullPhone='" + getFullPhone() + '\'' + ", email='" + email + '\'' + '}'; }

}
