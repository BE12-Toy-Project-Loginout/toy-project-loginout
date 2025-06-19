<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>LoginOut - 쇼핑몰</title>
    <link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@300;400;500;700&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="./resources/css/shopping-mall.css">
    <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
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

    <!-- Hero Section -->
    <section class="hero">
        <div class="container">
            <h1>신상품 컬렉션</h1>
            <p>2025 여름 신상품이 출시되었습니다. 지금 바로 확인해보세요.</p>
            <a href="#" class="btn">쇼핑하기</a>
        </div>
    </section>

    <!-- Featured Products -->
    <section class="products container">
        <h2 class="section-title">인기 상품</h2>
        <div class="product-grid">
            <!-- Product 1 -->
            <div class="product-card">
                <div class="product-image">
                    <div style="width: 100%; height: 100%; background-color: #e9ecef; display: flex; justify-content: center; align-items: center;">
                        <span style="color: #adb5bd; font-size: 14px;">상품 이미지</span>
                    </div>
                </div>
                <div class="product-info">
                    <div class="product-category">여성의류</div>
                    <h3 class="product-title">여름 원피스</h3>
                    <div class="product-price">39,000원</div>
                </div>
            </div>

            <!-- Product 2 -->
            <div class="product-card">
                <div class="product-image">
                    <div style="width: 100%; height: 100%; background-color: #e9ecef; display: flex; justify-content: center; align-items: center;">
                        <span style="color: #adb5bd; font-size: 14px;">상품 이미지</span>
                    </div>
                </div>
                <div class="product-info">
                    <div class="product-category">남성의류</div>
                    <h3 class="product-title">여름 티셔츠</h3>
                    <div class="product-price">29,000원</div>
                </div>
            </div>

            <!-- Product 3 -->
            <div class="product-card">
                <div class="product-image">
                    <div style="width: 100%; height: 100%; background-color: #e9ecef; display: flex; justify-content: center; align-items: center;">
                        <span style="color: #adb5bd; font-size: 14px;">상품 이미지</span>
                    </div>
                </div>
                <div class="product-info">
                    <div class="product-category">액세서리</div>
                    <h3 class="product-title">여름 모자</h3>
                    <div class="product-price">19,000원</div>
                </div>
            </div>

            <!-- Product 4 -->
            <div class="product-card">
                <div class="product-image">
                    <div style="width: 100%; height: 100%; background-color: #e9ecef; display: flex; justify-content: center; align-items: center;">
                        <span style="color: #adb5bd; font-size: 14px;">상품 이미지</span>
                    </div>
                </div>
                <div class="product-info">
                    <div class="product-category">신발</div>
                    <h3 class="product-title">여름 샌들</h3>
                    <div class="product-price">49,000원</div>
                </div>
            </div>
        </div>
    </section>

    <!-- New Arrivals -->
    <section class="products container">
        <h2 class="section-title">신상품</h2>
        <div class="product-grid">
            <!-- Product 1 -->
            <div class="product-card">
                <div class="product-image">
                    <div style="width: 100%; height: 100%; background-color: #e9ecef; display: flex; justify-content: center; align-items: center;">
                        <span style="color: #adb5bd; font-size: 14px;">상품 이미지</span>
                    </div>
                </div>
                <div class="product-info">
                    <div class="product-category">여성의류</div>
                    <h3 class="product-title">여름 블라우스</h3>
                    <div class="product-price">35,000원</div>
                </div>
            </div>

            <!-- Product 2 -->
            <div class="product-card">
                <div class="product-image">
                    <div style="width: 100%; height: 100%; background-color: #e9ecef; display: flex; justify-content: center; align-items: center;">
                        <span style="color: #adb5bd; font-size: 14px;">상품 이미지</span>
                    </div>
                </div>
                <div class="product-info">
                    <div class="product-category">남성의류</div>
                    <h3 class="product-title">여름 반바지</h3>
                    <div class="product-price">32,000원</div>
                </div>
            </div>

            <!-- Product 3 -->
            <div class="product-card">
                <div class="product-image">
                    <div style="width: 100%; height: 100%; background-color: #e9ecef; display: flex; justify-content: center; align-items: center;">
                        <span style="color: #adb5bd; font-size: 14px;">상품 이미지</span>
                    </div>
                </div>
                <div class="product-info">
                    <div class="product-category">액세서리</div>
                    <h3 class="product-title">여름 선글라스</h3>
                    <div class="product-price">25,000원</div>
                </div>
            </div>

            <!-- Product 4 -->
            <div class="product-card">
                <div class="product-image">
                    <div style="width: 100%; height: 100%; background-color: #e9ecef; display: flex; justify-content: center; align-items: center;">
                        <span style="color: #adb5bd; font-size: 14px;">상품 이미지</span>
                    </div>
                </div>
                <div class="product-info">
                    <div class="product-category">가방</div>
                    <h3 class="product-title">여름 토트백</h3>
                    <div class="product-price">45,000원</div>
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
