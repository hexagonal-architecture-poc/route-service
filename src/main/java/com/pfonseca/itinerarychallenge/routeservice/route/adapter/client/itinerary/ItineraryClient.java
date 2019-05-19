package com.pfonseca.itinerarychallenge.routeservice.route.adapter.client.itinerary;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.pfonseca.itinerarychallenge.routeservice.route.adapter.client.itinerary.domain.ItineraryPage;

@FeignClient(name = "itinerary", url = "${itinerary.url}/itineraries")
public interface ItineraryClient {

	@GetMapping(value = "/?origin={origin}&departureAfter={departureAfter}")
	public ItineraryPage listItineraries(@PathVariable("origin") long origin, @PathVariable("departureAfter") String departureAfter);

	
}
