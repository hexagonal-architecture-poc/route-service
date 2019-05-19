package com.pfonseca.itinerarychallenge.routeservice.route.controller;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import com.pfonseca.itinerarychallenge.routeservice.route.application.port.in.RouteUseCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.pfonseca.itinerarychallenge.routeservice.route.domain.Route;
import com.pfonseca.itinerarychallenge.routeservice.route.service.ItineraryClientBuilder;
import com.pfonseca.itinerarychallenge.routeservice.route.application.service.strategy.impl.ConnectionSortStrategy;
import com.pfonseca.itinerarychallenge.routeservice.route.application.service.strategy.impl.TimeSortStrategy;

@RunWith(SpringRunner.class)
@WebMvcTest(RouteController.class)
public class RouteControllerIntegrationTest {

	@Autowired
    private MockMvc mvc;
	
	@MockBean
    private RouteUseCase routeUseCase;
	
	@Test
	public void givenSearchingRoute_whenNoRouteWasFound_thenReturnNotFoundException404() throws Exception {
	     
	    Mockito.when(routeUseCase.searchRoute(Mockito.any(), Mockito.any())).thenReturn(Optional.empty());
	 
	    mvc.perform(get("/routes/less-time?origin=1&destiny=5")
	      .contentType(MediaType.APPLICATION_JSON))
	      .andExpect(status().isNotFound());
	}
	
	@Test
	public void givenSearchingRoute_whenRouteWasFoundByTimeSorter_thenReturnRouteWithStatus200() throws Exception {
		
		Route route = new Route(1L, 3L);
		
		Map<Long, Long[]> cityIds = new HashMap<>();
		cityIds.put(1L, new Long[]{2L});
		route.addItinerary(new ItineraryClientBuilder(cityIds).build().get(0));
		
		Mockito.when(routeUseCase.searchRoute(Mockito.any(), Mockito.any())).thenReturn(Optional.of(route));
		
		mvc.perform(get("/routes/less-time?origin=1&destiny=5")
				.contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.itineraries", hasSize(equalTo(1))));
		
		Mockito.verify(routeUseCase).searchRoute(Mockito.any(), Mockito.any(TimeSortStrategy.class));
	}
	
	@Test
	public void givenSearchingRoute_whenRouteWasFoundByConnectionSorter_thenReturnRouteWithStatus200() throws Exception {
		
		Route route = new Route(1L, 3L);
		
		Map<Long, Long[]> cityIds = new HashMap<>();
		cityIds.put(1L, new Long[]{2L});
		route.addItinerary(new ItineraryClientBuilder(cityIds).build().get(0));
		
		Mockito.when(routeUseCase.searchRoute(Mockito.any(), Mockito.any())).thenReturn(Optional.of(route));
		
		mvc.perform(get("/routes/less-connections?origin=1&destiny=5")
				.contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.itineraries", hasSize(equalTo(1))));
		
		Mockito.verify(routeUseCase).searchRoute(Mockito.any(), Mockito.any(ConnectionSortStrategy.class));
	}
	
}
