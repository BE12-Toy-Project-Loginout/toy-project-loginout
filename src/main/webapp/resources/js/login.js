$(document).ready(function(){

    //jquery로 컴포넌트에 이벤트
    $("#btn").on("click", function(){
        login();
    });



});

function login(){
    // 속성 id의 값이 id인 태그를 찾아서 거기에 입력된 value를 가져오는 함수
    let id = $('#id').val();
    let pwd = $('#pwd').val();



    $.ajax({
        type : 'post', // 타입 (get, post, put 등등)
        url : '/loginCheck', // 요청할 서버url 이거랑 맞춘거 저거 리턴으로?
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
            if (result === true) {
                alert("로그인 성공!");
                window.location.href = "/";  // 메인 페이지로 리다이렉트
            } else {
                alert("아이디 또는 비밀번호가 일치하지 않습니다.");
            }
        },
        error : function(request, status, error) { // 결과 에러 콜백함수
            console.log(error);
            alert("로그인 처리 중 오류가 발생했습니다.");
        }
    });

}
