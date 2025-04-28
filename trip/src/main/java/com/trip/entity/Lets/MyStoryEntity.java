package com.trip.entity.Lets;

import java.time.LocalDateTime;

public class MyStoryEntity {
			private Long id;
			private String imageUrl;
			private Long userId;
			private LocalDateTime createdAt;
			
			public MyStoryEntity() {
				this.createdAt=LocalDateTime.now();
			}
}
