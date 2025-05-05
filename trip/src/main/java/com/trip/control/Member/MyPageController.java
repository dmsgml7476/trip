package com.trip.control.Member;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.trip.config.auth.CustomUserDetails;
import com.trip.entity.Member.UserDetailEntity;
import com.trip.entity.Member.UserEntity;
import com.trip.repository.Member.UserAlertSettingRepository;
import com.trip.repository.Member.UserRepository;
import com.trip.repository.Member.WebNotificationRepository;
import com.trip.repository.Planner.TripPlanRepository;
import com.trip.service.Member.RecommendStoryService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class MyPageController {

	
	@GetMapping("/mypage")
	public String myPage(@AuthenticationPrincipal CustomUserDetails userDetails, Model model) {
		UserEntity user= userDetails.getUser();
		model.addAttribute("user", user);
		
		UserDetailEntity userDetail=user.getUserDetail();
		model.addAttribute("userDetail", userDetail);
		
		return "member/myPage";
	}
}
