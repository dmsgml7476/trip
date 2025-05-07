/*myPage js  */



const likeStories = window.likeStories || [];

console.log(">>> 좋아요 스토리 목록", likeStories);

  let currentIndex = 0;

  function updateLikeStoryBoxes() {
    const total = likeStories.length;

    const prev = (currentIndex - 1 + total) % total;
    const current = currentIndex;
    const next = (currentIndex + 1) % total;

    const prevBox = document.getElementById("prev-story-box");
    const currBox = document.getElementById("current-story-box");
    const nextBox = document.getElementById("next-story-box");

    // Helper
    function render(box, data) {
	  box.setAttribute("data-id", data.storyId); 
      box.querySelector(".story-img img").src = data.imageUrl;
      box.querySelector(".story-nickname").textContent = data.nickname + "님의 일정";
      box.querySelector(".story-title").textContent = data.title;
      box.querySelector(".story-date").textContent = data.date;
      box.querySelector(".story-content").textContent = data.content;
    }

    if (total >= 1) render(prevBox, likeStories[prev]);
    if (total >= 2) render(currBox, likeStories[current]);
    if (total >= 3) render(nextBox, likeStories[next]);
  }

  document.addEventListener("DOMContentLoaded", function () {
    // 최초 렌더링
    if (likeStories.length > 0) {
      updateLikeStoryBoxes();
    }

    // 좌우 버튼 이벤트 연결
    document.querySelector(".right").addEventListener("click", function () {
      currentIndex = (currentIndex + 1) % likeStories.length;
      updateLikeStoryBoxes();
    });

    document.querySelector(".left").addEventListener("click", function () {
      currentIndex = (currentIndex - 1 + likeStories.length) % likeStories.length;
      updateLikeStoryBoxes();
    });
	
	["prev-story-box", "current-story-box", "next-story-box"].forEach(id => {
	   const box = document.getElementById(id);
	   if (box) {
	     box.addEventListener("click", function () {
	       const storyId = this.getAttribute("data-id");
	       if (storyId) {
	         window.location.href = `/westory/weShow/${storyId}`;
	       }
	     });
	   }
	   });
  });



document.addEventListener("DOMContentLoaded", function () {
	
const moreBtn = document.getElementById('moreBtn');
  const moreMenu = document.getElementById('moreMenu');
  
  

  moreBtn.addEventListener('click', function (e) {
    moreMenu.style.display =
      moreMenu.style.display === 'block' ? 'none' : 'block';
  });

  // 외부 클릭 시 닫기
  document.addEventListener('click', function (e) {
    if (!moreBtn.contains(e.target) && !moreMenu.contains(e.target)) {
      moreMenu.style.display = 'none';
    }
	
	
  });
});

window.onload = function () {
        let today = new Date();
        var nowY = today.getFullYear(); //현재 연도
        var nowM = today.getMonth(); //현재 월
        var nowD = today.getDate(); //현재 일

        //연도, 월을 입력받지 않은 경우에는 현재 날짜의 연도, 월 정보를 사용함
        y = nowY;
        m = nowM;

        //현재 월의 1일 정보
        var theDate = new Date(y, m, 1);
        var theDay = theDate.getDay();

        //1월부터 12월까지 마지막 일을 배열로 저장
        var last = [31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31];

        //윤년 계산
        if ((y % 4 == 0 && y % 100 != 0) || y % 400 == 0)
          lastDate = last[1] = 29;

        //현재 월의 마지막 일 정보
        var lastDate = last[m]; //현재 월의 마지막이 몇일인지 구함

        //현재 월의 달력에 필요한 행의 개수
        var row = Math.ceil((theDay + lastDate) / 7);

        let cal = document.getElementById("cal");

        var draw = "<table class='calendar'>";
        var dNum = 1;
        for (var i = 1; i <= 6; i++) {
          draw += "<tr>";
          for (var k = 0; k < 7; k++) {
            if ((i == 1 && k < theDay) || dNum > lastDate) {
              draw += "<td class='day-box'> &nbsp; </td>";
            } else {
              //오늘 날짜
              if (dNum === nowD) {
                draw += "<td class='day-box'>" + dNum + "</td>";
              }
              //오늘이 아닌 날짜
              else {
                draw += "<td class='day-box'>" + dNum + "</td>";
              }
              //일 증가
              dNum++;
            }
          }
          draw += "</tr>";
        }

        draw += "</table>";

        cal.innerHTML = draw;
      };
