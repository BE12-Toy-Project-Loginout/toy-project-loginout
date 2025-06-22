package com.fastcampus.shop.dto;

import java.util.Date;
import java.util.Objects;

public class QnaDto {
    private Integer qnaId;
    private Integer memberId;
    private String title;
    private String content;
    private Date createAt;
    private String qnaCategory;
    private Boolean isSecret;
    private Integer productId;
    private String password;
    private Date updateAt;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getQnaId() {
        return qnaId;
    }

    public void setQnaId(Integer qnaId) {
        this.qnaId = qnaId;
    }

    public Integer getMemberId() {
        return memberId;
    }

    public void setMemberId(Integer memberId) {
        this.memberId = memberId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }

    public Date getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAtAt(Date updateAt) {
        this.updateAt = updateAt;
    }

    public String getQnaCategory() {
        return qnaCategory;
    }

    public void setQnaCategory(String qnaCategory) {
        this.qnaCategory = qnaCategory;
    }

    public Boolean getIsSecret() {
        return isSecret;
    }

    public void setIsSecret(Boolean secret) {
        isSecret = secret;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }


}