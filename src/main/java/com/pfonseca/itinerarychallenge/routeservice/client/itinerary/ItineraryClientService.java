package com.pfonseca.itinerarychallenge.routeservice.client.itinerary;

import java.time.LocalTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pfonseca.itinerarychallenge.routeservice.client.itinerary.domain.Itinerary;
import com.pfonseca.itinerarychallenge.routeservice.client.itinerary.domain.ItineraryPage;

@Service
public class ItineraryClientService {

	@Autowired
	private ItineraryClient itineraryClient;
	
	
	public List<Itinerary> getItinerariesFromOrigin(Long origin, LocalTime departureAfter) {
		ItineraryPage listItineraries = itineraryClient.listItineraries(origin, departureAfter.toString());
		List<Itinerary> itineraries = listItineraries.getContent();
		return itineraries;
	}
	
}
