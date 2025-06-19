<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>LOGINOUT HOME</title>
    <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
    <style>
        * {
            margin: 0;
            padding: 0;
            box-sizing: border-box;
        }

        body, html {
            height: 100%;
            font-family: 'Arial', sans-serif;
        }

        .container {
            position: relative;
            width: 100%;
            height: 100vh;
            overflow: hidden;
            background: url('${pageContext.request.contextPath}/resources/images/main1.png') no-repeat center center / cover;
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

        .text-content {
            position: absolute;
            top: 30%;
            left: 20%;
            color: white;
            text-align: left;
            z-index: 500;
        }

        .text-content h1 {
            font-size: 40px;
            margin: 0;
        }

        .text-content p {
            font-size: 16px;
        }

    </style>
</head>
<body>

<div class="container">

    <!-- 메뉴 열기 버튼 -->
    <div class="menu-toggle" id="menuToggle" onclick="toggleSidebar()">☰</div>

    <!-- 사이드바 -->
    <div class="sidebar" id="sidebar">
        <div class="close-btn" id="closeBtn" onclick="toggleSidebar()">✖</div>

        <ul>
            <li><a href="#" style="text-decoration: none; color: inherit;">SHOP</a></li>
            <li><a href="#" style="text-decoration: none; color: inherit;">공지사항</a></li>
            <li><a href="#" style="text-decoration: none; color: inherit;">Q&A</a></li>
            <li><a href="${pageContext.request.contextPath}/login" id="login-link" <c:if test="${isLoggedIn}">style="display: none;"</c:if>>로그인</a></li> <%-- 로그인 컨트롤러로 이동 --%>
            <li><a href="#" <c:if test="${isLoggedIn}">style="display: none;"</c:if>>회원가입</a></li>
            <li><a href="${pageContext.request.contextPath}/logout" id="logout-link" <c:if test="${!isLoggedIn}">style="display: none;"</c:if>>로그아웃</a></li>
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



</div>

<script>
    function toggleSidebar() {
        const sidebar = document.getElementById('sidebar');
        const closeBtn = document.getElementById('closeBtn');

        if (sidebar.classList.contains('open')) {
            sidebar.classList.remove('open');
            closeBtn.style.display = 'none';
        } else {
            sidebar.classList.add('open');
            closeBtn.style.display = 'block';
        }
    }

    $(document).ready(function() {
        // Add click event for login link
        $('#login-link').on('click', function(e) {
            // No need to prevent default as we want the normal link behavior
            console.log('Login link clicked');
        });
    });
</script>
</body>
</html>
