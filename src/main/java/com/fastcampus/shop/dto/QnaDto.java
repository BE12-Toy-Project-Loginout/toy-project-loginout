package com.fastcampus.shop.dto;

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
