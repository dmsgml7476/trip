package com.trip.entity.Planner;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class PlanDateEntity {
	
	private int plan_date_id;
	private int plan_id;
	private int plan_order;
	private LocalDate plan_date;
	private String memo;

}
