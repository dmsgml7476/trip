package com.trip.repository.Lets;

import org.springframework.data.jpa.repository.JpaRepository;

import com.trip.entity.Lets.MyStoryEntity;

public interface MyStoryRepository extends JpaRepository<MyStoryEntity, Long>{
	
}
