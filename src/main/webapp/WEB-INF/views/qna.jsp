<<<<<<< HEAD
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%--<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>--%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>QnADetails</title>
    <%--<link rel="stylesheet" href="<c:url value='/css/menu.css'/>">--%>
    <script src="https://code.jquery.com/jquery-3.7.1.min.js"></script>
    <style>
        .container {
            display: flex;
            justify-content: center;   /* 가로 가운데 정렬 */
            align-items: center;       /* 세로 가운데 정렬(필요 시) */
            flex-direction: column;
            min-height: 80vh;          /* 세로 중앙정렬 효과 */
        }
        .frm {
            display: flex;
            flex-direction: column;
            align-items: center;
            width: 600px;
            margin: 0 auto;
        }
        .frm input[type="text"], .frm textarea {
            width: 100%;
            margin-bottom: 15px;
            box-sizing: border-box;
            font-size: 1.1em;
        }
        .frm textarea {
            height: 300px;
            resize: vertical;
        }
        .btn {
            margin-right: 8px;
        }
        .writing-header {
            text-align: center;
        }
        .button-row {
            display: flex;
            flex-direction: row;
            justify-content: center;
            gap: 10px;
            margin-top: 10px;
        }
    </style>
</head>
<body>
<div id="menu">
    <ul>
        <li id="logo">Loginout</li>
        <li><a href="<c:url value='/'/>">Home</a></li>
        <%--<li><a href="<c:url value='/board/list'/>">Board</a></li>
        <li><a href="<c:url value='/login/login'/>">login</a></li>
        <li><a href="<c:url value='/register/add'/>">Sign in</a></li>--%>
        <li><a href=""><i class="fas fa-search small"></i></a></li>
    </ul>
</div>
<script>
    let msg="${msg}";
    if(msg == "WRT_ERR") alert("게시물 등록에 실패했습니다. 다시 시도해주세요.");
    if(msg=="MOD_ERR") alert("게시물 수정에 실패하였습니다. 다시 시도해 주세요.");
</script>
<div class="container">
<%--<div style="text-align:center">--%>
    <h2 class="writing-header">QnA 게시판 ${mode == "new" ? "글쓰기" : (mode == "edit" ? "수정" : "읽기")}</h2>
    <!-- 게시글 폼 시작 -->
    <form id="form" class="frm" action="" method="post">
    <input type="hidden" name="memberId" value="${qnaDto.memberId}">
        <input type="hidden" name="qnaId" value="${qnaDto.qnaId}">
        <input name="title" type="text" value="<c:out value='${qnaDto.title}'/>" placeholder="  제목을 입력해 주세요." ${mode == "new" || mode == "edit" ? "" : "readonly='readonly'"}><br>
        <textarea name="content" rows="20" placeholder=" 내용을 입력해 주세요." ${mode == "new" || mode == "edit" ? "" : "readonly='readonly'"}><c:out value='${qnaDto.content}'/></textarea><br>

    <%--<!-- 비밀글 여부 입력 (라디오 버튼) -->
        <div>
            <label>비밀글 여부: </label>
            <input type="radio" name="isSecret" value="true"
                ${qnaDto.isSecret ? 'checked' : ''} ${mode == "new" ? '' : 'disabled'}> 예
            <input type="radio" name="isSecret" value="false"
                ${qnaDto.isSecret == false ? 'checked' : ''} ${mode == "new" ? '' : 'disabled'}> 아니오
        </div>--%>

        <div class="button-row">
            <c:if test="${mode eq 'new'}">
                <button type="button" id="writeBtn" class="btn btn-write"><i class="fa fa-pencil"></i> 등록</button>
            </c:if>
            <c:if test="${mode ne 'new'}">
                <button type="button" id="writeNewBtn" class="btn btn-write"><i class="fa fa-pencil"></i> 새글</button>
            </c:if>
            <%--<c:if test="${mode eq 'view' and qnaDto.memberId eq loginId}">--%>
            <!-- 관리자 계정이거나 글쓴이이면 수정/삭제 노출 -->
            <c:if test="${mode eq 'view' and (qnaDto.memberId eq loginId or isAdmin)}">

                <button type="button" id="modifyBtn" class="btn btn-modify"><i class="fa fa-edit"></i> 수정</button>
                <button type="button" id="removeBtn" class="btn btn-remove"><i class="fa fa-trash"></i> 삭제</button>
            </c:if>
            <button type="button" id="listBtn" class="btn btn-list"><i class="fa fa-bars"></i> 목록</button>
        </div>
    </form>
    <!-- 게시글 폼 끝 -->

    <!-- ======= 댓글 영역 ======= -->
    <div style="width:600px; margin:0 auto;">
        <h3>댓글</h3>
        comment: <input type="text" name="answerContent" id="comment">
        <button id="sendBtn" type="button">등록</button>
        <button id="modBtn" type="button">수정</button>

        <div id="commentList"></div>
        <div id="replyForm" style="display: none">
            <input type="text" name="replyContent">
            <button id="wrtRepBtn" type="button">등록</button>
        </div>
    </div>
    <!-- ======= 댓글 영역 끝 ======= -->

</div>
<script>
    $(document).ready(function () {
        let formCheck = function() {
            let form = document.getElementById("form");
            if (form.title.value == "") {
                alert("제목을 입력해 주세요.");
                form.title.focus();
                return false;
            }
            if (form.content.value == "") {
                alert("내용을 입력해 주세요.");
                form.content.focus();
                return false;
            }
            return true;
        }

        // 새글 버튼
        $("#writeNewBtn").on("click", function(){
            location.href="<c:url value='/qna/write'/>";
        });

        // 등록(글쓰기)
        $("#writeBtn").on("click", function(){
            let form = $("#form");
            form.attr("action", "<c:url value='/qna/write'/>");
            form.attr("method", "post");

            if(formCheck())
                form.submit();
        });

        // 수정
        $("#modifyBtn").on("click", function(){
            let form = $("#form");
            let isReadonly = $("input[name=title]").attr('readonly');

            // 1. 읽기 상태이면, 수정 상태로 변경
            if(isReadonly=='readonly') {
                $(".writing-header").html("QnA 게시판 수정");
                $("input[name=title]").attr('readonly', false);
                $("textarea").attr('readonly', false);
                $("#modifyBtn").html("<i class='fa fa-pencil'></i> 등록");
                return;
            }

            // 2. 수정 상태이면, 서버로 전송
            form.attr("action", "<c:url value='/qna/modify${searchCondition.queryString}'/>");
            form.attr("method", "post");
            if(formCheck())
                form.submit();
        });

        // 삭제
        $("#removeBtn").on("click", function(){
            if(!confirm("정말로 삭제하시겠습니까?")) return;

            let form = $("#form");
            form.attr("action", "<c:url value='/qna/remove${searchCondition.queryString}'/>");
            form.attr("method", "post");
            form.submit();
        });

        // 목록
        $("#listBtn").on("click", function(){
            location.href="<c:url value='/qna/list${searchCondition.queryString}'/>";
        });
    });
</script>
<!-- 댓글 영역 -->
<script>
    //let qnaId = 2063;
    let qnaId = '${qnaDto.qnaId}';


    let toHTML = function (comments) {
        let tmp = "<ul>";

        comments.forEach(function (comment) {
            tmp += '<li data-answerId=' + comment.answerId
            tmp += ' data-panswerId=' + comment.panswerId
            tmp += ' data-qnaId=' + comment.qnaId + '>'
            if (comment.answerId != comment.panswerId)
                tmp += 'ㄴ'
            tmp += ' commenter=<span class="commenter">' + comment.memberId + '</span>'
            tmp += ' content=<span class="content">' + comment.answerContent + '</span>'
            tmp += ' update_at=' + comment.updateAt
            tmp += '<button class="delBtn">삭제</button>'
            tmp += '<button class="modBtn">수정</button>'
            tmp += '<button class="replyBtn">답글</button>'
            tmp += '</li>'
        })
        return tmp + "</ul>";
    }

    let showList = function(qnaId) {
        $.ajax({
            type:'GET',       // 요청 메서드
            url: '/toyproject/comments?qnaId='+qnaId,  // 요청 URI
            success : function(result){
                $("#commentList").html(toHTML(result));    // 올바른 jQuery 선택자
            },
            error   : function(){ alert("error") } // 에러가 발생했을 때, 호출될 함수
        });
    };

    $(document).ready(function() {
        showList(qnaId);

        $("#modBtn").click(function () {
            let answerId = $(this).attr("data-answerId");
            let answerContent = $("input[name=answerContent]").val();

            if (answerContent.trim() == '') {
                alert("댓글을 입력해주세요.");
                $("input[name=answerContent]").focus();
                return;
            }

            $.ajax({
                type: 'PATCH',       // 요청 메서드
                url: '/toyproject/comments/' + answerId,  // 요청 URI // /toyproject/comments/27
                headers: {"content-type": "application/json"}, // 요청 헤더
                data: JSON.stringify({answerId: answerId, answerContent: answerContent}),  // 서버로 전송할 데이터. stringify()로 직렬화 필요.
                success: function (result) {
                    alert(result);
                    showList(qnaId);
                },
                /*error: function () {
                    alert("error")
                } // 에러가 발생했을 때, 호출될 함수*/
                error: function (xhr) {
                    // 서버에서 받은 에러 메시지를 경고창에 출력
                    alert(xhr.responseText);
                }

            }); // $.ajax()
        });

        $("#sendBtn").click(function () {
            let answerContent = $("input[name=answerContent]").val();

            if (answerContent.trim() == '') {
                alert("댓글을 입력해주세요.");
                $("input[name=answerContent]").focus();
                return;
            }

            $.ajax({
                type: 'POST',       // 요청 메서드
                url: '/toyproject/comments?qnaId=' + qnaId,  // 요청 URI
                headers: {"content-type": "application/json"}, // 요청 헤더
                data: JSON.stringify({qnaId: qnaId, answerContent: answerContent}),  // 서버로 전송할 데이터. stringify()로 직렬화 필요.
                success: function (result) {
                    alert(result);
                    showList(qnaId);
                },
               /* error: function () {
                    alert("error")
                } // 에러가 발생했을 때, 호출될 함수*/
                error: function (xhr) {
                    // 서버에서 받은 에러 메시지를 경고창에 출력
                    alert(xhr.responseText);
                }

            }); // $.ajax()
        });

        $("#wrtRepBtn").click(function () {
            let answerContent = $("input[name=replyContent]").val();
            let panswerId = $("#replyForm").parent().attr("data-panswerId");

            if (answerContent.trim() == '') {
                alert("댓글을 입력해주세요.");
                $("input[name=replyContent]").focus();
                return;
            }

            $.ajax({
                type: 'POST',       // 요청 메서드
                url: '/toyproject/comments?qnaId=' + qnaId,  // 요청 URI
                headers: {"content-type": "application/json"}, // 요청 헤더
                data: JSON.stringify({panswerId:panswerId, qnaId: qnaId, answerContent: answerContent}),  // 서버로 전송할 데이터. stringify()로 직렬화 필요.
                success: function (result) {
                    alert(result);
                    showList(qnaId);
                },
                /*error: function () {
                    alert("error")
                } // 에러가 발생했을 때, 호출될 함수*/
                error: function (xhr) {
                    // 서버에서 받은 에러 메시지를 경고창에 출력
                    alert(xhr.responseText);
                }

            }); // $.ajax()

            $("#replyForm").css("display", "none")
            $("input[name=replyContent]").val('')
            $("replyForm").appendTo("body");
            //답글 폼 위치를 원래대로 되돌린다
        });


        $("#commentList").on("click", ".modBtn", function () {
            let answerId = $(this).parent().attr("data-answerId");
            let answerContent = $("span.content", $(this).parent()).text();

            //1.comment의 내용을 input에 넣어주기
            $("input[name=answerContent]").val(answerContent);
            //2.answerId  전달하기
            $("#modBtn").attr("data-answerId", answerId);
        });

        $("#commentList").on("click", ".replyBtn", function () {
            //1. replyForm을 옮기고
            $("#replyForm").appendTo($(this).parent());

            //2. 답글을 입력할 폼을 보여주고
            $("#replyForm").css("display", "block");

        });


        //$(".delBtn").click(function(){
        $("#commentList").on("click", ".delBtn", function () {
            let answerId = $(this).parent().attr("data-answerId");
            let qnaId = $(this).parent().attr("data-qnaId");
            $.ajax({
                type: 'DELETE',       // 요청 메서드
                url: '/toyproject/comments/' + answerId + '?qnaId=' + qnaId,  // 요청 URI
                success: function (result) {
                    alert(result)
                    showList(qnaId);
                },
                /*error: function () {
                    alert("error")
                } // 에러가 발생했을 때, 호출될 함수*/
                error: function (xhr) {
                    // 서버에서 받은 에러 메시지를 경고창에 출력
                    alert(xhr.responseText);
                }

            });
        });

    });
</script>

=======
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
  <meta charset="UTF-8">
  <title>Q&A</title>
  <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
  <script>
    // Define contextPath for login.js
    var contextPath = '${pageContext.request.contextPath}';
  </script>
  <script src="${pageContext.request.contextPath}/resources/js/login.js" type="text/javascript"></script>
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

    .header {
      font-size: 24px;
      font-weight: bold;
      margin-bottom: 20px;
    }

    .qna-table {
      width: 100%;
      border-collapse: collapse;
      margin-bottom: 20px;
    }

    .qna-table th, .qna-table td {
      padding: 10px;
      text-align: left;
      border-bottom: 1px solid #ddd;
    }

    .qna-table th {
      background-color: #f2f2f2;
      font-weight: bold;
    }

    .qna-table tr:hover {
      background-color: #f5f5f5;
    }

    .qna-id {
      width: 10%;
    }

    .qna-title {
      width: 50%;
    }

    .qna-date {
      width: 20%;
    }

    .qna-view-count {
      width: 10%;
    }

    .qna-pinned {
      background-color: #f8f8f8;
      font-weight: bold;
    }

    .qna-title a {
      display: block;
      text-decoration: none;
      color: inherit;
    }

    .qna-title a:hover {
      color: #007bff;
      text-decoration: underline;
    }
  </style>
</head>
<body>

<%@ include file="fragments/sidebar.jsp" %>

<div style="margin-left: 50px; padding-top: 50px;">

<div class="header">Q&A</div>

<table class="qna-table">
  <thead>
    <tr>
      <th class="qna-id">번호</th>
      <th class="qna-title">제목</th>
      <th class="qna-date">등록일</th>
      <th class="qna-view-count">조회수</th>
    </tr>
  </thead>
  <tbody>
    <c:forEach var="qna" items="${qnaList}" varStatus="status">
      <tr>
        <td class="qna-id">${qna.qnaId}</td>
        <td class="qna-title">
          <a href="${pageContext.request.contextPath}/qna/${qna.qnaId}">
            ${qna.title}
          </a>
        </td>
        <td class="qna-date">
          <fmt:formatDate value="${qna.createAt}" pattern="yyyy-MM-dd" />
        </td>
        <td class="qna-view-count">${qna.viewCnt}</td>
      </tr>
    </c:forEach>
  </tbody>
</table>

</div>
>>>>>>> develop
</body>
</html>
