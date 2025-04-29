package com.trip.entity.Planner;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class PlanDetailEntity {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long planDetailId;
	private int planDateId;
	private int placeId;
	private LocalDateTime visitTime;
	private LocalDateTime endTime;
	private int theDayOrder;
}
