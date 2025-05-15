package com.trip.repository.Planner;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

<<<<<<< HEAD
import com.trip.entity.Planner.PlaceEntity;
=======
import com.trip.entity.Planner.CategoryEntity;
>>>>>>> branch 'main' of https://github.com/dmsgml7476/trip.git
import com.trip.entity.Planner.RegionEntity;

@Repository
public interface RegionRepository extends JpaRepository<RegionEntity, Long>{

<<<<<<< HEAD
	Optional<RegionEntity> findByRegionName(String regionName);


=======
	Optional<RegionEntity> findByUpperRegionAndRegionName(String upperRegion, String regionName);

	Optional<RegionEntity> findByRegionName(String regionName);
>>>>>>> branch 'main' of https://github.com/dmsgml7476/trip.git
  
	
} 
