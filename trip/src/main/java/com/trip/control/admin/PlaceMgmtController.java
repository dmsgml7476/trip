package com.trip.control.admin;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.trip.entity.Planner.RegionEntity;
import com.trip.repository.Planner.RegionRepository;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin")
public class PlaceMgmtController {
	
	public final RegionRepository regionRepository;
	
	@GetMapping("/placeSave")
	public String placeSavePage(Model model) {
		List<RegionEntity> regionList = regionRepository.findAll();
		model.addAttribute("regionList", regionList);
		return "admin/page/placeSave";
	}
	
	
	
	
}
