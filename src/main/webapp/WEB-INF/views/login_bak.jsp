<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 25. 6. 17.
  Time: 오후 2:18
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>LoginOut - 로그인</title>
    <link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@300;400;500;700&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="./resources/css/shopping-mall.css">
    <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
    <script src="./resources/js/login.js" type="text/javascript"></script>
</head>
<body>
    <!-- Header -->
    <header class="header">
        <div class="container">
            <div class="header-top">
                <div class="logo">LoginOut</div>
                <div class="header-actions">
                    <a href="#">회원가입</a>
                    <a href="login">로그인</a>
                    <a href="#">장바구니</a>
                    <a href="#">마이페이지</a>
                </div>
            </div>
            <nav class="nav-main">
                <ul>
                    <li><a href="home">홈</a></li>
                    <li><a href="#">베스트</a></li>
                    <li><a href="#">신상품</a></li>
                    <li><a href="#">세일</a></li>
                    <li><a href="#">이벤트</a></li>
                    <li><a href="about">소개</a></li>
                </ul>
            </nav>
        </div>
    </header>

    <!-- Login Section -->
    <section class="container">
        <div class="login-container">
            <h2 class="login-title">로그인</h2>

            <div id="div_login" <c:if test="${isLoggedIn}">style="display: none;"</c:if>>
                <div class="form-group">
                    <label for="id">아이디</label>
                    <input type="text" id="id" name="userLoginId" class="form-control" placeholder="아이디를 입력하세요">
                </div>
                <div class="form-group">
                    <label for="pwd">비밀번호</label>
                    <input type="password" id="pwd" name="userPassword" class="form-control" placeholder="비밀번호를 입력하세요">
                </div>
                <button type="button" id="btn" class="login-btn">로그인</button>

                <div class="login-links">
                    <a href="#">아이디 찾기</a> | 
                    <a href="#">비밀번호 찾기</a> | 
                    <a href="#">회원가입</a>
                </div>
            </div>

            <div id="div_logout" <c:if test="${!isLoggedIn}">style="display: none;"</c:if>>
                <div style="text-align: center; padding: 20px 0;">
                    <p style="font-size: 18px; margin-bottom: 20px;">로그인 되었습니다.</p>
                    <button type="button" id="btn_logout" class="login-btn">로그아웃</button>
                </div>
            </div>
        </div>
    </section>

    <!-- Footer -->
    <footer class="footer">
        <div class="container">
            <div class="footer-content">
                <div class="footer-column">
                    <h3>고객센터</h3>
                    <ul>
                        <li>전화: 1234-5678</li>
                        <li>이메일: support@loginout.com</li>
                        <li>운영시간: 평일 9:00 - 18:00</li>
                    </ul>
                </div>
                <div class="footer-column">
                    <h3>쇼핑몰 정보</h3>
                    <ul>
                        <li><a href="#">회사소개</a></li>
                        <li><a href="#">이용약관</a></li>
                        <li><a href="#">개인정보처리방침</a></li>
                    </ul>
                </div>
                <div class="footer-column">
                    <h3>고객지원</h3>
                    <ul>
                        <li><a href="#">FAQ</a></li>
                        <li><a href="#">배송조회</a></li>
                        <li><a href="#">반품/교환</a></li>
                    </ul>
                </div>
            </div>
            <div class="footer-bottom">
                <p>&copy; 2025 LoginOut. All rights reserved.</p>
            </div>
        </div>
    </footer>
</body>
</html>
