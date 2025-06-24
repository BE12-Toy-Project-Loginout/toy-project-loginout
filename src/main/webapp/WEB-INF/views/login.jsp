<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="java.net.URLDecoder" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>LOGINOUT</title>
    <link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@400;700&display=swap" rel="stylesheet" />
    <link rel="stylesheet" href="<c:url value='/resources/css/login.css' />"/>
    <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
    <script>
        // Define contextPath for login.js
        var contextPath = '${pageContext.request.contextPath}';
    </script>
    <script src="${pageContext.request.contextPath}/resources/js/login.js" type="text/javascript"></script>
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

        .container {
            position: relative;
            width: 100%;
            height: 100vh;
            overflow: hidden;
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
    </style>
</head>
<body>

<div class="container">

    <%@ include file="fragments/sidebar.jsp" %>

    <main class="login-main">
        <div class="login-card">
            <h1 style="text-align: center; font-size: 1.875rem; font-weight: bold; margin-bottom: 1.5rem;">로그인</h1>

            <div id="div_login" <c:if test="${isLoggedIn}">style="display: none;"</c:if>>
                <form id="loginForm" action="loginCheck" method="post" style="display: flex; flex-direction: column; gap: 1rem;">
                    <div>
                        <label for="id" class="input-label">아이디</label>
                        <input type="text" id="id" name="userLoginId" class="input-field" />
                    </div>
                    <div>
                        <label for="pwd" class="input-label">비밀번호</label>
                        <input type="password" id="pwd" name="userPassword" class="input-field" />
                    </div>
                    <div id="msg" style="color: red; font-size: 0.875rem;"></div>
                    <div style="display: flex; justify-content: space-between; font-size: 0.875rem; color: #6b7280;">
                        <a href="#" style="text-decoration: underline;">아이디찾기</a>
                        <a href="#" style="text-decoration: underline;">비밀번호 찾기</a>
                    </div>
                    <button type="button" id="btn" style="width: 100%; background-color: #c084fc; color: white; padding: 0.5rem; border-radius: 0.375rem; border: none; cursor: pointer;">
                        로그인
                    </button>
                </form>
                <div style="margin-top: 1.5rem; text-align: center; color: #6b7280;">
                    loginout 회원가입시 <span style="font-weight: bold; color: #374151;">3000원</span> 할인 쿠폰코드 발급
                </div>
                <div class="footer-text">
                    아직 <span style="font-weight: bold; color: #374151;">LOGINOUT</span> 회원이 아니신가요?<br />
                    회원가입하고 다양한 혜택과 서비스를 이용해보세요!
                </div>
            </div>

            <div id="div_logout" <c:if test="${!isLoggedIn}">style="display: none;"</c:if>>
                <div style="text-align: center; padding: 20px 0;">
                    <p style="font-size: 18px; margin-bottom: 20px;"><b id="userNameDisplay">${userName}</b> 님, 로그인 되었습니다.</p>
                    <button type="button" id="btn_logout" style="width: 100%; background-color: #c084fc; color: white; padding: 0.5rem; border-radius: 0.375rem; border: none; cursor: pointer;">로그아웃</button>
                </div>
            </div>
        </div>
    </main>

    <footer style="background-color: #f3f4f6; padding: 1.5rem 0;">
        <div style="text-align: center; color: #6b7280;">
            <div style="font-weight: bold; font-size: 1.125rem; margin-bottom: 0.5rem;">LOGINOUT</div>

        </div>
    </footer>

</div>

</body>
</html>
