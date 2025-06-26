<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>LOGINOUT ADMIN</title>
    <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
    <script>
        // Define contextPath for login.js
        var contextPath = '${pageContext.request.contextPath}';
    </script>
    <script src="${pageContext.request.contextPath}/resources/js/login.js" type="text/javascript"></script>
    <script>
        // 사용자 계정 잠금 해제 함수
        function unlockUser(userLoginId) {
            if (!confirm(userLoginId + " 사용자의 계정 잠금을 해제하시겠습니까?")) {
                return;
            }

            $.ajax({
                type: 'post',
                url: contextPath + '/admin/unlock-user',
                data: { userLoginId: userLoginId },
                success: function(result) {
                    if (result.success) {
                        // 성공 메시지 표시
                        showResultMessage(result.message, 'success');

                        // 테이블에서 해당 행 제거
                        $('#user-row-' + userLoginId).fadeOut(300, function() {
                            $(this).remove();

                            // 테이블에 행이 없으면 "잠긴 사용자 계정이 없습니다" 메시지 표시
                            if ($('#locked-users-container tbody tr').length === 0) {
                                $('#locked-users-container').html(
                                    '<p style="text-align: center; color: #666; padding: 20px;">잠긴 사용자 계정이 없습니다.</p>'
                                );
                            }
                        });
                    } else {
                        // 실패 메시지 표시
                        showResultMessage(result.message, 'error');
                    }
                },
                error: function(xhr, status, error) {
                    console.error('Error:', error);
                    showResultMessage('서버 오류가 발생했습니다. 다시 시도해주세요.', 'error');
                }
            });
        }

        // 결과 메시지 표시 함수
        function showResultMessage(message, type) {
            var resultDiv = $('#result-message');

            // 메시지 타입에 따른 스타일 설정
            if (type === 'success') {
                resultDiv.css('background-color', '#d4edda').css('color', '#155724');
            } else {
                resultDiv.css('background-color', '#f8d7da').css('color', '#721c24');
            }

            // 메시지 설정 및 표시
            resultDiv.text(message).fadeIn(300);

            // 3초 후 메시지 숨기기
            setTimeout(function() {
                resultDiv.fadeOut(300);
            }, 3000);
        }
    </script>
    <style>
        * {
            margin: 0;
            padding: 0;
            box-sizing: border-box;
        }

        body, html {
            height: 100%;
            font-family: 'Noto Sans KR', sans-serif;
        }

        .container {
            position: relative;
            width: 100%;
            height: 100vh;
            overflow: hidden;
            background-color: #f5f5f5;
        }

        .admin-content {
            position: absolute;
            top: 50%;
            left: 50%;
            transform: translate(-50%, -50%);
            text-align: center;
        }

        h1 {
            font-size: 36px;
            margin-bottom: 20px;
            color: #333;
        }

        .menu-toggle {
            position: absolute;
            top: 20px;
            left: 20px;
            font-size: 24px;
            cursor: pointer;
            z-index: 1001;
        }

        .close-btn {
            position: absolute;
            top: 20px;
            right: 20px;
            font-size: 24px;
            cursor: pointer;
            display: none;
            z-index: 2000;
        }

        .sidebar {
            position: fixed;
            top: 0;
            left: -260px;
            width: 260px;
            height: 100%;
            background-color: white;
            box-shadow: 2px 0 5px rgba(0,0,0,0.2);
            padding: 60px 20px;
            transition: left 0.3s ease;
            z-index: 1500;
        }

        .sidebar.open {
            left: 0;
        }

        .sidebar ul {
            list-style: none;
            padding: 0;
        }

        .sidebar ul li {
            margin-bottom: 20px;
            font-size: 16px;
            font-weight: bold;
        }

        .sidebar ul li a {
            text-decoration: none;
            color: black;
        }

        .sidebar ul li a:hover {
            color: purple;
        }

        /* 세로 LOGINOUT 텍스트 - 더 크게 */
        .vertical-text {
            position: absolute;
            top: 50%;
            left: 0;
            transform: translateY(-50%);
            writing-mode: vertical-rl;
            text-orientation: mixed;
            color: #000;
            font-weight: bold;
            font-size: 20px;
            z-index: 500;
        }

        /* 하단 아이콘 - 항상 고정되게 */
        .icon-bar {
            position: fixed;
            bottom: 20px;
            left: 20px;
            display: flex;
            flex-direction: column;
            gap: 10px;
            z-index: 9999; /* 사이드바보다 위 */
        }

        .icon-bar img {
            width: 24px;
            height: 24px;
        }
    </style>
</head>
<body>
    <div class="container">
        <%@ include file="fragments/sidebar.jsp" %>

        <div class="admin-content">
            <h1>Admin Page</h1>
            <p>환영합니다, <b>${userName}</b> 관리자님!</p>

            <!-- 잠긴 사용자 목록 섹션 -->
            <div class="locked-users-section" style="margin-top: 40px; text-align: left; max-width: 800px; margin-left: auto; margin-right: auto;">
                <h2 style="margin-bottom: 20px; font-size: 24px; color: #333;">잠긴 사용자 계정 관리</h2>

                <div id="locked-users-container">
                    <c:choose>
                        <c:when test="${empty lockedUsers}">
                            <p style="text-align: center; color: #666; padding: 20px;">잠긴 사용자 계정이 없습니다.</p>
                        </c:when>
                        <c:otherwise>
                            <table style="width: 100%; border-collapse: collapse; margin-bottom: 20px;">
                                <thead>
                                    <tr style="background-color: #f2f2f2; border-bottom: 1px solid #ddd;">
                                        <th style="padding: 12px; text-align: left;">사용자 ID</th>
                                        <th style="padding: 12px; text-align: left;">사용자 이름</th>
                                        <th style="padding: 12px; text-align: center;">로그인 실패 횟수</th>
                                        <th style="padding: 12px; text-align: center;">마지막 로그인</th>
                                        <th style="padding: 12px; text-align: center;">상태</th>
                                        <th style="padding: 12px; text-align: center;">작업</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <c:forEach items="${lockedUsers}" var="user">
                                        <tr style="border-bottom: 1px solid #ddd;" id="user-row-${user.userLoginId}">
                                            <td style="padding: 12px;">${user.userLoginId}</td>
                                            <td style="padding: 12px;">${user.userName}</td>
                                            <td style="padding: 12px; text-align: center;">${user.loginFailCount}</td>
                                            <td style="padding: 12px; text-align: center;">${user.lastLogin}</td>
                                            <td style="padding: 12px; text-align: center;">
                                                <span style="color: red; font-weight: bold;">잠김</span>
                                            </td>
                                            <td style="padding: 12px; text-align: center;">
                                                <button onclick="unlockUser('${user.userLoginId}')" 
                                                        style="background-color: #c084fc; color: white; border: none; 
                                                               padding: 8px 12px; border-radius: 4px; cursor: pointer;">
                                                    잠금 해제
                                                </button>
                                            </td>
                                        </tr>
                                    </c:forEach>
                                </tbody>
                            </table>
                        </c:otherwise>
                    </c:choose>
                </div>

                <!-- 결과 메시지 표시 영역 -->
                <div id="result-message" style="margin-top: 20px; padding: 10px; border-radius: 4px; display: none;"></div>
            </div>
        </div>
    </div>
</body>
</html>
