<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>상품 상세</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/productDetail.css">
</head>

<body>


<div class="product-area">
    <div class="image-box">
        <img src="${pageContext.request.contextPath}/product/image?productId=${productDetail.productId}"
             onerror="this.src='${pageContext.request.contextPath}/resources/images/no-image.jpg'"
             alt="상품 이미지" />
    </div>

    <div class="info-box">
        <h1 class="product-name">${fn:escapeXml(productDetail.productName)}</h1>
        <div class="description">
            <c:choose>
                <c:when test="${not empty productDetail.productDetailDescription}">
                    <c:out value="${productDetail.productDetailDescription}" escapeXml="false" />
                </c:when>
                <c:otherwise>설명 없음</c:otherwise>
            </c:choose>
        </div>

        <!-- First Line: 정보 및 가격 -->
        <div class="line"></div>

        <fmt:parseNumber var="priceNum" value="${productDetail.productPrice}" integerOnly="true" />
        <div class="info-list">
            <li>
                <span class="label">판매가</span>
                <span class="price">
                    <c:choose>
                        <c:when test="${priceNum != null}">
                            <fmt:formatNumber value="${priceNum}" type="number" groupingUsed="true" />원
                        </c:when>
                        <c:otherwise>${productDetail.productPrice} 원 </c:otherwise>
                    </c:choose>
                </span>
            </li>
            <li>
                <span class="label">배송비</span>
                <span>
                    <c:choose>
                        <c:when test="${priceNum >= 20000}">무료배송</c:when>
                        <c:when test="${priceNum > 0}">2만원 미만: 2,500원</c:when>
                        <c:otherwise>2500원 (실 결제금액 2만원 이상 시, 무료배송)</c:otherwise>
                    </c:choose>
                </span>
            </li>
        </div>

        <!-- Second Line: 상품명, 수량 선택, 가격 (옵션 바)-->
        <div class="option-bar">
            <span class="product-name">${fn:escapeXml(productDetail.productName)}</span>

            <div class="quantity-row">
                <div class="input-cell">
                    <input type="number" id="quantity" value="1" min="1" oninput="updateTotal()" />
                </div>
            </div>

            <span class="price" id="priceDisplay">
                <fmt:parseNumber var="priceNum" value="${productDetail.productPrice}" integerOnly="true" />
                <c:choose>
                    <c:when test="${priceNum != null}">
                        <fmt:formatNumber value="${priceNum}" type="number" groupingUsed="true" />원
                    </c:when>
                    <c:otherwise>가격 정보 없음</c:otherwise>
                </c:choose>
            </span>
        </div>

        <div class="line"></div>

        <!-- Third Line: 총 상품 금액 바로 위에-->
        <div class="total-area">
            <span class="text">총 상품금액</span>
            <span id="productTotal" class="amount">0</span>
            <span id="totalQty" class="qty-info">1개</span>
        </div>

        <div class="line"></div>

        <div class="button-group">
            <button type="button" class="primary">바로 구매하기</button>
            <button type="button" class="secondary">장바구니</button>
            <button type="button" class="secondary">관심상품</button>
        </div>
    </div>
</div>

<script>
    function updateTotal() {
        const quantity = parseInt(document.getElementById("quantity").value || 1, 10);
        const unitPrice = ${productDetail.productPrice};  // 단가를 페이지에서 바로 가져옴
        const total = quantity * unitPrice;

        document.getElementById("productTotal").innerText = total.toLocaleString() + "원";  // 총 금액 표시
        document.getElementById("totalQty").innerText = quantity + "개";  // 수량 표시

        // 수량 옵션 선택합계
        document.getElementById("priceDisplay").innerText = total.toLocaleString() + "원";  // 갱신된 총 금액 표시
    }

    window.addEventListener('DOMContentLoaded', updateTotal);  // 페이지 로딩 후 바로 계산 시작
</script>

</body>
</html>
