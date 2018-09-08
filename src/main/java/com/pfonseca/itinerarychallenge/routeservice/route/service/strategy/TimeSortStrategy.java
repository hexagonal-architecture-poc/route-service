package com.pfonseca.itinerarychallenge.routeservice.route.service.strategy;

import com.pfonseca.itinerarychallenge.routeservice.route.domain.Route;

public class TimeSortStrategy implements SortStrategy{

	@Override
	public boolean checkRoute(Route completedRoute, Route routeToCompare) {
		return completedRoute.getTotalTime() < routeToCompare.getTotalTime();
	}

}
