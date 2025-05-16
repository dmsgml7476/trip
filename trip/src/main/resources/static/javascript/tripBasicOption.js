

// 지역 선택 


const upperRegionSelect = document.getElementById('mainRegion');
const subRegionSelect = document.getElementById('subRegion');

const upperRegions = [...new Set(regionList.map(r => r.upperRegionName))];

upperRegionSelect.innerHTML = '<option value="">상위 지역</option>';
upperRegions.forEach(upper => {
	const option = document.createElement('option');
	option.value=upper;
	option.textContent=upper;
	upperRegionSelect.appendChild(option);
});

upperRegionSelect.addEventListener('change',function(){
	const selectedUpper = this.value;
	
	console.log("상위지역 선택 잡힘");
	
	subRegionSelect.innerHTML = '<option value="">세부 지역</option>';
	
	regionList.forEach(region => {
		if(region.upperRegionName === selectedUpper){
			const option = document.createElement('option');
			option.value=region.regionId;
			option.textContent = region.regionName;
			subRegionSelect.appendChild(option);
		}
	});
});
