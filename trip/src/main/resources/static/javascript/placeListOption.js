
const typeSelect = document.getElementById("typeSelect");
const placeSelect = document.getElementById("placeSelect");

typeSelect.addEventListener('change', () => {
	const selectedType = typeSelect.value;
	
	
	placeSelect.innerHTML='<option value="">장소 선택</option>';
	
	placeInfo.forEach(placeInfo=>{
		if(placeInfo.categoryId===parseInt(selectedType)){
			const option = document.createElement('option');
			option.value = placeInfo.placeName;
			option.textContent=placeInfo.placeName;
			placeSelect.appendChild(option);
			
		}
	});
}); 