package com.trip.entity.Planner;

import java.time.LocalTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class PlanTrafficEntity {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long planTrafficId;
	private int planDateId;
	private int departPlaceId;
	private int arrivalPlaceId;
	private LocalTime trafficDepartTime;
	private LocalTime trafficArrivalTime;
}
