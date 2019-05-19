package com.pfonseca.itinerarychallenge.routeservice.route.application.service.strategy.impl;

import com.pfonseca.itinerarychallenge.routeservice.route.domain.Route;
import com.pfonseca.itinerarychallenge.routeservice.route.application.service.strategy.SortStrategy;

public class ConnectionSortStrategy implements SortStrategy{

	@Override
	public boolean compareCompletedRoute(Route completedRoute, Route routeToCompare) {
		return completedRoute.getItineraries().size() < routeToCompare.getItineraries().size();
	}

}
