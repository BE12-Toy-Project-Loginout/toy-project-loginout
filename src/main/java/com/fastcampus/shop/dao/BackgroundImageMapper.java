package com.fastcampus.shop.dao;

import com.fastcampus.shop.dto.BackgroundImage;
import org.springframework.stereotype.Repository;

@Repository
public interface BackgroundImageMapper {

    /**
     * 메인 배경 이미지를 조회합니다
     * @return 메인 배경 이미지 객체
     */
    BackgroundImage getMainBackgroundImage();
}
