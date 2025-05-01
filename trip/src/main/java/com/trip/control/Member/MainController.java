package com.trip.control.Member;

import java.util.List;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.trip.dto.Member.RecommendStoryDto;
import com.trip.service.Member.RecommendStoryService;

import lombok.RequiredArgsConstructor;



@Controller
@RequiredArgsConstructor
public class MainController {


	
	 private final RecommendStoryService recommendStoryService;

	    @GetMapping("/")
	    public String showMainPage(Model model) {
	        List<RecommendStoryDto> storyList = recommendStoryService.getRecommendedStoriesForGuest();
	        model.addAttribute("storyList", storyList);
	        
	        System.out.println("추천 스토리 개수: " + storyList.size());
	        for (RecommendStoryDto dto : storyList) {
	            System.out.println(" - " + dto.getStoryTitle() + " / " + dto.getNickname());
	        }
	        return "index"; // 여기에 타임리프 반복문으로 그려주면 됨
	    }
	    

	    
}
