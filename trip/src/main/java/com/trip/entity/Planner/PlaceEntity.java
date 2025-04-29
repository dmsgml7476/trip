package com.trip.entity.Planner;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;
@Entity
@Getter
@Setter
public class PlaceEntity {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	private int placeId;
	private String placeName;
	private Long regionId;
	private String placeAddress;
	private String placeInfo;
	private float placeLatitude;
	private float placeLongitude;
	private String placeTel;
	private LocalDate createDate;
	private LocalDate placeModifiedDate;
}
