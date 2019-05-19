package com.pfonseca.itinerarychallenge.routeservice.route.domain;

import java.time.LocalTime;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.pfonseca.itinerarychallenge.routeservice.route.adapter.client.itinerary.domain.City;
import com.pfonseca.itinerarychallenge.routeservice.route.adapter.client.itinerary.domain.Itinerary;

public class RouteTest {

	private Route route;
	
	@Before
	public void setup() {
		this.route = new Route(1L, 2L);
		
		Itinerary itinerary = new Itinerary();
		itinerary.setId(1L);
		itinerary.setDepartureTime(LocalTime.now());
		itinerary.setArrivalTime(itinerary.getDepartureTime().plusMinutes(10));
		itinerary.setOrigin(new City(1L, "Rio de Janeiro"));
		itinerary.setDestiny(new City(2L, "Sao Paulo"));
		
		this.route.addItinerary(itinerary);
	}
	
	@Test
	public void givenACompletedRoute_whenCheckingIfRouteIsCompleted_thenReturnTrue() {
		Assert.assertTrue(this.route.isCompleted());
	}
	
	@Test
	public void givenAnIncompletedRoute_whenCheckingIfRouteIsCompleted_thenReturnFalse() {
		this.route.getItineraries().get(0).setDestiny(new City(98L, "Different city"));
		Assert.assertFalse(this.route.isCompleted());
	}
	
	@Test
	public void givenRouteWithoutItineraries_whenCheckingIfRouteIsCompleted_thenReturnFalse() {
		this.route.getItineraries().clear();
		Assert.assertFalse(this.route.isCompleted());
	}
	
	@Test
	public void givenACompletedRoute_whenGetTotalTime_thenReturn10() {
		Assert.assertEquals(10L, this.route.getTotalTime().longValue());
	}
	
	@Test
	public void givenRouteWithoutItineraries_whenGetTotalTime_thenReturn0() {
		this.route.getItineraries().clear();
		Assert.assertEquals(0L, this.route.getTotalTime().longValue());
	}
	
}
