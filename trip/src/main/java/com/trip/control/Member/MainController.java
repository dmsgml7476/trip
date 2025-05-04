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
import com.trip.entity.Member.UserHashtagEntity;
import com.trip.repository.Member.UserRepository;
import com.trip.service.Member.RecommendStoryService;

import lombok.RequiredArgsConstructor;



@Controller
@RequiredArgsConstructor
public class MainController {

	private final UserRepository userRepository;
	
	 private final RecommendStoryService recommendStoryService;

	 @GetMapping("/")
	 public String showMainPage(Model model, @AuthenticationPrincipal CustomUserDetails userDetails) {
	     List<RecommendStoryDto> storyList = recommendStoryService.getRecommendedStoriesForGuest();
	     model.addAttribute("storyList", storyList);

	     if (userDetails != null) {
	         Long userId = userDetails.getUser().getId();

	         // 여기서 Fetch Join 사용!
	         UserEntity user = userRepository.findWithHashtagsById(userId)
	                 .orElseThrow(() -> new IllegalArgumentException("User not found"));

	         UserDetailEntity detail = user.getUserDetail();
	         model.addAttribute("userDetail", detail);
	         model.addAttribute("nickname", user.getNickname());

	         List<String> userHashtags = user.getUserHashtags().stream()
	                 .map(uh -> uh.getHashtags().getHashtag())
	                 .limit(3)
	                 .toList();

	         model.addAttribute("userHashtags", userHashtags);
	     }

	     return "index";
	 }
	    

	    
}
