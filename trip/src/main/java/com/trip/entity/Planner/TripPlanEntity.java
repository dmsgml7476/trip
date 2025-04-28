package com.trip.entity.Planner;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;
@Entity
@Getter
@Setter
public class TripPlanEntity {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int planId;
	private String memberId;
	private String planTitle;
	private LocalDate tripStartDate;
	private LocalDate tripFinishDate;
	private LocalDate writeDate;
	private LocalDate planModifiedDate;
}
