package com.fastcampus.shop.dao;

import com.fastcampus.shop.dto.BackgroundImage;
import org.springframework.stereotype.Repository;

@Repository
public interface BackgroundImageMapper {
    BackgroundImage getMainBackgroundImage();
}
