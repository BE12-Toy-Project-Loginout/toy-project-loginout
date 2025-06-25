package com.fastcampus.shop.dto;


import lombok.*;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class NoticeDto {

    private int noticeId;
    private String title;
    private String content;
    private int viewCount;
    private Date createAt;
    private int adminId;
    private int noticeOrder;
    private boolean pinned;

    public NoticeDto(String title, String content) {
        this.title = title;
        this.content = content;
    }

}
