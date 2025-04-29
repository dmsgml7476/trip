package com.trip.entity.Planner;

import java.util.ArrayList;
import java.util.List;

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
	private Long regionId;
	private String regionName;
	private String upperRegion;
	private float latitude;
	private float longitude;
	
	@OneToMany(mappedBy="region")
	private List<PlaceEntity> places = new ArrayList<>();
}
