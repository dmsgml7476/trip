package com.trip.repository.Planner;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.trip.entity.Planner.PlaceEntity;

@Repository
public interface PlaceRepository extends JpaRepository<PlaceEntity, Long>{



}
