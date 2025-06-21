<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>QnAList</title>
    <%--<link rel="stylesheet" href="<c:url value='/css/menu.css'/>">--%>
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
<script>
    let msg = "${msg}";
    if(msg == "DEL_OK") alert("성공적으로 삭제되었습니다.");
    if(msg == "DEL_ERR") alert("삭제에 실패했습니다.");

</script>
<div style="text-align:center">
    <table border="1">
        <tr>
            <th> 번호 </th>
            <th> 제목 </th>
            <th> 이름 </th>
            <th> 등록일 </th>
        </tr>
        <c:forEach var="qnaDto" items="${list}">
        <tr>
            <td>${qnaDto.qnaId}</td>
            <td><a href="<c:url value='/qna/read?qnaId=${qnaDto.qnaId}&currentPage=${currentPage}&pageSize=${pageSize}'/>">${qnaDto.title}</a></td>
            <td>${qnaDto.memberId}</td>
            <td>${qnaDto.createAt}</td>
        </tr>
        </c:forEach>
    </table>
    <br>
    <div>
        <!-- 이전 페이지 버튼 -->
<c:if test="${ph.showPrev}">
    <a href="<c:url value='/qna/list?currentPage=${ph.startPage-1}&pageSize=${ph.pageSize}'/>">&lt;</a>
</c:if>
<!-- 페이지 숫자 버튼 -->
<c:forEach var="i" begin="${ph.startPage}" end="${ph.endPage}">
    <a href="<c:url value='/qna/list?currentPage=${i}&pageSize=${ph.pageSize}'/>">${i}</a>
</c:forEach>
<!-- 다음 페이지 버튼 -->
<c:if test="${ph.showNext}">
    <a href="<c:url value='/qna/list?currentPage=${ph.endPage+1}&pageSize=${ph.pageSize}'/>">&gt;</a>
</c:if>
    </div>
</div>
</body>
</html>