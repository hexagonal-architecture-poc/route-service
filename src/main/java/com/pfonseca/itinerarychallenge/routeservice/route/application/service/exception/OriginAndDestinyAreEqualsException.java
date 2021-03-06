package com.pfonseca.itinerarychallenge.routeservice.route.application.service.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class OriginAndDestinyAreEqualsException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3108946818423035223L;

	
	public OriginAndDestinyAreEqualsException() {
		super("Origin and destiny are equals");
	}
	
}
