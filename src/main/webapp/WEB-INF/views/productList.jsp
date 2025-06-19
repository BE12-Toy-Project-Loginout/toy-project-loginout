<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
  <title>SHOP</title>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/productList.css" />
</head>
<body>

<%-- page/pageSize 기본값 안전 처리 --%>
<c:set var="safePage" value="${empty page ? 1 : page}" />
<c:set var="safePageSize" value="${empty pageSize ? 6 : pageSize}" />

<%-- 현재 선택된 카테고리 or 기본값 --%>
<div class="header">
  <c:choose>
    <c:when test="${not empty param.category}">
      ${param.category}
    </c:when>
    <c:otherwise>
      전체보기
    </c:otherwise>
  </c:choose>
</div>

<%-- 카테고리 필터 --%>
<div class="toolbar">
  <c:url var="categoryUrl" value="/product" />
  <c:forEach var="cat" items="${['전체보기', '트리트먼트/헤어팩','에센스/오일','샴푸','스타일링']}">
    <c:url var="link" value="/product">
      <c:param name="category" value="${cat}" />
      <c:if test="${not empty param.sort}">
        <c:param name="sort" value="${param.sort}" />
      </c:if>
    </c:url>
    <a href="${link}" class="${param.category == cat || (cat == '전체보기' && empty param.category) ? 'active-filter' : ''}">${cat}</a>
    <c:if test="${cat != '스타일링'}"> | </c:if>
  </c:forEach>
</div>

<!-- 정렬 바 + 총 아이템 수 묶는 래퍼 -->
<div class="sort-bar-wrapper">
  <div class="count-info">총 ${productCount} ITEMS</div>

  <div class="sort-toolbar">
    <c:forEach var="sortOption" items="${['신상품','상품명','낮은가격','높은가격','인기상품']}">
      <c:url var="sortLink" value="/product">
        <c:param name="sort" value="${sortOption}" />
        <c:if test="${not empty param.category}">
          <c:param name="category" value="${param.category}" />
        </c:if>
      </c:url>
      <a href="${sortLink}" class="${param.sort == sortOption || (sortOption == '신상품' && empty param.sort) ? 'active-filter' : ''}">${sortOption}</a>
      <c:if test="${sortOption != '인기상품'}"> | </c:if>
    </c:forEach>
  </div>
</div>


<%--&lt;%&ndash; 정렬 옵션 &ndash;%&gt;--%>
<%--<div class="toolbar">--%>
<%--  <c:forEach var="sortOption" items="${['신상품','상품명','낮은가격','높은가격','인기상품']}">--%>
<%--    <c:url var="sortLink" value="/product">--%>
<%--      <c:param name="sort" value="${sortOption}" />--%>
<%--      <c:if test="${not empty param.category}">--%>
<%--        <c:param name="category" value="${param.category}" />--%>
<%--      </c:if>--%>
<%--    </c:url>--%>
<%--    <a href="${sortLink}" class="${param.sort == sortOption || (sortOption == '신상품' && empty param.sort) ? 'active-filter' : ''}">${sortOption}</a>--%>
<%--    <c:if test="${sortOption != '인기상품'}"> | </c:if>--%>
<%--  </c:forEach>--%>
<%--</div>--%>


<%--&lt;%&ndash; 총 개수 &ndash;%&gt;--%>
<%--<div class="count-info">--%>
<%--  총 ${productCount} ITEMS--%>
<%--</div>--%>

<%-- 상품 목록 --%>
<div class="grid">
  <c:forEach var="product" items="${productList}" varStatus="status">
    <div class="product">
      <img src="${pageContext.request.contextPath}/resources/images/${product.product_image}" alt="${product.product_name}" />
      <div class="product-name">${product.product_name}</div>
      <div class="product-desc">${product.product_description}</div>
      <div class="product-price">
        <fmt:formatNumber value="${product.product_price}" type="number" />원
      </div>
    </div>
  </c:forEach>
</div>


<%-- 페이지 네비게이션 --%>
<div class="pagination">
  <c:if test="${pageHandler.showPrevious}">
    <a href="?page=${pageHandler.beginPage - 1}&pageSize=${pageHandler.pageSize}&category=${param.category}&sort=${param.sort}">&lt;</a>
  </c:if>

  <c:forEach var="i" begin="${pageHandler.beginPage}" end="${pageHandler.endPage}">
    <a href="?page=${i}&pageSize=${pageHandler.pageSize}&category=${param.category}&sort=${param.sort}"
       class="${i == safePage ? 'current-page' : ''}">${i}</a>
  </c:forEach>

  <c:if test="${pageHandler.showNext}">
    <a href="?page=${pageHandler.endPage + 1}&pageSize=${pageHandler.pageSize}&category=${param.category}&sort=${param.sort}">&gt;</a>
  </c:if>
</div>

</body>
</html>
