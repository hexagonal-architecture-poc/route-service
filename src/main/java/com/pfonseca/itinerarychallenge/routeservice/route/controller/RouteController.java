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
import com.pfonseca.itinerarychallenge.routeservice.route.service.strategy.SortStrategy;
import com.pfonseca.itinerarychallenge.routeservice.route.service.strategy.impl.ConnectionSortStrategy;
import com.pfonseca.itinerarychallenge.routeservice.route.service.strategy.impl.TimeSortStrategy;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/routes")
public class RouteController {

	@Autowired
	private RouteService routeService;
	
	@GetMapping("/less-time")
	@ApiOperation(value="Find a route based in the less time")
	@ApiImplicitParams({
	    @ApiImplicitParam(name = "origin", value = "Origin city id", required = true ),
	    @ApiImplicitParam(name = "destiny", value = "Destiny city id", required = true)
	})
	public Route lessTime(@Valid RouteFilter filter){
		return findRoute(filter, new TimeSortStrategy());
	}
	
	@GetMapping("/less-connections")
	@ApiOperation(value="Find a route based in the less number of connections")
	@ApiImplicitParams({
	    @ApiImplicitParam(name = "origin", value = "Origin city id", required = true),
	    @ApiImplicitParam(name = "destiny", value = "Destiny city id", required = true)
	})
	public Route lessConnections(@Valid RouteFilter filter){
		return findRoute(filter, new ConnectionSortStrategy());
	}
	
	private Route findRoute(RouteFilter filter, SortStrategy strategy) {
		Route route = routeService.searchRoute(filter, strategy);
		
		if(route == null) {
			throw new RouteNotFoundException();
		}
		
		return route;
	}
	
}
