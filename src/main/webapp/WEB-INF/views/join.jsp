<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<meta charset="UTF-8">

<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>회원 가입</title>
    <meta name="viewport" content="width=1024">
    <link href="https://fonts.googleapis.com/css?family=Roboto:400,700&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/join.css"> <!-- 분리된 CSS 파일 링크 -->
</head>
<body>
<%@ include file="fragments/sidebar.jsp" %>
<div class="container">
    <main class="main-content">
        <div style="width:100%; max-width:700px;">
            <h1>회원 가입</h1>
            <span class="required">* 필수 입력 사항</span>
            <section class="form-section">
                <form action="${pageContext.request.contextPath}/feature/home" method="post" onsubmit="return formCheck(this)">
                    <table>
                        <tr>
                            <th>아이디 *</th>
                            <td>
                                <div style="display:flex; align-items:center;">
                                    <input type="text" name="id" id="id" required autocomplete="username" placeholder="아이디 입력" style="flex:1; margin-right:10px;">
                                    <button type="button" onclick="checkId()" class="idcheck-btn">아이디 중복 확인</button>
                                </div>
                                <span id="idCheckMsg" class="desc"></span>
                                <span class="desc">(영문 소문자/숫자, <strong>4~16자</strong>)</span>
                            </td>
                        </tr>
                        <tr>
                            <th>비밀번호 *</th>
                            <td>
                                <input type="password" name="pwd" placeholder="비밀번호 입력">
                                <span class="desc">(영문 소문자/숫자/특수문자, <strong>8~16자</strong>)</span>
                            </td>
                        </tr>
                        <tr>
                            <th>비밀번호 확인 *</th>
                            <td>
                                <input type="password" name="verifyPwd" placeholder="비밀번호 재입력">
                            </td>
                        </tr>
                        <tr>
                            <th>이름 *</th>
                            <td>
                                <input type="text" name="name" placeholder="이름 입력">
                            </td>
                        </tr>
                        <tr>
                            <th rowspan="2">주소</th>
                            <td>
                                <div class="address-row">
                                    <input type="text" name="zipcode" placeholder="우편번호">
                                    <button type="button" onclick="execDaumPostcode()" class="zipcode-btn">우편번호 찾기</button>
                                </div>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <div class="address-fields">
                                    <input type="text" name="address1" placeholder="기본주소">
                                    <input type="text" name="address2" placeholder="상세주소(선택적 입력 가능)">
                                </div>
                            </td>
                        </tr>
                        <tr>
                            <th>휴대전화 *</th>
                            <td>
                                <div class="phone-row">
                                    <input type="text" name="phone1" value="010" maxlength="3" required>
                                    <span>-</span>
                                    <input type="text" name="phone2" maxlength="4" required>
                                    <span>-</span>
                                    <input type="text" name="phone3" maxlength="4" required>
                                </div>
                            </td>
                        </tr>
                        <tr>
                            <th>이메일 *</th>
                            <td>
                                <div class="input-with-btn">
                                    <input type="email" id="email" name="email" placeholder="이메일 입력">
                                    <button type="button" id="sendEmailBtn" class="email-btn">이메일 인증</button>
                                </div>

                                <div class="input-with-btn">
                                    <input type="text" id="emailCode" placeholder="인증코드 입력" />
                                    <button type="button" onclick="verifyCode()" class="email-btn">코드 확인</button>
                                </div>
                                <span id="verifyMsg"></span><p id="emailResult"></p>
                            </td>
                        </tr>
                    </table>
                    <c:if test="${not empty param.msg}">
                        <div id="msg" class="msg" style="color: #bba2d6; text-align: center; margin: 10px 0;">
                                ${param.msg}
                        </div>
                    </c:if>
                    <div class="submit-row">
                        <button type="submit" class="submit-btn" onclick="">회원가입</button>
                    </div>
                </form>
            </section>
        </div>
    </main>
</div>

<!-- Daum 주소 API -->
<script src="https://t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<!-- JS 파일 -->
<script src="${pageContext.request.contextPath}/resources/js/join.js"></script>

</body>
</html>
