// 우편번호 찾기
function execDaumPostcode() {
    new daum.Postcode({
        oncomplete: function (data) {
            const roadAddr = data.roadAddress;
            const jibunAddr = data.jibunAddress;

            document.querySelector('input[name="zipcode"]').value = data.zonecode;
            document.querySelector('input[name="address1"]').value = roadAddr || jibunAddr;
            document.querySelector('input[name="address2"]').focus();
        }
    }).open();
}

let isIdChecked = false;

// 아이디 중복 확인
function checkId() {
    const id = document.querySelector('input[name="id"]').value;
    fetch(`/feature/checkId?id=${encodeURIComponent(id)}`)
        .then(res => res.text())
        .then(msg => {
            const msgElem = document.getElementById("idCheckMsg");
            msgElem.innerText = msg;
            if (msg.includes("가능")) {
                msgElem.style.color = "green";
                isIdChecked = true;
            } else {
                msgElem.style.color = "red";
                isIdChecked = false;
            }
        }).catch(() => alert("중복 확인 중 오류 발생"));
}

// 메시지 표시 함수
function setMessage(msg, element) {
    const msgElem = document.getElementById("msg");
    msgElem.innerHTML = `<i class="fa fa-exclamation-circle"> ${msg}</i>`;
    if (element) element.select();
}

// 폼 유효성 검사
function formCheck(frm) {
    const email = frm.email.value;
    const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;

    if (!isIdChecked) {
        alert("아이디 중복 확인을 먼저 해주세요.");
        frm.id.focus();
        return false;
    }

    if (!(4 <= frm.id.value.length && frm.id.value.length <= 16)) {
        setMessage('id의 길이는 4~16자이어야 합니다.', frm.id);
        return false;
    }

    if (!(8 <= frm.pwd.value.length && frm.pwd.value.length <= 16)) {
        setMessage('pwd의 길이는 8~16자이어야 합니다.', frm.pwd);
        return false;
    }

    if (frm.pwd.value !== frm.verifyPwd.value) {
        setMessage('비밀번호와 일치하지 않습니다.', frm.verifyPwd);
        return false;
    }

    if (frm.name.value.length < 2) {
        setMessage('이름은 최소 2글자 이상 입력해야 합니다.', frm.name);
        return false;
    }

    if (frm.phone2.value.length !== 4) {
        setMessage('휴대폰 번호 중간 4자리를 입력해주세요.', frm.phone2);
        return false;
    }

    if (frm.phone3.value.length !== 4) {
        setMessage('휴대폰 번호 끝 4자리를 입력해주세요.', frm.phone3);
        return false;
    }

    if (!email) {
        setMessage('이메일 주소를 입력해 주세요.', frm.email);
        return false;
    }

    if (!isEmailVerified) {
        alert("이메일 인증을 완료해 주세요.");
        return false;
    }

    if (!emailRegex.test(email)) {
        setMessage('올바른 이메일 형식이 아닙니다.', frm.email);
        return false;
    }

    return true;
}

// 이메일 인증 전송
function sendEmail() {
    const email = document.getElementById("email").value;
    const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;

    if (!emailRegex.test(email)) {
        alert("올바른 이메일 형식이 아닙니다.");
        return;
    }
    if (!email) {
        alert("이메일을 입력하세요.");
        return;
    }

    fetch("/feature/sendEmail", {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify({
            to: email,
            subject: "이메일 인증 코드",
            text: "인증 코드: 123456"
        })
    })
        .then(response => response.text())
        .then(result => {
            document.getElementById("emailResult").innerText = result;
        })
        .catch(error => {
            alert("에러 발생: " + error.message);
        });
}

document.addEventListener("DOMContentLoaded", function () {
    document.getElementById("sendEmailBtn").addEventListener("click", sendEmail);
});

let isEmailVerified = false;

function verifyCode() {
    const email = document.getElementById("email").value.trim();
    const code = document.getElementById("emailCode").value.trim();

    const params = new URLSearchParams();
    params.append("email", email);
    params.append("code", code);

    fetch('/feature/verifyEmail', {
        method: 'POST',
        headers: { 'Content-Type': 'application/x-www-form-urlencoded' },
        body: params.toString(),
        credentials: 'include'
    })
        .then(res => res.text())
        .then(result => {
            console.log("서버 응답 결과: [" + result + "]");
            document.getElementById("emailResult").innerText = '';

            const msgElem = document.getElementById("verifyMsg");
            if (result === "인증 성공") {
                msgElem.innerText = "인증 완료!";
                isEmailVerified = true; // ★ 인증 성공 시 true로 설정
            } else {
                msgElem.innerText = "코드가 일치하지 않습니다.";
                isEmailVerified = false;
            }
        });
}