package com.trip.repository.Lets;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import com.trip.entity.Lets.MyStoryEntity;
@Repository

public interface MyStoryRepository extends JpaRepository<MyStoryEntity, Long>{
	
}
