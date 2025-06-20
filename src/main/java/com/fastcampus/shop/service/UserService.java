package com.fastcampus.shop.service;


import com.fastcampus.shop.dto.User;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

public interface UserService {

    public User validateUser(User user) throws Exception;

    public void updateLastLogin(String userLoginId) throws Exception;

    public void logout() throws Exception;

    public int getLoginFailCount(User user) throws Exception;

    void incrementLoginFailCount(String userLoginId) throws Exception;

    void isLocked(User user) throws Exception;

    String getUserStatus(User user) throws Exception;

    /**
     * 세션 기반으로 공통 모델 속성을 설정하는 메서드
     * @param request 세션을 얻기 위한 HTTP 요청
     * @param model 속성을 추가할 모델 객체
     * @return 사용자가 로그인되어 있으면 true, 아니면 false
     */
    boolean setUserAttributesInModel(HttpServletRequest request, Model model);

    /**
     * 로그인 처리를 수행하는 메서드
     * @param user 로그인 시도하는 사용자 정보
     * @param request HTTP 요청 객체
     * @return 로그인 결과 및 사용자 정보를 담은 Map
     */
    Map<String, Object> processLogin(User user, HttpServletRequest request);

    /**
     * 사용자가 관리자인지 확인하는 메서드
     * @param request HTTP 요청 객체
     * @return 관리자이면 true, 아니면 false
     */
    boolean isAdmin(HttpServletRequest request);
}
