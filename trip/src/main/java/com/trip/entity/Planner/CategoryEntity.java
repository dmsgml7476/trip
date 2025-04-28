package com.trip.entity.Planner;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class CategoryEntity {
	private Long category_id;
	private String category_type;

}
