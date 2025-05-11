package com.trip.service.admin;

import org.springframework.stereotype.Service;

import com.trip.dto.admin.CoordinateDto;
import com.trip.dto.admin.PlaceSaveDto;
import com.trip.entity.Planner.CategoryEntity;
import com.trip.entity.Planner.PlaceEntity;
import com.trip.entity.Planner.RegionEntity;
import com.trip.repository.Planner.CategoryRepository;
import com.trip.repository.Planner.PlaceRepository;
import com.trip.repository.Planner.RegionRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PlaceMgmtService {

	
	private final PlaceRepository placeRepository;
	private final RegionRepository regionRepository;
	private final CategoryRepository categoryRepository;
	private final KakaoMapService kakaoMapService;
	
    public void savePlace(PlaceSaveDto dto) {

        RegionEntity region = regionRepository.findById(dto.getRegionId())
                .orElseThrow(() -> new IllegalArgumentException("지역 없음"));

        CategoryEntity category = categoryRepository.findById(dto.getCategoryType().longValue())
                .orElseThrow(() -> new IllegalArgumentException("해당 카테고리가 존재하지 않습니다."));

        PlaceEntity place = PlaceEntity.of(dto, region, category);

        System.out.println("👉 입력 주소: " + dto.getPlaceAddress());

        // 위도/경도 관련 로직 제거됨

        placeRepository.save(place);
    }
}
