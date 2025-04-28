package com.trip.repository.Lets;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface StoryRepository extends JpaRepository<StoryRepository,Long> {
	List<StoryRepository> findByCreatedAtAfter(LocalDateTime cutoffTime);
}
