package com.trip.repository.Planner;

import org.springframework.stereotype.Repository;

import com.trip.entity.Planner.VehicleEntity;

@Repository
public interface VehicleRepository {

	  public String findByVehicleType(VehicleEntity vehicleEntity);
	  public String VehicleSave(VehicleEntity vehicleEntity);
}
