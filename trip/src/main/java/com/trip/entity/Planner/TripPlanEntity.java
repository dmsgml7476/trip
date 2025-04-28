package com.trip.entity.Planner;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;
@Entity
@Getter
@Setter
public class TripPlanEntity {

	private int plan_id;
	private String member_id;
	private String plan_title;
	private LocalDate trip_start_date;
	private LocalDate trip_finish_date;
	private LocalDate write_date;
	private LocalDate plan_modified_date;
}
