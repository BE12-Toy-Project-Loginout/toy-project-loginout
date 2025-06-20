<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>fastcampus</title>
    <link rel="stylesheet" href="<c:url value='/css/menu.css'/>">
</head>
<body>
<div id="menu">
    <ul>
        <li id="logo">fastcampus</li>
        <li><a href="<c:url value='/'/>">Home</a></li>
        <li><a href="<c:url value='/board/list'/>">Board</a></li>
        <li><a href="<c:url value='/login/login'/>">login</a></li>
        <li><a href="<c:url value='/register/add'/>">Sign in</a></li>
        <li><a href=""><i class="fas fa-search small"></i></a></li>
    </ul>
</div><div style="text-align:center">
    <table border="1">
        <tr>
            <th> 번호 </th>
            <th> 제목 </th>
            <th> 이름 </th>
            <th> 등록일 </th>
        </tr>
        <c:forEach var="qna" items="${list}">
        <tr>
            <td>${qna.qna_id}</td>
            <td>${qna.title}</td>
            <td>${qna.member_id}</td>
            <td>${qna.create_at}</td>
        </tr>
        </c:forEach>
    </table>
</div>
</body>
</html>