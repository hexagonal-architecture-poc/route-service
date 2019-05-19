package com.pfonseca.itinerarychallenge.routeservice.route.application.service.strategy;

import com.pfonseca.itinerarychallenge.routeservice.route.domain.Route;

public interface SortStrategy {

	/**
	 * @param completedRoute Completed route
	 * @param routeToCompare A possible route
	 * @return Return true if the completedRoute is better than the routeToCompare
	 */

    boolean compareCompletedRoute(Route completedRoute, Route routeToCompare);
	
}
