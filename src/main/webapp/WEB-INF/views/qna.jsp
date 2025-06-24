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
            <c:if test="${mode eq 'view' and qnaDto.memberId eq loginId}">
                <button type="button" id="modifyBtn" class="btn btn-modify"><i class="fa fa-edit"></i> 수정</button>
                <button type="button" id="removeBtn" class="btn btn-remove"><i class="fa fa-trash"></i> 삭제</button>
            </c:if>
            <button type="button" id="listBtn" class="btn btn-list"><i class="fa fa-bars"></i> 목록</button>
        </div>
    </form>

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
</body>
</html>


<%--$("#listBtn").on("click", function(){ //GET 방식
    //alert("ListBtn Clicked");
    location.href="<c:url value='/qna/list'/>?currentPage=${currentPage}&pageSize=${pageSize}";
});

$("#modifyBtn").on("click", function(){ //POST 방식
    // 1. 읽기 상태이면 수정 상태로 변경
    let form = $("form");
    let isReadOnly = $("input[name=title]").attr('readonly');

    if(isReadOnly == 'readonly') {
        $("input[name=title]").attr('readonly', false); //title
        $("textarea").attr('readonly', false); //content
        $("#modifyBtn").html("등록");
        $("h2").html("게시물 수정");
        return;
    }

    // 2. 수정 상태이면 수정된 내용을 서버로 전송
    form.attr("action", "<c:url value='/qna/modify'/>");
    form.attr("method", "post");
    form.submit();
});

$("#writeBtn").on("click", function(){ //POST 방식
    let form = $("#form"); //id가 form인 객체를 form 변수에 저장
    form.attr("action", "<c:url value='/qna/write'/>");
    form.attr("method", "post");
    form.submit();
});

$("#removeBtn").on("click", function(){ //POST 방식
    if(!confirm("정말로 삭제하겠습니까?")) return;
    let form = $("#form"); //id가 form인 객체를 form 변수에 저장
    form.attr("action", "<c:url value='/qna/remove'/>?currentPage=${currentPage}&pageSize=${pageSize}");
    form.attr("method", "post");
    form.submit();
});

}); //main함수
</script>
</body>
</html>--%>