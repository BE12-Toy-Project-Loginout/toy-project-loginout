<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html>
<head>
  <meta charset="UTF-8">
  <title>Q&A 상세</title>
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

    .qna-container {
      margin-left: 50px;
      padding-top: 50px;
      width: 80%;
    }

    .header {
      font-size: 24px;
      font-weight: bold;
      margin-bottom: 20px;
    }

    .qna-header {
      border-bottom: 2px solid #ddd;
      padding-bottom: 15px;
      margin-bottom: 20px;
    }

    .qna-title {
      font-size: 22px;
      font-weight: bold;
      margin-bottom: 10px;
    }

    .qna-info {
      display: flex;
      justify-content: space-between;
      color: #666;
      font-size: 14px;
    }

    .qna-content {
      min-height: 300px;
      padding: 20px 0;
      line-height: 1.6;
      white-space: pre-wrap;
    }

    .qna-footer {
      margin-top: 30px;
      padding-top: 15px;
      border-top: 1px solid #ddd;
      text-align: right;
    }

    .btn {
      display: inline-block;
      padding: 8px 16px;
      background-color: #f2f2f2;
      color: #333;
      text-decoration: none;
      border-radius: 4px;
      border: 1px solid #ddd;
      cursor: pointer;
    }

    .btn:hover {
      background-color: #e0e0e0;
    }

    .qna-pinned {
      display: inline-block;
      background-color: #f8f8f8;
      color: #333;
      padding: 3px 8px;
      border-radius: 4px;
      font-size: 12px;
      margin-right: 10px;
      border: 1px solid #ddd;
    }
  </style>
</head>
<body>

<%@ include file="fragments/sidebar.jsp" %>

<div class="qna-container">
  <div class="header">Q&A</div>

  <div class="qna-header">
    <div class="qna-title">
      ${qna.title}
    </div>
    <div class="qna-info">
      <span>
        <fmt:formatDate value="${qna.createAt}" pattern="yyyy-MM-dd HH:mm" />
      </span>
      <span>조회수: ${qna.viewCnt}</span>
    </div>
  </div>

  <div class="qna-content">
    ${qna.content}
  </div>

  <div class="qna-footer">
    <a href="${pageContext.request.contextPath}/qna" class="btn">목록으로</a>
  </div>
</div>

</body>
</html>
