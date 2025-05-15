package com.trip.control.Planner;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.trip.config.auth.CustomUserDetails;
import com.trip.dto.Planner.PlaceSelectDto;
import com.trip.dto.Planner.RegionSelectDto;
import com.trip.entity.Planner.PlaceEntity;
import com.trip.entity.Planner.RegionEntity;
import com.trip.repository.Planner.PlaceRepository;
import com.trip.repository.Planner.RegionRepository;
import com.trip.service.Planner.PlanService;

@Controller
public class TripPlannerController {
	
	@Autowired
	private PlaceRepository placeRepository;
	
	@Autowired
	private PlanService planService;
	
	@Autowired
	private RegionRepository regionRepository;
	
	@GetMapping("/tripMain")
	public String tripMain(@AuthenticationPrincipal CustomUserDetails userDetails, Model model) {
		
		return "planner/tripplanner";
	}
	
	
	//여행 지역, 여행 인원, 교통수단 선택
	@GetMapping("/tripBasicOption")//사용자가 요청한 주소
	public String tripBasicOption(Model model) {
		List<RegionSelectDto> regionList = regionRepository.findAll()
				.stream()
				.map(RegionSelectDto::from)
				.collect(Collectors.toList());
		
		model.addAttribute("regionList",regionList);
		model.addAttribute("vehicleType",planService.allVehicleName());
		
		
		return "planner/tripBasicOption";
		
	}
	@GetMapping("/tripDateOption")
	public String tripDateOption(Model model) {
		return "planner/tripDateOption";
	}
	
	@GetMapping("/placeListOption")
	public String placeListOption(Model model) {
		
		List<PlaceSelectDto> dtoList = placeRepository.findAll().stream()
		.map(p -> new PlaceSelectDto(
					p.getPlaceId(),
					p.getPlaceName(),
					p.getCategory().getCategoryId()
				))
		.collect(Collectors.toList());
		
		
		model.addAttribute("placeType",dtoList);

		return "planner/placeListOption";
		
	
	}
	
		
	
	
	
	
	@GetMapping("/placeResult")
	public String placeResult(@RequestParam("placeId") Long placeId, Model model) {
	
		
		return "planner/placeResult";
	}
	@GetMapping("/finalPlan")
	public String finalPlan(Model model) {
		
		return "planner/finalPlan";
	}
	@GetMapping("/placeDetail")
	public String placeDetail(@RequestParam("placeId") Long placeId, Model model) {
		PlaceEntity place = placeRepository.findById(placeId)
				.orElseThrow(() -> new IllegalAccessError("해당 장소를 찾을 수 없습니다"));
		model.addAttribute("place", place);
		
		model.addAttribute("placeName",place.getPlaceName());
		model.addAttribute("placeAddress",place.getPlaceAddress());
		model.addAttribute("placeInfo",place.getPlaceInfo());
		
		return "planner/placeDetail";
	}
	
	}

