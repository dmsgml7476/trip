package com.trip.entity.Planner;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class RegionEntity {

	private Long region_id;
	private String region_name;
	private Long upper_region;
	private float latitude;
	private float longitude;
}
