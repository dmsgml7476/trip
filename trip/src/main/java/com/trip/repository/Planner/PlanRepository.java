package com.trip.repository.Planner;

import java.time.LocalDate;

import org.springframework.stereotype.Repository;

import com.trip.entity.Planner.PlaceEntity;
import com.trip.entity.Planner.PlanDateEntity;
import com.trip.entity.Planner.PlanDetailEntity;

@Repository
public interface PlanRepository {
	public LocalDate findByPlanDateId(PlanDateEntity planDateEntity);
	public boolean modify(PlanDetailEntity planDetailEntity);
	public void deleteByPlaceId(PlaceEntity placeEntity);
	public String addByPlaceId(PlaceEntity placeEntity);
}
