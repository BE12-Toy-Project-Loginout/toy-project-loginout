package com.fastcampus.shop.dto;

import java.util.Date;
import java.util.Objects;

public class QnaCommentDto {
    private Integer answerId;
    private Integer panswerId;
    private Integer memberId;
    private String memberName;
    private Integer qnaId;
    private String answerContent;
    private Date createAt;
    private Date updateAt;

    public QnaCommentDto() { }

    public QnaCommentDto(Integer panswerId, Integer memberId, String memberName, Integer qnaId, String answerContent) {
        this.panswerId = panswerId;
        this.memberId = memberId;
        this.memberName = memberName;
        this.qnaId = qnaId;
        this.answerContent = answerContent;
    }

    public QnaCommentDto(Integer panswerId, Integer memberId, Integer qnaId, String answerContent) {
        this.panswerId = panswerId;
        this.memberId = memberId;
        this.qnaId = qnaId;
        this.answerContent = answerContent;
    }

    public Integer getAnswerId() {
        return answerId;
    }

    public void setAnswerId(Integer answerId) {
        this.answerId = answerId;
    }

    public Integer getPanswerId() {
        return panswerId;
    }

    public void setPanswerId(Integer panswerId) {
        this.panswerId = panswerId;
    }

    public Integer getMemberId() {
        return memberId;
    }

    public void setMemberId(Integer memberId) {
        this.memberId = memberId;
    }

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    public Integer getQnaId() {
        return qnaId;
    }

    public void setQnaId(Integer qnaId) {
        this.qnaId = qnaId;
    }

    public String getAnswerContent() {
        return answerContent;
    }

    public void setAnswerContent(String answerContent) {
        this.answerContent = answerContent;
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

    public void setUpdateAt(Date updateAt) {
        this.updateAt = updateAt;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        QnaCommentDto that = (QnaCommentDto) o;
        return Objects.equals(answerId, that.answerId) && Objects.equals(panswerId, that.panswerId) && Objects.equals(memberId, that.memberId) && Objects.equals(memberName, that.memberName) && Objects.equals(qnaId, that.qnaId) && Objects.equals(answerContent, that.answerContent);
    }

    @Override
    public int hashCode() {
        return Objects.hash(answerId, panswerId, memberId, memberName, qnaId, answerContent);
    }
}
