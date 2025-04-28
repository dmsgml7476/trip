package com.trip.entity.Planner;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter

public class VehicleEntity {
	private int vehicle_id;
	private String vehicle_type;
	private String vehicle_detail;
}
