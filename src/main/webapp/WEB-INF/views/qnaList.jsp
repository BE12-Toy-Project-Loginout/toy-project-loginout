<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%--<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>--%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>QnAList</title>
    <%--<link rel="stylesheet" href="<c:url value='/css/menu.css'/>">--%>
    <link rel="stylesheet" href="<c:url value='/resources/css/qna.css'/>">
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
    if(msg=="LIST_ERR")  alert("QnA 목록을 가져오는데 실패했습니다. 다시 시도해 주세요.");
    if(msg=="READ_ERR")  alert("삭제되었거나 없는 QnA 게시물입니다.");
    if(msg=="DEL_ERR")   alert("QnA 삭제에 실패했습니다.");
    if(msg=="DEL_OK")    alert("성공적으로 삭제되었습니다.");
    if(msg=="WRT_OK")    alert("성공적으로 등록되었습니다.");
    if(msg=="MOD_OK")    alert("성공적으로 수정되었습니다.");
</script>
<div style="text-align:center">
    <div class="qna-container">
        <div class="qna-header">
            <h1 class="qna-title">Q&A</h1>
            <div class="qna-info">
                로그인 후 문의 가능합니다.<br>
                정확한 상품명, 주문번호를 알려주시면<br>
                더욱 빠른 답변이 가능합니다.<br>
                <br>
                월-금 09:00 ~ 17:00 / 점심시간 12:00 ~ 13:00<br>
                (주말/공휴일 휴무)
            </div>
        </div>
        <div class="search-container">
            <form action="<c:url value='/qna/list'/>" class="search-form" method="get">
                <select class="search-option" name="option">
                    <option value="A" ${ph.sc.option=='A' || ph.sc.option=='' ? "selected" : ""}>제목+내용</option>
                    <option value="T" ${ph.sc.option=='T' ? "selected" : ""}>제목만</option>
                    <option value="W" ${ph.sc.option=='W' ? "selected" : ""}>작성자</option>
                </select>
                <input type="text" name="keyword" class="search-input" value="${ph.sc.keyword}" placeholder="검색어를 입력해주세요">
                <input type="submit" class="search-button" value="검색">
            </form>
            <button id="writeBtn" class="btn btn-write" onclick="location.href='<c:url value="/qna/write"/>'"><i class="fa fa-pencil"></i> 글쓰기</button>
        </div>
        <table border="1" style="margin:0 auto;">
            <tr>
                <th class="no">번호</th>
                <th class="title">제목</th>
                <th class="writer">작성자</th>
                <th class="createAt">작성일</th>
                <th class="viewCnt">조회수</th>
            </tr>
            <c:forEach var="qnaDto" items="${list}">
                <tr>
                    <td class="no">${qnaDto.qnaId}</td>
                    <td class="title">
                        <a href="<c:url value='/qna/read${ph.sc.queryString}&qnaId=${qnaDto.qnaId}'/>">
                                <c:out value='${qnaDto.title}'/>
                        </a>
                    </td>
                    <td class="writer">${qnaDto.memberName}</td>
                    <%--<td class="createAt">
                        <fmt:formatDate value="${qnaDto.createAt}" pattern="yyyy-MM-dd" />
                    </td>--%>
                    <c:choose>
                        <c:when test="${qnaDto.createAt.time >= startOfToday}">
                            <td class="regdate"><fmt:formatDate value="${qnaDto.createAt}" pattern="HH:mm" type="time"/></td>
                        </c:when>
                        <c:otherwise>
                            <td class="regdate"><fmt:formatDate value="${qnaDto.createAt}" pattern="yyyy-MM-dd" type="date"/></td>
                        </c:otherwise>
                    </c:choose>
                    <td class="viewcnt">${qnaDto.viewCnt}</td>
                </tr>
            </c:forEach>
            <c:if test="${empty list}">
                <tr><td colspan="4">게시물이 없습니다.</td></tr>
            </c:if>
        </table>
        <br>
        <div class="paging-container">
            <div class="paging">
                <c:if test="${totalCnt==null || totalCnt==0}">
                    <div> 게시물이 없습니다. </div>
                </c:if>
                <c:if test="${totalCnt!=null && totalCnt!=0}">
                    <c:if test="${ph.showPrev}">
                        <a class="page" href="<c:url value="/qna/list${ph.sc.getQueryString(ph.startPage-1)}"/>">&lt;</a>
                    </c:if>
                    <c:forEach var="i" begin="${ph.startPage}" end="${ph.endPage}">
                        <a class="page ${i==ph.sc.currentPage? "paging-active" : ""}" href="<c:url value="/qna/list${ph.sc.getQueryString(i)}"/>">${i}</a>
                    </c:forEach>
                    <c:if test="${ph.showNext}">
                        <a class="page" href="<c:url value="/qna/list${ph.sc.getQueryString(ph.endPage+1)}"/>">&gt;</a>
                    </c:if>
                </c:if>
            </div>
        </div>
    </div>
</div>
</body>
</html>

<%--
<script>
    let msg = "${msg}";
    if(msg == "WRT_OK") alert("성공적으로 등록되었습니다.");
    if(msg == "DEL_OK") alert("성공적으로 삭제되었습니다.");
    if(msg == "DEL_ERR") alert("삭제에 실패했습니다.");
</script>
<div style="text-align:center">
    <button type="button" id="writeBtn" onclick="location.href='<c:url value="/qna/write"/>'">글쓰기</button>
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
</html>--%>
