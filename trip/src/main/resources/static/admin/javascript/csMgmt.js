// 요소 선택
const modal = document.getElementById('csMgmtAnsModal');
const modalQuestionDate = document.getElementById('modalQuestionDate');
const modalCsOption = document.getElementById('modalCsOption');
const modalCategory = document.getElementById('modalCategory');
const modalCustomerId = document.getElementById('modalCustomerId');
const modalStatus = document.getElementById('modalStatus');
const modalQuestionText = document.getElementById('modalQuestionText');
const modalAnswer = document.getElementById('modalAnswer');
const statusSelect = document.querySelector('.cs-ans-status select');
const delayButton = document.querySelector('.cs-ans-delay');
const sendButton = document.querySelector('.cs-ans-close');

const csOptionMap = {
  COMPLAIN: "불편사항",
  ETC: "기타"
};

const categoryMap = {
  STORY: "스토리",
  TRAVEL: "장소",
  ETC: "기타"
};

const statusMap = {
  WAITING: "접수",
  IN_PROGRESS: "처리중",
  ANSWERED: "답변완료"
};

// 상태 드롭다운에 따른 동작 제어
statusSelect.addEventListener('change', function () {
  const selected = this.value;

  if (selected === 'WAITING') {
    delayButton.disabled = true;
    sendButton.disabled = true;
    modalAnswer.disabled = true;
    modalAnswer.value = '';
  } else if (selected === 'IN_PROGRESS') {
    delayButton.disabled = false;
    sendButton.disabled = true;
    modalAnswer.disabled = true;
  } else if (selected === 'ANSWERD') {
    delayButton.disabled = true;
    sendButton.disabled = false;
    modalAnswer.disabled = false;
  }
});

// place-row 클릭 시 모달 오픈 및 값 주입
document.querySelectorAll('.place-row').forEach(row => {
  row.addEventListener('click', function () {
    const csOption = this.getAttribute('data-cs-csOption');
    const category = this.getAttribute('data-cs-category');
    const status = this.getAttribute('data-cs-status');
    const answer = this.getAttribute('data-cs-answerText');

    // 선택된 행 표시용 (id 저장 목적)
    document.querySelectorAll('.place-row').forEach(r => r.classList.remove('selected'));
    this.classList.add('selected');

    modalQuestionDate.innerHTML =
      this.getAttribute('data-cs-questionDate') +
      "<br/>" +
      this.getAttribute('data-cs-questionTime');

    modalCsOption.textContent = csOptionMap[csOption] || csOption;
    modalCategory.textContent = categoryMap[category] || category;
    modalCustomerId.textContent = this.getAttribute('data-cs-loginId');
    modalStatus.textContent = statusMap[status] || status;
    modalQuestionText.textContent = this.getAttribute('data-cs-questionText');
    modalAnswer.value = (answer && answer.trim() !== "") ? answer : "";

    // 상태 셀렉트 초기화
    statusSelect.value = status;
    statusSelect.dispatchEvent(new Event('change'));

    modal.style.display = 'block';
  });
});

// 답변 전송
sendButton.addEventListener('click', function () {
  const answer = modalAnswer.value.trim();
  const status = statusSelect.value;
  const selectedRow = document.querySelector('.place-row.selected');
  const csId = selectedRow?.getAttribute('data-cs-id');

  if (!csId || status !== 'ANSWERD') return;
  if (!answer) {
    alert('답변 내용을 입력하세요.');
    return;
  }

  fetch('/admin/csMgmt/answer', {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json'
    },
    body: JSON.stringify({
      customerServiceId: csId,
      answerText: answer,
      status: 'ANSWERD'
    })
  })
    .then(response => {
      if (!response.ok) throw new Error('저장 실패');
      return response.json();
    })
    .then(() => {
      alert('답변이 저장되었습니다.');
      closeCsMgmtAnsModal();
      location.reload();
    })
    .catch(err => {
      console.error(err);
      alert('오류 발생: ' + err.message);
    });
});


delayButton.addEventListener('click', function () {
  const selectedRow = document.querySelector('.place-row.selected');
  const csId = selectedRow?.getAttribute('data-cs-id');

  if (!csId) return;

  fetch('/admin/csMgmt/answer', {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json'
    },
    body: JSON.stringify({
      customerServiceId: csId,
      answerText: "", // 답변 내용 없이 저장
      status: 'IN_PROGRESS'
    })
  })
    .then(response => {
      if (!response.ok) throw new Error('저장 실패');
      return response.json();
    })
    .then(() => {
      alert('처리 상태가 \'처리중\'으로 저장되었습니다.');
      closeCsMgmtAnsModal();
      location.reload();
    })
    .catch(err => {
      console.error(err);
      alert('오류 발생: ' + err.message);
    });
});


function closeCsMgmtAnsModal() {
  modal.style.display = "none";
}
