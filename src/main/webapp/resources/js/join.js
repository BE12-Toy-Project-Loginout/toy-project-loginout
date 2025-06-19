// 우편번호 찾기
function execDaumPostcode() {
    new daum.Postcode({
        oncomplete: function(data) {
            const roadAddr = data.roadAddress;
            const jibunAddr = data.jibunAddress;

            document.querySelector('input[name="zipcode"]').value = data.zonecode;
            document.querySelector('input[name="address1"]').value = roadAddr || jibunAddr;
            document.querySelector('input[name="address2"]').focus();
        }
    }).open();
}



// 아이디 중복확인
function checkId() {
    let isIdChecked = false;
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

// 폼 유효성 검사
function formCheck(frm) {
    const msgElem = document.getElementById("msg");
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

    if (!emailRegex.test(email)) {
        setMessage('올바른 이메일 형식이 아닙니다.', frm.email);
        return false;
    }

    return true;
}

function setMessage(msg, element) {
    const msgElem = document.getElementById("msg");
    msgElem.innerHTML = `<i class="fa fa-exclamation-circle"> ${msg}</i>`;
    if (element) element.select();
}
