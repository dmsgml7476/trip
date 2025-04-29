package com.trip.entity.Lets;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
@Entity
@Getter
@Setter
@AllArgsConstructor
public class MyStoryEntity {
			private Long userId;
			private Long storyId;
			
			@OneToOne
			@JoinColumn(name = "storyId", referencedColumnName = "storyId")
			private StoryEntity story;
			private String imageUrl;
			private LocalDateTime createdAt;
			
			public MyStoryEntity() {
				this.createdAt=LocalDateTime.now();
				
			}
}