package com.trip.entity.Lets;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class CommentEntity {
			private Long CommentId;
			private Long storyId;
			private Long userId;
			
			@Column(length = 255, nullable = false)
			private String CommentContent;
			private LocalDateTime createdAt;
			
			public CommentEntity() {
				this.createdAt=LocalDateTime.now();
			}
}
