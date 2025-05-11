document.addEventListener('DOMContentLoaded', function () {
  console.log("✅ JS 실행 시작됨");

  const upperRegionSelect = document.getElementById('upperRegionSelect');
  const subRegionSelect = document.getElementById('subRegionSelect');
  const filterForm = document.getElementById('filterForm');

  // 서버에서 전달된 선택값
  const selectedUpperRegion = upperRegionSelect.getAttribute("data-selected");
  const selectedRegionId = subRegionSelect.getAttribute("data-selected");

  // 1. 상위 지역 옵션 구성 및 선택 유지
  const upperRegions = [...new Set(regionList.map(r => r.upperRegion))];

  upperRegionSelect.innerHTML = '<option value="">상위 지역</option>';
  upperRegions.forEach(upper => {
    const option = document.createElement('option');
    option.value = upper;
    option.textContent = upper;

    if (upper === selectedUpperRegion) {
      option.selected = true;
    }

    upperRegionSelect.appendChild(option);
  });

  // 2. 하위 지역 select 초기화 (상위 지역이 선택된 경우)
  if (selectedUpperRegion) {
    subRegionSelect.innerHTML = '<option value="">세부 지역</option>';

    regionList.forEach(region => {
      if (region.upperRegion === selectedUpperRegion) {
        const option = document.createElement('option');
        option.value = region.regionId;
        option.textContent = region.regionName;

        // 선택 유지
        if (region.regionId == selectedRegionId) {
          option.selected = true;
        }

        subRegionSelect.appendChild(option);
      }
    });
  }

  // 3. 상위 지역 변경 시: 하위 지역 필터링 및 제출
  upperRegionSelect.addEventListener('change', function () {
    const selectedUpper = this.value;

    subRegionSelect.innerHTML = '<option value="">세부 지역</option>';
    regionList.forEach(region => {
      if (region.upperRegion === selectedUpper) {
        const option = document.createElement('option');
        option.value = region.regionId;
        option.textContent = region.regionName;
        subRegionSelect.appendChild(option);
      }
    });

    console.log("📌 상위 지역 선택됨:", selectedUpper);
    filterForm.submit();
  });

  // 4. 하위 지역 선택 시 제출
  subRegionSelect.addEventListener('change', function () {
    console.log("📌 하위 지역 선택됨:", subRegionSelect.value);
    filterForm.submit();
  });
});


// 5. 모달 열기
const modal = document.getElementById('showDetailModal');
const modalPlaceName = document.getElementById('modalPlaceName');
const modalPlaceTel = document.getElementById('modalPlaceTel');
const modalUpperRegion = document.getElementById('modalUpperRegion');
const modalRegion = document.getElementById('modalRegion');
const modalCategory = document.getElementById('modalCategory');
const modalAddress = document.getElementById('modalAddress');
const modalInfo = document.getElementById('modalInfo');
const modalCloseBtn = document.querySelector('.modal .close');

document.querySelectorAll('.place-row').forEach(row => {
  row.addEventListener('click', function () {
    modalPlaceName.textContent = this.getAttribute('data-place-name');
    modalPlaceTel.textContent = this.getAttribute('data-place-tel');
    modalUpperRegion.textContent = this.getAttribute('data-upper-region');
    modalRegion.textContent = this.getAttribute('data-region-name');
    modalCategory.textContent = this.getAttribute('data-category-type');
    modalAddress.textContent = this.getAttribute('data-address');
    modalInfo.textContent = this.getAttribute('data-info');

    modal.style.display = 'block';
  });
});

// 모달 닫기
modalCloseBtn.addEventListener('click', function () {
  modal.style.display = 'none';
});
