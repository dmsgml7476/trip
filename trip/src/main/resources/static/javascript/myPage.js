document.addEventListener("DOMContentLoaded", function () {
  // === 좋아요 스토리 렌더링 ===
  const likeStories = window.likeStories || [];
  let currentIndex = 0;

  function updateLikeStoryBoxes() {
    const total = likeStories.length;
    const prev = (currentIndex - 1 + total) % total;
    const current = currentIndex;
    const next = (currentIndex + 1) % total;

    const prevBox = document.getElementById("prev-story-box");
    const currBox = document.getElementById("current-story-box");
    const nextBox = document.getElementById("next-story-box");

    function render(box, data) {
      box.setAttribute("data-id", data.storyId);
      box.querySelector(".story-img img").src = data.imageUrl;
      box.querySelector(".story-nickname").textContent = data.nickname + "님의 일정";
      box.querySelector(".story-title").textContent = data.title;
      box.querySelector(".story-date").textContent = data.date;
      box.querySelector(".story-content").textContent = data.content;
    }

    if (likeStories.length >= 1) render(prevBox, likeStories[prev]);
    if (likeStories.length >= 2) render(currBox, likeStories[current]);
    if (likeStories.length >= 3) render(nextBox, likeStories[next]);
  }

  if (likeStories.length > 0) updateLikeStoryBoxes();

  document.querySelector(".right").addEventListener("click", () => {
    currentIndex = (currentIndex + 1) % likeStories.length;
    updateLikeStoryBoxes();
  });

  document.querySelector(".left").addEventListener("click", () => {
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

  // === 더보기 메뉴 ===
  const moreBtn = document.getElementById("moreBtn");
  const moreMenu = document.getElementById("moreMenu");

  moreBtn.addEventListener("click", function () {
    moreMenu.style.display = moreMenu.style.display === "block" ? "none" : "block";
  });

  document.addEventListener("click", function (e) {
    if (!moreBtn.contains(e.target) && !moreMenu.contains(e.target)) {
      moreMenu.style.display = "none";
    }
  });

  // === 달력 초기화 ===
  let today = new Date();
  let y = today.getFullYear();
  let m = today.getMonth();

  drawCalendar(y, m);

  document.querySelector(".minus-month").addEventListener("click", () => {
    m = m - 1 < 0 ? 11 : m - 1;
    y = m === 11 ? y - 1 : y;
    drawCalendar(y, m);
  });

  document.querySelector(".plus-month").addEventListener("click", () => {
    m = m + 1 > 11 ? 0 : m + 1;
    y = m === 0 ? y + 1 : y;
    drawCalendar(y, m);
  });

  document.querySelector(".plus-year").addEventListener("click", () => {
    y++;
    drawCalendar(y, m);
  });

  document.querySelector(".minus-year").addEventListener("click", () => {
    y--;
    drawCalendar(y, m);
  });
});

// === 날짜 유틸 ===
function parseDate(str) {
  const [yyyy, mm, dd] = str.split("-");
  return new Date(yyyy, mm - 1, dd);
}

function getDateRangeArray(start, end) {
  const dateArr = [];
  const current = new Date(start);

  while (current <= end) {
    const yyyy = current.getFullYear();
    const mm = String(current.getMonth() + 1).padStart(2, "0");
    const dd = String(current.getDate()).padStart(2, "0");
    dateArr.push(`${yyyy}-${mm}-${dd}`);
    current.setDate(current.getDate() + 1);
  }

  return dateArr;
}

// === 달력 렌더링 ===
function drawCalendar(year, month) {
  let now = new Date();
  let nowD = now.getDate();

  const monthNames = [1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12];
  document.getElementById("currentMonth").textContent = monthNames[month];
  document.getElementById("currentYear").textContent = year;

  const firstDay = new Date(year, month, 1).getDay();
  const last = [31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31];
  if ((year % 4 === 0 && year % 100 !== 0) || year % 400 === 0) last[1] = 29;
  const lastDate = last[month];

  let cal = document.getElementById("cal");
  let draw = "<table class='calendar'>";
  let dNum = 1;

  for (let i = 0; i < 6; i++) {
    draw += "<tr>";
    for (let k = 0; k < 7; k++) {
      if ((i === 0 && k < firstDay) || dNum > lastDate) {
        draw += "<td class='day-box'> &nbsp; </td>";
      } else {
        const dateStr = `${year}-${String(month + 1).padStart(2, "0")}-${String(dNum).padStart(2, "0")}`;

        if (year === now.getFullYear() && month === now.getMonth() && dNum === nowD) {
          draw += `<td class='day-box today' data-date="${dateStr}">${dNum}</td>`;
        } else {
          draw += `<td class='day-box' data-date="${dateStr}">${dNum}</td>`;
        }

        dNum++;
      }
    }
    draw += "</tr>";
  }
  draw += "</table>";
  cal.innerHTML = draw;

  const tripDates = [];
  (window.tripPlans || []).forEach(plan => {
    const start = parseDate(plan.tripStartDate);
    const end = parseDate(plan.tripFinishDate);
    const dates = getDateRangeArray(start, end);
    tripDates.push(...dates);
  });

  tripDates.forEach(date => {
    const cell = document.querySelector(`td[data-date="${date}"]`);
    if (cell) {
      cell.style.backgroundColor = "#c7f0ff";
    }
  });
}
