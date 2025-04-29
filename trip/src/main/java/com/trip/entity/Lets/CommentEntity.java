package com.trip.entity.Lets;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
public class CommentEntity {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	
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
