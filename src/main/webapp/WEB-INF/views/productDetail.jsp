<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>상품 상세</title>
    <link rel="stylesheet" type="text/css"
          href="${pageContext.request.contextPath}/resources/css/productDetail.css">
    <style>
        input[type=number]::-webkit-outer-spin-button,
        input[type=number]::-webkit-inner-spin-button {
            display: block !important;
            opacity: 1 !important;
        }
        input[type=number] {
            -moz-appearance: textfield;
            appearance: textfield;
        }
    </style>
</head>

<body>
<%@ include file="fragments/sidebar.jsp" %>

<div class="product-area" style="margin-left: 50px;">
    <div class="image-box">
        <img src="${pageContext.request.contextPath}/product/image?productId=${productDetail.productId}"
             onerror="this.src='${pageContext.request.contextPath}/resources/images/no-image.jpg'"
             alt="상품 이미지"/>
    </div>

    <div class="info-box">
        <h1 class="product-name">${fn:escapeXml(productDetail.productName)}</h1>
        <div class="description">
            <c:choose>
                <c:when test="${not empty productDetail.productDetailDescription}">
                    <c:out value="${productDetail.productDetailDescription}"
                           escapeXml="false"/>
                </c:when>
                <c:otherwise>설명 없음</c:otherwise>
            </c:choose>
        </div>

        <div class="line"></div>

        <fmt:parseNumber var="priceNum"
                         value="${productDetail.productPrice}"
                         integerOnly="true"/>
        <div class="info-list">
            <li>
                <span class="label">판매가</span>
                <span class="price">
                    <c:choose>
                        <c:when test="${priceNum != null}">
                            <fmt:formatNumber value="${priceNum}"
                                              type="number"
                                              groupingUsed="true"/>원
                        </c:when>
                        <c:otherwise>${productDetail.productPrice} 원</c:otherwise>
                    </c:choose>
                </span>
            </li>
            <li>
                <span class="label">배송비</span>
                <span>2500원 (실 결제금액 2만원 이상 시, 무료배송)</span>
            </li>
        </div>

        <div class="option-bar">
            <span class="product-name">${fn:escapeXml(productDetail.productName)}</span>
            <div class="quantity-row">
                <div class="input-cell">
                    <input type="number" id="quantity" value="1" min="1"
                           oninput="updateTotal()"/>
                </div>
            </div>
            <span class="price" id="priceDisplay">
                <fmt:formatNumber value="${priceNum}"
                                  type="number"
                                  groupingUsed="true"/>원
            </span>
        </div>

        <div class="line"></div>

        <div class="total-area">
            <span class="text">총 상품금액</span>
            <span id="productTotal" class="amount">0원</span>
            <span id="totalQty" class="qty-info">1개</span>
        </div>

        <div class="line"></div>

        <div class="button-group">
            <button type="button" class="primary"
                    onclick="sendProductData('${pageContext.request.contextPath}/order')">
                바로 구매하기
            </button>
            <button type="button" class="secondary"
                    onclick="sendProductData('${pageContext.request.contextPath}/cart')">
                장바구니
            </button>
            <input type="hidden" name="productDetailId"
                   value="${productDetail.productDetailId}/wish"/>
            <button type="submit">관심 목록</button>
        </div>

        <!-- 숨겨진 폼: 실제 POST 할 필드들 -->
        <form id="actionForm" method="post"
              action="${pageContext.request.contextPath}/cart"
              style="display:none;">
            <input type="hidden" name="productId"
                   value="${productDetail.productId}"/>
            <input type="hidden" name="productName"
                   value="${productDetail.productName}"/>

            <!-- 쉼표 제거된 단가를 JS 에서도 읽기 쉽게 숨김 필드로 -->

            <fmt:parseNumber var="priceNum"
                             value="${productDetail.productPrice}"
                             integerOnly="true"/>
            <input type="hidden" name="productPrice"
                   value="${priceNum}" />

            <input type="hidden" name="quantity"
                   id="formQuantity"
                   value="1" />

            <!-- 수량 & 최종 합계 -->

            <input type="hidden" name="totalPrice" id="formTotalPrice"
                   value="${fn:replace(productDetail.productPrice, ',', '')}"/>
        </form>
    </div>
</div>

<script>
    // 단가 (숫자)
    const unitPrice = ${priceNum};

    function updateTotal() {
        const qty = parseInt(document.getElementById("quantity").value || '1', 10);
        const total = unitPrice * qty;

        // 화면에 표시
        document.getElementById("productTotal")
            .innerText = total.toLocaleString() + "원";
        document.getElementById("totalQty")
            .innerText = qty + "개";
        document.getElementById("priceDisplay")
            .innerText = total.toLocaleString() + "원";

        // hidden 필드 갱신
        document.getElementById("formQuantity").value = qty;
        document.getElementById("formTotalPrice").value    = total;
    }

    function sendProductData(actionUrl) {
        updateTotal();  // 먼저 hidden quantity 를 최신화
        const form = document.getElementById('actionForm');
        form.action = actionUrl;
        form.submit();
    }

    window.addEventListener('DOMContentLoaded', updateTotal);
</script>

</body>
</html>
