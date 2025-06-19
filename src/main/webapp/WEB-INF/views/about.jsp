<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>LoginOut - 회사 소개</title>
    <link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@300;400;500;700&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="./resources/css/shopping-mall.css">
    <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
    <style>
        .about-section {
            padding: 60px 0;
        }
        .about-content {
            display: flex;
            flex-wrap: wrap;
            gap: 40px;
            margin-bottom: 60px;
        }
        .about-text {
            flex: 1;
            min-width: 300px;
        }
        .about-image {
            flex: 1;
            min-width: 300px;
            background-color: #e9ecef;
            height: 300px;
            display: flex;
            align-items: center;
            justify-content: center;
            border-radius: 8px;
        }
        .about-text h2 {
            font-size: 28px;
            margin-bottom: 20px;
            color: #333;
        }
        .about-text p {
            line-height: 1.8;
            color: #495057;
            margin-bottom: 15px;
        }
        .team-section {
            margin-bottom: 60px;
        }
        .team-grid {
            display: grid;
            grid-template-columns: repeat(auto-fill, minmax(250px, 1fr));
            gap: 30px;
        }
        .team-member {
            text-align: center;
        }
        .team-photo {
            width: 150px;
            height: 150px;
            border-radius: 50%;
            background-color: #e9ecef;
            margin: 0 auto 20px;
            display: flex;
            align-items: center;
            justify-content: center;
        }
        .team-name {
            font-size: 18px;
            font-weight: 500;
            margin-bottom: 5px;
        }
        .team-position {
            color: #868e96;
            margin-bottom: 10px;
        }
        .values-section {
            background-color: #f8f9fa;
            padding: 60px 0;
            margin-bottom: 60px;
        }
        .values-grid {
            display: grid;
            grid-template-columns: repeat(auto-fill, minmax(300px, 1fr));
            gap: 30px;
        }
        .value-item {
            background-color: #fff;
            padding: 30px;
            border-radius: 8px;
            box-shadow: 0 3px 10px rgba(0,0,0,0.1);
        }
        .value-title {
            font-size: 20px;
            margin-bottom: 15px;
            color: #ff6b6b;
        }
        .value-description {
            color: #495057;
            line-height: 1.6;
        }
    </style>
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

    <!-- About Hero Section -->
    <section class="hero">
        <div class="container">
            <h1>LoginOut 소개</h1>
            <p>고객의 스타일을 완성하는 패션 쇼핑몰</p>
        </div>
    </section>

    <!-- About Content -->
    <section class="about-section container">
        <div class="about-content">
            <div class="about-text">
                <h2>우리의 이야기</h2>
                <p>LoginOut은 2025년에 설립된 온라인 패션 쇼핑몰로, 고품질의 의류와 액세서리를 합리적인 가격에 제공하고 있습니다. 우리는 최신 트렌드를 반영한 제품을 선보이며, 고객들이 자신만의 스타일을 찾을 수 있도록 돕고 있습니다.</p>
                <p>우리의 목표는 단순히 제품을 판매하는 것이 아니라, 고객들에게 새로운 패션 경험을 제공하는 것입니다. 우리는 지속 가능한 패션을 지향하며, 환경에 미치는 영향을 최소화하기 위해 노력하고 있습니다.</p>
                <p>LoginOut은 고객 만족을 최우선으로 생각하며, 빠른 배송과 친절한 고객 서비스를 제공하고 있습니다. 우리와 함께 당신만의 스타일을 완성해보세요.</p>
            </div>
            <div class="about-image">
                <span style="color: #adb5bd; font-size: 14px;">회사 이미지</span>
            </div>
        </div>
    </section>

    <!-- Our Values -->
    <section class="values-section">
        <div class="container">
            <h2 class="section-title">우리의 가치</h2>
            <div class="values-grid">
                <div class="value-item">
                    <h3 class="value-title">품질</h3>
                    <p class="value-description">우리는 최고 품질의 제품만을 엄선하여 고객에게 제공합니다. 모든 제품은 엄격한 품질 검사를 거쳐 고객에게 전달됩니다.</p>
                </div>
                <div class="value-item">
                    <h3 class="value-title">혁신</h3>
                    <p class="value-description">우리는 항상 새로운 트렌드와 기술을 받아들이며, 고객에게 더 나은 쇼핑 경험을 제공하기 위해 끊임없이 혁신합니다.</p>
                </div>
                <div class="value-item">
                    <h3 class="value-title">지속가능성</h3>
                    <p class="value-description">우리는 환경을 생각하는 지속 가능한 패션을 추구합니다. 친환경 소재 사용과 윤리적인 생산 과정을 통해 환경 보호에 기여합니다.</p>
                </div>
            </div>
        </div>
    </section>

    <!-- Our Team -->
    <section class="team-section container">
        <h2 class="section-title">우리 팀</h2>
        <div class="team-grid">
            <div class="team-member">
                <div class="team-photo">
                    <span style="color: #adb5bd; font-size: 14px;">사진</span>
                </div>
                <h3 class="team-name">김민수</h3>
                <div class="team-position">CEO</div>
                <p>패션 산업에서 10년 이상의 경험을 가진 전문가입니다.</p>
            </div>
            <div class="team-member">
                <div class="team-photo">
                    <span style="color: #adb5bd; font-size: 14px;">사진</span>
                </div>
                <h3 class="team-name">이지연</h3>
                <div class="team-position">디자인 디렉터</div>
                <p>창의적인 디자인으로 브랜드의 아이덴티티를 만들어갑니다.</p>
            </div>
            <div class="team-member">
                <div class="team-photo">
                    <span style="color: #adb5bd; font-size: 14px;">사진</span>
                </div>
                <h3 class="team-name">박준호</h3>
                <div class="team-position">마케팅 매니저</div>
                <p>혁신적인 마케팅 전략으로 브랜드 가치를 높이고 있습니다.</p>
            </div>
            <div class="team-member">
                <div class="team-photo">
                    <span style="color: #adb5bd; font-size: 14px;">사진</span>
                </div>
                <h3 class="team-name">최수진</h3>
                <div class="team-position">고객 서비스 책임자</div>
                <p>고객의 만족을 최우선으로 생각하는 서비스를 제공합니다.</p>
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
