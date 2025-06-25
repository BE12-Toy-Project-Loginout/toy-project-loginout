<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script src="${pageContext.request.contextPath}/resources/js/sidebar.js" type="text/javascript"></script>

<!-- Sidebar CSS -->
<style>
    * {
        margin: 0;
        padding: 0;
        box-sizing: border-box;
    }

    body, html {
        height: 100%;
        font-family: 'Noto Sans KR', sans-serif;
    }

    .menu-toggle {
        position: absolute;
        top: 20px;
        left: 20px;
        font-size: 24px;
        cursor: pointer;
        z-index: 1001;
    }

    .close-btn {
        position: absolute;
        top: 20px;
        right: 20px;
        font-size: 24px;
        cursor: pointer;
        display: none;
        z-index: 2000;
    }

    .sidebar {
        position: fixed;
        top: 0;
        left: -260px;
        width: 260px;
        height: 100%;
        background-color: white;
        box-shadow: 2px 0 5px rgba(0,0,0,0.2);
        padding: 60px 20px;
        transition: left 0.3s ease;
        z-index: 1500;
    }

    .sidebar.open {
        left: 0;
    }

    .sidebar ul {
        list-style: none;
        padding: 0;
    }

    .sidebar ul li {
        margin-bottom: 20px;
        font-size: 16px;
        font-weight: bold;
    }

    .sidebar ul li a {
        text-decoration: none;
        color: black;
    }

    .sidebar ul li a:hover {
        color: purple;
    }

    /* 세로 LOGINOUT 텍스트 - 더 크게 */
    .vertical-text {
        position: absolute;
        top: 50%;
        left: 0;
        transform: translateY(-50%);
        writing-mode: vertical-rl;
        text-orientation: mixed;
        color: #000;
        font-weight: bold;
        font-size: 20px;
        z-index: 500;
    }

    /* 하단 아이콘 - 항상 고정되게 */
    .icon-bar {
        position: fixed;
        bottom: 20px;
        left: 20px;
        display: flex;
        flex-direction: column;
        gap: 10px;
        z-index: 9999; /* 사이드바보다 위 */
    }

    .icon-bar img {
        width: 24px;
        height: 24px;
    }

    /* Shop 메뉴 드롭다운 */
    .shop-submenu {
        display: none;
        padding-left: 20px;
        margin-top: 10px;
    }

    .shop-submenu li {
        margin-bottom: 10px;
        font-size: 14px; /* 기존 SHOP보다 조금 작게 */
    }

    .shop-submenu li a {
        color: #999; /* 회색으로 변경 */
        font-weight: normal; /* 굵기 조정 */
    }

    .shop-menu.active .shop-submenu {
        display: block;
    }

    .shop-menu.active > a {
        color: purple;
    }
</style>

<!-- 메뉴 열기 버튼 -->
<div class="menu-toggle" id="menuToggle" onclick="toggleSidebar()">☰</div>

<!-- 사이드바 -->
<div class="sidebar" id="sidebar">
    <div class="close-btn" id="closeBtn" onclick="toggleSidebar()">✖</div>

    <ul>
        <li><a href="${pageContext.request.contextPath}/home">Home</a></li>
        <li class="shop-menu">
            <a href="#" onclick="toggleShopMenu(event)">SHOP</a>
            <ul class="shop-submenu">
                <li><a href="${pageContext.request.contextPath}/product" class="${empty category || category == '전체보기' ? 'active-filter' : ''}">전체보기</a></li>
                <li><a href="${pageContext.request.contextPath}/product?category=트리트먼트/헤어팩" class="${category == '트리트먼트/헤어팩' ? 'active-filter' : ''}">트리트먼트/헤어팩</a></li>
                <li><a href="${pageContext.request.contextPath}/product?category=에센스/오일" class="${category == '에센스/오일' ? 'active-filter' : ''}">에센스/오일</a></li>
                <li><a href="${pageContext.request.contextPath}/product?category=샴푸" class="${category == '샴푸' ? 'active-filter' : ''}">샴푸</a></li>
                <li><a href="${pageContext.request.contextPath}/product?category=스타일링" class="${category == '스타일링' ? 'active-filter' : ''}">스타일링</a></li>
            </ul>
        </li>
        <li><a href="${pageContext.request.contextPath}/notice">공지사항</a></li>
        <li><a href="${pageContext.request.contextPath}/qna">Q&A</a></li>
        <li><a href="${pageContext.request.contextPath}/login" id="login-link" <c:if test="${isLoggedIn}">style="display: none;"</c:if>>로그인</a></li>
        <li><a href="#" id="signup-link" <c:if test="${isLoggedIn}">style="display: none;"</c:if>>회원가입</a></li>
        <li><a href="${pageContext.request.contextPath}/logout" id="logout-link" <c:if test="${!isLoggedIn}">style="display: none;"</c:if>>로그아웃</a></li>
        <li><a href="${pageContext.request.contextPath}/admin" id="admin-link" <c:if test="${!isAdmin}">style="display: none;"</c:if>>관리자 페이지</a></li>
        <li><a href="#" style="text-decoration: none; color: inherit;">장바구니</a></li>
        <li><a href="#" style="text-decoration: none; color: inherit;">주문조회</a></li>
        <li><a href="#" style="text-decoration: none; color: inherit;">마이페이지</a></li>
    </ul>

</div>

<!-- 세로 텍스트 (쇼핑몰 로고) -->
<div class="vertical-text">
    <span style="color: #c084fc;">LOGINOUT</span>
</div>

<!-- 고정 하단 아이콘 -->
<div class="icon-bar">
    <img src="${pageContext.request.contextPath}/resources/images/icon_mypage.png" alt="mypage">
    <img src="${pageContext.request.contextPath}/resources/images/icon_cart.png" alt="cart">
    <img src="${pageContext.request.contextPath}/resources/images/icon_search.png" alt="search">
</div>
