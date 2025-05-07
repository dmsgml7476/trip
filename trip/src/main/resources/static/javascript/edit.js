function submitProfile() {
	const form = document.getElementById('profileForm');
	form.submit();
}

function toggleMbtiEditBox() {
    const box = document.getElementById('mbtiEditBox');
    box.style.display = (box.style.display === 'none') ? 'block' : 'none';
  }
  
function combineMbti(event) {
   const mbti1 = document.querySelector('input[name="mbti1"]:checked');
   const mbti2 = document.querySelector('input[name="mbti2"]:checked');
   const mbti3 = document.querySelector('input[name="mbti3"]:checked');
   const mbti4 = document.querySelector('input[name="mbti4"]:checked');

   if (!mbti1 || !mbti2 || !mbti3 || !mbti4) {
     alert("MBTI의 4가지 항목을 모두 선택해주세요.");
     event.preventDefault(); // 제출 막기
     return;
   }

   const finalMbti = mbti1.value + mbti2.value + mbti3.value + mbti4.value;
   document.getElementById('finalMbti').value = finalMbti;
}