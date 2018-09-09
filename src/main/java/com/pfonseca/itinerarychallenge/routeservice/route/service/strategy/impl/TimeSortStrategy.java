package com.pfonseca.itinerarychallenge.routeservice.route.service.strategy.impl;

import com.pfonseca.itinerarychallenge.routeservice.route.domain.Route;
import com.pfonseca.itinerarychallenge.routeservice.route.service.strategy.SortStrategy;

public class TimeSortStrategy implements SortStrategy{

	@Override
	public boolean compareCompletedRoute(Route completedRoute, Route routeToCompare) {
		return completedRoute.getTotalTime() < routeToCompare.getTotalTime();
	}

}
