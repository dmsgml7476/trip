package com.trip.service.Member;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.trip.dto.Member.RecommendStoryDto;
import com.trip.entity.Lets.StoryEntity;
import com.trip.repository.Lets.StoryRepository;
import com.trip.repository.Member.UserHashtagRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RecommendStoryService {

	private final StoryRepository storyRepository;
	private final UserHashtagRepository userHashtagRepository;
	
	 public List<RecommendStoryDto> getRecommendedStoriesForGuest() {
	        List<StoryEntity> stories = storyRepository.findTop8PublicStoriesByLikes(PageRequest.of(0, 8));
	        return stories.stream()
	                .map(RecommendStoryDto::from)
	                .collect(Collectors.toList());
	    }


	    public List<RecommendStoryDto> getRecommendedStoriesForUser(Long userId) {
	        List<Long> hashtagIds = userHashtagRepository.findHashtagIdsByUserId(userId);
	        if (hashtagIds.isEmpty()) return getRecommendedStoriesForGuest();

	        List<Long> similarUserIds = userHashtagRepository.findUserIdsWithSameHashtags(hashtagIds, userId);
	        if (similarUserIds.isEmpty()) return getRecommendedStoriesForGuest();

	        List<StoryEntity> stories = storyRepository.findTop8PublicStoriesByUserIdsOrderByLikes(similarUserIds, PageRequest.of(0, 8));
	        return stories.stream()
	                .map(RecommendStoryDto::from)
	                .collect(Collectors.toList());
	    }
	
}
