package com.trip.entity.Lets;

import java.time.LocalDateTime;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

public class StoryEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
		
		private Long userId;
		private Long storyId;
		private Long storyCate;
		private String storyTitle;
		private String storyContent;
		private Long locationInfo;
		private String imageUrl;
		private Long openArea;
		private LocalDateTime createdAt;
		
		public StoryEntity() {
			this.createdAt=LocalDateTime.now();
		}
}
