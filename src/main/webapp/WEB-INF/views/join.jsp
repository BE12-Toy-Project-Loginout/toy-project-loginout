<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
<div class="container">
    <aside class="sidebar">
        <!-- Hamburger menu icon -->
        <svg class="menu-icon" viewBox="0 0 40 40" fill="none">
            <rect x="7" y="10" width="26" height="3" rx="1.5" fill="#3a4251"/>
            <rect x="7" y="18.5" width="26" height="3" rx="1.5" fill="#3a4251"/>
            <rect x="7" y="27" width="26" height="3" rx="1.5" fill="#3a4251"/>
        </svg>
        <div class="logout">LOGINOUT</div>
        <div class="icon-group">
            <!-- Clipboard icon -->
            <svg class="icon" viewBox="0 0 32 32" fill="none">
                <rect x="8" y="4" width="16" height="24" rx="2" stroke="#3a4251" stroke-width="2"/>
                <rect x="12" y="2" width="8" height="4" rx="2" fill="#3a4251"/>
            </svg>
            <!-- Moon icon -->
            <svg class="icon" viewBox="0 0 32 32" fill="none">
                <path d="M24 20.5C21.5 22.5 18 23.5 14.5 22.5C9.5 21 6 16.5 7.5 11.5C8.5 8 11 5.5 14 4.5C13.5 6 13.5 8 14.5 10C16 13.5 19.5 16 24 16.5C24 17.5 24 19 24 20.5Z" stroke="#3a4251" stroke-width="2"/>
            </svg>
            <!-- Search icon -->
            <svg class="icon" viewBox="0 0 32 32" fill="none">
                <circle cx="15" cy="15" r="8" stroke="#3a4251" stroke-width="2"/>
                <line x1="22" y1="22" x2="28" y2="28" stroke="#3a4251" stroke-width="2" stroke-linecap="round"/>
            </svg>
        </div>
    </aside>
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
