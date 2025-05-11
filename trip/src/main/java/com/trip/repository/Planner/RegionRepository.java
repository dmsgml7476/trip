package com.trip.repository.Planner;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.trip.entity.Planner.CategoryEntity;
import com.trip.entity.Planner.RegionEntity;

@Repository
public interface RegionRepository extends JpaRepository<RegionEntity, Long>{

	Optional<RegionEntity> findByUpperRegionAndRegionName(String upperRegion, String regionName);

	Optional<RegionEntity> findByRegionName(String regionName);
  
	
} 
