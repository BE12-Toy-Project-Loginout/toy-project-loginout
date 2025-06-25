<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
  <meta charset="UTF-8">
  <title>Q&A</title>
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

    .qna-table {
      width: 100%;
      border-collapse: collapse;
      margin-bottom: 20px;
    }

    .qna-table th, .qna-table td {
      padding: 10px;
      text-align: left;
      border-bottom: 1px solid #ddd;
    }

    .qna-table th {
      background-color: #f2f2f2;
      font-weight: bold;
    }

    .qna-table tr:hover {
      background-color: #f5f5f5;
    }

    .qna-id {
      width: 10%;
    }

    .qna-title {
      width: 50%;
    }

    .qna-date {
      width: 20%;
    }

    .qna-view-count {
      width: 10%;
    }

    .qna-pinned {
      background-color: #f8f8f8;
      font-weight: bold;
    }

    .qna-title a {
      display: block;
      text-decoration: none;
      color: inherit;
    }

    .qna-title a:hover {
      color: #007bff;
      text-decoration: underline;
    }
  </style>
</head>
<body>

<%@ include file="fragments/sidebar.jsp" %>

<div style="margin-left: 50px; padding-top: 50px;">

<div class="header">Q&A</div>

<table class="qna-table">
  <thead>
    <tr>
      <th class="qna-id">번호</th>
      <th class="qna-title">제목</th>
      <th class="qna-date">등록일</th>
      <th class="qna-view-count">조회수</th>
    </tr>
  </thead>
  <tbody>
    <c:forEach var="qna" items="${qnaList}" varStatus="status">
      <tr>
        <td class="qna-id">${qna.qnaId}</td>
        <td class="qna-title">
          <a href="${pageContext.request.contextPath}/qna/${qna.qnaId}">
            ${qna.title}
          </a>
        </td>
        <td class="qna-date">
          <fmt:formatDate value="${qna.createAt}" pattern="yyyy-MM-dd" />
        </td>
        <td class="qna-view-count">${qna.viewCnt}</td>
      </tr>
    </c:forEach>
  </tbody>
</table>

</div>
</body>
</html>
