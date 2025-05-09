package com.trip.service.Planner;

import org.springframework.stereotype.Service;

import com.trip.repository.Lets.StoryRepository;
import com.trip.repository.Member.UserHashtagRepository;
import com.trip.repository.Planner.PlaceRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PlaceService {

	private final PlaceRepository placeRepository;
	
	
}
