<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<c:set var="email" value="${receiverInfo.receiverEmail}"/>
<c:set var="email1" value="${fn:split(email, '@')[0]}"/>
<c:set var="email2" value="${fn:split(email, '@')[1]}"/>

<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>주문/결제</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/order.css"/>
</head>
<body>

<c:set var="addrType" value="${param.addrType != null ? param.addrType : 'member'}"/>

<div class="order-container">
    <h1 class="page-title">주문/결제</h1>

    <!-- 배송지 정보 -->
    <section class="section">
        <h2 class="section-title">배송지</h2>

        <div class="direct-input-tab">직접입력</div>

        <div class="address-options">
            <form method="post" action="">
                <c:forEach var="product" items="${products}" varStatus="status">
                    <input type="hidden" name="productId" value="${product.productId}"/>
                    <input type="hidden" name="quantity" value="${quantities[status.index]}"/>
                </c:forEach>
                <label>
                    <input type="radio" name="addrType" value="member"
                    ${addrType == 'member' ? 'checked' : ''} onchange="this.form.submit()"> 회원 정보와 동일
                </label>
                <label>
                    <input type="radio" name="addrType" value="new"
                    ${addrType == 'new' ? 'checked' : ''} onchange="this.form.submit()"> 새로운 배송지
                </label>
            </form>
        </div>

        <form id="orderForm" action="${pageContext.request.contextPath}/order/complete" method="post">
            <c:forEach var="product" items="${products}" varStatus="status">
            <input type="hidden" name="productIds" value="${product.productId}"/>
            <input type="hidden" name="quantities" value="${quantities[status.index]}"/>
            </c:forEach>

            <div class="form-row">
                <label>받는사람 *</label>
                <input type="text" name="receiver" value="${addrType == 'member' ? member.memberName : ''}"/>
            </div>

            <div class="form-row">
                <label>주소 *</label>
                <div class="address-box">
                    <input type="text" name="zipcode" placeholder="우편번호"
                           value="${addrType == 'member' ? member.memberZipcode : ''}"/>
                    <button type="button">주소검색</button>
                </div>
                <input type="text" name="address1" placeholder="기본주소"
                       value="${addrType == 'member' ? member.memberAddress : ''}"/>
                <input type="text" name="address2" placeholder="나머지 주소"
                       value="${addrType == 'member' ? member.memberAddressDetail : ''}"/>
            </div>

            <div class="form-row">
                <label>휴대전화 *</label>
                <div class="phone-box">
                    <select name="phone1">
                        <c:forEach var="prefix" items="${['010','011','016','017','018','019']}">
                            <option value="${prefix}"
                                    <c:if test="${addrType == 'member' && phone1 == prefix}">selected</c:if>>${prefix}</option>
                        </c:forEach>
                    </select> -
                    <input type="text" name="phone2" value="${addrType == 'member' ? phone2 : ''}"/>
                    <input type="text" name="phone3" value="${addrType == 'member' ? phone3 : ''}"/>
                </div>
            </div>

            <div class="form-row">
                <label>이메일</label>
                <div class="email-box">
                    <input type="text" name="email1" value="${email1}" placeholder="이메일 아이디"/> @
                    <input type="text" name="email2" id="emailDomainInput" value="${email2}" placeholder="직접 입력"/>
                    <select name="emailSelect" id="emailDomainSelect" onchange="handleEmailSelect(this)">
                        <option value="">선택하세요</option>
                        <option value="naver.com" ${email2 == 'naver.com' ? 'selected' : ''}>naver.com</option>
                        <option value="gmail.com" ${email2 == 'gmail.com' ? 'selected' : ''}>gmail.com</option>
                        <option value="custom" ${email2 != 'naver.com' and email2 != 'gmail.com' ? 'selected' : ''}>
                            직접입력
                        </option>
                    </select>
                </div>
            </div>

            <div class="form-row">
                <select name="deliveryMsg">
                    <option>-- 메시지 선택 (선택사항) --</option>
                </select>
            </div>

            <div class="form-row">
                <label><input type="checkbox" name="saveDefault"> 기본 배송지로 저장</label>
            </div>

            <section class="section">
                <h2 class="section-title">주문상품</h2>
                <ul class="order-items">
                    <c:set var="totalPrice" value="0"/>
                    <c:forEach var="product" items="${products}" varStatus="status">
                        <c:set var="qty" value="${quantities[status.index]}"/>
                        <c:set var="subtotal" value="${product.productPrice * qty}"/>
                        <li>
                            <span>${product.productName} x ${qty}개</span>
                            <span><fmt:formatNumber value="${subtotal}" type="number"/>원</span>
                        </li>
                        <c:set var="totalPrice" value="${totalPrice + subtotal}"/>
                    </c:forEach>
                </ul>
            </section>

            <section class="section">
                <h2 class="section-title">결제정보</h2>
                <ul class="price-summary">
                    <li>주문상품 <span><fmt:formatNumber value="${totalPrice}" type="number"/>원</span></li>
                    <li>배송비 <span>+0원</span></li>
                    <li>할인/부가결제 <span>-0원</span></li>
                    <li class="total">최종 결제 금액 <span class="highlight"><fmt:formatNumber value="${totalPrice}"
                                                                                         type="number"/>원</span></li>
                </ul>
            </section>
            <button class="pay-button" type="button">주문하기</button>
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
            inputEl.readOnly = true; // ✅ 바뀐 부분
            inputEl.value = selectEl.value;
        }
    }

    function validateOrderForm() {
        const receiver = document.querySelector("input[name='receiver']");
        const zipcode = document.querySelector("input[name='zipcode']");
        const address1 = document.querySelector("input[name='address1']");
        const address2 = document.querySelector("input[name='address2']");
        const phone2 = document.querySelector("input[name='phone2']");
        const phone3 = document.querySelector("input[name='phone3']");
        const email1 = document.querySelector("input[name='email1']");
        const email2 = document.querySelector("input[name='email2']");

        if (!receiver.value.trim()) {
            alert("받는 사람을 입력해주세요.");
            receiver.focus();
            return false;
        }

        if (!zipcode.value.trim() || !address1.value.trim()) {
            alert("주소를 입력해주세요.");
            zipcode.focus();
            return false;
        }

        if (!phone2.value.trim() || !phone3.value.trim()) {
            alert("휴대전화 번호를 모두 입력해주세요.");
            phone2.focus();
            return false;
        }

        if (!email1.value.trim() || !email2.value.trim()) {
            alert("이메일을 입력해주세요.");
            email1.focus();
            return false;
        }

        return true;
    }

    // 주문하기 버튼 이벤트 바인딩
    window.addEventListener("DOMContentLoaded", function () {
        const payButton = document.querySelector(".pay-button");
        const form = document.getElementById("orderForm");

        payButton.addEventListener("click", function (e) {
            if (!validateOrderForm()) {
                e.preventDefault(); // 제출 막기
            } else {
                form.submit(); // 유효성 통과 시 제출
            }
        });
    });
</script>

</body>
</html>
