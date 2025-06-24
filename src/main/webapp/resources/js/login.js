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

                // 관리자인 경우 관리자 페이지로 리다이렉트
                if (result.isAdmin === true) {
                    window.location.href = contextPath + '/admin';
                } else {
                    // 일반 사용자는 홈페이지로 리다이렉트
                    window.location.href = contextPath + '/home';
                }
                return;

                // This code is unreachable due to the return statement above
                // Keeping the sidebar menu update logic for reference
                /*
                // Update sidebar menu items
                $('#login-link').hide();
                $('#signup-link').hide();
                $('#logout-link').show();

                // Show admin link if user is admin
                if (result.isAdmin === true) {
                    $('#admin-link').show();
                } else {
                    $('#admin-link').hide();
                }
                */
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

            // Update sidebar menu items
            $('#login-link').show();
            $('#signup-link').show();
            $('#logout-link').hide();
            $('#admin-link').hide();

            // No need to hide user welcome message as it's been removed
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

// Sidebar functions moved to sidebar.js
