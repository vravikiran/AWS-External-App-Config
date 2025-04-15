package com.app.aws.external.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.aws.external.entities.Itinerary;
import com.app.aws.external.service.ItineraryService;

@RestController
@RequestMapping("/itinerary")
public class ItineraryController {
	@Autowired
	ItineraryService itineraryService;

	@PostMapping
	public ResponseEntity<Itinerary> createitinerary(@RequestBody Itinerary itinerary) {
		Itinerary createdItinerary = itineraryService.createItinerary(itinerary);
		return ResponseEntity.ok(createdItinerary);
	}

	@GetMapping
	public ResponseEntity<List<Itinerary>> getItineries() {
		List<Itinerary> itineraries = itineraryService.getItineries();
		return ResponseEntity.ok(itineraries);
	}
}
