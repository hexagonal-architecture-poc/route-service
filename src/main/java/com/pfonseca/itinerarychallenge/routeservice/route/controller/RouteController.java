package com.pfonseca.itinerarychallenge.routeservice.route.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pfonseca.itinerarychallenge.routeservice.route.controller.exception.RouteNotFoundException;
import com.pfonseca.itinerarychallenge.routeservice.route.controller.filter.RouteFilter;
import com.pfonseca.itinerarychallenge.routeservice.route.domain.Route;
import com.pfonseca.itinerarychallenge.routeservice.route.service.RouteService;
import com.pfonseca.itinerarychallenge.routeservice.route.service.strategy.ConnectionSortStrategy;
import com.pfonseca.itinerarychallenge.routeservice.route.service.strategy.SortStrategy;
import com.pfonseca.itinerarychallenge.routeservice.route.service.strategy.TimeSortStrategy;

@RestController
@RequestMapping("/routes")
public class RouteController {

	@Autowired
	private RouteService routeService;
	
	@GetMapping("/less-time")
	public Route list(@Valid RouteFilter filter){
		return extracted(filter, new TimeSortStrategy());
	}
	
	@GetMapping("/less-connections")
	public Route liste(@Valid RouteFilter filter){
		return extracted(filter, new ConnectionSortStrategy());
	}
	
	private Route extracted(RouteFilter filter, SortStrategy strategy) {
		Route route = routeService.searchRoute(filter, strategy);
		
		if(route == null) {
			throw new RouteNotFoundException();
		}
		
		return route;
	}
	
}
