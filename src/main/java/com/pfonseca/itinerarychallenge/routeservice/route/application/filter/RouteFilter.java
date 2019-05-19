package com.pfonseca.itinerarychallenge.routeservice.route.application.filter;

import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModel;

@ApiModel(description = "City model")
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
