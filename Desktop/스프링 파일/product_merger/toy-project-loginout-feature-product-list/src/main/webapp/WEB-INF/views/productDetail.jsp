<!-- src/main/webapp/WEB-INF/views/productDetail.jsp -->
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>상품 상세</title>
    <!-- CSS/스크립트 동일하게 사용 -->
</head>
<body>
<div class="product-area">
    <div class="image-box">
        <c:choose>
            <c:when test="${not empty productDetail.imageUrl}">
                <img src="${fn:escapeXml(productDetail.imageUrl)}" alt="${fn:escapeXml(productDetail.productName)}"/>
            </c:when>
            <c:otherwise>
                <div class="image-placeholder">이미지 없음</div>
            </c:otherwise>
        </c:choose>
    </div>
    <div class="info-box">
        <h1>
            <c:choose>
                <c:when test="${not empty productDetail.productName}">
                    ${fn:escapeXml(productDetail.productName)}
                </c:when>
                <c:otherwise>상품명 없음</c:otherwise>
            </c:choose>
        </h1>
        <hr>
        <div class="description">
            <c:choose>
                <c:when test="${not empty productDetail.productDetailDescription}">
                    ${fn:escapeXml(productDetail.productDetailDescription)}
                </c:when>
                <c:otherwise>설명 없음</c:otherwise>
            </c:choose>
        </div>
        <fmt:parseNumber var="priceNum" value="${productDetail.productDetailPrice}" integerOnly="true"/>
        <ul class="info-list">
            <li>
                <span class="label">판매가</span>
                <span>
                    <c:choose>
                        <c:when test="${priceNum != null}">
                            <fmt:formatNumber value="${priceNum}" type="number" groupingUsed="true"/>원
                        </c:when>
                        <c:otherwise>가격 정보 없음</c:otherwise>
                    </c:choose>
                </span>
            </li>
            <li>
                <span class="label">배송비</span>
                <span>
                    <c:choose>
                        <c:when test="${priceNum >= 20000}">
                            무료배송
                        </c:when>
                        <c:when test="${priceNum > 0}">
                            2만원 미만: 2,500원
                        </c:when>
                        <c:otherwise>배송비 정보 없음</c:otherwise>
                    </c:choose>
                </span>
            </li>
        </ul>
        <div class="quantity-row">
            <div class="label-cell">${fn:escapeXml(productDetail.productName)}</div>
            <div class="input-cell">
                <input type="number" id="quantity" value="1" min="1" oninput="updateTotal()">
            </div>
            <span id="unitPrice" data-price="${priceNum}" style="display:none;"></span>
            <div class="price-cell" id="linePrice"></div>
        </div>
        <div class="total-area">
            <span class="text">총 상품금액</span>
            <span id="productTotal" class="amount"></span>
            <span id="totalQty" class="qty-info"></span>
        </div>
        <div class="button-group">
            <button type="button" class="primary">바로 구매하기</button>
            <button type="button" class="secondary">장바구니</button>
            <button type="button" class="secondary">관심상품</button>
        </div>
    </div>
</div>
<script>
    function updateTotal() { /* 이전 스크립트 재사용 */ }
    window.addEventListener('DOMContentLoaded', updateTotal);
</script>
</body>
</html>
