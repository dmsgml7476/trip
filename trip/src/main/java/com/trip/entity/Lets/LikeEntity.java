package com.trip.entity.Lets;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LikeEntity {
	
	       @Id
          @GeneratedValue(strategy = GenerationType.IDENTITY)
			private Long id;
			private Long storyId;
			private Long likedByUserId; 
			private LocalDateTime likedAt;
}
