package com.trip.entity.Planner;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class RegionEntity {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@OneToMany
	@JoinColumn(name="region_id")
	private Long regionId;
	private String regionName;
	private Long upperRegion;
	private float latitude;
	private float longitude;
}
