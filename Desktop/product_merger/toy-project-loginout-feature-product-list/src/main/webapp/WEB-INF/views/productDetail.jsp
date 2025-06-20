<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>상품 상세</title>
    <style>
        .product-area {
            display: flex;
            justify-content: space-between;
            padding: 20px;
        }
        .image-box {
            width: 40%;
            height: 300px;
            background-color: #f1f1f1;
            display: flex;
            justify-content: center;
            align-items: center;
            color: #ccc; /* Text color for placeholder */
            font-size: 20px;
            font-weight: bold;
        }
        .info-box {
            width: 55%;
        }
        .button-group {
            margin-top: 20px;
        }
        .button-group button {
            margin-right: 10px;
        }
    </style>
</head>
<body>
<div class="product-area">
    <div class="image-box">
        <!-- 네모로 대체된 이미지 -->
        <div class="image-placeholder">이미지 없음</div>
    </div>

    <div class="info-box">
        <!-- 상품명 출력 -->
        <h1>${fn:escapeXml(productDetail.productName)}</h1>
        <hr>

        <!-- 상품 설명 출력 -->
        <div class="description">
            <c:choose>
                <c:when test="${not empty productDetail.productDetailDescription}">
                    ${fn:escapeXml(productDetail.productDetailDescription)}
                </c:when>
                <c:otherwise>설명 없음</c:otherwise>
            </c:choose>
        </div>

        <!-- 가격 처리 -->
        <fmt:parseNumber var="priceNum" value="${productDetail.productDetailPrice}" integerOnly="true"/>
        <ul class="info-list">
            <!-- 판매가 -->
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
            <!-- 배송비 -->
            <li>
                <span class="label">배송비</span>
                <span>
                    <c:choose>
                        <c:when test="${priceNum >= 20000}">무료배송</c:when>
                        <c:when test="${priceNum > 0}">2만원 미만: 2,500원</c:when>
                        <c:otherwise>배송비 정보 없음</c:otherwise>
                    </c:choose>
                </span>
            </li>
        </ul>

        <!-- 수량 및 가격 계산 -->
        <div class="quantity-row">
            <div class="label-cell">${fn:escapeXml(productDetail.productName)}</div>
            <div class="input-cell">
                <input type="number" id="quantity" value="1" min="1" oninput="updateTotal()">
            </div>
            <span id="unitPrice" data-price="${priceNum}" style="display:none;"></span>
        </div>

        <!-- 총 상품 금액 -->
        <div class="total-area">
            <span class="text">총 상품금액</span>
            <span id="productTotal" class="amount"></span>
            <span id="totalQty" class="qty-info"></span>
        </div>

        <!-- 버튼 그룹 -->
        <div class="button-group">
            <button type="button" class="primary">바로 구매하기</button>
            <button type="button" class="secondary">장바구니</button>
            <button type="button" class="secondary">관심상품</button>
        </div>
    </div>
</div>

<script>
    function updateTotal() {
        var quantity = document.getElementById("quantity").value;
        var unitPrice = document.getElementById("unitPrice").dataset.price;
        var total = quantity * unitPrice;
        document.getElementById("productTotal").innerText = total.toLocaleString();
        document.getElementById("totalQty").innerText = quantity + "개";
    }

    window.addEventListener('DOMContentLoaded', updateTotal);
</script>
</body>
</html>
