<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c"   uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn"  uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8"/>
    <title>상품 상세</title>
    <!-- 반드시 이 경로로 CSS를 걸어주세요 -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/productDetail.css"/>
</head>
<body>

<%@ include file="fragments/sidebar.jsp" %>

<div class="product-area">

    <!-- 1) 상단: 두 개의 50% 칸 -->
    <div class="top-section">

        <!-- 왼쪽: 이미지 -->
        <div class="image-box">
            <img src="${pageContext.request.contextPath}/product/image?productId=${productDetail.productId}"
                 onerror="this.src='${pageContext.request.contextPath}/resources/images/no-image.jpg'"
                 alt="${fn:escapeXml(productDetail.productName)}"/>
        </div>

        <!-- 오른쪽: 상품 정보 + 옵션바+총액+버튼 -->
        <div class="detail-box">

            <!-- 상품명/설명/판매가/배송비 -->
            <h1 class="product-name">${fn:escapeXml(productDetail.productName)}</h1>
            <p class="description">
                <c:choose>
                    <c:when test="${not empty productDetail.productDetailDescription}">
                        <c:out value="${productDetail.productDetailDescription}" escapeXml="false"/>
                    </c:when>
                    <c:otherwise>설명 없음</c:otherwise>
                </c:choose>
            </p>

            <fmt:parseNumber var="priceNum" value="${productDetail.productPrice}" integerOnly="true"/>

            <ul class="info-list">
                <li><span class="label">판매가</span>
                    <span class="price"><fmt:formatNumber value="${priceNum}" groupingUsed="true"/>원</span>
                </li>
                <li><span class="label">배송비</span>
                    <span>2500원 (실 결제금액 2만원 이상 시 무료배송)</span>
                </li>
            </ul>

            <div class="line"></div>

            <!-- ★★★ 여기가 “빨간 박스” ★★★ -->
            <div class="option-bar">
                <div class="option-name">${fn:escapeXml(productDetail.productName)}</div>
                <div class="quantity-cell">
                    <input type="number" id="quantity" value="1" min="1" oninput="updateTotal()"/>
                </div>
                <div class="option-price" id="priceDisplay">
                    <fmt:formatNumber value="${priceNum}" groupingUsed="true"/>원
                </div>
            </div>
            <div class="total-area">
                <span>총 상품금액(<span id="totalQty">1개</span>)</span>
                <span id="productTotal">0원</span>
            </div>
            <div class="button-group">
                <button type="button" class="btn-primary" onclick="submitToOrder()">바로 구매하기</button>
                <button type="button" class="btn-secondary" onclick="submitCart()">장바구니</button>
                <button type="button" class="btn-secondary" onclick="submitWishlist()">관심상품</button>
            </div>
            <!-- ★★★ “빨간 박스” 끝 ★★★ -->

        </div>
    </div>
</div>

<!-- 숨겨진 form: memberId 분기 -->
<c:choose>
<c:when test="${not empty sessionScope.memberId}">
<form id="cartForm"
      method="post"
      action="${pageContext.request.contextPath}/cart/${sessionScope.memberId}"
      style="display:none;">
    <input type="hidden" name="memberId" value="${sessionScope.memberId}"/>
    </c:when>
    <c:otherwise>
    <form id="cartForm"
          method="post"
          action="${pageContext.request.contextPath}/cart/guest"
          style="display:none;">
        </c:otherwise>
        </c:choose>
        <input type="hidden" name="productId"    value="${productDetail.productId}"/>
        <input type="hidden" name="productName"  value="${fn:escapeXml(productDetail.productName)}"/>
        <input type="hidden" name="productPrice" value="${priceNum}"/>
        <input type="hidden" name="quantity"     id="formQuantity"   value="1"/>
        <input type="hidden" name="totalPrice"   id="formTotalPrice" value="${priceNum}"/>
    </form>

    <script>
        const unitPrice = ${priceNum};

        function updateTotal() {
            const qty   = parseInt(document.getElementById('quantity').value||'1',10);
            const total = unitPrice*qty;
            document.getElementById('priceDisplay').innerText  = total.toLocaleString() + '원';
            document.getElementById('productTotal').innerText  = total.toLocaleString() + '원';
            document.getElementById('totalQty').innerText      = qty + '개';
            document.getElementById('formQuantity').value      = qty;
            document.getElementById('formTotalPrice').value    = total;
        }

        function submitCart() {
            updateTotal();
            document.getElementById('cartForm').submit();
        }

        function submitToOrder() {
            updateTotal();
            const f = document.getElementById('cartForm');
            f.action = '${pageContext.request.contextPath}/order';
            f.submit();
        }

        function submitWishlist() {
            updateTotal();
            const wf = document.createElement('form');
            wf.method = 'post';
            wf.action = '${pageContext.request.contextPath}/wishlist/add';
            wf.innerHTML = `
      <input type="hidden" name="productId"   value="${productDetail.productId}"/>
      <input type="hidden" name="productName" value="${fn:escapeXml(productDetail.productName)}"/>
      <input type="hidden" name="productPrice" value="${priceNum}"/>
      <input type="hidden" name="quantity"     value="${document.getElementById('formQuantity').value}"/>
    `;
            document.body.appendChild(wf);
            wf.submit();
        }

        document.addEventListener('DOMContentLoaded', updateTotal);
    </script>

</body>
</html>
