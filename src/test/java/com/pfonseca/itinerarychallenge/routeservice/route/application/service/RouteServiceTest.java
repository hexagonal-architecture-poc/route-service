package com.pfonseca.itinerarychallenge.routeservice.route.application.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import com.pfonseca.itinerarychallenge.routeservice.route.application.port.out.FindItineraryPort;
import com.pfonseca.itinerarychallenge.routeservice.route.application.service.RouteService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.pfonseca.itinerarychallenge.routeservice.route.application.filter.RouteFilter;
import com.pfonseca.itinerarychallenge.routeservice.route.domain.Route;
import com.pfonseca.itinerarychallenge.routeservice.route.application.service.exception.OriginAndDestinyAreEqualsException;
import com.pfonseca.itinerarychallenge.routeservice.route.application.service.strategy.impl.ConnectionSortStrategy;
import com.pfonseca.itinerarychallenge.routeservice.route.application.service.strategy.impl.TimeSortStrategy;

@RunWith(MockitoJUnitRunner.class)
public class RouteServiceTest {

	@InjectMocks
	private RouteService routeService;
	
	@Mock
	private FindItineraryPort findItineraryPort;
	
	@Before
	public void setup() {
		
	}
	
	
	@Test
	public void givenAnInvalidSearchFilter_whenSearchForARoute_thenReturnNull() {
		
		Mockito.when(this.findItineraryPort.getItinerariesFromOrigin(Mockito.anyLong(), Mockito.any())).thenReturn(new ArrayList<>());
		
		Optional<Route> route = this.routeService.searchRoute(new RouteFilter(1L, 2L), new ConnectionSortStrategy());
		Assert.assertFalse(route.isPresent());
	}
	
	@Test(expected = OriginAndDestinyAreEqualsException.class)
	public void givenAValidSearchFilter_whenSearchForARouteWithSameCity_thenThrowOriginAndDestinyAreEqualsException() {
		Optional<Route> route = this.routeService.searchRoute(new RouteFilter(1L, 1L), new ConnectionSortStrategy());
		Assert.assertFalse(route.isPresent());
	}
	
	@Test
	public void givenAValidSearchFilter_whenSearchForARoute_thenReturnARouteWithOneItinerary() {
		Map<Long, Long[]> cities = new HashMap<>();
		cities.put(1L, new Long[]{2L});
		
		Mockito.when(this.findItineraryPort.getItinerariesFromOrigin(Mockito.anyLong(), Mockito.any())).thenReturn(new ItineraryClientBuilder(cities).build());
		
		Optional<Route> route = this.routeService.searchRoute(new RouteFilter(1L, 2L), new ConnectionSortStrategy());
		Assert.assertTrue(route.isPresent());
		Assert.assertEquals(1, route.get().getItineraries().size());
		Assert.assertTrue(route.get().isCompleted());
	}
	
	@Test
	public void givenAValidSearchFilter_whenSearchForARoute_thenReturnARouteWithTwoItineraries() {
		Map<Long, Long[]> citiesFirstCall = new HashMap<>();
		citiesFirstCall.put(1L, new Long[]{2L});
		
		Map<Long, Long[]> citiesSecondCall = new HashMap<>();
		citiesSecondCall.put(2L, new Long[]{3L});
		
		Mockito.when(this.findItineraryPort.getItinerariesFromOrigin(Mockito.anyLong(), Mockito.any()))
			.thenReturn(new ItineraryClientBuilder(citiesFirstCall).build())
			.thenReturn(new ItineraryClientBuilder(citiesSecondCall).build());
		
		Optional<Route> route = this.routeService.searchRoute(new RouteFilter(1L, 3L), new ConnectionSortStrategy());
		Assert.assertTrue(route.isPresent());
		Assert.assertEquals(2, route.get().getItineraries().size());
		Assert.assertTrue(route.get().isCompleted());
	}
	
	@Test
	public void givenAValidSearchFilter_whenSearchForARouteWithLessConnections_thenReturnARouteWithTwoItineraries() {
		Map<Long, Long[]> citiesFirstCall = new HashMap<>();
		citiesFirstCall.put(1L, new Long[]{2L});
		
		Map<Long, Long[]> citiesSecondCall = new HashMap<>();
		citiesSecondCall.put(2L, new Long[]{3L, 4L});
		
		Map<Long, Long[]> citiesThirdCall = new HashMap<>();
		citiesThirdCall.put(4L, new Long[]{5L});
		
		Mockito.when(this.findItineraryPort.getItinerariesFromOrigin(Mockito.anyLong(), Mockito.any()))
			.thenReturn(new ItineraryClientBuilder(citiesFirstCall).build())
			.thenReturn(new ItineraryClientBuilder(citiesSecondCall).build())
			.thenReturn(new ItineraryClientBuilder(citiesThirdCall).build())
			.thenReturn(new ArrayList<>());
		
		Optional<Route> route = this.routeService.searchRoute(new RouteFilter(1L, 3L), new ConnectionSortStrategy());
		Assert.assertTrue(route.isPresent());
		Assert.assertEquals(2, route.get().getItineraries().size());
		Assert.assertTrue(route.get().isCompleted());
	}
	
	@Test
	public void givenAValidSearchFilter_whenNoRouteWasFound_thenReturnNull() {
		Map<Long, Long[]> citiesFirstCall = new HashMap<>();
		citiesFirstCall.put(1L, new Long[]{2L});
		
		Mockito.when(this.findItineraryPort.getItinerariesFromOrigin(Mockito.anyLong(), Mockito.any()))
			.thenReturn(new ItineraryClientBuilder(citiesFirstCall).build())
			.thenReturn(new ArrayList<>());
		
		Optional<Route> route = this.routeService.searchRoute(new RouteFilter(1L, 3L), new ConnectionSortStrategy());
		Assert.assertFalse(route.isPresent());
	}
	
	@Test
	public void givenAValidSearchFilter_whenTwoEquivalentRoutesWasFound_thenReturnFirstRoute() {
		
		Map<Long, Long[]> citiesFirstCall = new HashMap<>();
		citiesFirstCall.put(1L, new Long[]{2L, 2L});
		
		Mockito.when(this.findItineraryPort.getItinerariesFromOrigin(Mockito.anyLong(), Mockito.any()))
		.thenReturn(new ItineraryClientBuilder(citiesFirstCall).build())
		.thenReturn(new ArrayList<>());
		
		Optional<Route> route = this.routeService.searchRoute(new RouteFilter(1L, 2L), new TimeSortStrategy());
		Assert.assertTrue(route.isPresent());
		Assert.assertEquals(1, route.get().getItineraries().size());
		Assert.assertTrue(route.get().isCompleted());
	}
	
	
}
