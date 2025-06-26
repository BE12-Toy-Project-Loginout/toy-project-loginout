<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
  <meta charset="UTF-8">
  <title>공지사항</title>
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

    .header {
      font-size: 24px;
      font-weight: bold;
      margin-bottom: 20px;
    }

    .notice-table {
      width: 100%;
      border-collapse: collapse;
      margin-bottom: 20px;
    }

    .notice-table th, .notice-table td {
      padding: 10px;
      text-align: left;
      border-bottom: 1px solid #ddd;
    }

    .notice-table th {
      background-color: #f2f2f2;
      font-weight: bold;
    }

    .notice-table tr:hover {
      background-color: #f5f5f5;
    }

    .notice-id {
      width: 10%;
    }

    .notice-title {
      width: 50%;
    }

    .notice-date {
      width: 20%;
    }

    .notice-view-count {
      width: 10%;
    }

    .notice-pinned {
      background-color: #f8f8f8;
      font-weight: bold;
    }

    .notice-title a {
      display: block;
      text-decoration: none;
      color: inherit;
    }

    .notice-title a:hover {
      color: #007bff;
      text-decoration: underline;
    }
  </style>
</head>
<body>

<%@ include file="fragments/sidebar.jsp" %>

<div style="margin-left: 50px; padding-top: 50px;">

<div class="header">공지사항</div>

<table class="notice-table">
  <thead>
    <tr>
      <th class="notice-id">번호</th>
      <th class="notice-title">제목</th>
      <th class="notice-date">등록일</th>
      <th class="notice-view-count">조회수</th>
    </tr>
  </thead>
  <tbody>
    <c:forEach var="notice" items="${noticeList}" varStatus="status">
      <tr class="${notice.pinned ? 'notice-pinned' : ''}">
        <td class="notice-id">${notice.noticeId}</td>
        <td class="notice-title">
          <a href="${pageContext.request.contextPath}/notice/${notice.noticeId}">
            ${notice.title}
          </a>
        </td>
        <td class="notice-date">
          <fmt:formatDate value="${notice.createAt}" pattern="yyyy-MM-dd" />
        </td>
        <td class="notice-view-count">${notice.viewCount}</td>
      </tr>
    </c:forEach>
  </tbody>
</table>

</div>
</body>
</html>
