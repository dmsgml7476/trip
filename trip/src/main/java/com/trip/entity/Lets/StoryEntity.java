package com.trip.entity.Lets;

import java.time.LocalDateTime;

import com.trip.enumType.OpenArea;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class StoryEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
		
		private Long userId;
		private Long storyId;
		
		@Enumerated(EnumType.STRING)
		@Column(nullable = false)
		private Long storyCate;
		
		@Column(length = 255, nullable = false)
		private String storyTitle;
		
		@Column(length = 50)
		private String storyContent;
		
		@Column(nullable = false)
		private Long locationInfo;
		
		 @Column(length = 500)
		private String imageUrl;
		
		@Enumerated(EnumType.STRING)
	    @Column(nullable = false)
		private OpenArea openArea;
		
		  @Column(nullable = false)
		private LocalDateTime createdAt;
		
		public StoryEntity() {
			this.createdAt=LocalDateTime.now();
		}
}
