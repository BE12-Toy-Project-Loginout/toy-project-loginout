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
        body {
            font-family: 'Arial', sans-serif;
            background-color: #f4f4f4;
            margin: 0;
            padding: 0;
        }
        .product-area {
            display: flex;
            justify-content: space-between;
            padding: 20px;
            background-color: #fff;
            width: 80%;
            margin: 20px auto;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
            border-radius: 12px;
        }
        .image-box {
            width: 40%;
            height: 350px;
            background-color: #f1f1f1;
            display: flex;
            justify-content: center;
            align-items: center;
            color: #ccc;
            font-size: 20px;
            font-weight: bold;
            border-radius: 10px;
        }
        .info-box {
            width: 55%;
            padding-left: 20px;
        }
        .info-box h1 {
            font-size: 28px;
            font-weight: bold;
            color: #333;
            margin-bottom: 10px;
        }
        .info-box .description {
            font-size: 16px;
            color: #777;
            margin-top: 10px;
        }
        .info-list {
            list-style-type: none;
            padding: 0;
            margin-top: 20px;
        }
        .info-list li {
            display: flex;
            justify-content: space-between;
            padding: 8px 0;
            font-size: 16px;
        }
        .info-list .label {
            color: #333;
            font-weight: bold;
        }
        .quantity-row {
            display: flex;
            align-items: center;
            margin-top: 20px;
        }
        .quantity-row .label-cell {
            font-size: 16px;
            font-weight: bold;
            margin-right: 10px;
        }
        .quantity-row .input-cell input {
            width: 60px;
            padding: 5px;
            font-size: 16px;
            border: 1px solid #ddd;
            border-radius: 5px;
            text-align: center;
        }
        .total-area {
            display: flex;
            justify-content: space-between;
            margin-top: 30px;
            font-size: 20px;
            font-weight: bold;
        }
        .button-group {
            margin-top: 20px;
        }
        .button-group button {
            padding: 12px 20px;
            font-size: 16px;
            border-radius: 5px;
            border: none;
            cursor: pointer;
            margin-right: 15px;
        }
        .button-group .primary {
            background-color: #5f78f3;
            color: #fff;
        }
        .button-group .secondary {
            background-color: #f1f1f1;
            color: #5f78f3;
        }

        .header {
            background-color: #262626;
            color: #fff;
            padding: 15px;
            font-size: 20px;
            text-align: center;
            font-weight: bold;
        }

        .logo {
            width: 120px;
            position: absolute;
            left: 30px;
            top: 30px;
        }
        .logo img {
            width: 100%;
        }

        .price {
            color: #ff6f61;
        }
        .price .currency {
            font-weight: bold;
        }

        .product-name {
            font-size: 22px;
            color: #333;
        }
    </style>
</head>
<body>
<div class="header">
    LOGINOUT
</div>

<div class="product-area">
    <div class="image-box">
        <!-- 네모로 대체된 이미지 -->
        <div class="image-placeholder">이미지 없음</div>
    </div>

    <div class="info-box">
        <!-- 상품명 출력 -->
        <h1 class="product-name">${fn:escapeXml(productDetail.productName)}</h1>
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
                <span class="price">
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
            <div class="label-cell">수량</div>
            <div class="input-cell">
                <input type="number" id="quantity" value="1" min="1" oninput="updateTotal()">
            </div>
        </div>

        <!-- 총 상품 금액 -->
        <div class="total-area">
            <span class="text">총 상품금액</span>
            <span id="productTotal" class="amount">0</span>
            <span id="totalQty" class="qty-info">1개</span>
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
        var unitPrice = document.getElementById("unitPrice") ? document.getElementById("unitPrice").dataset.price : 0;
        var total = quantity * unitPrice;
        document.getElementById("productTotal").innerText = total.toLocaleString();
        document.getElementById("totalQty").innerText = quantity + "개";
    }

    window.addEventListener('DOMContentLoaded', updateTotal);
</script>
</body>
</html>
