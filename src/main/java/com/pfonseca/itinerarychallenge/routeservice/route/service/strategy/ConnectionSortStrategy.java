package com.pfonseca.itinerarychallenge.routeservice.route.service.strategy;

import com.pfonseca.itinerarychallenge.routeservice.route.domain.Route;

public class ConnectionSortStrategy implements SortStrategy{

	@Override
	public boolean checkRoute(Route completedRoute, Route routeToCompare) {
		return completedRoute.getItineraries().size() < routeToCompare.getItineraries().size();
	}

}
