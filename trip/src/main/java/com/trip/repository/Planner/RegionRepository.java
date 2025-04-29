package com.trip.repository.Planner;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.trip.entity.Planner.RegionEntity;

@Repository
public interface RegionRepository extends JpaRepository<RegionEntity, Long>{
  
	public String findByUpperRegion(RegionEntity regionEntity);
	public String findByRegionName(RegionEntity regionEntity);
	public String regionSave(RegionEntity regionEntity);
} 
