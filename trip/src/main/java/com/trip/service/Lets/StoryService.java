package com.trip.service.Lets;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.springframework.stereotype.Service;

import com.trip.dto.Lets.StoryCardDto;
import com.trip.dto.Lets.StoryImgUrl;
import com.trip.entity.Lets.StoryEntity;
import com.trip.entity.Member.UserHashtagEntity;
import com.trip.repository.Lets.StoryRepository;
import com.trip.repository.Member.UserHashtagRepository;
import com.trip.repository.Member.UserMainStoryRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class StoryService {

    private final UserMainStoryRepository userMainStoryRepository;
			private final StoryRepository storyRepository;
			
			private final UserHashtagRepository userHashtagRepository;

    
		
			// 스토리 목록 보여주기 위한 메서드 (최근 순, 공감 다수 순)
			public List<StoryCardDto> getStoryList(){
				List<StoryEntity> storyList = storyRepository.findTop2ByOrderByWriteAtDesc(); //최근순
				List<StoryEntity>storyLike = storyRepository.findTop2ByOrderByLikes();//공감순
				storyList.addAll(storyLike);
				
				List<StoryCardDto> storyCardList = new ArrayList<>(); 
				
				for(StoryEntity story : storyList) {
					StoryCardDto storyCardDto = new StoryCardDto();
					List<UserHashtagEntity> userHashtagEntityList = userHashtagRepository.findAllByUserId(story.getUser().getId());
					if(  !userHashtagEntityList.isEmpty() ) { // 해시 태그 있으면 실행
						List<String> tempTag = new ArrayList<>(); // 모든 해시 태그 담아두기 위한 임시공간
						for( UserHashtagEntity userHashtagEntity : userHashtagEntityList) { // 해시 태그 갯수만큼 반복 그래야 모든 해시 태그 다가져올수 있음
							tempTag.add(userHashtagEntity.getHashtags().getHashtag()); // 해시 태그 하나씩 임시공간에 저장
						}
						// 임시공간에 저장된 해시 태그 DTO의 배열에 저장
						storyCardDto.setHashTags( tempTag.toArray(new String[0]));
					}
					
					storyCardDto.setStoryId(story.getStoryId());
					storyCardDto.setStoryTitle(story.getStoryTitle());
					storyCardDto.setStoryContent(story.getStoryContent());
					storyCardDto.setStoryImgUrl(story.getImageUrl());
					storyCardDto.setStoryContent(story.getStoryContent());
					storyCardDto.setLikes(story.getLikes());
					
					System.out.println(  storyCardDto);
					storyCardList.add(storyCardDto);
				}
//				for(StoryEntity story : storyLike) {
//					StoryCardDto storyCardDto = new StoryCardDto();
//					storyCardDto.setStoryId(story.getStoryId());
//					storyCardDto.setStoryTitle(story.getStoryTitle());
//					storyCardDto.setStoryContent(story.getStoryContent());
//					storyCardDto.setStoryImgUrl(story.getImageUrl());
//					storyCardDto.setStoryContent(story.getStoryContent());
//					storyCardDto.setLikes(story.getLikes());
//					storyCardList.add(storyCardDto);
//				}
			
				return storyCardList;
			}
			
			// 커뮤니티 첫페이지에 스토리 이미지 목록 보여주기 
			public List<StoryImgUrl> getStoryImgUrls(){
				List<StoryImgUrl> storyImgUrls = new ArrayList<>();
				
				//스토리 테이블에서 최근 데이터 5개 가져오기
				List<StoryEntity> stories = storyRepository.findTop5ByOrderByWriteAtDesc();
				
				// entity -> dto
				for (StoryEntity story : stories) {
					StoryImgUrl storyImgUrl = new StoryImgUrl();
					storyImgUrl.setStoryId(story.getStoryId());
					storyImgUrl.setUrl(story.getImageUrl());
					storyImgUrls.add(storyImgUrl);
				}
				
				return storyImgUrls;
			}
}
