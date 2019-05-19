package com.pfonseca.itinerarychallenge.routeservice.route.application.service;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.springframework.util.Assert;

import com.pfonseca.itinerarychallenge.routeservice.route.adapter.client.itinerary.domain.City;
import com.pfonseca.itinerarychallenge.routeservice.route.adapter.client.itinerary.domain.Itinerary;

public class ItineraryClientBuilder {

	private final Map<Long, Long[]> cityIds;
	
	public ItineraryClientBuilder(Map<Long, Long[]> cityIds) {
		Assert.notNull(cityIds, "not null cities");
		this.cityIds = cityIds;
	}
	
	public List<Itinerary> build() {
		
		ArrayList<Itinerary> itineraries = new ArrayList<>();
		
		LocalTime time = LocalTime.MIN;
		
		this.cityIds.forEach((key, values) -> {
			
			Arrays.asList(values).forEach(value -> {
				
				Itinerary itinerary = new Itinerary();
				itinerary.setDepartureTime( time.plusMinutes(itineraries.size()*3) );
				itinerary.setArrivalTime( itinerary.getDepartureTime().plusMinutes(1) );
				itinerary.setOrigin(new City(key, "City" + key));
				itinerary.setDestiny(new City(value, "City" + value));
				
				itineraries.add(itinerary);
			});
			
		});
		
		return itineraries;
	}

}
