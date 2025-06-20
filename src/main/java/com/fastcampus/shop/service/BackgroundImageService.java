package com.fastcampus.shop.service;

import com.fastcampus.shop.dto.BackgroundImage;

public interface BackgroundImageService {

    /**
     * 메인 배경 이미지를 조회합니다
     * @return 메인 배경 이미지 객체
     */
    BackgroundImage getMainBackgroundImage();
}
