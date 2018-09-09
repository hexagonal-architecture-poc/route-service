package com.pfonseca.itinerarychallenge.routeservice.route.controller.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class RouteNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8815007189713494758L;

	public RouteNotFoundException() {
		super("Route not found");
	}
	
}
