<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 25. 6. 17.
  Time: 오후 2:18
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
  <head>
    <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
    <script src="./resources/js/login.js" type="text/javascript"></script>
    <title>Title</title>
  </head>
  <body>
    <div id="div_login" <c:if test="${isLoggedIn}">style="display: none;"</c:if>>
      <input type="text" id="id" name="userLoginId"/>
      <input type="text" id="pwd" name="userPassword"/>
      <p></p>
      <button type="button" id="btn">로그인</button>
    </div>
    <div id="div_logout" <c:if test="${!isLoggedIn}">style="display: none;"</c:if>>
      <p>로그인 되었습니다.</p>
      <button type="button" id="btn_logout">로그아웃</button>
    </div>
  </body>
</html>
