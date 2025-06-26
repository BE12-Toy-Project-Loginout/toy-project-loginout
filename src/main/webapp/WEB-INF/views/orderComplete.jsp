<%--
  Created by IntelliJ IDEA.
  User: parksumin
  Date: 2025. 6. 25.
  Time: 오전 11:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<html>
<head>
    <title>Title</title>
</head>
<body>
<%@ include file="fragments/sidebar.jsp" %>

<c:forEach var="i" begin="0" end="${fn:length(productIds) - 1}">
    <p>상품 ID: ${productIds[i]}</p>
    <p>수량: ${quantities[i]}개</p>
</c:forEach>

</body>
</html>
