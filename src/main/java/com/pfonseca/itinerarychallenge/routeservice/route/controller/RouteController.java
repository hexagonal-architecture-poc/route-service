package com.pfonseca.itinerarychallenge.routeservice.route.controller;

import com.pfonseca.itinerarychallenge.routeservice.route.application.domain.Route;
import com.pfonseca.itinerarychallenge.routeservice.route.application.filter.RouteFilter;
import com.pfonseca.itinerarychallenge.routeservice.route.application.port.in.RouteControllerPort;
import com.pfonseca.itinerarychallenge.routeservice.route.application.port.in.RouteUseCase;
import com.pfonseca.itinerarychallenge.routeservice.route.application.service.strategy.SortStrategy;
import com.pfonseca.itinerarychallenge.routeservice.route.application.service.strategy.impl.ConnectionSortStrategy;
import com.pfonseca.itinerarychallenge.routeservice.route.application.service.strategy.impl.TimeSortStrategy;
import com.pfonseca.itinerarychallenge.routeservice.route.controller.exception.RouteNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RouteController implements RouteControllerPort {

	@Autowired
	private RouteUseCase routeUseCase;

	public Route lessTime(RouteFilter filter){
		return findRoute(filter, new TimeSortStrategy());
	}

	public Route lessConnections(RouteFilter filter){
		return findRoute(filter, new ConnectionSortStrategy());
	}
	
	private Route findRoute(RouteFilter filter, SortStrategy strategy) {
		Route route = routeUseCase.searchRoute(filter, strategy);
		
		if(route == null) {
			throw new RouteNotFoundException();
		}
		
		return route;
	}
	
}
