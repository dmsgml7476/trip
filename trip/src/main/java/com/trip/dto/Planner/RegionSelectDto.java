package com.trip.dto.Planner;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegionSelectDto {
	private String regionName;
	private String upperRegionName;
	
	public RegionSelectDto(String regionName,String upperRegionName) {
		this.regionName = regionName;
		this.upperRegionName = upperRegionName;
	}
}
