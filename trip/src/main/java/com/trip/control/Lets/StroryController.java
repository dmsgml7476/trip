package com.trip.control.Lets;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.trip.dto.Lets.StoryCommentDto;
import com.trip.service.Lets.StoryService;

@Controller
public class StroryController  {
			//자동주입
			@Autowired
			private StoryService storyService;
			
		   // 메인 
			@GetMapping("/letsmain")
			public String letsmain(Model model) {
						
					model.addAttribute("storyImgList" , storyService.getStoryImgUrls());
				
					return "story/main";
			}
		
			//스토리 작성
			@GetMapping("/weUpload")
			public String weUpload() {
					return "story/weUpload";
			}
			//스토리 보기
			@GetMapping("/weStory")
			public String weShow(Model model) {
				model.addAttribute("cardList",storyService.getStoryList());
				model.addAttribute("storyCommentDto", new StoryCommentDto());
				
					return "story/weStory";
			}
			//나의 스토리
			@GetMapping("/myStory")
			public String myStory() {
				return "story/myStory";
			}
}
