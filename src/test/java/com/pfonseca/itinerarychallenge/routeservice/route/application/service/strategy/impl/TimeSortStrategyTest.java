package com.pfonseca.itinerarychallenge.routeservice.route.application.service.strategy.impl;

import java.time.LocalTime;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.pfonseca.itinerarychallenge.routeservice.route.adapter.client.itinerary.domain.Itinerary;
import com.pfonseca.itinerarychallenge.routeservice.route.domain.Route;

public class TimeSortStrategyTest {
	
	private Route routeWithLessTime;
	private Route routeWithMoreTime;
	
	@Before
	public void setup() {
		Itinerary firstItinerary = new Itinerary();
		firstItinerary.setDepartureTime(LocalTime.of(10,  11));
		firstItinerary.setArrivalTime(LocalTime.of(11,  11));
		
		routeWithLessTime = new Route(1L, 2L);
		routeWithLessTime.addItinerary(firstItinerary);
		
		
		Itinerary secondItinerary = new Itinerary();
		secondItinerary.setDepartureTime(LocalTime.of(15,  11));
		secondItinerary.setArrivalTime(LocalTime.of(16,  11));
		
		routeWithMoreTime = new Route(1L, 2L);
		routeWithMoreTime.addItinerary(firstItinerary);
		routeWithMoreTime.addItinerary(secondItinerary);
	}
	
	@Test
	public void givenConnectionSortStrategy_whenComparingOneBestRouteToAnother_thenReturnTrue() {
		Assert.assertTrue(new TimeSortStrategy().compareCompletedRoute(routeWithLessTime, routeWithMoreTime));
	}
	
	@Test
	public void givenConnectionSortStrategy_whenComparingOneWorstRouteToAnother_thenReturnFalse() {
		Assert.assertFalse(new TimeSortStrategy().compareCompletedRoute(routeWithMoreTime, routeWithLessTime));
	}
}
