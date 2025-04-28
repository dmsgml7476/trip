package com.trip.entity.Planner;

import java.time.LocalTime;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class PlanTrafficEntity {
	
	private int plan_traffic_id;
	private int plan_date_id;
	private int vihicle_id;
	private int depart_place_id;
	private int arrival_place_id;
	private LocalTime traffic_depart_time;
	private LocalTime traffic_arrival_time;
}
