package com.trip.repository.Planner;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.trip.entity.Planner.PlaceEntity;
import com.trip.entity.Planner.RegionEntity;

@Repository
public interface PlaceRepository extends JpaRepository<PlaceEntity, Long>{



}
