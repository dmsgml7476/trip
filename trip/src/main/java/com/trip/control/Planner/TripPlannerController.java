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
	
	@GetMapping("/tripPlan2")
	public String tripPlan2(@AuthenticationPrincipal CustomUserDetails userDetails, Model model) {
		return "planner/tripPlan2";
	}
	
	@GetMapping("/tripPlan3")
	public String tripPlan3(@AuthenticationPrincipal CustomUserDetails userDetails, Model model) {
		return "planner/tripPlan3";
	}
	
	@GetMapping("/tripPlan4")
	public String tripPlan4(@AuthenticationPrincipal CustomUserDetails userDetails, Model model) {
		return "planner/tripPlan4";
	}
	
	@GetMapping("/tripPlan5")
	public String tripPlan5(@AuthenticationPrincipal CustomUserDetails userDetails, Model model) {
		return "planner/tripPlan5";
	}
	@GetMapping("/tripPlan6")
	public String tripPlan6(@AuthenticationPrincipal CustomUserDetails userDetails, Model model) {
		return "planner/tripPlan6";
	}
	@GetMapping("/tripPlan7")
	public String tripPlan7(@AuthenticationPrincipal CustomUserDetails userDetails, Model model) {
		return "planner/tripPlan7";
	}
	@GetMapping("/firstPlan")
	public String firstPlan(@AuthenticationPrincipal CustomUserDetails userDetails, Model model) {
		return "planner/firstPlan";
	}
}
