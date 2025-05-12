package com.trip.dto.Planner;

import com.trip.entity.Planner.CategoryEntity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class PlaceSelectDto {
  private String placeName;
  private Long categoryId;
  
  public PlaceSelectDto(String placeName, Long categoryId) {
	  this.placeName = placeName;
	  this.categoryId = categoryId;
  }
}
