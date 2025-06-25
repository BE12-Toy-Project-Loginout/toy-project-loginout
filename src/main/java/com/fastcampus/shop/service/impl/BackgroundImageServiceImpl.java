package com.fastcampus.shop.service.impl;

import com.fastcampus.shop.dao.BackgroundImageMapper;
import com.fastcampus.shop.dto.BackgroundImage;
import com.fastcampus.shop.service.BackgroundImageService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service("backgroundImageService")
public class BackgroundImageServiceImpl implements BackgroundImageService {

    @Resource(name = "backgroundImageMapper")
    private BackgroundImageMapper backgroundImageMapper;

    @Override
    public BackgroundImage getMainBackgroundImage() {
        return backgroundImageMapper.getMainBackgroundImage();
    }
}
