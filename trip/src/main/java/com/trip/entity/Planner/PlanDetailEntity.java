package com.trip.entity.Planner;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class PlanDetailEntity {

	private int plan_detail_id;
	private int plan_date_id;
	private int place_id;
	private LocalDateTime visit_time;
	private LocalDateTime end_time;
	private int the_day_order;
}
