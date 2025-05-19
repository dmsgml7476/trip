
const typeSelect = document.getElementById("typeSelect");
const placeSelect = document.getElementById("placeSelect");

typeSelect.addEventListener('change', () => {
   const selectedType = typeSelect.value;
   
   
   placeSelect.innerHTML='<option value="">장소 선택</option>';
   
   placeInfo.forEach(placeInfo=>{
      if(placeInfo.categoryId===parseInt(selectedType)){
         const option = document.createElement('option');
         option.value = placeInfo.placeId;
         option.textContent=placeInfo.placeName;
         placeSelect.appendChild(option);
         
      }
   });
}); 

function saveSelection() {
  const type = document.getElementById('typeSelect').value;
  const location = document.getElementById('placeSelect').value;

  if (type && location) {
    localStorage.setItem('selectedType', typeSelect);
    localStorage.setItem('selectedLocation', placeSelect);
    alert('저장되었습니다.');
  } else {
    alert('모든 항목을 선택하세요.');
  }
}

function nextPage() {
  // 예: 다음 페이지로 이동
  window.location.href = 'placeDetail.html';
}
window.onload = function () {
    const type = localStorage.getItem('selectedType');
    const location = localStorage.getItem('selectedLocation');

    if (type && location) {
      document.getElementById('result').innerText = `유형: ${type}, 장소: ${location}`;
    } else {
      document.getElementById('result').innerText = '저장된 정보가 없습니다.';
    }
  }
  
 
  


