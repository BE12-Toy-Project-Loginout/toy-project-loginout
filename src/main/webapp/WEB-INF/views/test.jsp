<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <script src="https://code.jquery.com/jquery-1.11.3.js"></script>
</head>
<body>
<h2>Comment Test</h2>
comment: <input type="text" name="answerContent" id="comment"><br>
<%--comment: <input type="text" id="comment"><br>--%>
<button id="sendBtn" type="button">SEND</button>
<button id="modBtn" type="button">수정</button>

<div id="commentList"></div>
<div id="replyForm" style="display: none">
    <input type="text" name="replyContent">
    <button id="wrtRepBtn" type="button">등록</button>
</div>

<script>
    let qnaId = 2063;

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
            url: '/comments?qnaId='+qnaId,  // 요청 URI
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
                url: '/comments/' + answerId,  // 요청 URI // /toyproject/comments/27
                headers: {"content-type": "application/json"}, // 요청 헤더
                data: JSON.stringify({answerId: answerId, answerContent: answerContent}),  // 서버로 전송할 데이터. stringify()로 직렬화 필요.
                success: function (result) {
                    alert(result);
                    showList(qnaId);
                },
                error: function () {
                    alert("error")
                } // 에러가 발생했을 때, 호출될 함수
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
                url: '/comments?qnaId=' + qnaId,  // 요청 URI
                headers: {"content-type": "application/json"}, // 요청 헤더
                data: JSON.stringify({qnaId: qnaId, answerContent: answerContent}),  // 서버로 전송할 데이터. stringify()로 직렬화 필요.
                success: function (result) {
                    alert(result);
                    showList(qnaId);
                },
                error: function () {
                    alert("error")
                } // 에러가 발생했을 때, 호출될 함수
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
                url: '/comments?qnaId=' + qnaId,  // 요청 URI
                headers: {"content-type": "application/json"}, // 요청 헤더
                data: JSON.stringify({panswerId:panswerId, qnaId: qnaId, answerContent: answerContent}),  // 서버로 전송할 데이터. stringify()로 직렬화 필요.
                success: function (result) {
                    alert(result);
                    showList(qnaId);
                },
                error: function () {
                    alert("error")
                } // 에러가 발생했을 때, 호출될 함수
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
                url: '/comments/' + answerId + '?qnaId=' + qnaId,  // 요청 URI
                success: function (result) {
                    alert(result)
                    showList(qnaId);
                },
                error: function () {
                    alert("error")
                } // 에러가 발생했을 때, 호출될 함수
            });
        });

    });
</script>
</body>
</html>
