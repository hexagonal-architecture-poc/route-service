package com.pfonseca.itinerarychallenge.routeservice.route.controller.filter;

import javax.validation.constraints.NotNull;

public class RouteFilter {

	@NotNull
	private Long origin;
	
	@NotNull
	private Long destiny;
	
	public RouteFilter() {}
	
	public RouteFilter(Long origin, Long destiny) {
		this.origin = origin;
		this.destiny = destiny;
	}
	
	public Long getOrigin() {
		return origin;
	}
	public void setOrigin(Long origin) {
		this.origin = origin;
	}
	public Long getDestiny() {
		return destiny;
	}
	public void setDestiny(Long destiny) {
		this.destiny = destiny;
	}
	
}
