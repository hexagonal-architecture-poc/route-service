package com.pfonseca.itinerarychallenge.routeservice.route.service.strategy;

import com.pfonseca.itinerarychallenge.routeservice.route.domain.Route;

public interface SortStrategy {

	public boolean checkRoute(Route completedRoute, Route routeToCompare);
	
}
