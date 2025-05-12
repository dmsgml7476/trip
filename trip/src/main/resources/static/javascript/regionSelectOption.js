const upperRegionSelect = document.getElementById("upperRegionSelect");
const regionSelect = document.getElementById("regionSelect");

upperRegionSelect.addEventListener("change", () => {
  const selectedUpper = upperRegionSelect.value;

  regionSelect.innerHTML = '<option value="">하위</option>';

  regionInfoList.forEach(region => {
    if (region.upperRegionName === selectedUpper) {
      const option = document.createElement("option");
      option.value = region.regionName;
      option.textContent = region.regionName;
      regionSelect.appendChild(option);
    }
  });
});