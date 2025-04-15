package com.app.aws.external.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.aws.external.entities.Itinerary;
import com.app.aws.external.repository.ItineraryRepository;

@Service
public class ItineraryService {
	@Autowired
	ItineraryRepository itineraryRepository;
	
	public Itinerary createItinerary(Itinerary itinerary) {
		return itineraryRepository.save(itinerary);
	}
	
	public List<Itinerary> getItineries() {
		return itineraryRepository.findAll();
	}
}
