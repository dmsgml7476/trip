package com.trip.repository.Lets;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MyStoryRepository extends JpaRepository<MyStoryRepository, Long>{
	List<MyStoryRepository> findByUserId(Long userId);
}
