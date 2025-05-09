package com.trip.service.Member;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.trip.dto.Member.LikedStoryDto;
import com.trip.entity.Lets.StoryEntity;
import com.trip.repository.Lets.StoryRepository;
import com.trip.repository.Member.UserLikeRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MyPageService {

	private final UserLikeRepository userLikeRepository;
    private final StoryRepository storyRepository;

    public List<LikedStoryDto> getLikedStories(Long userId) {
        List<Long> likedStoryIds = userLikeRepository.findLikedStoryIdsByUserId(userId);
        if (likedStoryIds.isEmpty()) return List.of();

        List<StoryEntity> likedStories = storyRepository.findAllByIdIn(likedStoryIds);
        
        System.out.println(">> 좋아요 스토리 ID 목록: " + likedStoryIds);
        System.out.println(">> 가져온 스토리 개수: " + likedStories.size());
        return likedStories.stream()
                .map(LikedStoryDto::from)
                .collect(Collectors.toList());
    }
	
}
