package com.pfonseca.itinerarychallenge.routeservice.route.service;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pfonseca.itinerarychallenge.routeservice.client.itinerary.ItineraryClientService;
import com.pfonseca.itinerarychallenge.routeservice.client.itinerary.domain.Itinerary;
import com.pfonseca.itinerarychallenge.routeservice.route.controller.filter.RouteFilter;
import com.pfonseca.itinerarychallenge.routeservice.route.domain.Route;
import com.pfonseca.itinerarychallenge.routeservice.route.service.strategy.SortStrategy;

@Service
public class RouteService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(RouteService.class);

	@Autowired
	private ItineraryClientService itineraryClientService;
	
	public Route searchRoute(RouteFilter filter, SortStrategy strategy) {
		
		LOGGER.info("finding the itineraries");
		LOGGER.info("Origin: {}", filter.getOrigin());
		LOGGER.info("Destiny: {}", filter.getDestiny());
		
		List<Itinerary> itineraries = itineraryClientService.getItinerariesFromOrigin(filter.getOrigin(), LocalTime.MIN);
		LOGGER.info("count listItineraries: {}", itineraries.size());
		
		List<Route> possibleRoutes = new ArrayList<>();
		
		itineraries.forEach(itinerary -> {
			Route route = new Route(filter.getOrigin(), filter.getDestiny());
			route.addItinerary(itinerary);
			possibleRoutes.add(route);
		});
		
		
		while( !isFinished(possibleRoutes) ) {
			
			List<Route> newRoutes = new ArrayList<>();
			
			possibleRoutes.stream()
				.filter(route -> !route.isCompleted())
				.forEach(route -> {
					
					Itinerary lastItinerary = route.getItineraries().get(route.getItineraries().size()-1);
					
					LOGGER.info("Searching for more ways. Origin point: {} and time: {}", lastItinerary.getDestiny().getId(), lastItinerary.getArrivalTime());
					List<Itinerary> newItineraries = itineraryClientService.getItinerariesFromOrigin(
							lastItinerary.getDestiny().getId(), 
							lastItinerary.getArrivalTime()
					);
					
					if( CollectionUtils.isNotEmpty(newItineraries)) {
						
						newItineraries.forEach(newItinerariy -> {
							
							Route newRoute = new Route(route);
							newRoute.addItinerary(newItinerariy);
							newRoutes.add(newRoute);
						});
					}
					
					route.invalidate();
					
				});
			
			possibleRoutes.addAll(newRoutes);
			
			checkPossibleSolution(possibleRoutes, strategy);
			
			possibleRoutes.removeIf(route -> route.isInvalid());
			
		}
		
		if(CollectionUtils.isNotEmpty(possibleRoutes))
			return possibleRoutes.get(0);
		
		return null;
	}

	private void checkPossibleSolution(List<Route> possibleRoutes, SortStrategy strategy) {
		
		possibleRoutes.stream().filter(Route::isCompleted).forEach(completedRoute -> {
			
			possibleRoutes.stream()
				.filter(routeToCompare -> !routeToCompare.equals(completedRoute))
				.forEach(routeToCompare -> {
					
					if(strategy.checkRoute(completedRoute, routeToCompare)) {
						routeToCompare.invalidate();
					}
			});
			
		});
		
	}

	private boolean isFinished(List<Route> possibleRoutes) {
		
		if(possibleRoutes.size() > 0)
			return possibleRoutes.stream().allMatch(route -> route.isCompleted());
		
		return true;
		
	}

}
