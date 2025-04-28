package com.trip.entity.Planner;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;
@Entity
@Getter
@Setter
public class PlaceEntity {

	private int place_id;
	private String place_name;
	private Long category_id;
	private Long region_id;
	private String place_address;
	private String place_info;
	private float latitude;
	private float longitude;
	private String place_tel;
	private LocalDate create_date;
	private LocalDate place_modified_date;
}
