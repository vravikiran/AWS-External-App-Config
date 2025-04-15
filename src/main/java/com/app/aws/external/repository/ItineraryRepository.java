package com.app.aws.external.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.aws.external.entities.Itinerary;

@Repository
public interface ItineraryRepository extends JpaRepository<Itinerary, Long> {
}
