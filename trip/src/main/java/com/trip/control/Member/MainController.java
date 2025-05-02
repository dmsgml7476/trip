package com.trip.control.Member;

import java.util.List;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.trip.config.auth.CustomUserDetails;
import com.trip.dto.Member.RecommendStoryDto;
import com.trip.entity.Member.UserDetailEntity;
import com.trip.entity.Member.UserEntity;
import com.trip.service.Member.RecommendStoryService;

import lombok.RequiredArgsConstructor;



@Controller
@RequiredArgsConstructor
public class MainController {


	
	 private final RecommendStoryService recommendStoryService;

	    @GetMapping("/")
	    public String showMainPage(Model model, @AuthenticationPrincipal CustomUserDetails userDetails) {
	        List<RecommendStoryDto> storyList = recommendStoryService.getRecommendedStoriesForGuest();
	        model.addAttribute("storyList", storyList);
	        if (userDetails != null) {
	            System.out.println("✅ 로그인 상태: " + userDetails.getUser().getLoginId());
	        } else {
	            System.out.println("❌ 비로그인 상태");
	        }
	        
	       
//	        System.out.println("추천 스토리 개수: " + storyList.size());
	        
	        for (RecommendStoryDto dto : storyList) {
	        	System.out.println(" - " + dto.getStoryTitle() + " / " + dto.getNickname());
	        }
	        
	        if(userDetails != null) {
	        	UserEntity user = userDetails.getUser();
	        	UserDetailEntity detail = user.getUserDetail();
	        	System.out.println("✅ 프로필 이미지 파일명: " + detail.getProfileImg());
	        	model.addAttribute("userDetail", detail);
	        }
	        return "index"; // 여기에 타임리프 반복문으로 그려주면 됨
	    }
	    

	    
}
