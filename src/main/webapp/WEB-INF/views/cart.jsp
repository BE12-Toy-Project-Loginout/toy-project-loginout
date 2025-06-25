<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>장바구니</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/cart.css">
</head>
<body>
<h2>장바구니</h2>

<form id="cartForm" method="post" action="${pageContext.request.contextPath}/cart">
    <table>
        <thead>
        <tr>
            <th><input type="checkbox" id="selectAll"/></th>
            <th>이미지</th>
            <th>상품 정보</th>
            <th>판매가</th>
            <th>수량</th>
            <th>배송구분</th>
            <th>배송비</th>
            <th>합계</th>
            <th>선택</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="item" items="${cartList}">
            <tr>
                <td><input type="checkbox" name="selected" value="${item.cartId}"/></td>
                <td><div class="placeholder-img"></div></td>
                <td><strong>${item.productName}</strong></td>
                <td class="unit-price">${fn:replace(item.productPrice, ',', '')}</td>
                <td>
                    <input
                            type="number"
                            class="qty-input"
                            name="quantity_${item.cartId}"
                            value="${item.quantity}"
                            min="1"
                            oninput="updateCart()"
                    />
                </td>
                <td>택배</td>
                <td class="shipping">2500</td>
                <td class="row-sum"></td>
                <td>
                    <form action="${pageContext.request.contextPath}/order" method="post" style="display:inline">
                        <input type="hidden" name="cartId"    value="${item.cartId}"/>
                        <input type="hidden" name="productId" value="${item.productId}"/>
                        <input type="hidden" name="quantity"  value="${item.quantity}"/>
                        <button type="submit" class="btn btn-order">주문하기</button>
                    </form>
                    <form action="${pageContext.request.contextPath}/wishlist/add" method="post" style="display:inline">
                        <input type="hidden" name="productId" value="${item.productId}"/>
                        <button type="submit" class="btn">관심상품등록</button>
                    </form>
                    <form action="${pageContext.request.contextPath}/cart/delete" method="post" style="display:inline">
                        <input type="hidden" name="cartId" value="${item.cartId}"/>
                        <button type="submit" class="btn btn-delete">X 삭제</button>
                    </form>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</form>

<div class="summary">
    <div>
        총 상품 구매 금액: <strong id="totalProducts">0원</strong>
        &nbsp;+ 배송비: <strong id="totalShipping">0원</strong>
        &nbsp;= 결제 예정 금액: <strong id="grandTotal">0원</strong>
    </div>
</div>

<div class="footer-summary">
    <form id="orderAllForm" method="post" action="${pageContext.request.contextPath}/order">
        <c:forEach var="item" items="${cartList}">
            <input type="hidden" name="productId" value="${item.productId}" />
            <input type="hidden" name="quantity"  value="${item.quantity}" />
        </c:forEach>
        <button type="submit" class="btn btn-order">전체 상품 구매</button>
    </form>

    <form id="orderSelectedForm" method="post" action="${pageContext.request.contextPath}/order/selected">
        <button type="submit" class="btn">선택 상품 주문</button>
    </form>
</div>

<script>
    const SHIPPING_FEE = 2500;

    function updateCart() {
        let totalProd = 0;
        const rows = document.querySelectorAll('tbody tr');
        rows.forEach(row => {
            const unit = parseInt(row.querySelector('.unit-price').innerText, 10) || 0;
            let qty = parseInt(row.querySelector('.qty-input').value, 10);
            if (isNaN(qty) || qty < 1) qty = 1;
            const rowSum = unit * qty + SHIPPING_FEE;
            row.querySelector('.row-sum').innerText = rowSum.toLocaleString() + '원';
            totalProd += unit * qty;
        });
        const totalShip = SHIPPING_FEE * rows.length;
        document.getElementById('totalProducts').innerText  = totalProd.toLocaleString()  + '원';
        document.getElementById('totalShipping').innerText = totalShip.toLocaleString() + '원';
        document.getElementById('grandTotal').innerText    = (totalProd + totalShip).toLocaleString() + '원';
    }

    document.querySelectorAll('.qty-input').forEach(input =>
        input.addEventListener('input', updateCart)
    );
    updateCart();

    document.getElementById('selectAll').addEventListener('change', e => {
        document.querySelectorAll('input[name="selected"]').forEach(cb =>
            cb.checked = e.target.checked
        );
    });
</script>
</body>
</html>
