$(document).ready(function(){

    // Login button click event
    $("#btn").on("click", function(){
        login();
    });

    // Logout button click event
    $("#btn_logout").on("click", function(){
        logout();
    });

});

function login(){
    // 속성 id의 값이 id인 태그를 찾아서 거기에 입력된 value를 가져오는 함수
    let id = $('#id').val();
    let pwd = $('#pwd').val();

    // 입력 검증
    if(id.length === 0 || pwd.length === 0) {
        showErrorMessage('아이디 혹은 비밀번호를 반드시 입력해야 합니다.');
        return;
    }

    if(id.length < 3) {
        showErrorMessage('아이디의 길이는 3이상이어야 합니다.');
        return;
    }

    if(pwd.length < 3) {
        showErrorMessage('비밀번호의 길이는 3이상이어야 합니다.');
        return;
    }

    $.ajax({
        type : 'post', // 타입 (get, post, put 등등)
        url : contextPath + '/loginCheck', // 요청할 서버url
        async : true, // 비동기화 여부 (default : true)
        headers : { // Http header
            "Content-Type" : "application/json",
            "X-HTTP-Method-Override" : "POST"
        },
        dataType : 'json', // 데이터 타입 (html, xml, json, text 등등)
        data : JSON.stringify({ // 보낼 데이터 (Object , String, Array)
            "userLoginId" : id,
            "userPassword" : pwd
        }),
        success : function(result) { // 결과 성공 콜백함수
            console.log(result);
            if (result.success === true) {
                alert(result.message);
                // Show logout div and hide login div
                $('#div_login').hide();
                $('#div_logout').show();
            } else {
                showErrorMessage(result.message);
            }
        },
        error : function(request, status, error) { // 결과 에러 콜백함수
            console.log(error);
            showErrorMessage("로그인 처리 중 오류가 발생했습니다.");
        }
    });
}

function logout() {
    $.ajax({
        type: 'get',
        url: contextPath + '/logout',
        success: function() {
            alert("로그아웃 되었습니다.");
            // Show login div and hide logout div
            $('#div_login').show();
            $('#div_logout').hide();
            // Clear input fields
            $('#id').val('');
            $('#pwd').val('');
        },
        error: function(request, status, error) {
            console.log(error);
            alert("로그아웃 처리 중 오류가 발생했습니다.");
        }
    });
}

function showErrorMessage(msg) {
    $('#msg').html('<i class="fa fa-exclamation-circle"> ' + msg + '</i>');
}
