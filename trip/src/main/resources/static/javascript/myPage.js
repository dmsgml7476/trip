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