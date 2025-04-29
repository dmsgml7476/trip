package com.trip.entity.Member;

import lombok.*;

import java.time.LocalDateTime;

import jakarta.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name="visiter_log")
public class VisiterLogEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="visiter_log_id")
	private Long id;
	
	private Long userId;
	
	private String sessionId;
	

	@Column(name="visited_at", nullable=false, updatable=false)
	private LocalDateTime visitedAt=LocalDateTime.now();
	
}
