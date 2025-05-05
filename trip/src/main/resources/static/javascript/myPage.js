/*myPage js  */

document.addEventListener("DOMContentLoaded", function () {
	
	console.log("js 제대로 들어감?");
	
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