package com.trip.entity.Planner;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;
@Entity
@Getter
@Setter
public class TripPlanEntity {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int planId;
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="user_id",nullable = false)
	private UserEntity user;
	private String planTitle;
	private LocalDate tripStartDate;
	private LocalDate tripFinishDate;
	private LocalDate writeDate;
	private LocalDate planModifiedDate;
}
