package com.trip.entity.Member;

import java.time.LocalDateTime;

import com.trip.constant.Member.NotificationType;
import com.trip.entity.Lets.StoryEntity;
import com.trip.entity.Planner.TripPlanEntity;

import jakarta.persistence.*;
import lombok.*;


@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name="web_notification")
public class WebNotificationEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="web_notification_id")
	private Long id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="user_id", nullable=false)
	private UserEntity user;
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "story_id")
    private StoryEntity story;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "trip_plan_id")
	private TripPlanEntity tripPlan;
	
	@Enumerated(EnumType.STRING)
	@Column(nullable=false)
	private NotificationType type;
	
	@Column(nullable=false)
	private String message;
	
	private Long targetId;
	private boolean isRead = false;
	private LocalDateTime createAt = LocalDateTime.now();
	
	
}
