
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

//해시태그 ajax 방식으로 저장
document.querySelectorAll("#hashtagContainer .hashtag").forEach((tagElem) => {
  tagElem.addEventListener("click", () => {
    const tagId = tagElem.dataset.id;
    const isSelected = tagElem.classList.contains("selected");

    // 최대 5개 제한 검사 (선택할 때만)
    if (!isSelected && document.querySelectorAll(".hashtag.selected").length >= 5) {
      alert("해시태그는 최대 5개까지 선택 가능합니다.");
      return;
    }

    // 서버로 요청 보내기
    fetch(isSelected ? "/mypage/hashtag/delete" : "/mypage/hashtag", {
	      method: "POST",
	      headers: {
	        "Content-Type": "application/json"
	      },
	      body: JSON.stringify({ hashtagId: tagId })
	    })
	    .then((res) => {
	      if (!res.ok) throw new Error("서버 요청 실패");
	      return res.json();
	    })
	    .then((data) => {
	      if (data.success) {
	        tagElem.classList.toggle("selected"); // UI 반영
	      }
	    })
	    .catch((err) => {
	      console.error("해시태그 저장/삭제 실패:", err);
	    });
	  });
	});
	
	
	
/* 알림 동의 비동기 저장 */


document.querySelectorAll('input[name="agreedMyTripAlerts"], input[name="agreedMyTripAlerts"], input[name="agreedCommAlerts"]').forEach(input => {
	input.addEventListener("change", ()=>{
		const alertType = input.name === "agreedMyTripAlerts" ? "TRIP" : "COMM";
		const agreed = input.value === "true";
		
		fetch("/mypage/alerts/update", {
			method: "POST",
			headers: {
				"Content-Type" : "application/json"
			},
			body: JSON.stringify({
				alertType: alertType,
				agreed: agreed
			})
		})
		.then(res=> {
			if(!res.ok) throw new Error("저장 실패");
			return res.json();
		})
		.then(data=> {
			if (data.success) {
				console.log("알림 설정 저쟝 완료");
			}
		})
		.catch(err => {
			console.error("알림 설정 저장 실패:", err);
		});
	});
});