document.addEventListener("DOMContentLoaded", function () {
  const checkBtn = document.getElementById("checkIdBtn");
  const loginIdInput = document.getElementById("loginId");
  const msgBox = document.getElementById("idCheckMsg");

  const pwInput = document.getElementById("password");
  const pwMsg = document.getElementById("pwCheckMsg");

  const pwChkInput = document.getElementById("pwChk");
  const pwChkMsg = document.getElementById("pwChkCheckMsg");

  checkBtn.addEventListener("click", function () {
    const loginId = loginIdInput.value.trim();
    const regex = /^[a-z0-9_]+$/;

    if (!loginId) {
      msgBox.textContent = "아이디를 입력해주세요.";
      msgBox.style.color = "red";
      return;
    }

    if (loginId.length < 6 || loginId.length > 16) {
      msgBox.textContent = "아이디는 6자 이상 16자 이하로 입력해주세요.";
      msgBox.style.color = "red";
      return;
    }

    if (!regex.test(loginId)) {
      msgBox.textContent =
        "아이디는 소문자, 숫자, 언더바(_)만 사용할 수 있습니다.";
      msgBox.style.color = "red";
      return;
    }

    fetch(`/api/check-id?loginId=${encodeURIComponent(loginId)}`)
      .then((res) => res.json())
      .then((data) => {
        if (data.available) {
          msgBox.textContent = "사용 가능한 아이디입니다.";
          msgBox.style.color = "green";
        } else {
          msgBox.textContent = "이미 사용 중인 아이디입니다.";
          msgBox.style.color = "red";
        }
      })
      .catch(() => {
        msgBox.textContent = "서버 오류가 발생했습니다.";
        msgBox.style.color = "red";
      });
  });

  pwInput.addEventListener("input", function () {
    const pw = pwInput.value.trim();

    if (pw === "") {
      pwMsg.textContent = "";
      return;
    }

    const lengthValid = pw.length >= 8 && pw.length <= 20;
    const containsLetter = /[A-Za-z]/.test(pw);
    const containsDigit = /\d/.test(pw);
    const allowedCharsOnly = /^[A-Za-z\d!@#$%^&*]+$/.test(pw);

    let messages = [];

    if (!lengthValid) {
      messages.push("비밀번호는 8자 이상 20자 이하로 입력해주세요.");
    } else if (!(containsLetter && containsDigit)) {
      messages.push("비밀번호에는 영문자와 숫자를 모두 포함해야 합니다.");
    }

    if (!allowedCharsOnly) {
      messages.push("특수문자는 !,@,#,$,%,^,&,* 만 사용할 수 있습니다.");
    }

    if (messages.length > 0) {
      pwMsg.innerHTML = messages.join("<br>");
      pwMsg.style.color = "red";
    } else {
      pwMsg.textContent = "";
    }

    checkPwMatch();
  });

  pwChkInput.addEventListener("input", checkPwMatch);

  function checkPwMatch() {
    const pw = pwInput.value.trim();
    const pwChk = pwChkInput.value.trim();

    if (pw && pwChk && pw === pwChk) {
      pwChkMsg.textContent = "비밀번호가 일치합니다.";
      pwChkMsg.style.color = "green";
    } else if (pwChk.length > 0) {
      pwChkMsg.textContent = "비밀번호가 일치하지 않습니다.";
      pwChkMsg.style.color = "red";
    } else {
      pwChkMsg.textContent = "";
    }
  }
});
