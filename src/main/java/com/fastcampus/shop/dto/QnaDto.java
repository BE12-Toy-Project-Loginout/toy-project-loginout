package com.fastcampus.shop.dto;

<<<<<<< HEAD
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
    private int viewCnt;
    private int commentCnt;

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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(Date updateAt) {
        this.updateAt = updateAt;
    }

    public int getViewCnt() {
        return viewCnt;
    }

    public void setViewCnt(int viewCnt) {
        this.viewCnt = viewCnt;
    }

    public int getCommentCnt() {
        return commentCnt;
    }

    public void setCommentCnt(int commentCnt) {
        this.commentCnt = commentCnt;
    }

    @Override
    public String toString() {
        return "QnaDto{" +
                "qnaId=" + qnaId +
                ", memberId=" + memberId +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", createAt=" + createAt +
                ", qnaCategory='" + qnaCategory + '\'' +
                ", isSecret=" + isSecret +
                ", productId=" + productId +
                ", password='" + password + '\'' +
                ", updateAt=" + updateAt +
                ", viewCnt=" + viewCnt +
                ", commentCnt=" + commentCnt +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        QnaDto qnaDto = (QnaDto) o;
        return Objects.equals(qnaId, qnaDto.qnaId) && Objects.equals(memberId, qnaDto.memberId) && Objects.equals(title, qnaDto.title) && Objects.equals(content, qnaDto.content) && Objects.equals(qnaCategory, qnaDto.qnaCategory) && Objects.equals(isSecret, qnaDto.isSecret) && Objects.equals(productId, qnaDto.productId) && Objects.equals(password, qnaDto.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(qnaId, memberId, title, content, qnaCategory, isSecret, productId, password);
    }
}
=======
import lombok.*;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class QnaDto {

    private int qnaId;
    private String qnaCategory;
    private String title;
    private String content;
    private int isSecret;
    private String password;
    private Date createAt;
    private int viewCnt;
    private int commentCnt;
    private String qnaImage;
    private int memberId;
    private int productId;
    private Date updateAt;

    // 게시글 작성 시 사용하는 생성자
    public QnaDto(String title, String content) {
        this.title = title;
        this.content = content;
    }

}
>>>>>>> develop
