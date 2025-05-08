document.addEventListener("DOMContentLoaded", function () {
  // 모달 열기
  document.getElementById("findIdBtn").addEventListener("click", function (e) {
    e.preventDefault();
    openFindIdModal();
  });
  
  document.getElementById("findPwBtn").addEventListener("click", function (e) {
      e.preventDefault();
      openFindPwModal();
    });

  // 이메일 입력 및 인증번호 전송
  const emailInput = document.querySelector('input[type="email"]');
  const sendCodeBtn = document.querySelector('.check-email button');
  const emailMsg = document.getElementById('chkEmailMsg');

  const codeInput = document.getElementById("emailCodeInput");
  const chkCodeBtn = document.getElementById("chkNumCheck");
  const codeMsg = document.getElementById("chkCodeMsg");
  const nextBtn = document.querySelector(".next-btn");

  // 이메일 입력값이 없으면 버튼 비활성화
  emailInput.addEventListener("input", () => {
    sendCodeBtn.disabled = emailInput.value.trim() === "";
  });

  // 인증번호 전송 버튼 클릭
  sendCodeBtn.addEventListener("click", function (e) {
    e.preventDefault();

    const email = emailInput.value.trim();
    if (!email) return;

    fetch("/auth/email/send", {
      method: "POST",
      headers: {
        "Content-Type": "application/json"
      },
      body: JSON.stringify({
        email: email,
        context: "find" // 아이디 찾기 목적
      })
    })
      .then(res => res.json())
      .then(data => {
        if (data.status === "sent") {
          emailMsg.style.color = "green";
          emailMsg.textContent = "인증번호를 전송했습니다.";
          emailMsg.style.display = "block";
          codeInput.focus();
        } else if (data.status === "not_found") {
          emailMsg.style.color = "red";
          emailMsg.textContent = "일치하는 이메일이 없습니다.";
          emailMsg.style.display = "block";
        } else {
          emailMsg.style.color = "red";
          emailMsg.textContent = "이메일 전송에 실패했습니다.";
          emailMsg.style.display = "block";
        }
      })
      .catch(() => {
        emailMsg.textContent = "서버 오류가 발생했습니다.";
        emailMsg.style.color = "red";
        emailMsg.style.display = "block";
      });
  });

  // 인증번호 확인 버튼 클릭
  chkCodeBtn.addEventListener("click", function () {
    const code = codeInput.value.trim();

    if (!code) {
      codeMsg.textContent = "인증번호를 입력해주세요.";
      codeMsg.style.color = "red";
      codeMsg.style.display = "block";
      return;
    }

    fetch("/auth/email/verify", {
      method: "POST",
      headers: {
        "Content-Type": "application/json"
      },
      body: JSON.stringify({ code })
    })
      .then(res => res.json())
      .then(data => {
        if (data.success) {
          codeMsg.textContent = "인증되었습니다!";
          codeMsg.style.color = "green";
          codeMsg.style.display = "block";
          nextBtn.disabled = false;
        } else {
          codeMsg.textContent = "인증번호가 일치하지 않습니다.";
          codeMsg.style.color = "red";
          codeMsg.style.display = "block";
          nextBtn.disabled = true;
        }
      })
      .catch(() => {
        codeMsg.textContent = "서버 오류가 발생했습니다.";
        codeMsg.style.color = "red";
        codeMsg.style.display = "block";
      });
  });
});

// 모달 열기
function openFindIdModal() {
  const modal = document.getElementById("findIdModal");
  modal.style.display = "block";
}

// 모달 닫기
function closeFindIdModal() {
  const modal = document.getElementById("findIdModal");
  modal.style.display = "none";

  // 입력값 초기화
  modal.querySelectorAll("input").forEach(input => {
    input.value = "";
  });

  // 메시지 초기화
  const errorMsg = document.getElementById("pwErrorMsg");
  if (errorMsg) errorMsg.style.display = "none";

  const emailMsg = document.getElementById("chkEmailMsg");
  if (emailMsg) emailMsg.style.display = "none";

  const codeMsg = document.getElementById("chkCodeMsg");
  if (codeMsg) codeMsg.style.display = "none";

  // 다음 버튼 비활성화
  const nextBtn = document.querySelector(".next-btn");
  if (nextBtn) nextBtn.disabled = true;
}

function openIdPrintModal(email) {
  fetch(`/api/find-id?email=${encodeURIComponent(email)}`)
    .then(res => res.json())
    .then(data => {
      const printBox = document.querySelector(".found-id-print p");
      printBox.textContent = data.loginId || "아이디 없음";
      document.getElementById("idPrintModal").style.display = "block";
    });
}

function goToIdPrintModal() {
  const email = document.getElementById("emailInput").value.trim(); 
  closeFindIdModal();
  openIdPrintModal(email);
}


function closeIdPrintModal() {
	const modal=document.getElementById("idPrintModal");
	modal.style.display="none";
}


/* 비밀번호 찾기 창 */


function openFindPwModal() {
	const modal=document.getElementById("findPwModal");
	modal.style.display="block";
}