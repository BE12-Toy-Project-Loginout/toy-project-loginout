<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>QnADetails</title>
    <%--<link rel="stylesheet" href="<c:url value='/css/menu.css'/>">--%>
    <script src="https://code.jquery.com/jquery-3.7.1.min.js"></script>
</head>
<body>
<div id="menu">
    <ul>
        <li id="logo">Loginout</li>
        <li><a href="<c:url value='/'/>">Home</a></li>
        <%--<li><a href="<c:url value='/board/list'/>">Board</a></li>
        <li><a href="<c:url value='/login/login'/>">login</a></li>
        <li><a href="<c:url value='/register/add'/>">Sign in</a></li>--%>
        <li><a href=""><i class="fas fa-search small"></i></a></li>
    </ul>
</div>
<div style="text-align:center">
    <h2>게시물 읽기</h2>
    <form action="" id="form">
        <input type="hidden" name="memberId" value="${qnaDto.memberId}">
        <input type="text" name="qnaId" value="${qnaDto.qnaId}" readonly="readonly">
        <input type="text" name="title" value="${qnaDto.title}" readonly="readonly">
        <textarea name="content" id="" cols="30" rows="10" readonly="readonly">${qnaDto.content}</textarea>
        <button type="button" id="writeBtn" class="btn">등록</button>
        <button type="button" id="modifyBtn" class="btn">수정</button>
        <button type="button" id="removeBtn" class="btn">삭제</button>
        <button type="button" id="listBtn" class="btn">목록</button>
    </form>
</div>
<script>
    $(document).ready(function () {

        $("#listBtn").on("click", function(){ //GET 방식
            alert("ListBtn Clicked");
            location.href="<c:url value='/qna/list'/>?currentPage=${currentPage}&pageSize=${pageSize}";
        });

        $("#removeBtn").on("click", function(){ //POST 방식
            if(!confirm("정말로 삭제하겠습니까?")) return;
            let form = $("#form"); //id가 form인 객체를 form 변수에 저장
            form.attr("action", "<c:url value='/qna/remove'/>?currentPage=${currentPage}&pageSize=${pageSize}");
            form.attr("method", "post");
            form.submit();

        });

    }); //main함수
</script>
</body>
</html>