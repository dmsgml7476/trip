package com.trip.control.admin;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.trip.dto.admin.PlaceSaveDto;
import com.trip.dto.admin.RegionDto;
import com.trip.entity.Planner.PlaceEntity;
import com.trip.entity.Planner.RegionEntity;
import com.trip.repository.Planner.PlaceRepository;
import com.trip.repository.Planner.RegionRepository;
import com.trip.service.admin.PlaceMgmtService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin")
public class PlaceMgmtController {
	
	public final RegionRepository regionRepository;
	public final PlaceMgmtService placeMgmtService;

	
	@GetMapping("/placeSave")
	public String placeSavePage(Model model) {
		List<RegionDto> regionList=regionRepository.findAll()
				.stream()
				.map(RegionDto::from)
				.collect(Collectors.toList());
		
		model.addAttribute("regionList", regionList);
		return "admin/page/placeSave";
	}
	
	@PostMapping("/place/save")
	public String savePlace(@ModelAttribute PlaceSaveDto dto) {
		placeMgmtService.savePlace(dto);
		
		return "redirect:/admin/placeSave";
	}
	
	
}
