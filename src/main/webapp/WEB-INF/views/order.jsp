<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%--<%--%>
<%--  String productId = request.getParameter("productId");--%>
<%--  String quantityStr = request.getParameter("quantity");--%>
<%--  int quantity = quantityStr != null ? Integer.parseInt(quantityStr) : 1;--%>
<%--%>--%>

<!DOCTYPE html>
<html lang="ko">
<head>
  <meta charset="UTF-8">
  <title>주문/결제</title>
  <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/order.css" />
</head>
<body>

<div class="order-container">
  <h1 class="page-title">주문/결제</h1>

  <!-- 배송지 정보 -->
  <section class="section">
    <h2 class="section-title">배송지</h2>

    <div class="address-tabs">
      <button class="tab">최근 배송지</button>
      <button class="tab active">직접입력</button>
    </div>

    <div class="address-options">
      <label><input type="radio" name="addrType" checked> 회원 정보와 동일</label>
      <label><input type="radio" name="addrType"> 새로운 배송지</label>
    </div>

    <form id="orderForm" action="${pageContext.request.contextPath}/payment" method="post">
      <!-- 상품 정보 hidden으로 포함 -->
      <input type="hidden" name="productId" value="${product.productId}" />
      <input type="hidden" name="quantity" value="${quantity}" />

      <div class="form-row">
        <label>받는사람 *</label>
        <input type="text" name="receiver" value="" />
      </div>

      <div class="form-row">
        <label>주소 *</label>
        <div class="address-box">
          <input type="text" name="zipcode" placeholder="우편번호" />
          <button type="button">주소검색</button>
        </div>
        <input type="text" name="address1" placeholder="기본주소" />
        <input type="text" name="address2" placeholder="나머지 주소" />
      </div>

      <div class="form-row">
        <label>휴대전화 *</label>
        <div class="phone-box">
          <select name="phone1">
            <option value="010" selected>010</option>
            <option value="011">011</option>
            <option value="016">016</option>
            <option value="017">017</option>
            <option value="018">018</option>
            <option value="019">019</option>
          </select> -
          <input type="text" name="phone2" maxlength="4" /> -
          <input type="text" name="phone3" maxlength="4" />
        </div>
      </div>

      <div class="form-row">
        <label>이메일</label>
        <div class="email-box">
          <input type="text" name="email1" value="" placeholder="이메일 아이디" /> @

          <select name="emailSelect" id="emailDomainSelect" onchange="handleEmailSelect(this)">
            <option value="">선택하세요</option>
            <option value="naver.com">naver.com</option>
            <option value="gmail.com">gmail.com</option>
            <option value="custom">직접입력</option>
          </select>

          <input type="text" name="email2" id="emailDomainInput" placeholder="직접 입력" disabled />
        </div>

        <div class="form-row">
        <select name="deliveryMsg">
          <option>-- 메시지 선택 (선택사항) --</option>
        </select>
      </div>

      <div class="form-row">
        <label><input type="checkbox" name="saveDefault"> 기본 배송지로 저장</label>
      </div>

      <!-- 주문 상품 정보 -->
      <section class="section">
        <h2 class="section-title">주문상품</h2>
        <ul class="order-items">
          <li>
            <span>${product.productName} x ${quantity}개</span>
            <span>
              <fmt:formatNumber value="${product.productPrice * quantity}" type="number" />원
            </span>
          </li>
        </ul>
      </section>

      <!-- 결제 요약 -->
      <section class="section">
        <h2 class="section-title">결제정보</h2>
        <ul class="price-summary">
          <li>주문상품
            <span>
              <fmt:formatNumber value="${product.productPrice * quantity}" type="number" />원
            </span>
          </li>
          <li>배송비 <span>+0원</span></li>
          <li>할인/부가결제 <span>-0원</span></li>
          <li class="total">
            최종 결제 금액
            <span class="highlight">
              <fmt:formatNumber value="${product.productPrice * quantity}" type="number" />원
            </span>
          </li>
        </ul>

        <!-- 결제수단 -->
        <div class="payment-methods">
          <label>결제수단 선택</label>
          <ul>
            <li><input type="radio" name="payMethod" value="card" checked> 신용카드</li>
            <li><input type="radio" name="payMethod" value="virtual"> 가상계좌</li>
            <li><input type="radio" name="payMethod" value="bank"> 계좌이체</li>
          </ul>
        </div>

        <button class="pay-button" type="submit">결제하기</button>
      </section>
    </form>
  </section>
</div>

<script>
  function handleEmailSelect(selectEl) {
    const inputEl = document.getElementById("emailDomainInput");

    if (selectEl.value === "custom") {
      inputEl.disabled = false;
      inputEl.value = "";
      inputEl.focus();
    } else {
      inputEl.disabled = true;
      inputEl.value = selectEl.value;
    }
  }
</script>



</body>
</html>
