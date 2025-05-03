document.addEventListener("DOMContentLoaded", function () {
  const checkBtn = document.getElementById("checkIdBtn");
  const loginIdInput = document.getElementById("loginId");
  const msgBox = document.getElementById("idCheckMsg");

  console.log("✅ signUp.js loaded!");

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
});