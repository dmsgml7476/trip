package com.trip.entity.Lets;

import java.time.LocalDateTime;

import com.trip.entity.Member.UserEntity;
import com.trip.enumType.LocationInfo;
import com.trip.enumType.OpenArea;
import com.trip.enumType.StoryCategory;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter

public class StoryEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
		@Column(name = "story_id")
		private Long id;
		
		@Enumerated(EnumType.STRING)
		@Column(nullable = false)
		private StoryCategory storyCate;
		
		@Column(length = 255, nullable = false)
		private String storyTitle;
		
		@Column(length = 50)
		private String storyContent;
		
		@Column(nullable = false)
		private LocationInfo locationInfo;
		
		 @Column(length = 500)
		private String imageUrl;
		
		@Enumerated(EnumType.STRING)
	    @Column(nullable = false)
		private OpenArea openArea;
		
		  @Column(nullable = false)
		private LocalDateTime createdAt;
		
		@ManyToOne
		@Column()
		private UserEntity userEntity;
		  
		public StoryEntity() {
			this.createdAt=LocalDateTime.now();
		}
}
