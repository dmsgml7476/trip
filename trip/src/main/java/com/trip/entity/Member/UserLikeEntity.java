package com.trip.entity.Member;

import java.time.LocalDateTime;

import jakarta.persistence.*;
import lombok.*;


@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name="user_like")
public class UserLikeEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="user_like_id")
	private Long id;
	
	@ManyToOne (fetch=FetchType.LAZY)
	@JoinColumn(name="user_id", nullable=false)
	private UserEntity user;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="story_id", nullable=false)
	private StoryEntity story;
	
	private LocalDateTime likeTime = LocalDateTime.now();
	
	
	
}
