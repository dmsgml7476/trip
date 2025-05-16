// 날짜 클릭 시 선택되도록 이벤트 추가
setTimeout(() => {
  const cells = document.querySelectorAll(".day-box");
  cells.forEach((cell) => {
    cell.addEventListener("click", () => {
      // 이전 선택 제거
      cells.forEach((c) => c.style.backgroundColor = "");
      // 현재 선택 강조
      cell.style.backgroundColor = "#add8e6"; // 연한 파랑
    });
  });
}, 0);
let currentYear = new Date().getFullYear();
let currentMonth = new Date().getMonth();

function drawCalendar(y, m) {
  var theDate = new Date(y, m, 1);
  var theDay = theDate.getDay();
  var last = [31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31];

  if ((y % 4 === 0 && y % 100 !== 0) || y % 400 === 0) last[1] = 29;
  var lastDate = last[m];

  let cal = document.getElementById("cal");
  var draw = "<table class='calendar'>";
  var dNum = 1;

  for (var i = 1; i <= 6; i++) {
    draw += "<tr>";
    for (var k = 0; k < 7; k++) {
      if ((i === 1 && k < theDay) || dNum > lastDate) {
        draw += "<td class='day-box'>&nbsp;</td>";
      } else {
        draw += "<td class='day-box'>" + dNum + "</td>";
        dNum++;
      }
    }
    draw += "</tr>";
  }
  draw += "</table>";
  cal.innerHTML = draw;

  // 날짜 선택 기능도 다시 적용
  const cells = document.querySelectorAll(".day-box");
  cells.forEach((cell) => {
    cell.addEventListener("click", () => {
      cells.forEach((c) => (c.style.backgroundColor = ""));
      cell.style.backgroundColor = "#add8e6";
    });
  });

  document.querySelector(".current-month").innerText = (m + 1) + "월";
}

window.onload = function () {
  drawCalendar(currentYear, currentMonth);

  document.querySelector(".minus-month").onclick = () => {
    currentMonth--;
    if (currentMonth < 0) {
      currentMonth = 11;
      currentYear--;
    }
    drawCalendar(currentYear, currentMonth);
  };

  document.querySelector(".flus-month").onclick = () => {
    currentMonth++;
    if (currentMonth > 11) {
      currentMonth = 0;
      currentYear++;
    }
    drawCalendar(currentYear, currentMonth);
  };
};




