package com.pfonseca.itinerarychallenge.routeservice.route.application.service.strategy.impl;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.pfonseca.itinerarychallenge.routeservice.route.adapter.client.itinerary.domain.Itinerary;
import com.pfonseca.itinerarychallenge.routeservice.route.application.domain.Route;

public class ConnectionSortStrategyTest {

	private Route routeWithOneItinerary;
	private Route routeWithTwoItineraries;
	
	@Before
	public void setup() {
		routeWithOneItinerary = new Route(1L, 2L);
		routeWithOneItinerary.addItinerary(new Itinerary());
		
		routeWithTwoItineraries = new Route(1L, 2L);
		routeWithTwoItineraries.addItinerary(new Itinerary());
		routeWithTwoItineraries.addItinerary(new Itinerary());
	}
	
	@Test
	public void givenConnectionSortStrategy_whenComparingOneBestRouteToAnother_thenReturnTrue() {
		Assert.assertTrue(new ConnectionSortStrategy().compareCompletedRoute(routeWithOneItinerary, routeWithTwoItineraries));
	}
	
	@Test
	public void givenConnectionSortStrategy_whenComparingOneWorstRouteToAnother_thenReturnFalse() {
		Assert.assertFalse(new ConnectionSortStrategy().compareCompletedRoute(routeWithTwoItineraries, routeWithOneItinerary));
	}
	
}
