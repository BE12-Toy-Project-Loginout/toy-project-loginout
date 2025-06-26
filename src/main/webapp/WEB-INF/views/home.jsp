<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>LOGINOUT HOME</title>
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
            background: url('${pageContext.request.contextPath}${backgroundImage != null ? backgroundImage.imagePath : "/resources/images/bg_main.jpg"}') no-repeat center center / cover;
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
    <%@ include file="fragments/sidebar.jsp" %>



</div>

<script>
    $(document).ready(function() {
        // Add click event for login/logout link
        $('#login-logout-link').on('click', function(e) {
            // No need to prevent default as we want the normal link behavior
            console.log('Login/logout link clicked');
        });
    });
</script>
</body>
</html>
