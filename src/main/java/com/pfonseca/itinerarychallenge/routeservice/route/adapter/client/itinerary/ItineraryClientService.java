package com.pfonseca.itinerarychallenge.routeservice.route.adapter.client.itinerary;

import java.time.LocalTime;
import java.util.List;

import com.pfonseca.itinerarychallenge.routeservice.route.application.port.out.FindItineraryPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pfonseca.itinerarychallenge.routeservice.route.adapter.client.itinerary.domain.Itinerary;
import com.pfonseca.itinerarychallenge.routeservice.route.adapter.client.itinerary.domain.ItineraryPage;

@Service
public class ItineraryClientService implements FindItineraryPort {

	@Autowired
	private ItineraryClient itineraryClient;
	
	public List<Itinerary> getItinerariesFromOrigin(Long origin, LocalTime departureAfter) {
		ItineraryPage listItineraries = itineraryClient.listItineraries(origin, departureAfter.toString());
		List<Itinerary> itineraries = listItineraries.getContent();
		return itineraries;
	}
	
}
