package com.trip.entity.Lets;

import java.time.LocalDateTime;

public class CommentEntity {
			private Long CommentId;
			private Long storyId;
			private Long userId;
			private String CommentContent;
			private LocalDateTime createdAt;
			
			public CommentEntity() {
				this.createdAt=LocalDateTime.now();
			}
}
