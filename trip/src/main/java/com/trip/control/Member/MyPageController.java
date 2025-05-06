package com.trip.control.Member;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.trip.config.auth.CustomUserDetails;
import com.trip.entity.Lets.StoryEntity;
import com.trip.entity.Member.UserDetailEntity;
import com.trip.entity.Member.UserEntity;
import com.trip.entity.Member.UserMainStoryEntity;
import com.trip.repository.Lets.StoryRepository;
import com.trip.repository.Member.UserAlertSettingRepository;
import com.trip.repository.Member.UserDetailRepository;
import com.trip.repository.Member.UserMainStoryRepository;
import com.trip.repository.Member.UserRepository;
import com.trip.repository.Member.WebNotificationRepository;
import com.trip.repository.Planner.TripPlanRepository;
import com.trip.service.Member.RecommendStoryService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class MyPageController {

	private final UserDetailRepository userDetailRepository;
	private final UserRepository userRepository;
	private final UserMainStoryRepository userMainStoryRepository;
	private final StoryRepository storyRepository;
	
	@GetMapping("/mypage")
	public String myPage(@AuthenticationPrincipal CustomUserDetails userDetails, Model model) {
		
		
		if (userDetails != null) {	
			UserEntity user= userDetails.getUser();
			model.addAttribute("user", user);
			Long userId	= user.getId()	;
			
			user = userRepository.findWithHashtagsById(userId)
	                .orElseThrow(() -> new IllegalArgumentException("User not found"));
			
			List<String> userHashtags = user.getUserHashtags().stream()
	                .map(uh -> uh.getHashtags().getHashtag())
	                .limit(3)
	                .toList();

	        model.addAttribute("userHashtags", userHashtags);
			
			UserDetailEntity userDetail=user.getUserDetail();
			model.addAttribute("userDetail", userDetail);
			model.addAttribute("nickname", user.getNickname());
			
			// 대표스토리
			
			List<UserMainStoryEntity> mainStories = userMainStoryRepository.findByUserId(userId);
			
			Map<String, Long> mainStoryIds = new HashMap<>();
			Map<String, String> mainStoryImages= new HashMap<>();
			
			for(UserMainStoryEntity mainStory : mainStories) {
				StoryEntity story = storyRepository.findById(mainStory.getStoryId()).orElse(null);
			    if (story != null) {
			        String key = mainStory.getMainStoryNum().name();
			        mainStoryImages.put(key, story.getImageUrl());
			        mainStoryIds.put(key, story.getStoryId());
			    }
			}
			

			model.addAttribute("mainStoryIds", mainStoryIds);		
			model.addAttribute("mainStoryImages", mainStoryImages);
			
			return "member/myPage";
		}
		
		return "index";
	}
	
	
	@PostMapping("/mypage/profile/upload")
	public String updateProfileImg(@AuthenticationPrincipal CustomUserDetails userDetails, @RequestParam("profileImg") MultipartFile profileImg) throws IOException {
		UserEntity user = userDetails.getUser();
		
		String fileName = UUID.randomUUID().toString() + "_" + profileImg.getOriginalFilename();
		Path savePath = Paths.get("C:/trip-uploads/profile", fileName);
		Files.copy(profileImg.getInputStream(), savePath);
		
		UserDetailEntity userDetail=user.getUserDetail();
		userDetail.setProfileImg(fileName);
		
		userDetailRepository.save(userDetail);
		
		System.out.println("▶ 파일 이름: " + fileName);
		System.out.println("▶ 저장 경로: " + savePath.toAbsolutePath());
		System.out.println("▶ 이미지 저장 완료");
		
		return "redirect:/mypage";
	}
}
