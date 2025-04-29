package com.trip.repository.Planner;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.trip.entity.Planner.TripPlanEntity;

@Repository
public interface TripPlanRepository extends JpaRepository<TripPlanEntity, Long>{

}
