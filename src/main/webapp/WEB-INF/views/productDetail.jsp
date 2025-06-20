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
        /* 전체 레이아웃 */
        html, body {
            margin: 0; padding: 0; height: 100%; font-family: 'Arial', sans-serif;
            background-color: #fff;
        }
        .product-area {
            display: flex; flex: 1; padding: 20px; box-sizing: border-box;
        }
        @media (max-width: 900px) {
            .product-area {
                flex-direction: column; align-items: center; padding: 10px;
            }
        }
        .image-box {
            flex: 1; display: flex; justify-content: center; align-items: center;
        }
        .image-box .image-placeholder {
            width: 100%; max-width: 500px; aspect-ratio: 1/1;
            border: 2px solid #87AED9;
            display: flex; justify-content: center; align-items: center;
            color: #666; font-size: 14px; background-color: #f9f9f9;
            box-sizing: border-box;
        }
        @media (max-width: 900px) {
            .image-box .image-placeholder {
                max-width: 90vw;
            }
        }
        .info-box {
            flex: 1; display: flex; flex-direction: column; padding-left: 40px;
            box-sizing: border-box;
        }
        @media (max-width: 900px) {
            .info-box { padding-left: 0; margin-top: 20px; width: 100%; }
        }
        .info-box h1 {
            margin: 0 0 10px; font-size: 26px; color: #111;
        }
        hr {
            border: none;
            border-top: 1px solid #E0E0E0;
            margin: 10px 0;
        }
        .description {
            font-size: 14px; color: #333; line-height: 1.5; white-space: pre-wrap;
            margin-bottom: 20px; border-bottom: 1px solid #E0E0E0; padding-bottom: 20px;
        }
        .info-list {
            margin: 0; padding: 0; list-style: none;
        }
        .info-list li {
            display: flex; justify-content: space-between; padding: 10px 0;
            font-size: 16px; color: #333;
        }
        .info-list li + li {
            border-top: 1px solid #E0E0E0;
        }
        .info-list .label {
            font-weight: bold;
        }

        /* 수량 + 단가 행 */
        .quantity-row {
            display: flex; align-items: center;
            background-color: #F2F2F2;
            padding: 10px; margin-top: 20px; box-sizing: border-box;
        }
        .quantity-row .label-cell {
            flex: 1; font-size: 16px; color: #333;
        }
        .quantity-row .input-cell {
            display: flex; align-items: center;
        }
        .quantity-row input[type="number"] {
            width: 60px; padding: 5px; font-size: 16px;
            border: 1px solid #CCC; border-radius: 4px; text-align: center;
            margin: 0 10px;

            /* --- 스피너 항상 보이도록: appearance auto --- */
            appearance: auto;
            -moz-appearance: number-input;
        }
        /* 스피너 아이콘 강제 노출 */
        input[type="number"]::-webkit-outer-spin-button,
        input[type="number"]::-webkit-inner-spin-button {
            -webkit-appearance: inner-spin-button;
            display: block;
            opacity: 1;
            margin: 0;
        }
        input[type="number"] {
            appearance: auto;
            -moz-appearance: number-input;
            /* 오른쪽 패딩으로 스피너와 텍스트 겹침 방지 */
            padding-right: 24px;
        }
        .quantity-row .price-cell {
            flex: 1; text-align: right; font-size: 16px; color: #333;
        }

        /* 합계 영역 */
        .total-area {
            display: flex; justify-content: flex-end; align-items: baseline;
            margin-top: 20px;
        }
        .total-area .text {
            font-size: 16px; color: #333; margin-right: 10px;
        }
        .total-area .amount {
            font-size: 28px; font-weight: bold; color: #000;
        }
        .total-area .qty-info {
            font-size: 16px; color: #555; margin-left: 8px;
        }

        /* 버튼 그룹 */
        .button-group {
            display: flex; gap: 10px; margin-top: 30px;
        }
        .button-group button {
            flex: 1; padding: 12px 0; font-size: 16px; border: none;
            border-radius: 4px; cursor: pointer; transition: opacity 0.2s;
        }
        .button-group button.primary {
            background-color: #CFBCDE; color: #fff;
        }
        .button-group button.secondary {
            background-color: #D9D9D9; color: #333;
        }
        .button-group button:hover {
            opacity: 0.9;
        }
    </style>
    <script>
        // 수량 변경 시, 단가×수량을 linePrice와 productTotal에 반영
        function updateTotal() {
            var unitPriceElem = document.getElementById('unitPrice');
            var unitPrice = 0;
            if (unitPriceElem) {
                var unitPriceStr = unitPriceElem.dataset.price;
                unitPrice = parseInt(unitPriceStr, 10) || 0;
            }
            var qtyInput = document.getElementById('quantity');
            var qty = 1;
            if (qtyInput) {
                qty = parseInt(qtyInput.value, 10) || 1;
                if (qty < 1) {
                    qty = 1;
                    qtyInput.value = 1;
                }
            }
            var lineTotal = unitPrice * qty;
            var linePriceElem = document.getElementById('linePrice');
            if (linePriceElem) {
                linePriceElem.textContent = lineTotal.toLocaleString() + "원";
            }
            var productTotalElem = document.getElementById('productTotal');
            if (productTotalElem) {
                productTotalElem.textContent = lineTotal.toLocaleString() + "원";
            }
            var totalQtyElem = document.getElementById('totalQty');
            if (totalQtyElem) {
                totalQtyElem.textContent = "(" + qty + "개)";
            }
            // (옵션) 배송비/최종결제금액 계산 후 업데이트 로직 삽입 가능
        }
        window.addEventListener('DOMContentLoaded', updateTotal);
    </script>
</head>
<body>
<div class="product-area">
    <!-- 이미지 placeholder -->
    <div class="image-box">
        <div class="image-placeholder">이미지 영역</div>
    </div>

    <!-- 정보 박스 -->
    <div class="info-box">
        <!-- 상품명 -->
        <h1>
            <c:choose>
                <c:when test="${not empty productDetail.productName}">
                    ${fn:escapeXml(productDetail.productName)}
                </c:when>
                <c:otherwise>상품명 없음</c:otherwise>
            </c:choose>
        </h1>

        <hr>

        <!-- 설명 -->
        <div class="description">
            <c:choose>
                <c:when test="${not empty productDetail.productDetailDescription}">
                    ${fn:escapeXml(productDetail.productDetailDescription)}
                </c:when>
                <c:otherwise>설명 없음</c:otherwise>
            </c:choose>
        </div>

        <!-- 판매가/배송비 리스트 -->
        <ul class="info-list">
            <li>
                <span class="label">판매가</span>
                <span>
                    <c:choose>
                        <c:when test="${productDetail.productDetailPrice != null}">
                            <fmt:formatNumber value="${productDetail.productDetailPrice}" type="number" groupingUsed="true"/>원
                        </c:when>
                        <c:otherwise>가격 정보 없음</c:otherwise>
                    </c:choose>
                </span>
            </li>
            <li>
                <span class="label">배송비</span>
                <span>
                    <c:choose>
                        <c:when test="${productDetail.productDetailPrice != null and productDetail.productDetailPrice >= 20000}">
                            무료배송
                        </c:when>
                        <c:when test="${productDetail.productDetailPrice != null}">
                            2500원 (실결제금액 2만원 이상 시 무료배송)
                        </c:when>
                        <c:otherwise>배송비 정보 없음</c:otherwise>
                    </c:choose>
                </span>
            </li>
        </ul>

        <!-- 수량 + 단가 행 -->
        <div class="quantity-row">
            <div class="label-cell">
                <c:choose>
                    <c:when test="${not empty productDetail.productName}">
                        ${fn:escapeXml(productDetail.productName)}
                    </c:when>
                    <c:otherwise>상품</c:otherwise>
                </c:choose>
            </div>
            <div class="input-cell">
                <!-- 기본 스피너만, 좌우 -/+ 버튼 없이 -->
                <input type="number" id="quantity" value="1" min="1" oninput="updateTotal()">
            </div>
            <!-- 단가 데이터 (보이지 않음) -->
            <span id="unitPrice" data-price="${productDetail.productDetailPrice}" style="display:none;"></span>
            <!-- 단가×수량 결과 표시 -->
            <div class="price-cell" id="linePrice"></div>
        </div>

        <!-- 총합계 -->
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
</body>
</html>
