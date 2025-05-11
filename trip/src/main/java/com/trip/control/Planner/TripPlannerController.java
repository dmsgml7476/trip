package com.trip.control.Planner;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.trip.config.auth.CustomUserDetails;
import com.trip.entity.Planner.RegionEntity;

@Controller
public class TripPlannerController {

	@GetMapping("/tripMain")
	public String tripMain(@AuthenticationPrincipal CustomUserDetails userDetails, Model model) {
		
		return "planner/tripplanner";
	}
	
	
	//여행 지역, 여행 인원, 교통수단 선택
	@GetMapping("/tripBasicOption")
	public String tripBasicOption(Model model) {
		
		RegionEntity regionEntity= new RegionEntity();
		regionEntity.setRegionName("서울");
		regionEntity.setUpperRegion("경기도");
		
		model.addAttribute("cityname",regionEntity);
		
		
		return "planner/tripBasicOption";
	}
	
	
	}

