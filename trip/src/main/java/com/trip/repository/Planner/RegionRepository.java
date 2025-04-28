package com.trip.repository.Planner;

import org.springframework.stereotype.Repository;

import com.trip.entity.Planner.RegionEntity;

@Repository
public interface RegionRepository {
  
	public String findByUpperRegion(RegionEntity regionEntity);
	public String findByRegionName(RegionEntity regionEntity);
	public String regionSave(RegionEntity regionEntity);
} 
